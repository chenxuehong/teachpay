package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.personal.PrivateMasterInfoEntity;

import java.util.List;

public class MyRecommenderInfoModel extends JsonListResult<MyRecommenderInfoModel.MyRecommenderInfoEntity> {
    public static class MyRecommenderInfoEntity {

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 5
         * type : mechanism_recommender
         * user_id : 9641
         * name : zwy
         * phone : 15623900679
         * status : 2
         * create_time : 2020-09-11 14:07:16
         * update_time : 2020-09-12 10:52:33
         * rule_id : 4
         * refuse_contect :
         * frozen_contect :
         * earnings_this_month : 0
         * verification_code : null
         * invate_num : 0
         * invate_code : 96416568
         */

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
        public int status;
        public String create_time;
        public String update_time;
        public String rule_id;
        public String refuse_contect;
        public String frozen_contect;
        public String earnings_this_month;
        public String verification_code;
        public String invate_num;
        public String invate_code;
        public Map map;
        public static class Map{
            public UserRecommenderIncomeLogEntity latelyUserRecommenderIncomeLogEntity;
            public List<MasterMechanismModel.MasterMechanismEntity> inviteMechanismList;
            public List<PrivateMasterInfoEntity> inviteMasterList;
        }
    }
}
