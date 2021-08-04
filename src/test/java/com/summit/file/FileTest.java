package com.summit.file;

import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.summit.JsproDemoApplication;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @author: jeerper
 * @date: 2021-08-02 11:11:46
 */
public class FileTest {
    @Test
    public void FileTest() throws Exception {
        String filePath = new StringBuilder()
                .append(SystemUtil.getUserInfo().getCurrentDir())
                .append(File.separator)
                .append(JsproDemoApplication.TestFile)
                .toString();
        saveBigData(filePath,"hello world");
        readBigData(filePath);
    }

    private void saveBigData (String filePath, String content){
        OutputStreamWriter outputStream = null;
        BufferedWriter bufferedWriter = null;
        try{
            File file = new File(filePath);
            if (!file.exists()){
                file.mkdir();
            }
            String path = filePath + File.separator + JsproDemoApplication.FileName;
            outputStream= new OutputStreamWriter(new FileOutputStream(path));
            bufferedWriter = new BufferedWriter(outputStream);
            bufferedWriter.write(content);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                outputStream.flush();
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void readBigData(String filePath){
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        try{
            if (file.isFile()){
                FileReader fr = new FileReader(filePath);
                BufferedReader br = new BufferedReader(fr);
                char[] chs= new char[2048];
                int readLength;
                while (true){
                    readLength = br.read(chs);
                    if (readLength == -1){
                        break;
                    }
                    sb.append(chs,0,readLength);
                }
                br.close();
                fr.close();
                String content = sb.toString();
                System.out.println(content);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
