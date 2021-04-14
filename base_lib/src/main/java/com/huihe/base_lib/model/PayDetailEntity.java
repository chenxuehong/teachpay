package com.huihe.base_lib.model;

import java.util.List;

public class PayDetailEntity {
    private String originalPrice;
    private Long points;
    private List<UserCouponEntity> userCouponEntities;

    public Long getPoints() {
        return points;
    }

    public List<UserCouponEntity> getUserCouponEntities() {
        return userCouponEntities;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }
}
