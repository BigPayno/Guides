package com.payno.transaction.transactions;

import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author payno
 * @date 2020/6/8 11:22
 * @description
 */
public class Transactions {
    public static void showTransaction(){
        System.err.println("name : "+TransactionSynchronizationManager.getCurrentTransactionName());
        System.err.println("level : "+TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());
    }
}
