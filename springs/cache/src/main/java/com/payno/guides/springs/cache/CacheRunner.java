package com.payno.guides.springs.cache;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2020/6/2 16:48
 * @description
 */
@Component
public class CacheRunner implements ApplicationRunner, ApplicationContextAware {

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        context.getBeansOfType(CacheManager.class).values().forEach(System.err::println);
    }
}
