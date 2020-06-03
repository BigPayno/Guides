package com.payno.transaction.framework;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.transaction.Transaction;

/**
 * @author payno
 * @date 2020/6/2 11:15
 * @description
 */
public class Guide {
    public static void main(String[] args) {
        TransactionManager transactionManager = new DataSourceTransactionManager();
    }
}
