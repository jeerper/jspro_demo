package com.summit.controller;

import com.summit.dao.entity.UserModel;
import com.summit.redis.RedisHelper;
import com.summit.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jeerper
 * @date: 2021-08-04 17:43:34
 */
@RestController
@RequestMapping("/redisTemplate")
public class RedisTemplateController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RedisHelper redisHelper;


    @RequestMapping(value = "/setKey",method = RequestMethod.GET)
    public Object test(String key , String value){
        try {
            redisHelper.setKeyForver(key,value);
            return  ResponseUtil.sucessObjectResponse("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.failedResponse("根据用户名查找用户失败", e.getMessage());
        }
    }
        @RequestMapping(value = "/getKey",method = RequestMethod.GET)
    public Object test(String key){
        try {
            String vaule = redisHelper.getKey(key);
            logger.info("Redis写入的值：{}",vaule);
            return  ResponseUtil.sucessObjectResponse(vaule);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.failedResponse("根据用户名查找用户失败", e.getMessage());
        }
    }
}
