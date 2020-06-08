package com.payno.transaction.error.error1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author payno
 * @date 2020/6/8 09:28
 * @description
 */
@Service
public class ErrorTest {

    @Autowired ApplyRepo repo;

    @Autowired PlatformTransactionManager manager;

    @Transactional(rollbackFor = RuntimeException.class)
    public void test(){
        repo.save(Apply.builder().cusName("payno").build());
        System.err.println(TransactionSynchronizationManager.getCurrentTransactionName());
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                //  最终打印结果显示afterCommit里依旧使用旧的事务
                System.err.println(TransactionSynchronizationManager.getCurrentTransactionName());
                //  和项目中发生一样的事情，保存失败
                repo.save(Apply.builder().cusName("chad").build());
            }
        });
    }
}
