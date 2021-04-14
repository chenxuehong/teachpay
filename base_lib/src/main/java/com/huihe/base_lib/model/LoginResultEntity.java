package com.huihe.base_lib.model;

public class LoginResultEntity {

    private String userToken;
    private UserInfoEntity userInfoEntity;
    private String userSign;
    private String wx_openid;
    private boolean result;
    public String getUserToken() {
        return userToken;
    }

    public void setWx_openid(String wx_openid) {
        this.wx_openid = wx_openid;
    }

    public String getWx_openid() {
        return wx_openid;
    }

    public boolean isResult() {
        return result;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

}
