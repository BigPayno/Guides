package com.payno.transaction;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TransactionApplication implements ApplicationRunner, ApplicationContextAware {

    ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.err.println(context.getBean(TransactionManager.class).getClass());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
