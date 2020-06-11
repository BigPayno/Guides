package pattern.patterns.resolver;

import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author payno
 * @date 2020/6/11 16:55
 * @description
 */
public interface IProfileAware {
    Collection<String> openProfiles();

    class Default implements IProfileAware{
        @Override
        public Collection<String> openProfiles() {
            return Lists.newArrayList("test","dev");
        }
    }
}
