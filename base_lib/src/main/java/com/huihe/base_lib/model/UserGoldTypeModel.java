package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonListResult;

public class UserGoldTypeModel extends JsonListResult<UserGoldTypeModel.UserGoldTypeEntity> {
    public static class UserGoldTypeEntity{

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
         * gold_num : 50
         * pic_path : http://www.huihejituan.com/tripPictstorage/goldtype/xuzhu_03.png
         * type : realname_authentication
         * introduce : 实名认证
         * level : 2
         * create_time : 2019-04-29 12:04:08
         * update_time : 2019-04-29 13:52:14
         * frequency : 1
         * status : 0
         * is_complete : false
         * canbeled_count : 0
         * oper_id : null
         * completed_frequency : 0
         */

        private int pageSize;
        private int currentPage;
        private int totalItem;
        private int startRow;
        private Object sortName;
        private Object orderBy;
        private Object fileds;
        private int totalPage;
        private int id;
        private int gold_num;
        private String pic_path;
        private String type;
        private String introduce;
        private int level;
        private String create_time;
        private String update_time;
        private int frequency;
        private int status;
        private boolean is_complete;
        private int canbeled_count;
        private String oper_id;
        private int completed_frequency;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGold_num() {
            return gold_num;
        }

        public void setGold_num(int gold_num) {
            this.gold_num = gold_num;
        }

        public String getPic_path() {
            return pic_path;
        }

        public void setPic_path(String pic_path) {
            this.pic_path = pic_path;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
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

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public boolean isIs_complete() {
            return is_complete;
        }

        public void setIs_complete(boolean is_complete) {
            this.is_complete = is_complete;
        }

        public int getCanbeled_count() {
            return canbeled_count;
        }

        public void setCanbeled_count(int canbeled_count) {
            this.canbeled_count = canbeled_count;
        }

        public String getOper_id() {
            return oper_id;
        }

        public void setOper_id(String oper_id) {
            this.oper_id = oper_id;
        }

        public int getCompleted_frequency() {
            return completed_frequency;
        }

        public void setCompleted_frequency(int completed_frequency) {
            this.completed_frequency = completed_frequency;
        }
    }
}
