package com.payno.guides.servers.mqs.amqp.guide;

import com.payno.guides.servers.mqs.amqp.AmqpApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 快速启动
 *
 * @author zhaolei22
 * @date 2020/07/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AmqpApp.class)
public class FastStart {

    static final String EXCHANGE = "rabbitmq.socialiosvc.async.event.exchange";
    static final String QUEUE = "rabbitmq.socialiosvc.async.event.queue";

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Before
    public void before(){
        amqpAdmin.declareQueue(new Queue("FastStartQueue"));
    }

    @Test
    public void inspect(){
        amqpTemplate.convertAndSend("FastStartQueue","foo");
        String foo =(String) amqpTemplate.receiveAndConvert("FastStartQueue");
        System.out.println(foo);
    }
}
