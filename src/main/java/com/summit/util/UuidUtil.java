package com.summit.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author: jeerper
 * @since: 2021/4/14 14:55
 */
public class UuidUtil {
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
