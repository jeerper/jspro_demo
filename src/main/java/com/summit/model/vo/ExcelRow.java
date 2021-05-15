package com.summit.model.vo;

/**
 * @author: jeerper
 * @date: 2021-05-15 17:05:07
 */
public class ExcelRow {
    private boolean tree;
    private int level;
    private boolean detail;

    public ExcelRow() {
    }

    public boolean isTree() {
        return tree;
    }

    public void setTree(boolean tree) {
        this.tree = tree;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }
}
