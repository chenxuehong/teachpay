package com.huihe.base_lib.model.event;

public class IMErrorCodeEvent {
    public int code;
    public String msg;

    public IMErrorCodeEvent(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
