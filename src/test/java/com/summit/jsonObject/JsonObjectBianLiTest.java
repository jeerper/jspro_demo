package com.summit.jsonObject;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Iterator;
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
}
