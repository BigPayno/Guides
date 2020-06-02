package com.payno.guides.springs.cache;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author payno
 * @date 2020/6/2 14:37
 * @description
 */
@EnableCaching
@SpringBootApplication
public class CacheApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CacheApp.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
