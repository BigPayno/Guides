package com.payno.guides.springs.retry;

/**
 * 重试回调指南
 *
 * @author zhaolei22
 * @date 2020/07/17
 */
public class RetryCallbacksGuide {
    /*
    * RetryCallback定义了需要执行重试的操作，定义好操作后，就是如何重试的问题了。
    *       RetryTemplate通过制定不同的重试策略来执行如何重试的逻辑。默认的重试策略是SimpleRetryPlicy，也就是会重试3次。重试第1次如果成功后面就不会继续重试了。那么如果3尺都重试失败了呢？
    *       流程结束或者返回兜底结果。要返回兜底结果需要配置RecoveyCallBack，从名字可以看出这是一个兜底回调接口，也就是重试失败后执行的逻辑。
    *
    * RetryCallback
    *       重试回调
    *
    * RecoveryCallback
    *       降级处理
    * */
}
