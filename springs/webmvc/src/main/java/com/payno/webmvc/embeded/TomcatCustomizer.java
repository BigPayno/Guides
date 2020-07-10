package com.payno.webmvc.embeded;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * tomcat编辑器
 *      Customizer接口
 *
 * @author zhaolei22
 * @date 2020/07/09
 */
@Slf4j
@Component
public class TomcatCustomizer implements WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> {
    @Override
    public void customize(ConfigurableTomcatWebServerFactory factory) {
        factory.setPort(8081);
        log.info("......");
    }
}
