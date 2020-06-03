package advisor.advisor;

import advisor.imports.ImportApp;
import org.springframework.aop.framework.ProxyConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author payno
 * @date 2020/6/3 15:50
 * @description
 */
@EnableAsync
@SpringBootApplication
public class AdvisorApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context=new SpringApplicationBuilder(AdvisorApp.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
