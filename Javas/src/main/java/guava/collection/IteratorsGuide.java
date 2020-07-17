package guava.collection;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;

import java.util.Iterator;

/**
 * 迭代器指南
 *
 * @author zhaolei22
 * @date 2020/07/10
 */
public class IteratorsGuide {
    public static void main(String[] args) {

        Iterator<Integer> integerIterator = Lists.newArrayList(1,1,1,1,2,1,5).iterator();

        /*
        *   求出现次数
        * */
        int count = Iterators.frequency(integerIterator,Integer.valueOf(1));
        System.out.println(count);

        /*
        *   Array的迭代器
        * */
        String[] strings = new String[]{"a","c","b"};
        Iterator<String> iterator = Iterators.forArray(strings);
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        /*
        *   peek获取下一个元素但不移动指针
        * */
        PeekingIterator<Integer> peekingIterator=Iterators.peekingIterator(
                Lists.newArrayList(1,1,1,1,2,1,5).iterator()
        );
        while(peekingIterator.hasNext()){
            int cur = peekingIterator.next();
            if(peekingIterator.hasNext()){
                int next = peekingIterator.peek();
                if(cur>next){
                    System.out.println(cur);
                }
            }
        }

        /*
        *   循环迭代器
        * */
        Iterator<String> cycle=Iterators.cycle("a","b");
        int limit = 2;
        while (cycle.hasNext()&&limit-->0){
            System.out.println(cycle.next());
        }
    }
}
