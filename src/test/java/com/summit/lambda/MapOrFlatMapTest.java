package com.summit.lambda;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author: jeerper
 * @since: 2021/3/22 10:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MapOrFlatMapTest {
    @Test
    public void testMap(){
        ArrayList<String> words = Lists.newArrayList("java8", "Lambdas", "in");
        List<Integer> collect = words.stream().map(String::length).collect(toList());
        Map<String, Integer> collect1 = words.stream().collect(Collectors.toMap(i -> i, i -> i.length()));
        log.info("words 长度是words:{}", ""+collect); //words 长度是:[5, 7, 2]
        log.info("collect1 map是:{}", collect1);//{Lambdas=7, in=2, java8=5}
    }
    @Test
    public void testMap2(){
        ArrayList<String> words = Lists.newArrayList("helloworld");
        List<String[]> collect2 = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        log.info("collect2 集合是:{}", JSONObject.toJSONString(collect2));//collect2 集合是:[["h","e","l","l","o","w","o","r","l","d"]]
    }
    @Test
    public void testFlatMap(){
        List<String> words = Lists.newArrayList("helloworld");
        List<String> collect = words.stream().map(str -> str.split("")).//将字符串通过“ ”分割成数组
                flatMap(Stream::of).//通过Stream.of将数组生成一个Stream，然后通过flatMap将多个Stream流对象合并成一个Stream流
                distinct().collect(toList());//去重输出
        log.info("collect 集合是:{}", JSONObject.toJSONString(collect));// collect 集合是:["h","e","l","o","w","r","d"]
    }
}
