package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonListResult;

import java.util.List;

public class NonPaymentLiveCardOrderDataModel extends JsonListResult<NonPaymentLiveCardOrderDataModel.NonPaymentLiveCardOrderEntity> {
    public static class NonPaymentLiveCardOrderEntity {

        /**
         * id : 1032
         * create_time : 2020-11-13 18:31:29
         * update_time : 2020-11-13 18:31:29
         * type : curriculum
         * status : 2
         * full_name : null
         * curriculum_num : 8
         * minute_num : 0
         * user_id : 9765
         * expire_time : 2020-11-27 18:31:30
         */

        private int id;
        private String create_time;
        private String update_time;
        private String type;
        private int status;
        private Object full_name;
        private int curriculum_num;
        private int minute_num;
        private String user_id;
        private String expire_time;
        private MapBean map;

        public MapBean getMap() {
            return map;
        }

        public static class MapBean {
            private List<LiveCardOrderInfoEntity> rechargeRecordEntity;

            public List<LiveCardOrderInfoEntity> getRechargeRecordEntity() {
                return rechargeRecordEntity;
            }

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCurriculum_num() {
            return curriculum_num;
        }

        public void setCurriculum_num(int curriculum_num) {
            this.curriculum_num = curriculum_num;
        }

        public int getMinute_num() {
            return minute_num;
        }

        public void setMinute_num(int minute_num) {
            this.minute_num = minute_num;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }
    }
}
