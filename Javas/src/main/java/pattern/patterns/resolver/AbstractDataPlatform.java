package pattern.patterns.resolver;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAware;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.List;

/**
 * @author payno
 * @date 2020/6/11 16:51
 * @description
 */
public abstract class AbstractDataPlatform implements IDataPlatform, ApplicationRunner, ApplicationContextAware {

    IProfileAware profileAware;
    Collection<IDataSource> dataSources;
    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        synchronized (profileAware){
            IProfileAware profileAware = context.getBean(IProfileAware.class);
            if(profileAware==null){
                this.profileAware = new IProfileAware.Default();
            }else{
                this.profileAware = profileAware;
            }
        }
        synchronized (dataSources){
            Collection<IDataSource> sources= context.getBeansOfType(IDataSource.class).values();
        }
    }

    @Override
    public <V> V resolve(IDataApply apply) {
        String data = null;

        return null;
    }
}
