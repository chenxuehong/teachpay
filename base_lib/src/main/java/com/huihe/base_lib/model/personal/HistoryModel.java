package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonResult;

public class HistoryModel extends JsonResult<HistoryModel.HistoryEntity> {
    public class HistoryEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 318
         * user_id : 9108
         * create_time : 2019-08-08 17:32:09
         * update_time : 2019-08-08 19:20:32
         * operation_type : collect
         * history_id : 3068
         * address :
         * status : 1
         * history_type : note
         * destination :
         * map : null
         */

        private String id;
        private String user_id;
        private String create_time;
        private String update_time;
        private String operation_type;
        private String history_id;
        private String address;
        private int status;
        private String history_type;
        private String destination;
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

        public String getOperation_type() {
            return operation_type;
        }

        public void setOperation_type(String operation_type) {
            this.operation_type = operation_type;
        }

        public String getHistory_id() {
            return history_id;
        }

        public void setHistory_id(String history_id) {
            this.history_id = history_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getHistory_type() {
            return history_type;
        }

        public void setHistory_type(String history_type) {
            this.history_type = history_type;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public Object getMap() {
            return map;
        }

        public void setMap(Object map) {
            this.map = map;
        }
    }
}
