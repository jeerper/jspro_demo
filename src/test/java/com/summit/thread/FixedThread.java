package com.summit.thread;

import com.alibaba.fastjson.JSON;
import com.summit.dao.entity.UserModel;
import com.summit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: jeerper
 * @since: 2021/4/14 15:24
 */
public class FixedThread {
    private static final Logger logger = LoggerFactory.getLogger(FixedThread.class);
    private  static final  String outPath="d:/importPah";
    @Autowired
    private static UserService userService;
    private static final int THREAD_NUM = 500;
    public static void main(String[] args) throws InterruptedException, IOException {
        runThread();
        UserModel userModel = new UserModel();
        asynchronousDeal(userModel);
    }
    public static void runThread(){
        ExecutorService executorService=null;
        Map<String, File> ossUrlMap =new HashMap<>();
        try {
            executorService = Executors.newFixedThreadPool(THREAD_NUM);
            CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
            for (int i=0 ; i < THREAD_NUM ; i++){
                ThreadTask task = new ThreadTask(userService,countDownLatch, outPath, ossUrlMap,i);
                executorService.execute(task);
            }
            countDownLatch.await();
        }catch (Exception e){
        }finally {
            //关闭线程池
            if (executorService !=null){
                executorService.shutdown();
            }
        }
        ossUrlMap.values().stream().forEach(item->{
            System.out.println(item.getName());
        });

    }
    public static void asynchronousDeal(UserModel userModel){
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUM);
        try{
            threadPool.execute(()->{
                createUser(userModel);
            });
        }catch (Exception e){
            logger.warn("异步处理数据异常",e);
        }finally {
            threadPool.shutdown();
        }
    }
    private static void createUser(UserModel userModel){
        userService.insertUserInfo(userModel);
        logger.info("异步创建的用户成功，创建的用户为：{}", JSON.toJSONString(userModel));
    }
}
