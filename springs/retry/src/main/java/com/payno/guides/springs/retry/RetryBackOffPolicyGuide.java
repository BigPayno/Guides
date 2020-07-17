package com.payno.guides.springs.retry;

/**
 * 重试让步政策指导
 *
 * @author zhaolei22
 * @date 2020/07/17
 */
public class RetryBackOffPolicyGuide {
    public static void main(String[] args) {
        /*
        * NoBackOffPolicy：
        *       无退避算法策略，每次重试时立即重试
        *
        * FixedBackOffPolicy：
        *       固定时间的退避策略，需设置参数sleeper和backOffPeriod，sleeper指定等待策略，默认是Thread.sleep，即线程休眠，backOffPeriod指定休眠时间，默认1秒
        *
        * UniformRandomBackOffPolicy：
        *       随机时间退避策略，需设置sleeper、minBackOffPeriod和maxBackOffPeriod，该策略在[minBackOffPeriod,maxBackOffPeriod之间取一个随机休眠时间，minBackOffPeriod默认500毫秒，maxBackOffPeriod默认1500毫秒
        *
        * ExponentialBackOffPolicy：
        *       指数退避策略，需设置参数sleeper、initialInterval、maxInterval和multiplier，initialInterval指定初始休眠时间，默认100毫秒，maxInterval指定最大休眠时间，默认30秒，multiplier指定乘数，即下一次休眠时间为当前休眠时间*multiplier
        *
        * ExponentialRandomBackOffPolicy：
        *       随机指数退避策略，引入随机乘数可以实现随机乘数回退
        * */
    }
}
