package advisor.feign;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author payno
 * @date 2020/6/15 10:02
 * @description
 */
@SpringBootApplication
public class FeignApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(FeignApp.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
