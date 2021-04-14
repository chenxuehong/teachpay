package com.huihe.base_lib.model;

public class GiftBean {

    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 1
     * gift_name : 见面礼
     * create_time : 2019-09-27 17:50:15
     * update_time : 2019-09-27 17:51:11
     * type :
     * amount : 10
     * discount_amout : 10
     * is_discount : false
     * status : 1
     * pic : http://www.huihejituan.com/tripPictstorage/qmore/gift/meeting.png
     * introduction :
     */

    private String id;
    private String gift_name;
    private String create_time;
    private String update_time;
    private String type;
    private int amount;
    private int discount_amout;
    private boolean is_discount;
    private int status;
    private String pic;
    private String introduction;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGift_name() {
        return gift_name;
    }

    public void setGift_name(String gift_name) {
        this.gift_name = gift_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDiscount_amout() {
        return discount_amout;
    }

    public void setDiscount_amout(int discount_amout) {
        this.discount_amout = discount_amout;
    }

    public boolean isIs_discount() {
        return is_discount;
    }

    public void setIs_discount(boolean is_discount) {
        this.is_discount = is_discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
