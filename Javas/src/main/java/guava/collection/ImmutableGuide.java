package guava.collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author payno
 * @date 2019/9/2 16:56
 * @description
 */
public class ImmutableGuide {
    @Data
    @AllArgsConstructor
    public class DataA{
        private String a;
    }

    @Test
    public void test(){
        List<DataA> list=ImmutableList.of(new DataA("payno"));
        list.get(0).setA("payne");
        list.forEach(System.err::println);
    }

    public static void main(String[] args) {
        Collection<String> collection=new ArrayList<>();
        ImmutableList.copyOf(collection);
        ImmutableSet.copyOf(collection);
    }
}
