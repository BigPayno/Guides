package com.payno.guides.springs.cache.framework.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author payno
 * @date 2020/6/2 15:55
 * @description
 */
public class SpringCacheManagerAdaptor implements CacheManager {

    org.springframework.cache.CacheManager cacheManager;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new SpringCache<>(cacheManager.getCache(name));
    }
}
