package com.payno.transaction.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author payno
 * @date 2020/6/8 11:45
 * @description
 */
@Component
public class TransactionPropagationTest {
    @Autowired
    ModelRepo repo;

    //  如果存在事务，则在事务中执行，否则正常执行

    @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.SUPPORTS)
    public void saveOnRollback(Model model){
        Transactions.showTransaction();
        repo.save(model);
        throw new RuntimeException();
    }

    //  默认级别，如果当前不存在事务，则开启一个新的，如果存在，则在当前事务中执行

    @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRED)
    public void save(Model model){
        Transactions.showTransaction();
        repo.save(model);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void test(Model model){
        repo.save(model);
        saveOnRollback(Model.builder().cusName("rollback").build());
    }
}
