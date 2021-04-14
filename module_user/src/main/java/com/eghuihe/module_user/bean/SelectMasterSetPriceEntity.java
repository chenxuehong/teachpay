package com.eghuihe.module_user.bean;

import com.huihe.base_lib.model.MasterSetPriceEntity;

public class SelectMasterSetPriceEntity {
    public MasterSetPriceEntity masterSetPriceEntity;
    public boolean isSelected;

    public SelectMasterSetPriceEntity(MasterSetPriceEntity masterSetPriceEntity, boolean isSelected) {
        this.masterSetPriceEntity = masterSetPriceEntity;
        this.isSelected = isSelected;
    }

    public MasterSetPriceEntity getMasterSetPriceEntity() {
        return masterSetPriceEntity;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
