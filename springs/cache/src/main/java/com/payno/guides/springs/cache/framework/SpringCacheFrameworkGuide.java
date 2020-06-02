package com.payno.guides.springs.cache.framework;

/**
 * @author payno
 * @date 2020/6/2 14:48
 * @description
 */
public class SpringCacheFrameworkGuide {
    /**
     *  @see Spring.Cache.jpg
     *      关于Cache内部框架的结构
     *      并且画出了Shiro集成的思路
     *
     *      1.基于RestTemplate和Redis-Hash实现Shiro的CacheManager/IRedisManager(guides.servers.redis.SpringRedisManager)
     *          =>应该可以直接实现CacheManager
     *      2.基于Spring的Cache实现Shiro的CacheManager
     *          => 有几个功能无法实现
     *      3.基于Spring的Cache实现crazyCake的RedisManager
     *          => 有几个功能无法实现
     */
}
