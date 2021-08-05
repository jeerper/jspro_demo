package com.summit.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: jeerper
 * @date: 2021-08-05 15:21:01
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisSerializer fastJsonRedisSerializer(){
        return new FastJsonRedisSerializer<Object>(Object.class);
    }
    @Bean
    public RedisTemplate initRedisTemplate(LettuceConnectionFactory factory,RedisSerializer fastJsonRedisSerializer){
        //关闭共享链接
        factory.setShareNativeConnection(false);
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return  redisTemplate;
    }
}
