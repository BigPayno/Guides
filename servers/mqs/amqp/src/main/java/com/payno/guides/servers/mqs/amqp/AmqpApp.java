package com.payno.guides.servers.mqs.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author payno
 * @date 2020/6/17 21:32
 * @description
 */
@EnableRabbit
@EnableTransactionManagement
@SpringBootApplication
public class AmqpApp {
    public static void main(String[] args) {
        SpringApplication.run(AmqpApp.class, args);
    }
}
