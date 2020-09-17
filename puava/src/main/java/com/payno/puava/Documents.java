package com.payno.puava;

import com.google.common.annotations.Beta;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Enums;
import com.google.common.base.Splitter;
import com.google.common.io.Resources;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 * 够用了，懒得搞
 *
 * @author zhaolei22
 * @date 2020/09/04
 */
@Beta
public class Documents {

    static String PROTO_TYPE_END = "{";
    static String PROTO_INNER = " ";

    private static String safeGet(List<String> strs,int index){
        if(strs.size()>index){
            return strs.get(index);
        }else{
            return "";
        }
    }

    enum JavaType{
        Object,Boolean,Integer,Long,String
    }

    enum Protobuf{
        ENUM{
            @Override
            void extract(List<String> lineParams) {
                System.out.println(
                    CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,lineParams.get(1))+
                            "("+
                            lineParams.get(2)+
                            "),"
                );
            }
        },
        MESSAGE{
            @Override
            void extract(List<String> lineParams) {
                System.out.println(
                    ProtobufType.parse(lineParams.get(2)).javaType().name() + " " +
                    CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,lineParams.get(3))+";"
                );
            }
        };
        abstract void extract(List<String> lineParams);
    }

    enum ProtobufType{
        unknown{
            @Override
            JavaType javaType() {
                return JavaType.Object;
            }
        },
        bool{
            @Override
            JavaType javaType() {
                return JavaType.Boolean;
            }
        },
        int32{
            @Override
            JavaType javaType() {
                return JavaType.Integer;
            }
        },
        int64{
            @Override
            JavaType javaType() {
                return JavaType.Long;
            }
        },
        string{
            @Override
            JavaType javaType() {
                return JavaType.String;
            }
        };
        abstract JavaType javaType();
        public static ProtobufType parse(String source){
            return Enums.getIfPresent(ProtobufType.class,source)
                    .or(ProtobufType.unknown);
        }
    }

    public static void parseMessage(String resourcePath,Protobuf protobuf) throws IOException{
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(resourcePath);
        URL url = resource.getURL();
        List<String> documentLines = Resources.asCharSource(url, Charsets.UTF_8).readLines();
        //parseMessage("classpath:proto.test",Protobuf.MESSAGE);
        Iterator<String> iterator = Splitter.on(PROTO_INNER)
                .split(
                        Splitter.on(PROTO_TYPE_END).split(documentLines.get(0)).iterator().next()
                ).iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        documentLines.subList(1,documentLines.size()-1)
                .forEach(line->{
                    line = CharMatcher.forPredicate(character ->
                            character.equals(' ')||
                            character.equals('=')||
                            character.equals('/')||
                            character.equals(';')).collapseFrom(line,' ');
                    List<String> lineParams = Splitter.on(PROTO_INNER).splitToList(line);
                    protobuf.extract(lineParams);
                });
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        parseMessage("classpath:proto.test", Protobuf.MESSAGE);
        parseMessage("classpath:proto.enum", Protobuf.ENUM);
    }
}
