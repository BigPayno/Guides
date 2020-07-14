package com.payno.guides.servers.mqs.amqp;

import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.stereotype.Service;

/**
 * @author payno
 * @date 2020/6/18 18:35
 * @description
 */
@Service
public class AmqpTransactionGuide {
    public static void main(String[] args) {
        System.err.println(RabbitTransactionManager.class);
    }
}
