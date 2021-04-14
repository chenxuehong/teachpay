package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

import java.util.List;

public class StudyCenterModel extends JsonListResult<StudyCenterModel.StudyCenterEntity> {

    public static class StudyCenterEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 22
         * create_time : 2020-01-06 22:47:44
         * update_time : 2020-01-06 22:48:25
         * user_id : 9191
         * type : major
         * studycard_id : 7
         * course_num : 34
         * status : 2
         * master_id : 0
         * language :
         * level : 0
         * start_time : 2020-01-06 22:47:44
         * end_time : 2020-11-26 22:47:44
         * map : {"cardinfo":[{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":4,"create_time":"2019-06-20 10:06:35","update_time":"2019-07-22 14:49:29","card_name":"专业学习卡","equity_explain":"预约私教课程,有效期1个月,不得转让，一经购买不得退回","discount":0.1,"is_discount":true,"type":"major","status":1,"level":3,"studyPriceEntities":[{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":7,"create_time":"2019-06-20 15:01:38","update_time":"2019-07-31 12:59:02","amout":1200,"course_num":12,"discount":1,"discount_amout":648,"is_discount":true,"type":"major","transaction_id":"com.huihejituan.curiousearth.majorCard.twelve","platform":"ios","status":1,"time_type":"月卡","equity_explain":"可预约专业外教，语言不限?12节课时(25~30分钟/课时)?不得转让，一经购买不得退回?有效期一个月，过期作废","duration":1,"give_coin":1300,"member_level":1},{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":8,"create_time":"2019-06-20 15:01:38","update_time":"2019-07-29 17:36:01","amout":102,"course_num":1,"discount":1,"discount_amout":68,"is_discount":true,"type":"major","transaction_id":"com.huihejituan.curiousearth.majorCard.one","platform":"ios","status":1,"time_type":"单节课","equity_explain":"无权益?不得转让，一经购买不得退回?有效期一个月，过期作废","duration":1,"give_coin":0,"member_level":0},{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":12,"create_time":"2019-06-20 15:01:38","update_time":"2019-07-31 12:59:18","amout":3600,"course_num":36,"discount":1,"discount_amout":1648,"is_discount":true,"type":"major","transaction_id":"com.huihejituan.curiousearth.majorCard.thirtysix","platform":"ios","status":1,"time_type":"季卡","equity_explain":"可预约专业外教，语言不限?36节课时(25~30分钟/课时)?不得转让，一经购买不得退回?有效期一个月，过期作废","duration":3,"give_coin":3200,"member_level":1}],"user_id":null,"map":null}],"monthClassCount":34,"monthSurplusClassCount":34,"latelyclass":[]}
         */

        private String id;
        private String create_time;
        private String update_time;
        private String user_id;
        private String type;
        private String studycard_id;
        private int course_num;
        private int status;
        private String master_id;
        private String language;
        private String full_name;
        private String mechanism_id;
        private int level;
        private String start_time;
        private String end_time;
        private MapBean map;
        private float offset;

        public String getFull_name() {
            return full_name;
        }

        public String getMechanism_id() {
            return mechanism_id;
        }

        public float getOffset() {
            return offset;
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStudycard_id() {
            return studycard_id;
        }

        public void setStudycard_id(String studycard_id) {
            this.studycard_id = studycard_id;
        }

        public int getCourse_num() {
            return course_num;
        }

        public void setCourse_num(int course_num) {
            this.course_num = course_num;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMaster_id() {
            return master_id;
        }

        public void setMaster_id(String master_id) {
            this.master_id = master_id;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
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

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public  static class MapBean {
            /**
             * cardinfo : [{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":4,"create_time":"2019-06-20 10:06:35","update_time":"2019-07-22 14:49:29","card_name":"专业学习卡","equity_explain":"预约私教课程,有效期1个月,不得转让，一经购买不得退回","discount":0.1,"is_discount":true,"type":"major","status":1,"level":3,"studyPriceEntities":[{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":7,"create_time":"2019-06-20 15:01:38","update_time":"2019-07-31 12:59:02","amout":1200,"course_num":12,"discount":1,"discount_amout":648,"is_discount":true,"type":"major","transaction_id":"com.huihejituan.curiousearth.majorCard.twelve","platform":"ios","status":1,"time_type":"月卡","equity_explain":"可预约专业外教，语言不限?12节课时(25~30分钟/课时)?不得转让，一经购买不得退回?有效期一个月，过期作废","duration":1,"give_coin":1300,"member_level":1},{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":8,"create_time":"2019-06-20 15:01:38","update_time":"2019-07-29 17:36:01","amout":102,"course_num":1,"discount":1,"discount_amout":68,"is_discount":true,"type":"major","transaction_id":"com.huihejituan.curiousearth.majorCard.one","platform":"ios","status":1,"time_type":"单节课","equity_explain":"无权益?不得转让，一经购买不得退回?有效期一个月，过期作废","duration":1,"give_coin":0,"member_level":0},{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":12,"create_time":"2019-06-20 15:01:38","update_time":"2019-07-31 12:59:18","amout":3600,"course_num":36,"discount":1,"discount_amout":1648,"is_discount":true,"type":"major","transaction_id":"com.huihejituan.curiousearth.majorCard.thirtysix","platform":"ios","status":1,"time_type":"季卡","equity_explain":"可预约专业外教，语言不限?36节课时(25~30分钟/课时)?不得转让，一经购买不得退回?有效期一个月，过期作废","duration":3,"give_coin":3200,"member_level":1}],"user_id":null,"map":null}]
             * monthClassCount : 34
             * monthSurplusClassCount : 34
             * latelyclass : []
             */

            private int minute_count;
            private int curriculum_count;

            public void setMinute_count(int minute_count) {
                this.minute_count = minute_count;
            }

            public void setCurriculum_count(int curriculum_count) {
                this.curriculum_count = curriculum_count;
            }

            public int getMinute_count() {
                return minute_count;
            }

            public int getCurriculum_count() {
                return curriculum_count;
            }

            private int monthClassCount;
            private int monthSurplusClassCount;
            private List<CardinfoBean> cardinfo;
            private List<AppointmentinfoBean> latelyclass;

            public int getMonthClassCount() {
                return monthClassCount;
            }

            public void setMonthClassCount(int monthClassCount) {
                this.monthClassCount = monthClassCount;
            }

            public int getMonthSurplusClassCount() {
                return monthSurplusClassCount;
            }

            public void setMonthSurplusClassCount(int monthSurplusClassCount) {
                this.monthSurplusClassCount = monthSurplusClassCount;
            }

            public List<CardinfoBean> getCardinfo() {
                return cardinfo;
            }

            public void setCardinfo(List<CardinfoBean> cardinfo) {
                this.cardinfo = cardinfo;
            }

            public List<AppointmentinfoBean> getLatelyclass() {
                return latelyclass;
            }

            public void setLatelyclass(List<AppointmentinfoBean> latelyclass) {
                this.latelyclass = latelyclass;
            }

            public  static class CardinfoBean {
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
                 * create_time : 2019-06-20 10:06:35
                 * update_time : 2019-07-22 14:49:29
                 * card_name : 专业学习卡
                 * equity_explain : 预约私教课程,有效期1个月,不得转让，一经购买不得退回
                 * discount : 0.1
                 * is_discount : true
                 * type : major
                 * status : 1
                 * level : 3
                 * studyPriceEntities : [{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":7,"create_time":"2019-06-20 15:01:38","update_time":"2019-07-31 12:59:02","amout":1200,"course_num":12,"discount":1,"discount_amout":648,"is_discount":true,"type":"major","transaction_id":"com.huihejituan.curiousearth.majorCard.twelve","platform":"ios","status":1,"time_type":"月卡","equity_explain":"可预约专业外教，语言不限?12节课时(25~30分钟/课时)?不得转让，一经购买不得退回?有效期一个月，过期作废","duration":1,"give_coin":1300,"member_level":1},{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":8,"create_time":"2019-06-20 15:01:38","update_time":"2019-07-29 17:36:01","amout":102,"course_num":1,"discount":1,"discount_amout":68,"is_discount":true,"type":"major","transaction_id":"com.huihejituan.curiousearth.majorCard.one","platform":"ios","status":1,"time_type":"单节课","equity_explain":"无权益?不得转让，一经购买不得退回?有效期一个月，过期作废","duration":1,"give_coin":0,"member_level":0},{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":12,"create_time":"2019-06-20 15:01:38","update_time":"2019-07-31 12:59:18","amout":3600,"course_num":36,"discount":1,"discount_amout":1648,"is_discount":true,"type":"major","transaction_id":"com.huihejituan.curiousearth.majorCard.thirtysix","platform":"ios","status":1,"time_type":"季卡","equity_explain":"可预约专业外教，语言不限?36节课时(25~30分钟/课时)?不得转让，一经购买不得退回?有效期一个月，过期作废","duration":3,"give_coin":3200,"member_level":1}]
                 * user_id : null
                 * map : null
                 */

                private String id;
                private String create_time;
                private String update_time;
                private String card_name;
                private String equity_explain;
                private double discount;
                private boolean is_discount;
                private String type;
                private int status;
                private int level;
                private Object user_id;
                private Object map;
                private List<StudyPriceEntitiesBean> studyPriceEntities;

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

                public String getCard_name() {
                    return card_name;
                }

                public void setCard_name(String card_name) {
                    this.card_name = card_name;
                }

                public String getEquity_explain() {
                    return equity_explain;
                }

                public void setEquity_explain(String equity_explain) {
                    this.equity_explain = equity_explain;
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

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public Object getUser_id() {
                    return user_id;
                }

                public void setUser_id(Object user_id) {
                    this.user_id = user_id;
                }

                public Object getMap() {
                    return map;
                }

                public void setMap(Object map) {
                    this.map = map;
                }

                public List<StudyPriceEntitiesBean> getStudyPriceEntities() {
                    return studyPriceEntities;
                }

                public void setStudyPriceEntities(List<StudyPriceEntitiesBean> studyPriceEntities) {
                    this.studyPriceEntities = studyPriceEntities;
                }

                public  class StudyPriceEntitiesBean {
                    /**
                     * pageSize : 10
                     * currentPage : 0
                     * totalItem : 0
                     * startRow : 0
                     * sortName : null
                     * orderBy : null
                     * fileds : null
                     * totalPage : 0
                     * id : 7
                     * create_time : 2019-06-20 15:01:38
                     * update_time : 2019-07-31 12:59:02
                     * amout : 1200
                     * course_num : 12
                     * discount : 1
                     * discount_amout : 648
                     * is_discount : true
                     * type : major
                     * transaction_id : com.huihejituan.curiousearth.majorCard.twelve
                     * platform : ios
                     * status : 1
                     * time_type : 月卡
                     * equity_explain : 可预约专业外教，语言不限?12节课时(25~30分钟/课时)?不得转让，一经购买不得退回?有效期一个月，过期作废
                     * duration : 1
                     * give_coin : 1300
                     * member_level : 1
                     */

                    private String id;
                    private String create_time;
                    private String update_time;
                    private String amout;
                    private int course_num;
                    private String discount;
                    private String discount_amout;
                    private boolean is_discount;
                    private String type;
                    private String transaction_id;
                    private String platform;
                    private int status;
                    private String time_type;
                    private String equity_explain;
                    private int duration;
                    private int give_coin;
                    private int member_level;

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

                    public String getAmout() {
                        return amout;
                    }

                    public void setAmout(String amout) {
                        this.amout = amout;
                    }

                    public int getCourse_num() {
                        return course_num;
                    }

                    public void setCourse_num(int course_num) {
                        this.course_num = course_num;
                    }

                    public String getDiscount() {
                        return discount;
                    }

                    public void setDiscount(String discount) {
                        this.discount = discount;
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

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
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

                    public String getTime_type() {
                        return time_type;
                    }

                    public void setTime_type(String time_type) {
                        this.time_type = time_type;
                    }

                    public String getEquity_explain() {
                        return equity_explain;
                    }

                    public void setEquity_explain(String equity_explain) {
                        this.equity_explain = equity_explain;
                    }

                    public int getDuration() {
                        return duration;
                    }

                    public void setDuration(int duration) {
                        this.duration = duration;
                    }

                    public int getGive_coin() {
                        return give_coin;
                    }

                    public void setGive_coin(int give_coin) {
                        this.give_coin = give_coin;
                    }

                    public int getMember_level() {
                        return member_level;
                    }

                    public void setMember_level(int member_level) {
                        this.member_level = member_level;
                    }
                }
            }
        }
    }
}
