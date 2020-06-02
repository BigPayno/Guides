package spring.reflect.generic;

import com.google.common.collect.Streams;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author payno
 * @date 2020/6/2 11:25
 * @description
 */
public class GenericTypeResolverGuide {

    class GenericList extends LinkedList<String>{ }

    interface Generic<A,B>{}

    class GenericMap implements Generic<String,Integer>{}

    public static void main(String[] args) {
        System.out.println(
                GenericTypeResolver.resolveTypeArgument(GenericList.class,LinkedList.class)
        );
        Stream.of(
                GenericTypeResolver.resolveTypeArguments(GenericMap.class,Generic.class)
        ).forEach(System.out::println);
    }
}
