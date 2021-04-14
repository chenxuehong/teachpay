package com.huihe.base_lib.model.personal;

import java.util.List;

public class SettledInfoEntity {
    public String type;
    public List<String> settledDetailList;

    public SettledInfoEntity(String type, List<String> settledDetailList) {
        this.type = type;
        this.settledDetailList = settledDetailList;
    }
}
