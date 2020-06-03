package com.payno.transaction.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author payno
 * @date 2020/6/2 17:27
 * @description
 */
@Component
public class TransactionManagerApiGuide {
    @Autowired
    PlatformTransactionManager transactionManager;

    void test(){
    }
}
