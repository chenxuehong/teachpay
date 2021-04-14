package com.huihe.base_lib.model;

public class UseCouponParam {
    public String id;
    public String master_set_price_id;
    public String user_id;

    public UseCouponParam(String id, String master_set_price_id, String user_id) {
        this.id = id;
        this.master_set_price_id = master_set_price_id;
        this.user_id = user_id;
    }
}
