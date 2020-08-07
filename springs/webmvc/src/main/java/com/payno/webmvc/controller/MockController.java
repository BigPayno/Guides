package com.payno.webmvc.controller;

import com.google.common.collect.ImmutableMap;
import lombok.Data;
import org.payno.mock.server.core.protocol.HttpProtocolDetails;
import org.payno.mock.server.support.aop.NeedMock;
import org.payno.mock.server.support.autoconfigure.EnableMock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mock/")
@EnableMock(mockServer = true)
public class MockController {

    @Data
    static class Person{
        String name;
        String gender;
    }

    @GetMapping("person")
    @NeedMock(spy = true)
    public Person person() {
        Person person = new Person();
        person.setName("PAYNO");
        return person;
    }

    @PostMapping("http")
    public HttpProtocolDetails mock(@RequestBody String uri){
        HttpProtocolDetails details = new HttpProtocolDetails();
        details.setResponseStatus(440);
        details.setSleepTime(10000);
        details.setHeaders(ImmutableMap.of("mock","payno","mockUrl",uri));
        details.setCookies(ImmutableMap.of("mockCook","payno"));
        details.setRedirectUri("");
        return details;
    }

    @GetMapping("person2")
    @NeedMock(mock = true)
    public Person person2(){
        return null;
    }
}
