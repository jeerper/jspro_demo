package com.summit.util;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.summit.controller.UserController;
import com.summit.model.vo.Parameter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author: jeerper
 * @date: 2021-05-15 17:11:45
 */
public class ExcelExportHelper {
    public static  ExcelExportHelper helper = null;
    private static final Logger logger = LoggerFactory.getLogger(ExcelExportHelper.class);

    public ExcelExportHelper() {
    }
    public static synchronized ExcelExportHelper getInstance(){
        if (helper == null){
            helper= new ExcelExportHelper();
        }
        return  helper;
    }
    public void exportExcelFile(Parameter parameter) throws Exception {
        if (!parameter.getOutputFile().exists()){
            parameter.getOutputFile().createNewFile();
        }else {
            parameter.getOutputFile().delete();
            parameter.getOutputFile().createNewFile();
        }
        InputStream inp=null;
        FileOutputStream fileOut = null;
        Workbook wb=null;
        try{
            File modelFile = new File(parameter.getModelFilePath());
            inp=new FileInputStream(modelFile);
            wb = WorkbookFactory.create(inp);
            /**
             * 未写完
             */
            for (int i = 0 ; i< wb.getNumberOfSheets(); ++i ){

            }

        }catch (FileNotFoundException e){
           logger.error("FileNotFoundException",e);
        }catch (InvalidFormatException e){
            logger.error("InvalidFormatException",e);
        }catch (IOException e){
            logger.error("IOException",e);
        }finally {
            if (fileOut !=null){
                fileOut.close();
            }
            if (inp !=null){
                inp.close();
            }
        }

    }
}
