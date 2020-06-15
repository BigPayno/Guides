package advisor.feign;

import feign.Feign;
import feign.spring.SpringContract;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * @author payno
 * @date 2020/6/15 09:46
 * @description
 */
@Component
public class FeignRunner implements ApplicationRunner, ApplicationContextAware, EnvironmentAware {

    AnnotationConfigApplicationContext context;
    Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context= (AnnotationConfigApplicationContext)applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment =environment;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        FeignComponentScanner scanner=new FeignComponentScanner(false,environment);
        Set<BeanDefinition> beanDefinitions=scanner.findCandidateComponents("advisor.feign");
        beanDefinitions.forEach(beanDefinition -> {
            try{
                Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                Object feignClient = Feign.builder()
                        .contract(new SpringContract())
                        .target(clazz,"http://localhost:8080");
                FeignInvocationHandler invocationHandler=new FeignInvocationHandler(feignClient);
                Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{clazz},invocationHandler);
                context.registerBean(proxy.getClass(),new Object[]{invocationHandler} );
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        FeignClient feignClient=context.getBean(FeignClient.class);
        System.out.println(feignClient.get("payno"));
    }
}
