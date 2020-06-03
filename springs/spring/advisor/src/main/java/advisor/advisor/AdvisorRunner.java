package advisor.advisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2020/6/3 14:37
 * @description
 */
@Component
public class AdvisorRunner implements ApplicationRunner {

    @Autowired
    AdvisorTestService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(service.getClass());
        service.test();
    }
}
