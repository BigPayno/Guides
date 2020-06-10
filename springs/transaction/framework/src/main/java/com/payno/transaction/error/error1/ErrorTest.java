package com.payno.transaction.error.error1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;

/**
 * @author payno
 * @date 2020/6/8 09:28
 * @description
 */
@Service
public class ErrorTest {

    @Autowired ApplyRepo repo;

    @Autowired
    EntityManager entityManager;

    @Transactional(rollbackFor = RuntimeException.class)
    public void test(){
        repo.save(Apply.builder().cusName("payno").build());
        System.err.println(TransactionSynchronizationManager.getCurrentTransactionName());
        EntityManagerHolder holder=(EntityManagerHolder)TransactionSynchronizationManager.getResource(entityManager);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                //  最终打印结果显示afterCommit里依旧使用旧的事务
                System.err.println(TransactionSynchronizationManager.getCurrentTransactionName());
                EntityManagerHolder holder=(EntityManagerHolder)TransactionSynchronizationManager.getResource(entityManager);
                //  和项目中发生一样的事情，保存失败
                repo.save(Apply.builder().cusName("chad").build());
            }
        });
    }
}
