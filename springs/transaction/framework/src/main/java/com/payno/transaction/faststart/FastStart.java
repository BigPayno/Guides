package com.payno.transaction.faststart;

/**
 * @author payno
 * @date 2020/6/2 10:19
 * @description
 */
public class FastStart {
    /**
     *  事务指的是支持事务的终端们可以通过回滚完成事务
     *      A->B    =>      A->B->A
     *      比如Redis支持事务，那么按照Spring的标准实现事务便可以实现事务注解@Transaction
     *      比如JDBC支持事务，那么按照Spring提供的接口便可以实现事务
     *      类似的，全局事务，MQ事务，缓存事务都可以集成到Spring里
     *
     *   具体实现可以参考设计模式的备忘录模式
     */

}
