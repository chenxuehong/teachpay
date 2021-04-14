package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class UserSchoolModel extends JsonListResult<UserSchoolModel.UserSchoolEntity> {
    public class UserSchoolEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 10
         * user_id : 9099
         * school_name : 上海大学
         * start_time : 2020-1-22
         * end_time : 2020-2-22
         * create_time : 2020-02-22 13:50:01
         * update_time : 2020-02-22 13:50:01
         * is_visible : true
         * oper_id : null
         */

        private String id;
        private String user_id;
        private String school_name;
        private String start_time;
        private String end_time;
        private String create_time;
        private String update_time;
        private boolean is_visible;
        private String oper_id;

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

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
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

        public String getOper_id() {
            return oper_id;
        }

        public void setOper_id(String oper_id) {
            this.oper_id = oper_id;
        }
    }
}
