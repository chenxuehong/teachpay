package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonListResult;

public class UserRecommenderIncomeListModel extends JsonListResult<UserRecommenderIncomeListModel.UserRecommenderIncomeEntity> {
    public static class UserRecommenderIncomeEntity {
        public String pageSize;
        public String currentPage;
        public String totalItem;
        public String startRow;
        public String totalPage;
        public String id;
        public String type;
        public String user_id;
        public String name;
        public String phone;
        public String status;
        public String create_time;
        public String update_time;
        public String rule_id;
        public String refuse_contect;
        public String frozen_contect;
        public String earnings_this_month;
        public String invate_num;
        public String register_type;
        public String email;
        public String invate_code;
        public String verification_code;
        public Map map;

        public static class Map {
            public UserInfoEntity userInfo;
        }
    }
}
