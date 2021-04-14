package com.huihe.base_lib.model.base;

import com.huihe.base_lib.model.BaseRespModel;

public class JsonResult<T> extends BaseRespModel {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}