package com.summit.thread;

import com.summit.dao.entity.UserModel;
import com.summit.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: jeerper
 * @date: 2021-07-06 16:51:44
 */
public class CachedThreadPool {
    @Autowired
    private UserService userService;
    @Test
    public void testCachedThreadPool(UserModel user) throws Exception {
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
}
