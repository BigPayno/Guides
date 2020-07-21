package guava.collection;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/**
 * @author payno
 * @date 2019/10/29 10:10
 * @description
 */
public class IterablesGuide {
    public static void main(String[] args) {
        Iterables.getLast(ImmutableList.copyOf(new String[]{"a","b"}));
        String find1 = Iterables.find(
                ImmutableList.of("a","b","c"),item->"a".equals(item));
        try{
            String find2 = Iterables.find(
                    ImmutableList.of("a","b","c"),item->"d".equals(item));
        }catch (Exception e){ }

        Optional<String> find3 = Iterables.tryFind(
                ImmutableList.of("a","b","c"),item->"d".equals(item));
        System.out.println(find3.toJavaUtil().isPresent());
    }
}
