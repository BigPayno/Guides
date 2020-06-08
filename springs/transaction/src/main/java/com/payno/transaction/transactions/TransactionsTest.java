package com.payno.transaction.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author payno
 * @date 2020/6/8 11:13
 * @description
 */
@Component
public class TransactionsTest {

    @Autowired ModelRepo repo;

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveOnRollback(Model model){
        Transactions.showTransaction();
        repo.save(model);
        throw new RuntimeException();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void save(Model model){
        Transactions.showTransaction();
        repo.save(model);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveWithSynchronization(Model model){
        Transactions.showTransaction();
        repo.save(model);
        if(TransactionSynchronizationManager.isSynchronizationActive()){
            //  只有在事务里才能使用
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    Transactions.showTransaction();
                    System.out.println("commit");
                }

                @Override
                public void afterCompletion(int status) {
                    Transactions.showTransaction();
                    super.afterCompletion(status);
                }
            });
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveWithSynchronizationButRollback(Model model){
        Transactions.showTransaction();
        repo.save(model);
        if(TransactionSynchronizationManager.isSynchronizationActive()){
            //  事务回滚也不会触发回调
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    Transactions.showTransaction();
                    System.out.println("commit");
                }
            });
        }
        throw new RuntimeException();
    }
}
