package com.payno.puava;

import com.fasterxml.jackson.databind.JavaType;
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
import java.sql.SQLType;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 够用了，懒得搞
 *
 * @author zhaolei22
 * @date 2020/09/04
 */
@Beta
public class Documents {

    enum JavaType{
        /**
         * 对象
         */
        Object,
        /**
         * 布尔
         */
        Boolean,
        /**
         * 整数
         */
        Integer,
        /**
         * 长
         */
        Long,
        /**
         * 字符串
         */
        String,
        /**
         * 日期
         */
        Date
    }

    enum Protobuf{
        /**
         * 枚举
         */
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
        /**
         * 消息
         */
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
        /**
         * 未知的
         */
        unknown{
            @Override
            JavaType javaType() {
                return JavaType.Object;
            }
        },
        /**
         * 保龄球
         */
        bool{
            @Override
            JavaType javaType() {
                return JavaType.Boolean;
            }
        },
        /**
         * int32
         */
        int32{
            @Override
            JavaType javaType() {
                return JavaType.Integer;
            }
        },
        /**
         * int64
         */
        int64{
            @Override
            JavaType javaType() {
                return JavaType.Long;
            }
        },
        /**
         * 字符串
         */
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

    static ResourceLoader RESOURCE_LOADER = new DefaultResourceLoader();

    private static String safeGet(List<String> strs,int index){
        if(strs.size()>index){
            return strs.get(index);
        }else{
            return "";
        }
    }

    private static List<String> documentLines(String resourcePath) throws IOException {
        Resource resource = RESOURCE_LOADER.getResource(resourcePath);
        URL url = resource.getURL();
        return Resources.asCharSource(url, Charsets.UTF_8).readLines();
    }

    static String PROTO_TYPE_END = "{";
    static String PROTO_INNER = " ";

    public static void parseMessage(String resourcePath,Protobuf protobuf) throws IOException{
        List<String> documentLines = documentLines(resourcePath);
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

    private static final Pattern NAME = Pattern.compile("`.*`");
    private static final Pattern DES = Pattern.compile("'.*'");
    enum SqlType{
        /**
         * 长整型数字
         */
        BIGINT{
            @Override
            JavaType javaType() {
                return JavaType.Long;
            }
        },
        /**
         * 非常小的整数
         */
        TINYINT{
            @Override
            JavaType javaType() {
                return JavaType.Integer;
            }
        },
        /**
         * datetime
         */
        DATETIME{
            @Override
            JavaType javaType() {
                return JavaType.Date;
            }
        },
        /**
         * 时间戳
         */
        TIMESTAMP{
            @Override
            JavaType javaType() {
                return JavaType.Date;
            }
        };
        abstract JavaType javaType();

        static Set<SqlType> SET = EnumSet.allOf(SqlType.class);
    }

    public static void parseCreateDDL(String resourcePath) throws IOException{
        List<String> documentLines = documentLines(resourcePath);
        documentLines.stream().skip(1)
                .filter(item->item.endsWith(","))
                .forEach(item->{
                    String name="";
                    String type="";
                    String desc="";
                    Matcher nameMatcher = NAME.matcher(item);
                    while (nameMatcher.find()){
                        name = nameMatcher.group(0);
                        name = name.substring(1,name.length()-1);
                        name = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,name);
                    }
                    JavaType javaType = SqlType.SET.stream()
                            .filter(sqlType->item.contains(sqlType.name()))
                            .findAny()
                            .map(SqlType::javaType)
                            .orElse(JavaType.Object);
                    type = javaType.name();
                    Matcher descriptionMatcher = DES.matcher(item);
                    while (descriptionMatcher.find()){
                        desc = descriptionMatcher.group(0);
                        desc = desc.substring(1,desc.length()-1);
                    }
                    System.out.printf("/**%n * %s%n */%n %s %s;%n", desc,type,name);
                });
    }


    public static void main(String[] args) throws IOException {
        //parseCreateDDL("classpath:test.sql");
        parseMessage("classpath:proto.test", Protobuf.MESSAGE);
        //parseMessage("classpath:proto.enum", Protobuf.ENUM);
    }
}
