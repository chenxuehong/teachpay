package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class UserClassCardModel extends JsonListResult<UserClassCardModel.UserClassCardEntity> {
    public static class UserClassCardEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 1032
         * create_time : 2020-11-13 18:31:29
         * update_time : 2020-11-13 18:31:29
         * type : curriculum
         * status : 2
         * full_name : null
         * curriculum_num : 8
         * minute_num : 0
         * user_id : 9765
         * map : null
         * expire_time : 2020-11-27 18:31:30
         * default_use : false
         * phone : null
         * login_name : null
         * nick_name : null
         * loginIds : null
         * nickNameIds : null
         * is_experience : true
         * cat_coin : null
         */

        private int pageSize;
        private int currentPage;
        private int totalItem;
        private int startRow;
        private Object sortName;
        private Object orderBy;
        private Object fileds;
        private int totalPage;
        private String id;
        private String create_time;
        private String update_time;
        private String type;
        private int status;
        private Object full_name;
        private String curriculum_num;
        private String minute_num;
        private String user_id;
        private Object map;
        private String expire_time;
        private boolean default_use;
        private Object phone;
        private Object login_name;
        private Object nick_name;
        private Object loginIds;
        private Object nickNameIds;
        private boolean is_experience;
        private Object cat_coin;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalItem() {
            return totalItem;
        }

        public void setTotalItem(int totalItem) {
            this.totalItem = totalItem;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public Object getSortName() {
            return sortName;
        }

        public void setSortName(Object sortName) {
            this.sortName = sortName;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public Object getFileds() {
            return fileds;
        }

        public void setFileds(Object fileds) {
            this.fileds = fileds;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
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

        public Object getFull_name() {
            return full_name;
        }

        public void setFull_name(Object full_name) {
            this.full_name = full_name;
        }

        public String getCurriculum_num() {
            return curriculum_num;
        }

        public void setCurriculum_num(String curriculum_num) {
            this.curriculum_num = curriculum_num;
        }

        public String getMinute_num() {
            return minute_num;
        }

        public void setMinute_num(String minute_num) {
            this.minute_num = minute_num;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public Object getMap() {
            return map;
        }

        public void setMap(Object map) {
            this.map = map;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public boolean isDefault_use() {
            return default_use;
        }

        public void setDefault_use(boolean default_use) {
            this.default_use = default_use;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getLogin_name() {
            return login_name;
        }

        public void setLogin_name(Object login_name) {
            this.login_name = login_name;
        }

        public Object getNick_name() {
            return nick_name;
        }

        public void setNick_name(Object nick_name) {
            this.nick_name = nick_name;
        }

        public Object getLoginIds() {
            return loginIds;
        }

        public void setLoginIds(Object loginIds) {
            this.loginIds = loginIds;
        }

        public Object getNickNameIds() {
            return nickNameIds;
        }

        public void setNickNameIds(Object nickNameIds) {
            this.nickNameIds = nickNameIds;
        }

        public boolean isIs_experience() {
            return is_experience;
        }

        public void setIs_experience(boolean is_experience) {
            this.is_experience = is_experience;
        }

        public Object getCat_coin() {
            return cat_coin;
        }

        public void setCat_coin(Object cat_coin) {
            this.cat_coin = cat_coin;
        }
    }
}
