package com.payno.transaction.error.error1;

import com.payno.transaction.TransactionApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author payno
 * @date 2020/6/8 09:31
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionApplication.class)
public class TSTest {
    @Autowired ErrorTest test;

    @Test
    public void test(){
        System.err.println(TransactionSynchronizationManager.getCurrentTransactionName());
        test.test();
    }
}
