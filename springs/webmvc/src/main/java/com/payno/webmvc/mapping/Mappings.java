package com.payno.webmvc.mapping;

import com.payno.webmvc.WebmvcApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

/**
 * @author payno
 * @date 2020/4/17 10:12
 * @description
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebmvcApplication.class)
public class Mappings {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Test
    public void getAllApi() {
        Map<RequestMappingInfo, HandlerMethod> map = this.handlerMapping.getHandlerMethods();
        Set<RequestMappingInfo> set = map.keySet();
        for (RequestMappingInfo info : set) {

            HandlerMethod handlerMethod = map.get(info);
            handlerMethod.getMethodParameters();
            handlerMethod.getReturnType();
            RequestMapping requestMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);
            System.out.println(requestMapping);
            // springmvc的url地址，不包含项目名
            PatternsRequestCondition patternsCondition = info.getPatternsCondition();

            System.out.println(patternsCondition);

        }
        System.out.println(111);
    }
}
