//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.huihe.base_lib.model.base;

import java.util.List;

public class JsonRowsResult<T> {
    private int count;
    private List<T> rows;
    private int pageNo;
    private int pageSize;

    public void setCount(int count) {
        this.count = count;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getCount() {
        return count;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<T> getRows() {
        return rows;
    }
}
