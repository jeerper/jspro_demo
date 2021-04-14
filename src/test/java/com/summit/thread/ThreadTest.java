package com.summit.thread;

import com.summit.model.ThreadModel;
import com.summit.util.UuidUtil;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: jeerper
 * @since: 2021/4/14 15:24
 */
public class ThreadTest {
    private  static final  String outPath="d:/importPah";
    //private  static final String fileName = "test.txt";
    public static void main(String[] args) throws InterruptedException, IOException {
        runThread();
        //File file = new File("d:/importPah/febd2e66b3b545e9b487a8f674dca664test.txt");
        // file = new File("D:/myfile.txt");
        // boolean newFile = file.createNewFile();
    }
    public static void runThread() throws InterruptedException {
        ExecutorService executorService=null;
        Map<String, File> ossUrlMap =new HashMap<>();
        //String localPath= basePath;
        int xjCount=10;
        try {
            executorService = Executors.newFixedThreadPool(xjCount);
            CountDownLatch countDownLatch = new CountDownLatch(xjCount);
            for (int j=0;j<xjCount;j++){
                ThreadModel task = new ThreadModel(countDownLatch, outPath, ossUrlMap);
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
