package com.huihe.base_lib.model.home;

import com.huihe.base_lib.model.base.JsonResult;

public class FreeFriendsCountModel extends JsonResult<FreeFriendsCountModel.FreeFriendsCountEntity> {
    public class FreeFriendsCountEntity{

        /**
         * freeCount : 2
         */

        private int freeCount;

        public int getFreeCount() {
            return freeCount;
        }

        public void setFreeCount(int freeCount) {
            this.freeCount = freeCount;
        }
    }
}
