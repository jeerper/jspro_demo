package com.summit.map;

import com.summit.dao.entity.UserModel;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: jeerper
 * @since: 2021/4/15 14:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapTest {

    @Test
    public void testMap() throws Exception {
        UserModel user = new UserModel();
        Map<String, Object> strtMap = objetToMap(user);
        for (String mp : strtMap.keySet()) {
            System.out.println("key = " + mp + ", value = " + strtMap.get(mp));
        }
//        for (Map.Entry en : strtMap.entrySet()) {
//            System.out.println("Key = " + en.getKey() + ", Value = " + en.getValue());
//        }
        boolean allFieldNull = isAllFieldNull(user);
        System.out.println("allFieldNull-->"  + allFieldNull);


        String a = "实验室主任";
        int i = a.indexOf("实验室主任");
        if (i != -1){
            System.out.println("aaa");
        }
    }

    /**
     * obj 转 map
     * @param obj
     * @return
     * @throws Exception
     */
    public Map<String,Object> objetToMap(Object obj) throws Exception {
        Map<String,Object> reMap = new HashMap<>();
        if (obj==null){
            return null;
        }
        Class<?> objClass = obj.getClass();
        while (objClass !=null){
            Field[] fields = objClass.getDeclaredFields();
            for (int i=0;i< fields.length;i++){
                Field f = objClass.getDeclaredField(fields[i].getName());
                f.setAccessible(true);
                Object o = f.get(obj);
                reMap.put(fields[i].getName(),o);
            }
            objClass = objClass.getSuperclass();
        }
        return reMap;
    }

    public boolean isAllFieldNull(Object obj) throws Exception {
        if (obj==null){
            return true;
        }
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for (int i=0;i< fields.length;i++){
            Field f = objClass.getDeclaredField(fields[i].getName());
            f.setAccessible(true);
            Object o = f.get(obj);
            if (null != o){
                if (o instanceof String){
                    if (StringUtils.isNotBlank((String) o)){
                        return false;
                    }else {
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

}
