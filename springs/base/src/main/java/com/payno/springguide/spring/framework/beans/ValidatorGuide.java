package com.payno.springguide.spring.framework.beans;

import lombok.Data;
import org.junit.Test;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

/**
 * 验证器指南
 *
 * @author zhaolei22
 * @date 2020/09/17
 * @see BeansContractGuide
 */
public class ValidatorGuide {

    @Data
    class Valid{
        @NotEmpty
        String data;
    }

    /**
     * api
     * @see Validator
     * @see javax.validation.Validator
     * @see javax.validation.ValidatorFactory
     *      Spring方法校验(以及作为自定义扩展，还包括 Hibernate Validator 4.3)
     * @see org.springframework.validation.annotation.Validated
     * @see org.springframework.validation.beanvalidation.MethodValidationPostProcessor
     *      可以参考：7. Spring3.1支持方法级别验证
     *      这种用法@Validated注解只能打在类上。
     *      这里存在的问题是：@Validated和@Transactional同时打在类上，会优先执行@Transactional，但是更合理的是优先验证参数。这就需要更改代理类上的拦截器的顺序
     */
    @Test
    public void api(){
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        //javax.validation.Validator validator = factoryBean.getValidator();
        //validator.validate(new Valid());
        Validator springValidator = factoryBean;
        springValidator.validate(new Valid(),new MapBindingResult(new HashMap<>(),"valid"));
    }
}
