package com.huihe.base_lib.model;

import java.util.List;

public class TeachPaypalDetailEntity {
    public Boolean todaySignIn;
    public String singInDay;
    public List<GoldEntity> goldList;

    public static class GoldEntity {
        public String id;
        public String gold_num;
        public String pic_path;
        public String type;
        public String introduce;
        public String level;
        public String create_time;
        public String update_time;
        public String frequency;
        public String status;
        public Boolean is_teach_paypal;
        public String oper_id;
        public Boolean is_daily;
        public String completed_frequency;
    }
}
