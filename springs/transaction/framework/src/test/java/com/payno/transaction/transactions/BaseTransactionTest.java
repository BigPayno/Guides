package com.payno.transaction.transactions;

import com.payno.transaction.TransactionApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author payno
 * @date 2020/6/8 11:17
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionApplication.class)
public class BaseTransactionTest {
    @Autowired TransactionPropagationTest propagationTest;
    @Autowired TransactionsTest test;
    @Autowired ModelRepo repo;

    @Before
    public void init(){
        repo.deleteAll();
    }

    @After
    public void result(){
        System.err.println("result:");
        repo.findAll().forEach(System.err::println);
    }

    @Test
    public void rollBack(){
        try{
            test.save(Model.builder().cusName("noRollback").build());
            test.saveOnRollback(Model.builder().cusName("rollback").build());
        }catch (Exception e){
            // Nothing to do
        }
    }

    @Test
    public void synchronization(){
        test.saveWithSynchronization(Model.builder().cusName("synchronization").build());
        test.saveWithSynchronizationButRollback(Model.builder().cusName("synchronizationButRollback").build());
    }

    @Test
    public void propagationTest(){
        propagationTest.test(Model.builder().cusName("payno").build());
    }
}
