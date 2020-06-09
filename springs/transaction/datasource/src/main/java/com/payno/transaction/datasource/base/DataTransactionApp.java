package com.payno.transaction.datasource.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author payno
 * @date 2020/6/9 15:46
 * @description
 */
@Profile("base")
@EnableTransactionManagement
@SpringBootApplication
public class DataTransactionApp {
    public static void main(String[] args) {
        SpringApplication.run(DataTransactionApp.class, args);
    }
}
