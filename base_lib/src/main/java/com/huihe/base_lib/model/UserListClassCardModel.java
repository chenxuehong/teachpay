package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.personal.UserClassCardModel;

import java.util.List;

public class UserListClassCardModel extends JsonListResult<UserListClassCardModel.UserListClassCardEntity> {

    public static class UserListClassCardEntity {
        private Integer status;
        private String curriculum_num;
        private String minute_num;
        public String type;
        public MapBean map;

        public void setStatus(Integer status) {
            this.status = status;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public String getType() {
            return type;
        }

        public MapBean getMap() {
            return map;
        }

        public void setCurriculum_num(String curriculum_num) {
            this.curriculum_num = curriculum_num;
        }

        public void setMinute_num(String minute_num) {
            this.minute_num = minute_num;
        }

        public Integer getStatus() {
            return status;
        }

        public String getCurriculum_num() {
            return curriculum_num;
        }

        public String getMinute_num() {
            return minute_num;
        }

        public static class MapBean {

            private List<UserClassCardModel.UserClassCardEntity> userClassCard;

            public void setUserClassCard(List<UserClassCardModel.UserClassCardEntity> userClassCard) {
                this.userClassCard = userClassCard;
            }

            public List<UserClassCardModel.UserClassCardEntity> getUserClassCard() {
                return userClassCard;
            }
        }
    }
}
