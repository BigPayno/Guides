package com.payno.transaction.collection;


import org.springframework.transaction.PlatformTransactionManager;

/**
 * 事务处理支持
 *
 * @author payno
 * @date 2020/07/19
 */
public interface TransationSupport {
    void begin();
    void commit();
    void rollback();
}

