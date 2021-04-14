package com.huihe.base_lib.model.login;

public class AccessTokenEntity {
    public String code;

    public AccessTokenEntity(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
