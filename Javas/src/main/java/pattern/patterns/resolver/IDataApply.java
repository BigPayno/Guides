package pattern.patterns.resolver;

import java.util.Collection;

/**
 * @author payno
 * @date 2020/6/11 16:47
 * @description
 */
public interface IDataApply<Q> {
    String serviceCode();
    Collection<String> profiles();
    Q apply();
}
