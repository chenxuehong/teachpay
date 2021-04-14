package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class UserPreferenceModel extends JsonListResult<UserPreferenceModel.UserPreferenceEntity> {
    public class UserPreferenceEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 790
         * user_id : 9357
         * preference : 语言学习
         * type : 社交
         * country :
         * sex : 0
         * languages :
         * overseas_identity_name :
         * create_time : 2020-02-17 23:11:41
         * update_time : 2020-02-17 23:11:41
         * business_type :
         * start_age : null
         * end_age : null
         * status : 1
         * entityList : null
         */

        private String id;
        private String user_id;
        private String preference;
        private String type;
        private String country;
        private int sex;
        private String languages;
        private String overseas_identity_name;
        private String create_time;
        private String update_time;
        private String business_type;
        private Object start_age;
        private Object end_age;
        private int status;
        private Object entityList;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPreference() {
            return preference;
        }

        public void setPreference(String preference) {
            this.preference = preference;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getLanguages() {
            return languages;
        }

        public void setLanguages(String languages) {
            this.languages = languages;
        }

        public String getOverseas_identity_name() {
            return overseas_identity_name;
        }

        public void setOverseas_identity_name(String overseas_identity_name) {
            this.overseas_identity_name = overseas_identity_name;
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

        public String getBusiness_type() {
            return business_type;
        }

        public void setBusiness_type(String business_type) {
            this.business_type = business_type;
        }

        public Object getStart_age() {
            return start_age;
        }

        public void setStart_age(Object start_age) {
            this.start_age = start_age;
        }

        public Object getEnd_age() {
            return end_age;
        }

        public void setEnd_age(Object end_age) {
            this.end_age = end_age;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getEntityList() {
            return entityList;
        }

        public void setEntityList(Object entityList) {
            this.entityList = entityList;
        }
    }
}
