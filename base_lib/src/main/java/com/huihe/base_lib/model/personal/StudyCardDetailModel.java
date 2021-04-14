package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

import java.util.List;

public class StudyCardDetailModel extends JsonListResult<StudyCardDetailModel.StudyCardDetailEntity> {
    public class StudyCardDetailEntity{


        /**
         * card_name : 私教学习卡
         * create_time : 2019-06-20 10:07:15
         * currentPage : 0
         * discount : 0.1
         * equity_explain : 预约私教课程,有效期1个月,不得转让，一经购买不得退回
         * id : 1
         * is_discount : true
         * level : 0
         * pageSize : 10
         * startRow : 0
         * status : 1
         * studyPriceEntities : [{"amout":3360,"course_num":12,"create_time":"2019-06-20 15:00:23","currentPage":0,"discount":1,"discount_amout":1648,"duration":1,"equity_explain":"可预约私教，语言不限?12节课时(25~30分钟/课时)?不得转让，一经购买不得退回?有效期一个月，过期作废","give_coin":5700,"id":1,"is_discount":true,"member_level":3,"pageSize":10,"platform":"ios","startRow":0,"status":1,"time_type":"月卡","totalItem":0,"totalPage":0,"transaction_id":"com.huihejituan.curiousearth.privateCard.twelve","type":"private_education","update_time":"2019-07-31 12:58:21"},{"amout":237,"course_num":1,"create_time":"2019-06-20 15:00:45","currentPage":0,"discount":1,"discount_amout":158,"duration":1,"equity_explain":"无权益?不得转让，一经购买不得退回?有效期一个月，过期作废","give_coin":0,"id":2,"is_discount":true,"member_level":0,"pageSize":10,"platform":"ios","startRow":0,"status":1,"time_type":"单节课","totalItem":0,"totalPage":0,"transaction_id":"com.huihejituan.curiousearth.privateCard.one","type":"private_education","update_time":"2019-07-29 17:35:57"},{"amout":10080,"course_num":36,"create_time":"2019-06-20 15:01:38","currentPage":0,"discount":1,"discount_amout":3998,"duration":3,"equity_explain":"可预约私教，语言不限?36节课时(25~30分钟/课时)?不得转让，一经购买不得退回?有效期一个月，过期作废","give_coin":14000,"id":9,"is_discount":true,"member_level":3,"pageSize":10,"platform":"ios","startRow":0,"status":1,"time_type":"季卡","totalItem":0,"totalPage":0,"transaction_id":"com.huihejituan.curiousearth.privateCard.thirtysix","type":"private_education","update_time":"2019-07-31 12:59:06"}]
         * totalItem : 0
         * totalPage : 0
         * type : private_education
         * update_time : 2019-06-20 10:09:32
         */

        private String card_name;
        private String create_time;
        private double discount;
        private String equity_explain;
        private String id;
        private boolean is_discount;
        private int level;
        private int status;
        private String type;
        private String update_time;

        private List<StudyPriceEntitiesBean> studyPriceEntities;

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public String getEquity_explain() {
            return equity_explain;
        }

        public void setEquity_explain(String equity_explain) {
            this.equity_explain = equity_explain;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIs_discount() {
            return is_discount;
        }

        public void setIs_discount(boolean is_discount) {
            this.is_discount = is_discount;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public List<StudyPriceEntitiesBean> getStudyPriceEntities() {
            return studyPriceEntities;
        }

        public void setStudyPriceEntities(List<StudyPriceEntitiesBean> studyPriceEntities) {
            this.studyPriceEntities = studyPriceEntities;
        }

        public  class StudyPriceEntitiesBean {
            /**
             * amout : 3360
             * course_num : 12
             * create_time : 2019-06-20 15:00:23
             * currentPage : 0
             * discount : 1
             * discount_amout : 1648
             * duration : 1
             * equity_explain : 可预约私教，语言不限?12节课时(25~30分钟/课时)?不得转让，一经购买不得退回?有效期一个月，过期作废
             * give_coin : 5700
             * id : 1
             * is_discount : true
             * member_level : 3
             * pageSize : 10
             * platform : ios
             * startRow : 0
             * status : 1
             * time_type : 月卡
             * totalItem : 0
             * totalPage : 0
             * transaction_id : com.huihejituan.curiousearth.privateCard.twelve
             * type : private_education
             * update_time : 2019-07-31 12:58:21
             */

            private double amout;
            private int course_num;
            private String create_time;
            private float discount;
            private float discount_amout;
            private int duration;
            private String equity_explain;
            private String give_coin;
            private String id;
            private boolean is_discount;
            private int member_level;
            private String platform;
            private int status;
            private String time_type;
            private String transaction_id;
            private String type;
            private String update_time;
            private String special_type;
            public String getSpecial_type() {
                return special_type;
            }
            public double getAmout() {
                return amout;
            }

            public void setAmout(double amout) {
                this.amout = amout;
            }

            public int getCourse_num() {
                return course_num;
            }

            public void setCourse_num(int course_num) {
                this.course_num = course_num;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public float getDiscount() {
                return discount;
            }

            public void setDiscount(float discount) {
                this.discount = discount;
            }

            public float getDiscount_amout() {
                return discount_amout;
            }

            public void setDiscount_amout(float discount_amout) {
                this.discount_amout = discount_amout;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getEquity_explain() {
                return equity_explain;
            }

            public void setEquity_explain(String equity_explain) {
                this.equity_explain = equity_explain;
            }

            public String getGive_coin() {
                return give_coin;
            }

            public void setGive_coin(String give_coin) {
                this.give_coin = give_coin;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIs_discount() {
                return is_discount;
            }

            public void setIs_discount(boolean is_discount) {
                this.is_discount = is_discount;
            }

            public int getMember_level() {
                return member_level;
            }

            public void setMember_level(int member_level) {
                this.member_level = member_level;
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

            public String getTime_type() {
                return time_type;
            }

            public void setTime_type(String time_type) {
                this.time_type = time_type;
            }

            public String getTransaction_id() {
                return transaction_id;
            }

            public void setTransaction_id(String transaction_id) {
                this.transaction_id = transaction_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }
        }
    }
}
