package com.payno.transaction.error.error1;

import com.payno.transaction.TransactionApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author payno
 * @date 2020/6/8 09:31
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionApplication.class)
public class TSTest {
    @Autowired ErrorTest test;

    /**
     *      bind Redisson
     *      bind DataSource
     *      bind method
     *      unbind method
     *      unbind DataSource
     *      unbind Redssion
     *
     *      Jpa 默认事务是 require + readOnly，会加入当前事务
     *          从这一点设置的时间点开始（时间点a）到这个事务结束的过程中，其他事务所提交的数据，该事务将看不见！（查询中不会出现别人在时间点a之后提交的数据）
     *
     *      应用场合：
     *          如果你一次执行单条查询语句，则没有必要启用事务支持，数据库默认支持SQL执行期间的读一致性；
     *          如果你一次执行多条查询语句，例如统计查询，报表查询，在这种场景下，多条查询SQL必须保证整体的读一致性，
     *          否则，在前条SQL查询之后，后条SQL查询之前，数据被其他用户改变，则该次整体的统计查询将会出现读数据不一致的状态，
     *          此时，应该启用事务支持。
     */

    @Test
    public void test(){
        System.err.println(TransactionSynchronizationManager.getCurrentTransactionName());
        test.test(false);
    }

    @Test
    public void test2(){
        System.err.println(TransactionSynchronizationManager.getCurrentTransactionName());
        test.test(true);
    }

    @Test
    public void base(){
        test.base();
    }
}
