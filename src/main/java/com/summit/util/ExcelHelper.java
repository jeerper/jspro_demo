package com.summit.util;

import cn.hutool.core.io.FileUtil;
import com.summit.model.vo.Parameter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: jeerper
 * @date: 2021-05-15 14:41:33
 */
public class ExcelHelper {
    public static Workbook getWorkbook(MultipartFile file, InputStream inputStream) throws IOException {
        Workbook hssfWorkbook=null;
        String filename = file.getOriginalFilename();
        if (filename.endsWith("xls")){
            hssfWorkbook=new HSSFWorkbook(inputStream);
        }else if (filename.endsWith("xlsx")){
            hssfWorkbook=new XSSFWorkbook(inputStream);
        }
        return hssfWorkbook;
    }
    public static String getCellValue(Cell cell){
        String cellValue;
        if (cell==null){
            cellValue = "";
        }else {
            try{
                switch (cell.getCellType()){
                    case Cell.CELL_TYPE_STRING:
                        cellValue=cell.getStringCellValue().trim();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        cellValue = String.valueOf(cell.getNumericCellValue());
                        cellValue = new BigDecimal(cellValue).toPlainString();
                        if (cellValue.endsWith(".0")){
                            cellValue=cellValue.substring(0,cellValue.length()-2);
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        cellValue=String.valueOf(cell.getStringCellValue()).trim();
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        cellValue=cell.getCellFormula();
                        break;
                    default:
                        cellValue=cell.getStringCellValue().trim();
                }
            }catch (Exception e){
                e.printStackTrace();
                cellValue="";
            }
        }
        return  cellValue;
    }
    public static File exportExcelUtil(String rootPath, String template){
        String templatePath= rootPath + File.separator + template;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyymmddhhmmss");
        String templateXlxsPath = rootPath + File.separator + String.valueOf(formatter.format(new Date())) + ".xls";
        File templateXlxFile = new File(templateXlxsPath);
        /**
         * templatePath : 源目录
         * templateXlxFile : 目标文件
         */
        FileUtil.copy(new File(templatePath),templateXlxFile,false);
        return templateXlxFile;
    }

    /**
     * 导出
     * @param request
     * @param response
     * @param xmlPath xml模板
     * @param xlsPath xls模板
     * @param dtList  数据
     * @param nodeId
     * @param rootPath 导出模板路径
     */
    public static void  export(HttpServletRequest request, HttpServletResponse response,String xmlPath,String xlsPath,
                               List<List<?>> dtList,List<String> nodeId,String rootPath) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyymmddhhmmss");
        String tempExportFilePath = rootPath + File.separator + String.valueOf(formatter.format(new Date())) + ".xls";
        File tempFile = new File(tempExportFilePath);
        File outputFile = new File(xlsPath);
        FileUtil.copy(outputFile,tempFile,false);
        Parameter param = new Parameter();
        param.setDtList(dtList);
        param.setExcelRows(null);
        param.setNodeId(nodeId);
        param.setHeadData(null);
        param.setOutputFile(tempFile);
        param.setXmlPath(xmlPath);
        param.setModelFilePath(xlsPath);
        ExcelExportHelper.getInstance().exportExcelFile(param);
        commonStreamOperation(request,response,tempFile);
        FileUtil.del(tempFile);
    }

    /**
     * 导入、导出时的公共流操作
     */
    public static  void commonStreamOperation(HttpServletRequest request, HttpServletResponse response,File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ServletOutputStream os = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            response.reset();
            if (file.getName().endsWith("xlsx")){
                response.setContentType("application/application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            }
            if (file.getName().endsWith("txt")){
                response.setContentType("text/plain");
            }
            if (file.getName().endsWith("xls")){
                response.setContentType("application/vnd.ms-excel");
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
            int bytesRead=0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = bis.read(buffer)) != -1){
                bos.write(buffer, 0 , bytesRead);
            }
            bos.flush();
            bos.close();
            bis.close();
            os.flush();
            os.close();
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
