package common;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ConfigersTest {
    @Data
    static class User{
        String name;
        String nick;
        List<String> followers;
        void addFollower(String str){
            if(followers==null){
                followers = new ArrayList<>();
            }
            followers.add(str);
        }
    }

    @Data
    static class UserInfo{
        String nick;
    }

    public static void main(String[] args) {
        User user = new User();
        UserInfo userInfo = new UserInfo();
        userInfo.setNick("BigPayno");
        String name = "payno";
        List<String> follwers = Lists.newArrayList("a","b");
        List<String> follwers2 = Lists.newArrayList("c","b");
        List<String> follwers3 = Lists.newArrayList("a","b","e");
        List<String> follwers4 = Lists.newArrayList("a","b","e","f");



        Consumers.fluent()
                .onStringNotNullOrBlank(name,user::setName)
                .onStringNotNullOrBlank(userInfo::getNick,user::setNick)
                .onObjectNotNull(follwers,user::setFollowers)
                .onCollectionNotNullOrEmply(follwers2,user::addFollower)
                .onCollection(follwers4,user::addFollower,collection -> collection.size()>3,Predicates.not(Predicates.in(follwers)))
                .on("a",user::addFollower, Predicates.not(Predicates.in(follwers)))
                .on(user,System.out::print);

    }
}
