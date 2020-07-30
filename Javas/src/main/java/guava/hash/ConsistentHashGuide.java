package guava.hash;

import com.google.common.hash.Hashing;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * 一致性哈希指南
 *
 * @author zhaolei22
 * @date 2020/07/24
 */
public class ConsistentHashGuide {

    private static Map<Integer,AtomicInteger> consistentHashTest(Collection<Long> input, int buckets){
        ConcurrentHashMap<Integer, AtomicInteger> countMap = new ConcurrentHashMap<>(buckets);
        input.parallelStream().forEach(val->{
            int hashResult = Hashing.consistentHash(val,buckets);
            if(countMap.get(hashResult)==null){
                countMap.putIfAbsent(hashResult, new AtomicInteger());
                countMap.get(hashResult).incrementAndGet();
            }else{
                countMap.get(hashResult).incrementAndGet();
            }
        });
        return countMap;
    }

    public static void main(String[] args) {
        List<Long> nums = LongStream.range(0,100000).boxed().collect(Collectors.toList());
        consistentHashTest(nums,5).entrySet().forEach(entry->{
            System.out.println(entry.getKey()+" : "+entry.getValue().get());
        });
    }
}
