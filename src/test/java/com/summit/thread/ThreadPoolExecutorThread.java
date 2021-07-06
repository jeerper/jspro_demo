package com.summit.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.summit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author: jeerper
 * @date: 2021-05-12 11:41:57
 */
public class ThreadPoolExecutorThread {
    private  static final  String outPath="d:/importPah";
    public static void main(String[] args) throws InterruptedException, IOException {
        handleTask();
    }
    private static void handleTask() {
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("change-pool-%d").build();
        ThreadPoolExecutor pool=null;
        Map<String, File> ossUrlMap =new HashMap<>();
        int xjCount=10;
        try {
            pool = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingDeque<>(1), nameThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//            pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
//                    Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
//                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                    System.out.println(r.toString()+"执行了拒绝策略");
//
//                }
//            });
            CountDownLatch countDownLatch = new CountDownLatch(xjCount);
            for (int j=0 ;j < xjCount;j++){
                ThreadTask task = new ThreadTask(countDownLatch, outPath, ossUrlMap,j);
                pool.execute(task);
            }
            countDownLatch.await();
        }catch (Exception e){
        }finally {
            //关闭线程池
            if (pool !=null){
                pool.shutdown();
            }
        }
        ossUrlMap.values().stream().forEach(item->{
            System.out.println(item.getName());
        });
        Set<Map.Entry<String, File>> entries = ossUrlMap.entrySet();
        entries.stream().forEach(item->{
            String key = item.getKey();
            File value = item.getValue();

        });
        for (Map.Entry<String, File>  entry : ossUrlMap.entrySet()){

        }
    }
}
