package com.summit.lambda;

import com.alibaba.fastjson.JSON;
import com.summit.dao.entity.User;
import org.junit.Test;

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
    private BigDecimal emptyTransForm(Double money){
        if (money==null){
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(money);
    }
}
