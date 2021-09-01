package com.summit.listener;

import com.summit.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: jeerper
 * @date: 2021-09-01 11:16:58
 */
@Component
public class ApplicationrReadyEventListener implements ApplicationListener<ApplicationEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationrReadyEventListener.class);
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        new Thread(()->{
            try{
                logger.info("ApplicationrReadyEventListener------已经提前启动！");
            }catch (Exception e){
                logger.error("启动失败--->",e);
            }
        }).start();
    }
}
