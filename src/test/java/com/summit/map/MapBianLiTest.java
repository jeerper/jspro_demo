package com.summit.map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: jeerper
 * @date: 2021-05-24 11:03:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapBianLiTest {
    @Test
    public void testMap() throws Exception {
        Map<String,String> map=new HashMap<String,String>();
        map.put("username", "qq");
        map.put("passWord", "123");
        map.put("userID", "1");
        map.put("email", "qq@qq.com");
        bianLi_1(map);
    }

    /**
     * 第一种用for循环
     * @param map
     */
    public void bianLi_1(Map<String,String> map){
        for (Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry.getKey()+"--->"+entry.getValue());
        }
    }

    /**
     * 第二种用迭代
     * @param map
     */
    public void bianLi_2(Map<String,String> map){
        Set<Map.Entry<String, String>> set = map.entrySet();
        Iterator<Map.Entry<String, String>> i = set.iterator();
        while(i.hasNext()){
            Map.Entry<String, String> next = i.next();
            System.out.println(next.getKey()+"==>"+next.getValue());
        }
    }

    /**
     * 用keySet()迭代
     * @param map
     */
    public void bianLi_3(Map<String,String> map){
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()){
            String key = it.next();
            String value = map.get(key);
            System.out.println(key+"--"+value);
        }
    }

    /**
     * 用entrySet()迭代
     * @param map
     */
    public void bianLi_4(Map<String,String> map){
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            String key=entry.getKey().toString();
            String value=entry.getValue().toString();
            System.out.println(key+"===="+value);
        }
    }

}
