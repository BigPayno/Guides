package com.payno.guides.springs.cache.framework.cache;

import org.apache.shiro.cache.CacheException;
import org.springframework.cache.Cache;

import java.util.Collection;
import java.util.Set;

/**
 * @author payno
 * @date 2020/6/2 15:44
 * @description
 */
public class SpringCache<K,V> implements org.apache.shiro.cache.Cache<K,V> {

    public SpringCache(Cache cache){
        this.cache = cache;
    }

   Cache cache;

    @Override
    public V get(K k) throws CacheException {
        return (V) cache.get(k).get();
    }

    @Override
    public V put(K k, V v) throws CacheException {
        cache.put(k,v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v=(V)cache.get(k);
        cache.evict(k);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        cache.clear();
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
