package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class UserCompanyModel extends JsonListResult<UserCompanyModel.UserCompanyEntity> {
    public class UserCompanyEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 49
         * user_id : 9099
         * company_name : 123
         * company_industry : 123
         * duty : 开发
         * start_time : 2019-09-25
         * end_time : 2019-09-25
         * create_time : 2019-09-25 14:52:31
         * update_time : 2019-09-25 14:52:31
         * is_visible : true
         * userCompanyEntities : null
         */

        private String id;
        private String user_id;
        private String company_name;
        private String company_industry;
        private String duty;
        private String start_time;
        private String end_time;
        private String create_time;
        private String update_time;
        private boolean is_visible;
        private Object userCompanyEntities;


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

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_industry() {
            return company_industry;
        }

        public void setCompany_industry(String company_industry) {
            this.company_industry = company_industry;
        }

        public String getDuty() {
            return duty;
        }

        public void setDuty(String duty) {
            this.duty = duty;
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

        public boolean isIs_visible() {
            return is_visible;
        }

        public void setIs_visible(boolean is_visible) {
            this.is_visible = is_visible;
        }

        public Object getUserCompanyEntities() {
            return userCompanyEntities;
        }

        public void setUserCompanyEntities(Object userCompanyEntities) {
            this.userCompanyEntities = userCompanyEntities;
        }
    }
}
