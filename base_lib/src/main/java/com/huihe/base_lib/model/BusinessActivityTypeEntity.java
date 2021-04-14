package com.huihe.base_lib.model;

public class BusinessActivityTypeEntity {


    /**
     * id : 4
     * type : single_payment
     * name : 单节付
     * title :
     * status : 2
     * create_time : 2021-01-25 19:29:41
     * update_time : 2021-01-27 13:14:20
     * start_time : 2021-01-25 19:29:41
     * end_time : 2021-01-25 19:29:41
     * is_new_customers : true
     * coupon_time : 6
     * tags :
     * background_image : https://w.wallhaven.cc/full/72/wallhaven-7232p9.jpg
     * activity_description :
     * source :
     * url :
     * number_of_people : 0
     * each_time_percentage : 0
     * each_time_percentage_max : 0
     */

    private String id;
    private String type;
    private String name;
    private String title;
    private int status;
    private String create_time;
    private String update_time;
    private String start_time;
    private String end_time;
    private boolean is_new_customers;
    private String coupon_time;
    private String tags;
    private String background_image;
    private String activity_description;
    private String source;
    private String url;
    private String discount_amount;
    private String number_of_people;
    private String each_time_percentage;
    private String each_time_percentage_max;

    public String getDiscount_amout() {
        return discount_amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public boolean isIs_new_customers() {
        return is_new_customers;
    }

    public void setIs_new_customers(boolean is_new_customers) {
        this.is_new_customers = is_new_customers;
    }

    public String getCoupon_time() {
        return coupon_time;
    }

    public void setCoupon_time(String coupon_time) {
        this.coupon_time = coupon_time;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getActivity_description() {
        return activity_description;
    }

    public void setActivity_description(String activity_description) {
        this.activity_description = activity_description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNumber_of_people() {
        return number_of_people;
    }

    public void setNumber_of_people(String number_of_people) {
        this.number_of_people = number_of_people;
    }

    public String getEach_time_percentage() {
        return each_time_percentage;
    }

    public void setEach_time_percentage(String each_time_percentage) {
        this.each_time_percentage = each_time_percentage;
    }

    public String getEach_time_percentage_max() {
        return each_time_percentage_max;
    }

    public void setEach_time_percentage_max(String each_time_percentage_max) {
        this.each_time_percentage_max = each_time_percentage_max;
    }
}
