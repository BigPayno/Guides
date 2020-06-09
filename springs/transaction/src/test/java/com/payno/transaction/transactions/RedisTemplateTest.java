package com.payno.transaction.transactions;

import com.payno.transaction.TransactionApplication;
import com.payno.transaction.redis.RedisTemplateTransaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author payno
 * @date 2020/6/9 11:17
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionApplication.class)
public class RedisTemplateTest {
    @Autowired
    RedisTemplateTransaction templateTransaction;

    @Autowired
    StringRedisTemplate template;

    @Before
    public void init(){
        template.delete("test1");
        template.delete("test2");
    }

    @After
    public void result(){
        System.err.println("result:");
        template.keys("test*").forEach(key->{
            System.err.println(key+" : "+template.opsForValue().get(key));
        });
    }

    @Test
    //  rollback
    public void test(){
        try{
            templateTransaction.test();
        }catch (Exception e){ }
    }

    @Test
    // noRollBack 因为没有事务信息，虽然template开启了事务支持
    public void test1(){
        try{
            templateTransaction.test1();
        }catch (Exception e){ }
    }

    @Test
    public void test2(){
        try{
            templateTransaction.test2();
        }catch (Exception e){ }
    }

    @Test
    public void test3(){
        try{
            templateTransaction.test();
        }catch (Exception e){ }
        try{
            templateTransaction.test1();
        }catch (Exception e){ }
        try{
            templateTransaction.test2();
        }catch (Exception e){ }
    }
}
