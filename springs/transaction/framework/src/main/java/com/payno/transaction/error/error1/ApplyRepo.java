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
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    <S extends Apply> S save(S s);

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    <S extends Apply> Iterable<S> saveAll(Iterable<S> iterable);
}
