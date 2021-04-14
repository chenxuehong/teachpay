package com.huihe.base_lib.model;

public class UserCouponEntity {

    private String id;
    private String user_id;
    private String coupon_id;
    private String create_time;
    private String status;
    private String type;
    private String overdue_time;
    private String course_num;
    private Object coupon_code;
    private String coupon_list_id;
    private String appointment_id;
    private String recharge_id;
    private String mechanism_id;
    private String coup_name;
    private String discount;
    private String cash;
    private Boolean isChecked;
    private Map map;

    public String getCash() {
        return cash;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public static class Map{
        private CommodityCouponEntity commodityCouponEntity;

        public CommodityCouponEntity getCommodityCouponEntity() {
            return commodityCouponEntity;
        }
    }

    public Map getMap() {
        return map;
    }

    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getOverdue_time() {
        return overdue_time;
    }

    public String getCourse_num() {
        return course_num;
    }

    public Object getCoupon_code() {
        return coupon_code;
    }

    public String getCoupon_list_id() {
        return coupon_list_id;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public String getRecharge_id() {
        return recharge_id;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public String getCoup_name() {
        return coup_name;
    }

    public String getDiscount() {
        return discount;
    }
}
