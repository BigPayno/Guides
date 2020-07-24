package com.payno.guides.servers.mqs.amqp.guide;


import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.ReturnListener;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.ConfirmType;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

import java.io.IOException;

/**
 * 连接和通道
 *
 * @author zhaolei22
 * @date 2020/07/24
 */
public class ConnectionAndChannel extends FastStart{
    /*
    *   Connection连接本质上就是TCP连接，系统之间那点事-问题驱动-TCP的连接和关闭是比较耗费时间的。
    * 我们可以使用一个单例的Connection对象创建多个Channel来实现数据传输，但是在channel信息比较大的情况下，
    * Connection带宽会限制消息的传输。那么需要设计Connection池，将流量分摊到不同的connection上。
    * @https://www.jianshu.com/p/24e541170ace
    * */

    @Test
    public void confirmByRawApi(){
        CachingConnectionFactory connectionFactory= (CachingConnectionFactory) rabbitTemplate.getConnectionFactory();
        System.out.println(connectionFactory.isPublisherConfirms());
        System.out.println(connectionFactory.isPublisherReturns());
        connectionFactory.setPublisherConfirmType(ConfirmType.SIMPLE);
        connectionFactory.setPublisherReturns(true);
        connectionFactory.addChannelListener((channel, b) -> {
            channel.addConfirmListener(new ConfirmListener(){
                @Override
                public void handleAck(long l, boolean b) throws IOException {
                    //doSth
                }

                @Override
                public void handleNack(long l, boolean b) throws IOException {
                    //doSth
                }
            });
            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int i, String s, String s1, String s2, BasicProperties basicProperties, byte[] bytes) throws IOException {
                    //doSth
                }
            });
        });
    }

    @Test
    public void confirmByTemplate(){
        /**
         * Mandatory为true时,消息通过交换器无法匹配到队列会返回给生产者
         *          为false时,匹配不到会直接被丢弃
         */
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            //"消息发送成功:correlationData({}),ack({}),cause({})"
        });

        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText, String exchange, String routingKey) -> {
            //"消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}"
        });
    }

    @Test
    public void publishAndConsumeWithDiffConnection(){
        /*

        当一个服务同时作为消息的发送端和接收端时，建议使用不同的Connection避免一方出现故障或者阻塞影响另一方。
        只需要在RabbitTemplate中加入下面的配置，那么RabbitTemplate在创建Connection时，会根据这个boolean的值，
        选择使用ConnectionFactory本身或者ConnectionFactory中的publisherConnectionFactory（也即是一个ConnectionFactory）来创建。

        */
        rabbitTemplate.setUsePublisherConnection(true);
    }
}
