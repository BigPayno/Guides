package guava.escaper;

import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;
import org.junit.Test;

import java.net.URLDecoder;

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
        System.out.println(fragmentEscaper.escape("https://acacaffv%acs"));
    }

    @Test
    public void test1(){
        try{
            System.out.println(
                URLDecoder.decode("123%.txt","UTF-8")
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try{
            System.out.println(
                URLDecoder.decode(
                        UrlEscapers.urlFormParameterEscaper().escape("123%.txt"),"UTF-8")
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
