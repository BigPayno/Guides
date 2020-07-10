package com.payno.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * webmvc应用程序
 *      支持两种启动方式：
 *          1.jar包
 *          2.war包 需要继承SpringBootServletInitializer
 * @author zhaolei22
 * @date 2020/07/09
 */
@SpringBootApplication
@ComponentScan(basePackages = "com")
public class WebmvcApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebmvcApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }
}
