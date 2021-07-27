package com.summit.thread;

import com.summit.dao.entity.UserModel;
import com.summit.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: jeerper
 * @date: 2021-07-06 16:51:44
 */
public class CachedThreadPool {
    private static final Logger logger = LoggerFactory.getLogger(CachedThreadPool.class);
    @Autowired
    private UserService userService;
    @Test
    public void testCachedThreadPool_1(UserModel user) throws Exception {
        int poolCount= 10;
        ExecutorService cachedThreadPool=null;
        try{
            cachedThreadPool = Executors.newCachedThreadPool();
            CountDownLatch latch = new CountDownLatch(poolCount);
            for(int i = 0 ; i < poolCount ; i++){
                cachedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        userService.insertUserInfo(user);
                        latch.countDown();
                    }
                });
            }
            latch.await();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cachedThreadPool !=null){
                cachedThreadPool.shutdown();
            }
        }

    }
    @Test
    public void testCachedThreadPool_2() throws Exception {
        List<UserModel> users = userService.findUsers();
        ExecutorService pool = Executors.newCachedThreadPool();
        if (!ObjectUtils.isEmpty(users)){
            for(UserModel user : users){
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try{
                            logger.info("处理数据开始!");
                            insertUserInfo(user);
                            logger.info("处理数据结束!");
                        }catch (Exception e){
                            logger.warn("处理数据异常!");
                        }
                    }
                };
                pool.submit(runnable);//提交任务至线程池执行
            }
        }
        pool.shutdown();//线程池不再接收任何新任务，但此时线程池不会立即退出，直到添加到线程池的任务都已经处理完成，才会退出
        pool.awaitTermination(1, TimeUnit.HOURS);
        logger.info("所有数据处理完成");

    }
    private  void  insertUserInfo(UserModel user){
        userService.insertUserInfo(user);
    }

}
