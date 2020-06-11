package pattern.patterns.resolver;

/**
 * @author payno
 * @date 2020/6/11 16:39
 * @description
 */
public interface IDataSource<A> {

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
     * profile
     * @author: payno
     * @time: 2020/6/11 16:41
     * @description:
     * @param
     * @return: java.lang.String 支持的Profile
     */
    String profile();

    /**
     * source
     * @author: payno
     * @time: 2020/6/11 16:42
     * @description: 返回三方数据源数据，可能抛出异常
     * @param
     * @return: java.lang.String
     */
    String source(A a) throws Exception;
}
