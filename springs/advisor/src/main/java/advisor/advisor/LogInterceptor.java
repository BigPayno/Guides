package advisor.advisor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author payno
 * @date 2020/6/3 15:29
 * @description
 */
public class LogInterceptor implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("...........................");
        System.err.println(method.getName());
        System.out.println("............................");
    }
}
