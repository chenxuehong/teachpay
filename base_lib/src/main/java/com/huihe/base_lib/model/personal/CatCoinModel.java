package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class CatCoinModel extends JsonListResult<CatCoinModel.CatCoinEntity> {
    public class CatCoinEntity{

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
         * create_time : 2019-04-10 23:54:28
         * update_time : 2019-04-27 14:45:02
         * commodity_name : 1元
         * type : account
         * amount : 1
         * transaction_id : com.huihejituan.curiousearth.cat1
         * source : ios
         * discount_amout : 1
         * is_discount : false
         * give_coin : 0
         * value_coin : 100
         * status : 1
         * discount : 1
         * introduction :
         */

        private String id;
        private String create_time;
        private String update_time;
        private String commodity_name;
        private String type;
        private float amount;
        private String transaction_id;
        private String source;
        private String discount_amout;
        private boolean is_discount;
        private String give_coin;
        private String value_coin;
        private int status;
        private String discount;
        private String introduction;

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

        public String getCommodity_name() {
            return commodity_name;
        }

        public void setCommodity_name(String commodity_name) {
            this.commodity_name = commodity_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getDiscount_amout() {
            return discount_amout;
        }

        public void setDiscount_amout(String discount_amout) {
            this.discount_amout = discount_amout;
        }

        public boolean isIs_discount() {
            return is_discount;
        }

        public void setIs_discount(boolean is_discount) {
            this.is_discount = is_discount;
        }

        public String getGive_coin() {
            return give_coin;
        }

        public void setGive_coin(String give_coin) {
            this.give_coin = give_coin;
        }

        public String getValue_coin() {
            return value_coin;
        }

        public void setValue_coin(String value_coin) {
            this.value_coin = value_coin;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }
}
