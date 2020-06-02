package com.payno.guides.springs.cache.framework;

/**
 * @author payno
 * @date 2020/6/2 14:42
 * @description
 */
public class JSR107FrameworkGuide {
    /**
     * Java Caching定义了5个核心接口，
     * 分别是CachingProvider, CacheManager, Cache, Entry和Expiry。
     *
     *     CachingProvider定义了创建、配置、获取、管理和控制多个CacheManager。
     *          一个应用可以在运行期访问多个CachingProvider。
     *     CacheManager定义了创建、配置、获取、管理和控制多个唯一命名的Cache，
     *          这些Cache存在于CacheManager的上下文中。一个CacheManager仅被一个CachingProvider所拥有。
     *     Cache是一个类似Map的数据结构并临时存储以Key为索引的值。一个Cache仅被一个CacheManager所拥有。
     *     Entry是一个存储在Cache中的key-value对。
     *     Expiry每一个存储在Cache中的条目有一个定义的有效期。一旦超过这个时间，条目为过期的状态。
     *          一旦过期，条目将不可访问、更新和删除。缓存有效期可以通过ExpiryPolicy设置。
     */
}
