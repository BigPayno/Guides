package com.payno.guides.servers.mqs.amqp;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author payno
 * @date 2020/6/17 21:35
 * @description
 */
@Configuration
public class AmqpConfiguration {
    @Autowired
    public PlatformTransactionManager rabbitTM(ConnectionFactory connectionFactory){
        return new RabbitTransactionManager(connectionFactory);
    }
}
