package com.payno.transaction.datasource.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author payno
 * @date 2020/6/9 15:51
 * @description
 */
@Profile("base")
@Configuration
public class DataSourceTransactionConfigurer implements TransactionManagementConfigurer {

    @Autowired
    DataSource dataSource;

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager transactionManager=new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }
}
