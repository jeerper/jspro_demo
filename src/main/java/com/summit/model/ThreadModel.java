package com.summit.model;



import cn.hutool.core.io.FileUtil;
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
public class ThreadModel extends  Thread {
    private  static final Logger logger= LoggerFactory.getLogger(ThreadModel.class);
    private CountDownLatch count;
    private String outPath;
    private Map<String,File> ossUrlMap;
    private  static final String fileName = "test.txt";

    public ThreadModel(CountDownLatch count, String outPath, Map<String, File> ossUrlMap) {
        this.count = count;
        this.outPath = outPath;
        this.ossUrlMap = ossUrlMap;
    }

    public  void run(){
        try{
            String uuid = UuidUtil.getUuid();
       //     File file = FileUtil.file(outPath + File.separator + uuid + fileName);
            File file = new File(outPath + File.separator + uuid + fileName);
            if(file.createNewFile())
                System.out.println("文件创建成功！");
            else{
                System.out.println("出错了，该文件已经存在。");
            }
            ossUrlMap.put(uuid,file);
            count.countDown();
        }catch (Exception e){
            logger.error("-------异常---------",e);
        }
    }

}
