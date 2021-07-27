package com.summit.lambda;

import com.alibaba.fastjson.JSON;
import com.summit.dao.entity.User;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: jeerper
 * @date: 2021-05-28 14:49:26
 */
public class GroupingByTest {
    private List<User> users;
    public GroupingByTest() {
        users = new ArrayList<>();
        users.add(new User("1","张飞",10.1));
        users.add(new User("1","刘备",11.1));
        users.add(new User("2","关羽",12.1));
        users.add(new User("2","曹操",13.1));
        users.add(new User("3","李明",14.1));
        users.add(new User("3","张三",15.1));
    }
    @Test
    public void groupingByTest(){
        new GroupingByTest();
        List<User> newUsers = new ArrayList<>();
        users.parallelStream()
                .collect(Collectors.groupingBy(item -> item.getId(), Collectors.toList()))
                .forEach((id,transfer)->{
                    transfer.stream()
                            .reduce((a,b)->new User(a.getId(),a.getName(),
                                    emptyTransForm(a.getMoney()).add(emptyTransForm(b.getMoney())).doubleValue()))
                            .ifPresent(newUsers::add);
                });
        newUsers.stream()
                .sorted(Comparator.comparing(User::getId,Comparator.nullsFirst(String::compareTo))
                        .thenComparing(User::getName,Comparator.nullsFirst(String::compareTo)))
                .collect(Collectors.toList());
        System.out.println("users----->"+JSON.toJSONString(users));
        System.out.println("newUsers----->"+JSON.toJSONString(newUsers));
    }
    @Test
    public void groupingByTest2(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        long sum = list.stream().mapToInt(item -> StringUtils.isNotBlank(item) ? Integer.parseInt(item) : 0).summaryStatistics().getSum();
        System.out.println("sum----->"+JSON.toJSONString(sum));
        Integer sum2 = list.stream().map(item->StringUtils.isNotBlank(item) ? Integer.parseInt(item) : 0).reduce((x,y)->x+y).get();
        System.out.println("sum2----->"+JSON.toJSONString(sum2));
        Double sum3 =list.stream().map(item->StringUtils.isNotBlank(item) ? Double.parseDouble(item) : 0)
                .reduce((x,y)->emptyTransForm(x).add(emptyTransForm(y)).doubleValue())
                .get();
        System.out.println("sum3----->"+JSON.toJSONString(sum3));
    }
    @Test
    public void groupingByTest1(){
         List<User> users = new ArrayList<>();
        users.add(new User("1","张飞",10.1,12.1));
        users.add(new User("3","刘备",11.1,12.2));
        users.add(new User("3","关羽",12.1,12.4));
        users.add(new User("4","曹操",13.1,11.3));
        users.add(new User("5","李明",14.1,12.5));
        users.add(new User("6","张三",15.1,45.6));
        List<User> newUsers = new ArrayList<>();
//        users.parallelStream()
//                .collect(Collectors.groupingBy(item -> item.getId(), Collectors.toList()))
//                .forEach((id,transfer)->{
//                    transfer.stream()
//                            .reduce((a,b)->new User(a.getId(),a.getName(),
//                                    emptyTransForm(a.getMoney()).add(emptyTransForm(a.getMoney11())).doubleValue()))
//                            .ifPresent(newUsers::add);
//                });
//        newUsers.stream()
//                .sorted(Comparator.comparing(User::getId,Comparator.nullsFirst(String::compareTo))
//                .thenComparing(User::getName,Comparator.nullsFirst(String::compareTo)))
//                .collect(Collectors.toList());
        users.parallelStream()
                .collect(Collectors.groupingBy(item -> item.getId(), Collectors.toList()))
                .forEach((id,transfer)->{
                    transfer.stream()
                            .reduce((a,b)->new User(a.getId(),a.getName(),
                                    emptyTransForm(a.getMoney()).add(emptyTransForm(a.getMoney11())).doubleValue()))
                            .ifPresent(newUsers::add);
                });
        System.out.println("users----->"+JSON.toJSONString(users));
        System.out.println("newUsers----->"+JSON.toJSONString(newUsers));
    }
    private BigDecimal emptyTransForm(Double money){
        if (money==null){
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(money);
    }
}
