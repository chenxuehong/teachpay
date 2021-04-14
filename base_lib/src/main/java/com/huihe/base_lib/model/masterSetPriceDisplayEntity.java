package com.huihe.base_lib.model;

public class masterSetPriceDisplayEntity {

    /**
     * id : 6
     * create_time : 2021-03-03 14:39:36
     * update_time : 2021-03-04 13:49:29
     * master_set_price_id : 1
     * live_streaming_id : 22
     * status : 2
     * sorted : 1
     * mechanism_id : 26
     * master_set_price_ids : null
     * pic :
     * live_stream_prices : null
     * original_price : 0
     * titles : null
     * live_stream_price : 1111
     * earnings : 0
     * title : [1节]标题1
     * living_single_session_price : 0
     * living_single_session_prices : null
     * is_live_streaming : false
     */

    private String id;
    private String create_time;
    private String update_time;
    private String master_set_price_id;
    private String live_streaming_id;
    private String status;
    private Long sorted;
    private String mechanism_id;
    private String pic;
    private String original_price;
    private Object titles;
    private String live_stream_price;
    private String earnings;
    private String title;
    private String course_num;
    private String living_single_session_price;
    private boolean is_live_streaming;

    public String getCourse_num() {
        return course_num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMaster_set_price_id() {
        return master_set_price_id;
    }

    public void setMaster_set_price_id(String master_set_price_id) {
        this.master_set_price_id = master_set_price_id;
    }

    public String getLive_streaming_id() {
        return live_streaming_id;
    }

    public void setLive_streaming_id(String live_streaming_id) {
        this.live_streaming_id = live_streaming_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSorted() {
        return sorted;
    }

    public void setSorted(Long sorted) {
        this.sorted = sorted;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public void setMechanism_id(String mechanism_id) {
        this.mechanism_id = mechanism_id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public Object getTitles() {
        return titles;
    }

    public void setTitles(Object titles) {
        this.titles = titles;
    }

    public String getLive_stream_price() {
        return live_stream_price;
    }

    public void setLive_stream_price(String live_stream_price) {
        this.live_stream_price = live_stream_price;
    }

    public String getEarnings() {
        return earnings;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLiving_single_session_price() {
        return living_single_session_price;
    }

    public void setLiving_single_session_price(String living_single_session_price) {
        this.living_single_session_price = living_single_session_price;
    }

    public boolean isIs_live_streaming() {
        return is_live_streaming;
    }

    public void setIs_live_streaming(boolean is_live_streaming) {
        this.is_live_streaming = is_live_streaming;
    }
}
