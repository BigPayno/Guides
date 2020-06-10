package com.payno.transaction.chained;

import com.payno.transaction.transactions.Model;
import com.payno.transaction.transactions.ModelRepo;
import com.payno.transaction.transactions.TransactionPropagationTest;
import org.redisson.api.RSet;
import org.redisson.api.RTransaction;
import org.redisson.api.RedissonClient;
import org.redisson.api.TransactionOptions;
import org.redisson.spring.transaction.RedissonTransactionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author payno
 * @date 2020/6/8 17:16
 * @description
 */
@Component
public class ChainedTransaction {

    @Autowired
    TransactionPropagationTest test;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    ModelRepo repo;

    /**
     *  最终jpa未回滚，redisson没有提交
     */
    public void test(){
        RTransaction transaction = redissonClient.createTransaction(TransactionOptions.defaults());
        RSet<String> set=transaction.getSet("tran-test");
        set.add("rollbackRedis");
        repo.save(Model.builder().cusName("rollback").build());
        throw new RuntimeException();
    }

    /**
     *  最终jpa提交，redisson提交
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void test2(){
        RedissonTransactionHolder holder=(RedissonTransactionHolder)TransactionSynchronizationManager.getResource(redissonClient);
        RSet<String> set=holder.getTransaction().getSet("tran-test");
        set.add("rollbackRedis");
        repo.save(Model.builder().cusName("rollback").build());
    }

    /**
     * jpa、redisson未提交
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void test3(){
        RedissonTransactionHolder holder=(RedissonTransactionHolder)TransactionSynchronizationManager.getResource(redissonClient);
        RSet<String> set=holder.getTransaction().getSet("tran-test");
        set.add("rollbackRedis");
        repo.save(Model.builder().cusName("rollback").build());
        throw new RuntimeException();
    }
}
