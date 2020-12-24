package com.summit.util.page;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author: jeerper
 * @since: 2020/12/24 15:47
 */
public class Page<T> extends RowBounds implements Serializable {
    private static final long serialVersionUID = -476864763822107492L;
    private int total;
    private int pageSize=10;
    private int pages;
    private int currentPage=1;
    private String orderByField;
    private boolean isAsc = true;
    private List<T> records= Collections.emptyList();

    public Page() {
    }
    public Page(int currentPage, int pageSize) {
        super(offsetCurrent(currentPage,pageSize),pageSize);
        if (currentPage>1){
            this.pageSize = pageSize;
        }
        this.currentPage = currentPage;
    }
    protected static int offsetCurrent(int current,int size){
        return current>0 ? (current-1)*size : 0;
    }
    public  int getOffsetCurrent(){
        return offsetCurrent(this.currentPage,this.pageSize);
    }

    public int getTotal() {
        return this.total;
    }

    public Page setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public Page setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPages() {
        if (this.pages==0){
            return 0;
        }else {
            this.pages=this.total/this.pageSize;
            if (this.total % this.pageSize != 0){
                ++this.pages;
            }
            return pages;
        }

    }

    public Page setPages(int pages) {
        this.pages = pages;
        return this;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public Page setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public String getOrderByField() {
        return this.orderByField;
    }

    public Page setOrderByField(String orderByField) {
        if (StringUtils.isNotEmpty(orderByField)){
            this.orderByField = orderByField;
        }
        return this;
    }

    public boolean isAsc() {
        return this.isAsc;
    }

    public Page setAsc(boolean asc) {
        this.isAsc = asc;
        return this;
    }

    public List<T> getRecords() {
        return this.records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Page{" +
                "total=" + total +
                ", pageSize=" + pageSize +
                ", pages=" + pages +
                ", currentPage=" + currentPage +
                ", orderByField='" + orderByField + '\'' +
                ", isAsc=" + isAsc +
                ", records=" + records +
                '}';
    }
}
