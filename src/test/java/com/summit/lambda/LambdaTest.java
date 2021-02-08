package com.summit.lambda;



import com.google.common.collect.Lists;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author: jeerper
 * @since: 2021/2/7 17:19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LambdaTest {
    @Test
    public void map(){
        List<String> data = Arrays.asList("a", "b", "c", "d");
        List<String> collect = data.stream().map(String::toUpperCase).collect(toList());
        System.out.println(collect); //[A, B, C, D]
        List<String> collect2 = data.stream().map(item ->
                item.replace("a","g")
        ).collect(toList());
        System.out.println(collect2); //[A, B, C, D]

    }

    @Test
    public void flatMap(){

        List<List<Integer>> outer = new ArrayList<>();
        List<Integer> inner1 = new ArrayList<>();
        inner1.add(1);
        List<Integer> inner2 = new ArrayList<>();
        inner1.add(2);
        List<Integer> inner3 = new ArrayList<>();
        inner1.add(3);
        List<Integer> inner4 = new ArrayList<>();
        inner1.add(4);
        List<Integer> inner5 = new ArrayList<>();
        inner1.add(5);
        outer.add(inner1);
        outer.add(inner2);
        outer.add(inner3);
        outer.add(inner4);
        outer.add(inner5);
        List<Integer> result = outer.stream().flatMap(inner -> inner.stream().map(i -> i + 1)).collect(toList());
        System.out.println(result);
    }

    @Test
    public void flatMap2(){
        List<String> hobby1 = Arrays.asList("java", "c", "音乐");
        List<String> hobby2 = Arrays.asList("c++", "c", "游戏");
        User user1 = new User(1, "张三", hobby1);
        User user2 = new User(2, "李四", hobby2);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        // 将集合中每个用户的爱好进行计算，取并集
        List<String> result = users.stream()
                .map(user -> user.hobby)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }

     class User {
        int id;
        String name;
        List<String> hobby;

        public User(int id, String name, List<String> hobby) {
            this.id = id;
            this.name = name;
            this.hobby = hobby;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
