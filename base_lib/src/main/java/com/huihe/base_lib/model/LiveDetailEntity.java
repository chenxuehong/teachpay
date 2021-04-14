package com.huihe.base_lib.model;

import com.huihe.base_lib.model.personal.MasterMechanismModel;

public class LiveDetailEntity {

    /**
     * id : 63
     * create_time : 2021-03-09 10:44:55
     * update_time : 2021-03-09 10:44:55
     * status : 2
     * master_set_price_id : 0
     * master_set_price_ids : null
     * pic :
     * start_time : 2021-03-09 10:44:55
     * end_time : 2021-03-09 10:44:55
     * room_id : 1400255047__4
     * earnings : 0
     * title : 解决了
     * titles : null
     * mechanism_id : 4
     * click_num : 0
     * live_stream_price : 0
     * living_single_session_price : null
     * living_single_session_prices : null
     * live_stream_prices : null
     * master_id : 0
     * display_id : null
     */

    private String id;
    private String create_time;
    private String update_time;
    private String status;
    private String master_set_price_id;
    private String pic;
    private String start_time;
    private String end_time;
    private String room_id;
    private String earnings;
    private String title;
    private String mechanism_id;
    private String click_num;
    private String live_stream_price;
    private String living_single_session_price;
    private String living_single_session_prices;
    private String live_stream_prices;
    private String master_id;
    private String display_id;
    private Map map;

    public Map getMap() {
        return map;
    }

    public static class Map{
        private MasterMechanismModel.MasterMechanismEntity MasterMechanismEntity;

        public MasterMechanismModel.MasterMechanismEntity getMasterMechanismEntity() {
            return MasterMechanismEntity;
        }
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaster_set_price_id() {
        return master_set_price_id;
    }

    public void setMaster_set_price_id(String master_set_price_id) {
        this.master_set_price_id = master_set_price_id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
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

    public String getMechanism_id() {
        return mechanism_id;
    }

    public void setMechanism_id(String mechanism_id) {
        this.mechanism_id = mechanism_id;
    }

    public String getClick_num() {
        return click_num;
    }

    public void setClick_num(String click_num) {
        this.click_num = click_num;
    }

    public String getLive_stream_price() {
        return live_stream_price;
    }

    public void setLive_stream_price(String live_stream_price) {
        this.live_stream_price = live_stream_price;
    }

    public String getLiving_single_session_price() {
        return living_single_session_price;
    }

    public void setLiving_single_session_price(String living_single_session_price) {
        this.living_single_session_price = living_single_session_price;
    }

    public String getLiving_single_session_prices() {
        return living_single_session_prices;
    }

    public void setLiving_single_session_prices(String living_single_session_prices) {
        this.living_single_session_prices = living_single_session_prices;
    }

    public String getLive_stream_prices() {
        return live_stream_prices;
    }

    public void setLive_stream_prices(String live_stream_prices) {
        this.live_stream_prices = live_stream_prices;
    }

    public String getMaster_id() {
        return master_id;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public String getDisplay_id() {
        return display_id;
    }

    public void setDisplay_id(String display_id) {
        this.display_id = display_id;
    }
}
