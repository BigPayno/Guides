package pattern.patterns.resolver;

/**
 * @author payno
 * @date 2020/6/11 16:46
 * @description
 */
public interface IDataPlatform {
    <V> V resolve(IDataApply apply);
}
