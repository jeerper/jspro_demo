package com.summit.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: jeerper
 * @date: 2021-08-04 16:37:37
 */
@Component
public class RedisHelper {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入一个键值对带失效时间
     * @param key
     * @param vaule
     * @param timeout
     */
    public void serKey2Expire(String key, String vaule,long timeout){
        redisTemplate.opsForValue().set(key,vaule);
        redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
    }

    /**
     * 写入一个键值对
     * @param key
     * @param o
     */
    public void setKey(String key,Object o){
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        ops.set(key,o.toString(),60,TimeUnit.MINUTES);
    }

    /**
     * 写入一个键值对
     * @param key
     * @param o
     */
    public void setKeyForver(String key,Object o){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(key,o.toString());
    }

    /**
     * 通过键获取值
     * @param key
     * @return
     */
    public String getKey(String key){
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        String value = ops.get(key);
        return value;
    }

    /**
     * 删除一个键值对
     * @param key
     */
    public void delKey(String key){
        redisTemplate.delete(key);
    }
}
