package com.huihe.base_lib.model.base;


import com.huihe.base_lib.model.BaseRespModel;

import java.util.List;


public class JsonListResult<T> extends BaseRespModel {

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}