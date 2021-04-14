package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class ClassCardModel extends JsonListResult<ClassCardModel.ClassCardEntity> {
    public class ClassCardEntity{

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
         * create_time : 2019-10-17 16:46:55
         * update_time : 2019-10-18 13:20:29
         * type : minute
         * equity_explain : 点卡，按照一分钟计算，不足一分钟按一分钟算?用于观看直播课程，语言不限?不得转让，一经购买不得退回?有效期一年，过期作废
         * discount : 1
         * is_discount : false
         * transaction_id :
         * platform : ios
         * status : 1
         * give_curriculum : 0
         * give_minute : 0
         * curriculum_num : 0
         * minute_num : 1
         * price : 0.5
         * discount_amout : 0.5
         * commodity_type : single_product
         * map : null
         * user_id : null
         */

        private String id;
        private String create_time;
        private String update_time;
        private String type;
        private String equity_explain;
        private String discount;
        private boolean is_discount;
        private String transaction_id;
        private String platform;
        private int status;
        private String give_curriculum;
        private String give_minute;
        private Integer curriculum_num;
        private Integer minute_num;
        private float price;
        private float discount_amout;
        private String commodity_type;
        private Object map;
        private Object user_id;


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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEquity_explain() {
            return equity_explain;
        }

        public void setEquity_explain(String equity_explain) {
            this.equity_explain = equity_explain;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public boolean isIs_discount() {
            return is_discount;
        }

        public void setIs_discount(boolean is_discount) {
            this.is_discount = is_discount;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getGive_curriculum() {
            return give_curriculum;
        }

        public void setGive_curriculum(String give_curriculum) {
            this.give_curriculum = give_curriculum;
        }

        public String getGive_minute() {
            return give_minute;
        }

        public void setGive_minute(String give_minute) {
            this.give_minute = give_minute;
        }

        public Integer getCurriculum_num() {
            return curriculum_num;
        }

        public void setCurriculum_num(Integer curriculum_num) {
            this.curriculum_num = curriculum_num;
        }

        public Integer getMinute_num() {
            return minute_num;
        }

        public void setMinute_num(Integer minute_num) {
            this.minute_num = minute_num;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public float getDiscount_amout() {
            return discount_amout;
        }

        public void setDiscount_amout(float discount_amout) {
            this.discount_amout = discount_amout;
        }

        public String getCommodity_type() {
            return commodity_type;
        }

        public void setCommodity_type(String commodity_type) {
            this.commodity_type = commodity_type;
        }

        public Object getMap() {
            return map;
        }

        public void setMap(Object map) {
            this.map = map;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }
    }
}
