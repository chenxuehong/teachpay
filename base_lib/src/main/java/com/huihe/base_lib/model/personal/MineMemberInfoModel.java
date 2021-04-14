package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class MineMemberInfoModel extends JsonListResult<MineMemberInfoModel.MineMemberInfoEntity> {
    public class MineMemberInfoEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 13
         * user_id : 9099
         * member_level : 3
         * is_member : true
         * create_time : 2019-06-22 04:52:03
         * start_time : 2019-10-26 15:39:54
         * end_time : 2021-04-26 15:39:54
         * statistics_time : null
         */

        private String id;
        private String user_id;
        private int member_level;
        private boolean is_member;
        private String create_time;
        private String start_time;
        private String end_time;
        private Object statistics_time;


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

        public int getMember_level() {
            return member_level;
        }

        public void setMember_level(int member_level) {
            this.member_level = member_level;
        }

        public boolean isIs_member() {
            return is_member;
        }

        public void setIs_member(boolean is_member) {
            this.is_member = is_member;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
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

        public Object getStatistics_time() {
            return statistics_time;
        }

        public void setStatistics_time(Object statistics_time) {
            this.statistics_time = statistics_time;
        }
    }
}
