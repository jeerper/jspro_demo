package com.summit.aop;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.summit.controller.UserController;
import com.summit.dao.entity.UserRoleModel;
import com.summit.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: jeerper
 * @since: 2020/12/23 17:04
 */
@Component
@Aspect
public class UserAspectHander {
    private static final Logger logger = LoggerFactory.getLogger(UserAspectHander.class);
    @Autowired
    private UserRoleService userRoleService;

    @Pointcut("@annotation(com.summit.annotation.UserSave)")
    public  void userSavePointCut(){
    }

    public void afterUserSave(JoinPoint joinPoint,  Object data){
        Object[] args = joinPoint.getArgs();
        if (args.length>0){
            JSONObject param = JSON.parseObject(JSONObject.toJSONString(data));
            String username = param.getString("username");
            if (StringUtils.isNotBlank(username)){
                List<UserRoleModel> userRolesList = userRoleService.getUserRolessByUserName(username);
                userRolesList.forEach(item -> System.out.println(item.getRoleCode()));
            }
        }
    }
}
