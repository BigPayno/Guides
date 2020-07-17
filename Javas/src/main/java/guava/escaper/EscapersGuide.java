package guava.escaper;

import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;

/**
 * 幸免型导
 *
 * @author zhaolei22
 * @date 2020/07/17
 */
public class EscapersGuide {
    public static void main(String[] args) {
        /*
        * URL 中也经常需要进行特殊字符的替换，否则解析可能会出现意想不到的结果，Guava也提供了URL相关的Escape
        * URL 格式：protocol ://hostname[:port] / path / [;parameters][?query]#fragment
        *
        * */
        Escaper fragmentEscaper = UrlEscapers.urlFragmentEscaper();
        System.out.println(fragmentEscaper.escape("https://acacaffv#acs"));
    }
}
