package com.summit.thread;

import com.summit.service.UserService;
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
    private  static final  String outPath="d:/importPah";
    @Autowired
    private static UserService userService;
    public static void main(String[] args) throws InterruptedException, IOException {
        runThread();
    }
    public static void runThread() throws InterruptedException {
        ExecutorService executorService=null;
        Map<String, File> ossUrlMap =new HashMap<>();
        int xjCount=10;
        try {
            executorService = Executors.newFixedThreadPool(xjCount);
            CountDownLatch countDownLatch = new CountDownLatch(xjCount);
            for (int j=0 ;j < xjCount;j++){
                ThreadTask task = new ThreadTask(userService,countDownLatch, outPath, ossUrlMap,j);
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
}
