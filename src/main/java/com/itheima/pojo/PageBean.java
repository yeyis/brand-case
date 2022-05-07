package com.itheima.pojo;

import java.util.List;

public class PageBean<T> {
    private int totalSize;
    private List<T> rows;

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
