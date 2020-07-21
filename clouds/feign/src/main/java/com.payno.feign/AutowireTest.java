package com.payno.feign;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class AutowireTest implements InitializingBean {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    public void test(){
        System.out.println(handlerMapping);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("payno");
    }
}
