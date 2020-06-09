package com.payno.transaction.chained;

import org.redisson.api.RedissonClient;
import org.redisson.spring.transaction.RedissonTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;

/**
 * @author payno
 * @date 2020/6/8 16:53
 * @description
 */
@Profile("redis")
@Configuration
public class TransactionConfiguration implements TransactionManagementConfigurer{

    @Autowired
    RedissonClient redissonClient;

    @Resource
    JpaTransactionManager transactionManager;

    /**
     *  redisson对一些对象的操作可以使用事务操作，
     *  这些对象包括RBucket、Rbuckets，RSet、RSetCache，RMap、RMapCache、RLocalCachedMap
     */

    @Override
    @Bean
    @Primary
    public TransactionManager annotationDrivenTransactionManager() {
        ChainedTransactionManager chainedTransactionManager=new ChainedTransactionManager(
                new RedissonTransactionManager(redissonClient),
                transactionManager
        );
        return chainedTransactionManager;
    }
}
