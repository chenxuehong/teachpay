package com.huihe.base_lib.model;

import java.util.List;

public class MutiLanguageEntity {
    public List<String> languageList;
    public String identity_type;

    public String getIdentity_type() {
        return identity_type;
    }

    public MutiLanguageEntity(List<String> languageList, String identity_type) {
        this.languageList = languageList;
        this.identity_type = identity_type;
    }

    public List<String> getLanguageList() {
        return languageList;
    }
}
