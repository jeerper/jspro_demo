package com.summit.model.vo;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author: jeerper
 * @date: 2021-05-15 17:01:38
 */
public class Parameter {
    private String xmlPath;
    private List<String> nodeId;
    private String modelFilePath;
    private File outputFile;
    private List<List<?>> dtList;
    private List<List<ExcelRow>> excelRows;
    private List<Map<String,Object>> headData;

    public Parameter() {
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public List<String> getNodeId() {
        return nodeId;
    }

    public void setNodeId(List<String> nodeId) {
        this.nodeId = nodeId;
    }

    public String getModelFilePath() {
        return modelFilePath;
    }

    public void setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public List<List<?>> getDtList() {
        return dtList;
    }

    public void setDtList(List<List<?>> dtList) {
        this.dtList = dtList;
    }

    public List<List<ExcelRow>> getExcelRows() {
        return excelRows;
    }

    public void setExcelRows(List<List<ExcelRow>> excelRows) {
        this.excelRows = excelRows;
    }

    public List<Map<String, Object>> getHeadData() {
        return headData;
    }

    public void setHeadData(List<Map<String, Object>> headData) {
        this.headData = headData;
    }
}
