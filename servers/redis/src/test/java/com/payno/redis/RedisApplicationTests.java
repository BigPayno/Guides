package com.payno.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Test
    void contextLoads() {
        System.out.println(redisTemplate);
    }

}
