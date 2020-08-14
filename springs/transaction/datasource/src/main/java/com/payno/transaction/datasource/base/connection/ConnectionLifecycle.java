package com.payno.transaction.datasource.base.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Types;

/**
 * @author payno
 * @date 2020/6/10 08:56
 * @description
 */
@Profile("base")
@Component
@Transactional
public class ConnectionLifecycle {
    @Autowired
    JdbcTemplate template;

    @Autowired
    DataSource dataSource;

    @Transactional(rollbackFor = RuntimeException.class)
    public void save(Apply apply1,Apply apply2){
        template.update(
                "insert into apply(apply_id,cus_name) values(?,?)",
                new Object[]{apply1.getApplyId(),apply1.getCusName()},
                new int[]{Types.NUMERIC,Types.VARCHAR});
        ConnectionHolder connection = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
        System.out.println(connection);
        System.err.println("Transaction-beforeCommit:"+TransactionSynchronizationManager.getCurrentTransactionName());
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                ConnectionHolder connection = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
                System.out.println(connection);
                System.err.println("Transaction-afterCommit:"+TransactionSynchronizationManager.getCurrentTransactionName());
                template.update(
                        "insert into apply(apply_id,cus_name) values(?,?)",
                        new Object[]{apply2.getApplyId(),apply2.getCusName()},
                        new int[]{Types.NUMERIC,Types.VARCHAR});
            }
        });
    }
}
