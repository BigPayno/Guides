package com.payno.transaction.error.error1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author payno
 * @date 2020/6/8 09:23
 * @description
 */
public interface ApplyRepo extends CrudRepository<Apply, Integer> {

    @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRED)
    default <S extends Apply> S saveOnRequired(S s){
        return save(s);
    }

    @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRES_NEW)
    default <S extends Apply> S saveOnRequiredNew(S s){
        return save(s);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    <S extends Apply> Iterable<S> saveAll(Iterable<S> iterable);
}
