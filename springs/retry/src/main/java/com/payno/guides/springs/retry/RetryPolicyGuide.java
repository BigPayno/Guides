package com.payno.guides.springs.retry;

/**
 * 重试策略指导
 *
 * @author zhaolei22
 * @date 2020/07/17
 */
public class RetryPolicyGuide {

    /*
    *   NeverRetryPolicy：只允许调用RetryCallback一次，不允许重试
    *   AlwaysRetryPolicy：允许无限重试，直到成功，此方式逻辑不当会导致死循环
    *   SimpleRetryPolicy：固定次数重试策略，默认重试最大次数为3次，RetryTemplate默认使用的策略
    *   TimeoutRetryPolicy：超时时间重试策略，默认超时时间为1秒，在指定的超时时间内允许重试
    *   ExceptionClassifierRetryPolicy：设置不同异常的重试策略，类似组合重试策略，区别在于这里只区分不同异常的重试
    *   CircuitBreakerRetryPolicy：有熔断功能的重试策略，需设置3个参数openTimeout、resetTimeout和delegate
    *   CompositeRetryPolicy：组合重试策略，有两种组合方式，乐观组合重试策略是指只要有一个策略允许重试即可以，悲观组合重试策略是指只要有一个策略不允许重试即可以，但不管哪种组合方式，组合中的每一个策略都会执行
    *
    * */
}
