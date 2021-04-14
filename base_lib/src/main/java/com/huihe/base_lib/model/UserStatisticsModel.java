package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonResult;

public class UserStatisticsModel extends JsonResult<UserStatisticsModel.UserStatisticsEntity> {
    public static class UserStatisticsEntity{

        /**
         * rechargeCount : 20
         * coupNum : 20
         * points : 0
         */

        private String rechargeCount;
        private String coupNum;
        private String points;

        public String getRechargeCount() {
            return rechargeCount;
        }

        public void setRechargeCount(String rechargeCount) {
            this.rechargeCount = rechargeCount;
        }

        public String getCoupNum() {
            return coupNum;
        }

        public void setCoupNum(String coupNum) {
            this.coupNum = coupNum;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public UserStatisticsEntity(String rechargeCount, String coupNum, String points) {
            this.rechargeCount = rechargeCount;
            this.coupNum = coupNum;
            this.points = points;
        }
    }
}
