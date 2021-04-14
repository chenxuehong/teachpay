package com.huihe.base_lib.model.personal;

public class IdentifyEntify {

    public int identifyType; // 0:实名认证，1：身份认证，2：外教认证

    public IdentifyEntify(int identifyType) {
        this.identifyType = identifyType;
    }
}
