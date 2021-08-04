package com.summit.redisTemplate;

import com.summit.redis.RedisHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: jeerper
 * @date: 2021-08-04 17:28:32
 */
public class RedisTemplateTest {
    @Autowired
    private RedisHelper redisHelper;
    @Test
    public void RedisTest() throws Exception {
        redisHelper.setKey("aaaa","222");

    }
}
