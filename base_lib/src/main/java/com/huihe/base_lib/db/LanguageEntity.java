package com.huihe.base_lib.db;

import com.huihe.base_lib.ui.widget.indexBar.bean.BaseIndexPinyinBean;
import com.huihe.base_lib.utils.MultiLanguageUtil;

public class LanguageEntity extends BaseIndexPinyinBean {

    public String code;
    public String key;
    public String value;
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LanguageEntity(String code, String key, String value) {
        this.code = code;
        this.key = key;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getTarget() {

        if (MultiLanguageUtil.getInstance().isZh()) {
            return value;
        }
        return code;
    }
}
