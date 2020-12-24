package com.summit.aop;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: jeerper
 * @since: 2020/12/23 17:04
 */
@Component
@Aspect
public class UserAspectHander {
    @Pointcut("@annotation(com.summit.annotation.UserSave)")
    public  void userSavePointCut(){
    }

    public void afterUserSave(JoinPoint joinPoint,  Object data){
        Object[] args = joinPoint.getArgs();
        if (args.length>0){
            JSONObject param = JSON.parseObject(JSONObject.toJSONString(data));
            String username = param.getString("username");
            if (StringUtils.isNotBlank(username)){

            }
        }
    }
}
