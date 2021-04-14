package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonListResult;

public class SpecialLecturerModel extends JsonListResult<SpecialLecturerModel.SpecialLecturerEntity> {
    public static class SpecialLecturerEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 6
         * create_time : 2020-08-24 13:41:13
         * user_id : 4260
         * each_student : 5
         * each_money : 100
         * start_class_time : 17:00~17:30,18:00~18:30,19:00~19:30,20:00~20:30
         * start_frequency : 1
         * least_earn : 1
         * disregard_peopleNum : 1
         */

        private String id;
        private String create_time;
        private String user_id;
        private String each_student;
        private String each_money;
        private String start_class_time;
        private String start_frequency;
        private String least_earn;
        private String disregard_peopleNum;

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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getEach_student() {
            return each_student;
        }

        public void setEach_student(String each_student) {
            this.each_student = each_student;
        }

        public String getEach_money() {
            return each_money;
        }

        public void setEach_money(String each_money) {
            this.each_money = each_money;
        }

        public String getStart_class_time() {
            return start_class_time;
        }

        public void setStart_class_time(String start_class_time) {
            this.start_class_time = start_class_time;
        }

        public String getStart_frequency() {
            return start_frequency;
        }

        public void setStart_frequency(String start_frequency) {
            this.start_frequency = start_frequency;
        }

        public String getLeast_earn() {
            return least_earn;
        }

        public void setLeast_earn(String least_earn) {
            this.least_earn = least_earn;
        }

        public String getDisregard_peopleNum() {
            return disregard_peopleNum;
        }

        public void setDisregard_peopleNum(String disregard_peopleNum) {
            this.disregard_peopleNum = disregard_peopleNum;
        }
    }
}
