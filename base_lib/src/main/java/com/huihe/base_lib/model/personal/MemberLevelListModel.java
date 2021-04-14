package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

import java.util.List;

public class MemberLevelListModel extends JsonListResult<MemberLevelListModel.MemberLevelListEntity> {
    public class MemberLevelListEntity {

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 4
         * member_name : 畅聊会员
         * member_level : 1
         * create_time : 2019-04-26 21:45:03
         * update_time : 2019-08-02 17:27:01
         * platform : android
         * discount : 2.1
         * is_discount : true
         * member_equity : 每月可主动沟通45境友,语言在线翻译
         * cardEntityList : [{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":4,"level_id":4,"discount":4,"amount":45,"member_name":"一月","discount_amout":18,"is_discount":true,"give_coin":180,"duration":1,"create_time":"2019-04-26 21:50:20","update_time":"2019-08-02 17:39:17","status":1,"platform":"android","transaction_id":"","introduction":""},{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":6,"level_id":4,"discount":3.3,"amount":135,"member_name":"一季","discount_amout":45,"is_discount":true,"give_coin":450,"duration":3,"create_time":"2019-04-26 21:51:48","update_time":"2019-08-02 17:39:21","status":1,"platform":"android","transaction_id":"","introduction":""},{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":8,"level_id":4,"discount":2.1,"amount":540,"member_name":"一年","discount_amout":118,"is_discount":true,"give_coin":1180,"duration":12,"create_time":"2019-04-26 21:53:52","update_time":"2019-08-02 17:39:26","status":1,"platform":"android","transaction_id":"","introduction":""}]
         * introduction : 专属会员权益,赠送1800金币,专属客服,豪礼1.5金币,自由沟通境友,任意查看境友,优质好友匹配,翻译多国语言
         */

        private String id;
        private String member_name;
        private int member_level;
        private String create_time;
        private String update_time;
        private String platform;
        private double discount;
        private boolean is_discount;
        private String member_equity;
        private String introduction;
        private List<CardEntityListBean> cardEntityList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public int getMember_level() {
            return member_level;
        }

        public void setMember_level(int member_level) {
            this.member_level = member_level;
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

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public boolean isIs_discount() {
            return is_discount;
        }

        public void setIs_discount(boolean is_discount) {
            this.is_discount = is_discount;
        }

        public String getMember_equity() {
            return member_equity;
        }

        public void setMember_equity(String member_equity) {
            this.member_equity = member_equity;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public List<CardEntityListBean> getCardEntityList() {
            return cardEntityList;
        }

        public void setCardEntityList(List<CardEntityListBean> cardEntityList) {
            this.cardEntityList = cardEntityList;
        }

        public class CardEntityListBean {
            /**
             * pageSize : 10
             * currentPage : 0
             * totalItem : 0
             * startRow : 0
             * sortName : null
             * orderBy : null
             * fileds : null
             * totalPage : 0
             * id : 4
             * level_id : 4
             * discount : 4
             * amount : 45
             * member_name : 一月
             * discount_amout : 18
             * is_discount : true
             * give_coin : 180
             * duration : 1
             * create_time : 2019-04-26 21:50:20
             * update_time : 2019-08-02 17:39:17
             * status : 1
             * platform : android
             * transaction_id :
             * introduction :
             */

            private String id;
            private String level_id;
            private float discount;
            private String member_name;
            private float discount_amout;
            private String amount;
            private boolean is_discount;
            private String give_coin;
            private Integer duration;
            private String create_time;
            private String update_time;
            private int status;
            private String platform;
            private String transaction_id;
            private String introduction;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLevel_id() {
                return level_id;
            }

            public void setLevel_id(String level_id) {
                this.level_id = level_id;
            }

            public float getDiscount() {
                return discount;
            }

            public void setDiscount(float discount) {
                this.discount = discount;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
            }

            public float getDiscount_amout() {
                return discount_amout;
            }

            public void setDiscount_amout(float discount_amout) {
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

            public Integer getDuration() {
                return duration;
            }

            public void setDuration(Integer duration) {
                this.duration = duration;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public String getTransaction_id() {
                return transaction_id;
            }

            public void setTransaction_id(String transaction_id) {
                this.transaction_id = transaction_id;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }
        }
    }
}
