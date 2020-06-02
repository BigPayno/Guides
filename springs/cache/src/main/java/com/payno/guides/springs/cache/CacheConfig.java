package com.payno.guides.springs.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author payno
 * @date 2020/6/2 16:51
 * @description
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport {
    /**
     *  CachingConfigurerSupport-|>CachingConfigurer
     */
    @Bean
    @Override
    public CacheManager cacheManager(){
        return new CaffeineCacheManager();
    }
}
