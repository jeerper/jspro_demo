package com.summit.jsonObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: jeerper
 * @date: 2021-05-28 14:34:47
 */
public class JsonObjectBianLiTest {
    @Test
    public void bianLiTest() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a1","test1");
        jsonObject.put("a2","test2");
        jsonObject.put("a3","test3");
        Iterator riter = jsonObject.entrySet().iterator();
        while (riter.hasNext()) {
            Map.Entry<String,Object> rentry = (Map.Entry<String,Object>) riter.next();
            String rkey = rentry.getKey().toString();
            String rValue = rentry.getValue().toString();
            System.out.println("rkey---->"+rkey);
            System.out.println("rValue---->"+rValue);
        }

    }
    @Test
    public void removeList() throws Exception {
       List<String> names = new ArrayList<>();
       names.add("aaa");
       names.add("abc");
       names.add("cc");
       System.out.println(JSON.toJSONString(names));
       names.remove("cc");
       System.out.println(JSON.toJSONString(names));
       System.out.println(names.contains("abc"));
       System.out.println(names.indexOf("a")!=-1);
    }
    @Test
    public void removeJsonKey() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aaa",1);
        jsonObject.put("bb",1);
        jsonObject.put("cc",1);
        System.out.println(JSON.toJSONString(jsonObject));
        jsonObject.remove("aaa");
        System.out.println(JSON.toJSONString(jsonObject));
    }
}
