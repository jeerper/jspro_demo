package com.summit.thread;

import com.alibaba.fastjson.JSON;
import com.summit.dao.entity.UserModel;
import com.summit.lambda.MapOrFlatMapTest;
import com.summit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * @author: jeerper
 * @date: 2021-07-30 11:34:52
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ThreadPoolExecutorDemo {

    private static final Logger logger = LoggerFactory.getLogger(MapOrFlatMapTest.class);

    /**
     * ThreadPoolTaskExecutor线程是Spring的线程池，其底层是依据JDK线程池ThreadPoolExecutor来实现的。
     */
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private UserService userService;

    @Test
    public void handleTask() throws InterruptedException {
        for(int i = 0 ; i < 5 ; i++){
            threadPoolTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 工作开始！");
                        Thread.sleep((long) (Math.random() * 2000));
                        System.out.println(Thread.currentThread().getName() + " 工作结束！");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    Thread.sleep(3000);
                }
            });
        }
        int n = Runtime.getRuntime().availableProcessors();//获取到服务器的cpu内核
        logger.info("服务器的cpu内核:{}", n);
        Thread.sleep(5000);
        System.out.println("主线程工作结束！");
        threadPoolTaskExecutor.shutdown();
    }

}
