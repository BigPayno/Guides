package com.payno.guides.springs.retry;

import org.springframework.retry.support.RetryTemplate;

/**
 * 重试框架指南
 *
 * @author zhaolei22
 * @date 2020/07/17
 */
public class RetryFrameworkGuide {

    /*
    *       spring retry是从spring batch独立出来的一个能功能，主要实现了重试和熔断。
    * 对于重试是有场景限制的，不是什么场景都适合重试，比如参数校验不合法、写操作等（要考虑
    * 写是否幂等）都不适合重试。远程调用超时、网络突然中断可以重试。在微服务治理框架中，通
    * 常都有自己的重试与超时配置，比如dubbo可以设置retries=1，timeout=500调用失败只重
    * 试1次，超过500ms调用仍未返回则调用失败。在spring retry中可以指定需要重试的异常类型
    * ，并设置每次重试的间隔以及如果重试失败是继续重试还是熔断（停止重试）。
    *
    * */

    public static void main(String[] args) {

    }
}
