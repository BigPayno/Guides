package com.payno.spring.security.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2020/5/15 09:34
 * @description
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
