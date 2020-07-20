package com.payno.guides.servers.mqs.kafka;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@SpringBootTest
@EmbeddedKafka
public class FastStart {
    @Autowired
    EmbeddedKafkaBroker broker;

    KafkaTemplate<String,String> template;

    @Before
    public void init(){
        template = new KafkaTemplate<String, String>(new DefaultKafkaProducerFactory<String, String>(
                KafkaTestUtils.producerProps(broker)
        ));
    }

    @Test
    public void inspect() throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String,String>> future=template
                .send("topic",String.format("timestamp: %s;",System.currentTimeMillis()));
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            public void onFailure(Throwable throwable) {
                System.err.println("onFailture "+throwable);
            }

            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                System.out.println("onSuccess "+stringStringSendResult);
            }
        });
        System.out.println(future.get());
    }
}
