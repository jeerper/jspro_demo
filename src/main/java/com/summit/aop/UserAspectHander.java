package com.summit.aop;

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
}
