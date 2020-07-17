package com.payno.guides.springs.retry;

/**
 * 重试注释
 *
 * @author zhaolei22
 * @date 2020/07/17
 */
public class RetryAnnotations {


    public static void main(String[] args) {
        /*
        * @EnableRetry
        * @Retryable
        * @Recover
        * @Backoff
        * @CircuitBreaker
        *
        * @EnableRetry：能否重试，
        *   proxyTargetClass属性为true时（默认false），使用CGLIB代理
        *
        * @Retryable：注解需要被重试的方法
        *   include 指定处理的异常类。默认为空
        *   exclude指定不需要处理的异常。默认为空
        *   vaue指定要重试的异常。默认为空
        *   maxAttempts 最大重试次数。默认3次
        *   backoff 重试等待策略。默认使用@Backoff注解
        *
        * @Backoff：重试回退策略（立即重试还是等待一会再重试）
        *   不设置参数时，默认使用FixedBackOffPolicy，重试等待1000ms
        *   只设置delay()属性时，使用FixedBackOffPolicy，重试等待指定的毫秒数
        *   当设置delay()和maxDealy()属性时，重试等待在这两个值之间均态分布
        *   使用delay()，maxDealy()和multiplier()属性时，使用ExponentialBackOffPolicy
        *   当设置multiplier()属性不等于0时，同时也设置了random()属性时，使用ExponentialRandomBackOffPolicy
        *
        * @Recover: 用于方法。用于@Retryable失败时的“兜底”处理方法。
        * @Recover注释的方法必须要与@Retryable注解的方法“签名”保持一致，第一入参为要重试的异常，其他参数与@Retryable保持一致，返回值也要一样，否则无法执行！
        *
        * @CircuitBreaker：用于方法，实现熔断模式。
        *   include 指定处理的异常类。默认为空
        *   exclude指定不需要处理的异常。默认为空
        *   vaue指定要重试的异常。默认为空
        *   maxAttempts 最大重试次数。默认3次
        *   openTimeout 配置熔断器打开的超时时间，默认5s，当超过openTimeout之后熔断器电路变成半打开状态（只要有一次重试成功，则闭合电路）
        *   resetTimeout 配置熔断器重新闭合的超时时间，默认20s，超过这个时间断路器关闭
        *
        * */
    }
}
