package com.summit.thread;



import com.summit.dao.entity.UserModel;
import com.summit.service.UserService;
import com.summit.util.ApplicationContextProvider;
import com.summit.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


/**
 * @author: jeerper
 * @since: 2021/4/14 14:43
 */
public class ThreadTask extends  Thread {
    private  static final Logger logger= LoggerFactory.getLogger(ThreadTask.class);
    private CountDownLatch count;
    private String outPath;
    private Map<String,File> ossUrlMap;
    private  static final String fileName = "test.txt";
    private int j;
    private UserService userService;

    public ThreadTask(UserService userService,CountDownLatch count, String outPath, Map<String, File> ossUrlMap, int j) {
        this.count = count;
        this.outPath = outPath;
        this.ossUrlMap = ossUrlMap;
        this.j = j;
        this.userService = userService;
    }
    public ThreadTask(CountDownLatch count, String outPath, Map<String, File> ossUrlMap, int j) {
        this.count = count;
        this.outPath = outPath;
        this.ossUrlMap = ossUrlMap;
        this.j = j;
        this.userService= ApplicationContextProvider.getBean(UserService.class);
    }
    public  void run(){
        try{
            //处理逻辑开始
            UserModel user = new UserModel();
            userService.insertUserInfo(user);
            //处理逻辑结束
            Thread.sleep(1000);
            System.out.println("ThreadName:"+Thread.currentThread().getName());
            String uuid = UuidUtil.getUuid();
       //     File file = FileUtil.file(outPath + File.separator + uuid + fileName);
            File file = new File(outPath + File.separator + uuid + fileName);
            if(file.createNewFile())
                System.out.println("文件创建成功！");
            else{
                System.out.println("出错了，该文件已经存在。");
            }
            System.out.println("j--------->"+j);
            ossUrlMap.put(uuid,file);
            count.countDown();
        }catch (Exception e){
            logger.error("-------异常---------",e);
        }
    }

}
