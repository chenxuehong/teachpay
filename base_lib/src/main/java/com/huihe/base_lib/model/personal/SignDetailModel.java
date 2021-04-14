package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonResult;

public class SignDetailModel extends JsonResult<SignDetailModel.SignDetailEntity> {
    public static class SignDetailEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : null
         * user_id : null
         * create_time : null
         * cat_coin : null
         * today_sign_in : true
         * day : 1
         * is_invitation : false
         */

        private Object id;
        private Object user_id;
        private Object create_time;
        private Object cat_coin;
        private boolean today_sign_in;
        private Integer day;
        private boolean is_invitation;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public Object getCat_coin() {
            return cat_coin;
        }

        public void setCat_coin(Object cat_coin) {
            this.cat_coin = cat_coin;
        }

        public boolean isToday_sign_in() {
            return today_sign_in;
        }

        public void setToday_sign_in(boolean today_sign_in) {
            this.today_sign_in = today_sign_in;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        public boolean isIs_invitation() {
            return is_invitation;
        }

        public void setIs_invitation(boolean is_invitation) {
            this.is_invitation = is_invitation;
        }
    }
}
