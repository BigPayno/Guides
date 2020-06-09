package com.payno.transaction.transactions;

import com.payno.transaction.TransactionApplication;
import com.payno.transaction.chained.ChainedTransaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author payno
 * @date 2020/6/8 17:20
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionApplication.class)
public class RedisTransactionTest {

    @Autowired ModelRepo repo;
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    ChainedTransaction chainedTransaction;

    @Before
    public void init(){
        repo.deleteAll();
        redissonClient.getSet("tran-test").delete();
    }

    @After
    public void result(){
        System.err.println("result:");
        repo.findAll().forEach(System.err::println);
        redissonClient.getSet("tran-test").stream(1).forEach(System.err::println);
    }

    @Test
    public void test(){
        try{
            chainedTransaction.test();
        }catch (Exception e){
            // jpa rollback
        }
    }

    @Test
    public void test2(){
        try{
            chainedTransaction.test2();
        }catch (Exception e){
            // jpa and redisson rollback
        }
    }
}
