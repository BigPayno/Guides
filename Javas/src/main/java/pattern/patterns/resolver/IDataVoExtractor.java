package pattern.patterns.resolver;

/**
 * @author payno
 * @date 2020/6/11 16:44
 * @description
 */
public interface IDataVoExtractor<V>{
    /**
     * supports
     * @author: payno
     * @time: 2020/6/11 16:41
     * @description:
     * @param sourceCode 支持的数据源类型
     * @return: boolean
     */
    boolean supports(String sourceCode);

    /**
     * resolve
     * @author: payno
     * @time: 2020/6/11 16:46
     * @description: 解析源数据返回结果
     * @param source
     * @return: V
     */
    V resolve(String source);
}
