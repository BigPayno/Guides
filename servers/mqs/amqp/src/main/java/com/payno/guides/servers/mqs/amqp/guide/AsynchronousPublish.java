package com.payno.guides.servers.mqs.amqp.guide;

import com.rabbitmq.client.ShutdownSignalException;
import org.junit.Test;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;

/**
 * 异步发布
 *      发布是一个异步的行为，我们如何确定消息正常被rabbit接受,有哪些失败的场景
 *
 * Publish to an exchange but there is no matching destination queue.
 *      找不到对应的queue
 *      具体可以查看PublisherComfirmAndReturn
 * @see org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback
 * Publish to a non-existent exchange.
 *      找不到对应的exchange
 *      默认只打日志，只有开启事务时会抛出异常
 * @see org.springframework.amqp.rabbit.connection.ConnectionListener
 *
 * @author zhaolei22
 * @date 2020/07/14
 */
public class AsynchronousPublish extends FastStart{
    @Test
    public void connectionListener(){
        rabbitTemplate.getConnectionFactory().addConnectionListener(new ConnectionListener() {
            @Override
            public void onCreate(Connection connection) {
                //do nothing!
            }

            @Override
            public void onClose(Connection connection) {
                //do nothing!
                System.err.println(connection);
            }

            @Override
            public void onShutDown(ShutdownSignalException signal) {
                System.err.println(signal.getReason());
            }
        });

        rabbitTemplate.setExchange("NON-EXISTENT-EXCHANGE");
        rabbitTemplate.convertAndSend("EROOR","MSG");
    }
}
