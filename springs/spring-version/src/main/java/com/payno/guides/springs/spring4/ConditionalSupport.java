package com.payno.guides.springs.spring4;

import org.springframework.context.annotation.Profile;

/**
 * 条件注入注解支持
 *      之前存在Condition接口
 *      如今可以使用Conditional(Condition.class)实现
 *      这里支持元注解
 *      其中@Profile->@Conditional(ProfileCondition)
 * @author zhaolei22
 * @date 2020/07/21
 */
@Profile("hello")
public class ConditionalSupport {
}
