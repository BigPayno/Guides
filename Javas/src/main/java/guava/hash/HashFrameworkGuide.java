package guava.hash;

import com.google.common.collect.Lists;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import utils.charset.Charsets;

/**
 * 散列框架指南
 *
 * @author zhaolei22
 * @date 2020/07/17
 */
public class HashFrameworkGuide {
    public static void main(String[] args) {
        /*
        *   Hashing 拥有一些产生HashFunction的静态工厂方法
        *           拥有组合HashCode、HashFunction的静态工厂方法
        * */
        HashFunction hashFunction = Hashing.sha256();
        /*
        *   HashFunction 拥有hash String或者long以及bytes的hash方法.HashCode
        *                能够产生对应的Hasher
        * */
        HashCode hashCode=hashFunction.hashLong(123456L);
        System.out.println(hashCode.toString());
        System.out.println(hashCode.asLong());
        System.out.println(hashCode.asInt());
        System.out.println(hashCode.asBytes());

        /*
        *   Hasher 建造者
        * */
        Hasher hasher = hashFunction.newHasher();
        HashCode hashCode1 = hasher
                .putString("abc", Charsets.UTF_8)
                .putLong(1234L)
                .hash();
        System.out.println(hashCode1.toString());

        HashCode combineHashCode = Hashing
                .combineOrdered(Lists.newArrayList(hashCode,hashCode1));
        System.out.println(combineHashCode.toString());
    }
}
