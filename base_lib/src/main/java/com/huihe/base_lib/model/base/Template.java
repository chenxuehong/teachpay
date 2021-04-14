package com.huihe.base_lib.model.base;

import com.huihe.base_lib.ui.activity.BaseActivity;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Template {
    private String title;
    private int res;
    private String describe;
    private Class clazz;
    private String url;//模块外跳转链接
    private int type;//0.本模块内Activity跳转 1.业务模块跳转 2.应用外跳转[WebView]
    private Map<String, ?> params;//其他附加参数

    public String getTitle() {
        return title;
    }

    public int getRes() {
        return res;
    }

    public String getDescribe() {
        return describe;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getUrl() {
        return url;
    }

    public int getType() {
        return type;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public Template(String title, int res, String url, int type, String describe) {
        this.title = title;
        this.res = res;
        this.url = url;
        this.type = type;
        this.describe = describe;
    }

    public Template(String title, int res, Class<? extends BaseActivity> clazz) {
        this(title, res, clazz, "");
    }

    public Template(String title, int res, Class<? extends BaseActivity> clazz, String describe) {
        this.title = title;
        this.res = res;
        this.clazz = clazz;
        this.describe = describe;
    }
}
