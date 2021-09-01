package com.summit.comparator;

import com.summit.dao.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jeerper
 * @date: 2021-09-01 17:05:40
 */
public class compareTest {
    @Test
    public void compareTest() throws Exception {
        List<User> list = new ArrayList<>();
        list.add(new User("1","张飞",10.1));
        list.add(new User("1","张飞",11.1));
        list.add(new User("2","关羽",12.1));
        list.add(new User("2","曹操",13.1));
        list.add(new User("3","李明",14.1));
        list.add(new User("3","张三",15.1));
        list.sort(PinyinComparator.comparing(User::getName));
        list.forEach(n-> System.out.println(n));
    }
}
