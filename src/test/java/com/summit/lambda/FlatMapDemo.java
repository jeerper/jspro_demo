package com.summit.lambda;

import com.alibaba.fastjson.JSON;
import com.summit.dao.entity.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: jeerper
 * @date: 2021-06-08 10:25:31
 */
public class FlatMapDemo {
    @Test
    public void flatMapDemoTest1(){
        List<String> longCodes = new ArrayList<>();
        longCodes.add("5559f2a9b7124fbcbf7282f312c50b7d|34bb19c9c0bb4f3c9a3001f5a4270c6b|bb6a769f1b9c4fa59a0a56c32c3f8f33");
        longCodes.add("fb8eff1c3dc846179369cddc48fd8bf4|136b073c7e3e469ab42e5624f2456f71");
        longCodes.add("9da7edbbbd7c4d43aed4ff467f47fbf1");
        List<String> pLedgerIds = longCodes.stream().flatMap(item -> Arrays.stream(item.split("\\|"))).distinct().collect(Collectors.toList());
        System.out.println("-----pLedgerIds----"+ JSON.toJSONString(pLedgerIds));
        List<String> ledgerIds = pLedgerIds.stream().filter(item -> {
            if ("5559f2a9b7124fbcbf7282f312c50b7d".equals(item) || "34bb19c9c0bb4f3c9a3001f5a4270c6b".equals(item)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        System.out.println("-----ledgerIds----"+ JSON.toJSONString(ledgerIds));
        ListIterator<String> iterator = ledgerIds.listIterator();
        while (iterator.hasNext()){
            String temp = iterator.next();
            System.out.println("---temp---"+temp);
        }
    }

    @Test
    public void flatMapDemoTest2(){
        List<User> users = new ArrayList<>();
        users.add(new User("1","张飞",10.1));
        users.add(new User("1","张飞",11.1));
        users.add(new User("2","关羽",12.1));
        users.add(new User("2","曹操",13.1));
        users.add(new User("3","李明",14.1));
        users.add(new User("3","张三",15.1));
        Map<String, List<User>> userMap = users.stream().collect(Collectors.groupingBy(User::getId));
        System.out.println("---userMap---"+JSON.toJSONString(userMap));
        List<String> names = userMap.values().stream().flatMap(item -> item.stream().map(User::getName).distinct()).collect(Collectors.toList());
//        List<String> names = userMap.values().stream().flatMap(item -> {
//            Stream<String> stream = item.stream().map(User::getName);
//            return  stream;
//        }).collect(Collectors.toList());
//        List<String> names = userMap.values().stream().flatMap(item->{
//            //List<String> nameList = item.stream().map(User::getName).collect(Collectors.toList());
//            List<String> nameList = item.stream().map(a->{
//                return a.getName();
//            }).collect(Collectors.toList());
//            String[] nameData = nameList.toArray(new String[nameList.size()]);
//            Stream<String> stream = Arrays.stream(nameData);
//            return stream;
//        }).collect(Collectors.toList());
        System.out.println("---names---"+JSON.toJSONString(names));
//        ---userMap---{"1":[{"id":"1","money":10.1,"name":"张飞"},{"id":"1","money":11.1,"name":"刘备"}],"2":[{"id":"2","money":12.1,"name":"关羽"},{"id":"2","money":13.1,"name":"曹操"}],"3":[{"id":"3","money":14.1,"name":"李明"},{"id":"3","money":15.1,"name":"张三"}]}
//        ---names---["张飞","刘备","关羽","曹操","李明","张三"]

    }
}
