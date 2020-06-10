package com.payno.transaction.redis;

import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @author payno
 * @date 2020/6/9 11:13
 * @description
 * @Override
 *    RedisTemplate事务的实现机制
 *      TransactionSynchronizationManager注册AfterComplete的后置处理
 *      然后利用RedisConnectionHolder绑定资源，key是connectionFactory得到Connection
 *      然后使用RedisTxCommands进行事务相关的处理（RedisTransactionSynchronizer)
 *
 *      RedisConnectionUtils里有相关的逻辑
 */
@Component
public class RedisTemplateTransaction {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedissonConnectionFactory factory;

    @PostConstruct
    public void init(){
        redisTemplate.setEnableTransactionSupport(true);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void test(){
        redisTemplate.opsForValue().set("test","rollback");
        RedisConnection connection=RedisConnectionUtils
                .doGetConnection(factory,false,true,true);
        System.err.println(connection);
        throw new RuntimeException();
    }

    public void test1(){
        redisTemplate.opsForValue().set("test1","rollback");
        RedisConnection connection=RedisConnectionUtils
                .doGetConnection(factory,false,true,true);
        System.err.println(connection);
        throw new RuntimeException();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void test2(){
        redisTemplate.opsForValue().set("test2","rollback");
        RedisConnection connection=RedisConnectionUtils
                .doGetConnection(factory,false,true,true);
        System.err.println(connection);
    }
}
