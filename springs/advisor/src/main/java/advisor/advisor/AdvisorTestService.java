package advisor.advisor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author payno
 * @date 2020/6/3 14:36
 * @description
 */
@Log
@Service
public class AdvisorTestService {
    public void test(){}

    @Async
    public void test2(){}
}
