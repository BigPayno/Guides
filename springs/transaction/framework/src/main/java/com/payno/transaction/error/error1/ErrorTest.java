package com.payno.transaction.error.error1;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;

/**
 * @author payno
 * @date 2020/6/8 09:28
 * @description
 */
@Service
public class ErrorTest {

    @Autowired ApplyRepo repo;

    @Autowired
    DataSource dataSource;

    public void base(){
        repo.save(Apply.builder().cusName("chad").build());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void test(boolean rollback){
        repo.saveAll(Lists.newArrayList(Apply.builder().cusName("payno").build()));
        System.err.println(TransactionSynchronizationManager.getCurrentTransactionName());
        ConnectionHolder holder=(ConnectionHolder)TransactionSynchronizationManager.getResource(dataSource);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                //  最终打印结果显示afterCommit里依旧使用旧的事务
                System.err.println("Transaction-afterCommit:"+TransactionSynchronizationManager.getCurrentTransactionName());
                ConnectionHolder holder=(ConnectionHolder)TransactionSynchronizationManager.getResource(dataSource);
                //  和项目中发生一样的事情，保存失败
                repo.save(Apply.builder().cusName("chad").build());
            }

            @Override
            public void afterCompletion(int status) {
                //  最终打印结果显示afterCommit里依旧使用旧的事务
                System.err.println("Transaction-afterCompletion:"+TransactionSynchronizationManager.getCurrentTransactionName());
                ConnectionHolder holder=(ConnectionHolder)TransactionSynchronizationManager.getResource(dataSource);
                //  和项目中发生一样的事情，保存失败
                repo.save(Apply.builder().cusName("chad2").build());
            }
        });
    }
}
