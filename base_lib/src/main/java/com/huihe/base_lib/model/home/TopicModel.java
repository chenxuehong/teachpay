package com.huihe.base_lib.model.home;

import com.huihe.base_lib.model.base.JsonListResult;

public class TopicModel extends JsonListResult<TopicModel.TopicEntity> {

    public class TopicEntity {

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 41
         * create_time : 2019-07-01 16:21:00
         * update_time : 2019-08-03 09:35:03
         * type : scene_broadcast
         * content : 酒吧或KTV
         * status : 2
         */

        private String id;
        private String create_time;
        private String update_time;
        private String type;
        private String content;
        private int status;


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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
