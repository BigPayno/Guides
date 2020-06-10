package com.payno.transaction.datasource.test;

import com.payno.transaction.datasource.base.DataTransactionApp;
import com.payno.transaction.datasource.base.connection.Apply;
import com.payno.transaction.datasource.base.connection.ConnectionLifecycle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.In;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author payno
 * @date 2020/6/10 14:54
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataTransactionApp.class)
public class ConnectionTest {

    @Autowired
    ConnectionLifecycle connectionLifecycle;

    @Test
    public void lifecycle(){
        connectionLifecycle.save(
                Apply.builder().applyId(Integer.valueOf(1)).cusName("payno1").build(),
                Apply.builder().applyId(Integer.valueOf(2)).cusName("payno2").build()
        );
    }

}
