package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class AttentionLiveClassTeacherModel extends JsonListResult<AttentionLiveClassTeacherModel.AttentionLiveClassTeacherEntity> {

    public class AttentionLiveClassTeacherEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 12
         * user_id : 9099
         * master_id : 9191
         * create_time : 2020-01-19 14:57:54
         * update_time : 2020-01-19 14:57:54
         * status : 1
         * map : null
         */

        private String id;
        private String user_id;
        private String master_id;
        private String create_time;
        private String update_time;
        private int status;
        private Object map;

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

        public String getMaster_id() {
            return master_id;
        }

        public void setMaster_id(String master_id) {
            this.master_id = master_id;
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

        public Object getMap() {
            return map;
        }

        public void setMap(Object map) {
            this.map = map;
        }
    }
}
