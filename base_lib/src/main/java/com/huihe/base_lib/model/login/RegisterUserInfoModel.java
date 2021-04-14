package com.huihe.base_lib.model.login;

import com.huihe.base_lib.model.base.JsonResult;

public class RegisterUserInfoModel extends JsonResult<RegisterUserInfoModel.RegisterUserInfoEntity> {
    public class RegisterUserInfoEntity{

        /**
         * reward : 50
         * userSign : eJyrVgrxCdZLrSjILEpVsjIytTQyMDDQAQuWpRYpWSkZ6RkoQfjFKdmJBQWZKUpWhiYGBkampgYm
         5hCZzJTUvJLMtEywBktjEzOYjsx0oIB-eV6iW3lgromJe2S*u4tfnn55cq6JmbdluZ9jcIiPsXOW
         p7FrkVtAWGW2LVRjSWYu0DWGphYGFgbm5hbmtQCNvi*8
         * user_id : 9346
         * TIMMessage : 1
         * message : 注册成功
         */

        private String reward;
        private String userSign;
        private String user_id;
        private String message;

        public String getReward() {
            return reward;
        }

        public void setReward(String reward) {
            this.reward = reward;
        }

        public String getUserSign() {
            return userSign;
        }

        public void setUserSign(String userSign) {
            this.userSign = userSign;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }


        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
