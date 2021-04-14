package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonResult;

public class MasterFollowIsFollowModel extends JsonResult<MasterFollowIsFollowModel.MasterFollowIsFollowEntity> {
    public static class MasterFollowIsFollowEntity{

        /**
         * is_follow : false
         */

        private boolean is_follow;

        public boolean isIs_follow() {
            return is_follow;
        }

        public void setIs_follow(boolean is_follow) {
            this.is_follow = is_follow;
        }
    }
}
