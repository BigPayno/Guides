package advisor.feign;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author payno
 * @date 2020/6/15 10:59
 * @description
 */
public class FeignInvocationHandler implements InvocationHandler {
    public FeignInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target,args);
    }
}
