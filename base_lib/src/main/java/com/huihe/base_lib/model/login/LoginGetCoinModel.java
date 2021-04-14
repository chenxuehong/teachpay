package com.huihe.base_lib.model.login;

import com.huihe.base_lib.model.base.JsonResult;

public class LoginGetCoinModel extends JsonResult<LoginGetCoinModel.LoginGetCoinEntity> {
    public static class LoginGetCoinEntity{

        /**
         * reward : 10
         * state : true
         */

        private String reward;
        private boolean state;

        public String getReward() {
            return reward;
        }

        public void setReward(String reward) {
            this.reward = reward;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }
    }
}
