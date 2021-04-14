package com.huihe.base_lib.model.home;

import com.huihe.base_lib.model.base.JsonResult;

public class UpdateFreeFriendsModel extends JsonResult<UpdateFreeFriendsModel.UpdateFreeFriendsEntity> {
    public class UpdateFreeFriendsEntity{

        /**
         * result : false
         */

        private boolean result;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }
}
