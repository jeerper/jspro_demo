package com.summit.lambda;

import com.summit.dao.entity.User;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author: jeerper
 * @date: 2021-06-08 11:31:01
 */
public class OptionalDemo {
    @Test
    public void optionalDemoTest(){
        List<String> names = null;
        Optional.ofNullable(names).ifPresent(list -> list.sort(Comparator.naturalOrder()));
        System.out.println(names);
        User user=null;
        String id = Optional.ofNullable(user).orElse(new User()).getId();
        System.out.println(id);
    }

}
