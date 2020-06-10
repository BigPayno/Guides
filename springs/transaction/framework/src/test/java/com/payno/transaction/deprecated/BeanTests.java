package com.payno.transaction.deprecated;

import com.payno.transaction.TransactionApplication;
import com.payno.transaction.deprecated.Test.Bean;
import com.payno.transaction.deprecated.Test.Config;
import com.payno.transaction.deprecated.Test.ContextHolder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionApplication.class)
class BeanTests {
    @Autowired
    Config config;
    @Autowired
    ContextHolder contextHolder;
    @Test
    void contextLoads() {
        Config config=contextHolder.get().getBean(Config.class);
        config.setBean(new Bean("new"));
        config.print();
    }
}
