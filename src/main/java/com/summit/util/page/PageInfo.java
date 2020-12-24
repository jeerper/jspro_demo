package com.summit.util.page;

import com.summit.util.page.Page;

import java.util.Collections;
import java.util.List;

/**
 * @author: jeerper
 * @since: 2020/12/24 16:45
 */
public class PageInfo<T> {
    /* 状态 */
    private String code;

    /* 返回信息 */
    private String meaasge;

    /*数据列表 */
    private List<T> data= Collections.emptyList();

    /*总数 */
    private int total;

    /* 每页显示的条数 */
    private int size;

    /* 总页数   */
    private int pages;

    /*当前页 */
    private int current=1;

    private String error;

    public PageInfo(Page<T> page, String meaasge, String code) {
        this.code=code;
        this.meaasge=meaasge;
        this.data=page.getRecords();
        this.total=page.getTotal();
        this.size=page.getPageSize();
        this.pages=page.getPages();
        this.current=page.getCurrentPage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMeaasge() {
        return meaasge;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMeaasge(String meaasge) {
        this.meaasge = meaasge;
    }
}
