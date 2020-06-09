package com.payno.transaction.chained;

import com.payno.transaction.transactions.Model;
import com.payno.transaction.transactions.ModelRepo;
import com.payno.transaction.transactions.TransactionPropagationTest;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.api.TransactionOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
     *  最终jpa回滚，redisson没有回滚
     */
    @Transactional(value = "transactionManager",rollbackFor = RuntimeException.class)
    public void test(){
        RSet<String> set=redissonClient.createTransaction(TransactionOptions.defaults()).getSet("tran-test");
        set.add("rollbackRedis");
        repo.save(Model.builder().cusName("rollback").build());
        throw new RuntimeException();
    }

    @Transactional(value = "redssionTransactionManager",rollbackFor = RuntimeException.class)
    public void test2(){
        RSet<String> set=redissonClient.getSet("tran-test");
        set.add("rollbackRedis");
        repo.save(Model.builder().cusName("rollback").build());
        throw new RuntimeException();
    }
}
