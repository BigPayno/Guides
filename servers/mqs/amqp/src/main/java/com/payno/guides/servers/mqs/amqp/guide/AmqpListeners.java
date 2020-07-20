package com.payno.guides.servers.mqs.amqp.guide;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * amqp监听器
 *
 * @author zhaolei22
 * @date 2020/07/17
 */
public class AmqpListeners extends FastStart{

    /**
     * 连接监听器
     *      在连接工厂注册
     */
    @Test
    public void connectionListener(){
        ConnectionFactory connectionFactory = rabbitTemplate.getConnectionFactory();

        connectionFactory.addConnectionListener(connection -> {
            System.err.println(connection);
            System.err.println(connection.getDelegate());
        });

        inspect();
    }

    /**
     * 消息侦听器和容器
     *      MessageListener 只是拥有处理消息的能力
     *      监听的能力在Container中实现
     *
     * 访问Rabbit的抽象基类是RabbitAccessor
     *      其实现有
     *          RabbitTemplate
     *          BatchingRabbitTemplate
     *          AbstractMessageListenerContainer(抽象类)
     *      另外通过委托实现RabbitAccessor的类有
     *          RabbitAdmin
     *      RabbitAccessor本身访问Rabbit的能力来自ConnectionFactory
     *
     * 消息监听在于
     *      在AbstractMessageListenerContainer中
     *          executeListener、invokeListener、doInvokeListener
     *             executeListener会调用MessagePostProcessor对消息进行处理，然后调用invokeListener
     *             invokeListener会进行不同逻辑的唤醒，带事务的，ChannelAwareMessageListener和MessageListener处理不同
     *
     *      在SimpleMessageListenerContainer中
     *          doReceiveAndExecute
     *
     * 公司的MqTopicQueueListenBundleBuilder和MqTopicQueueListenBundle无非将这些打包了
     *      根据初始参数直接拿到连接工厂，监听容器，RabbitAdmin
     */
    @Test
    public void messageListenerAndContainer(){
        SimpleMessageListenerContainer listenerContainer =
                new SimpleMessageListenerContainer(rabbitTemplate.getConnectionFactory());
        listenerContainer.setConcurrentConsumers(2);
        //  开启事务支持，RedisTemplate是利用原子事务管理器在执行前后调用开启和commit的命令
        //  AMQP模块则是通过TransactionTemplate进行操作
        listenerContainer.setChannelTransacted(true);
        listenerContainer.setMessageListener(message -> {
            System.err.println(message);
        });
        listenerContainer.setQueueNames("FastStartQueue");
        listenerContainer.start();
        ExecutorService service = Executors.newFixedThreadPool(1);
        /*
        *   因为消息被监听器消费
        *   所以Inspect没有接收到消息
        *
        * */
        service.execute(()->{
            inspect();
        });
        //  防止Junit过早退出
        MoreExecutors.shutdownAndAwaitTermination(service,1,TimeUnit.MINUTES);
    }

    @Autowired
    RabbitListenerEndpointRegistry listenerEndpointRegistry;

    /*
    *   注册消息监听容器的方式有linagg
    *       1.加入RabbitListenerEndpointRegistry
    *       2.基于注解
    *           1.@RabbitListener 注解标记方法，当监听到队列 debug 中有消息时则会进行接收并处理
    *           2.@RabbitListener 可以标注在类上面，需配合 @RabbitHandler 注解一起使用
    *             @RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
    * */
}
