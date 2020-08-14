package com.payno.springguide.spring.history.enable.autoconfig;

import com.payno.springguide.spring.history.enable.bootstrap.People;
import org.springframework.context.annotation.*;

/**
 * @author payno
 * @date 2019/11/22 08:56
 * @description
 */
@Configuration
//@Import(People.class)
//@DependsOn("springUtils")
public class HelloConfig{
    @Bean
    public People hello(){
        return new People("hello");
    }
}
