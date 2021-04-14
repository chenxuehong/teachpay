package com.huihe.base_lib.model.im;

import com.huihe.base_lib.model.base.JsonResult;

public class PushHistoryIsReadModel extends JsonResult<PushHistoryIsReadModel.PushHistoryIsReadEntity> {
    public static class PushHistoryIsReadEntity{

        /**
         * authIsRead : true
         * dynamicIsRead : false
         * eventIsRead : false
         * systemIsRead : true
         */

        private boolean authIsRead;
        private boolean dynamicIsRead;
        private boolean eventIsRead;
        private boolean systemIsRead;

        public boolean isAuthIsRead() {
            return authIsRead;
        }

        public void setAuthIsRead(boolean authIsRead) {
            this.authIsRead = authIsRead;
        }

        public boolean isDynamicIsRead() {
            return dynamicIsRead;
        }

        public void setDynamicIsRead(boolean dynamicIsRead) {
            this.dynamicIsRead = dynamicIsRead;
        }

        public boolean isEventIsRead() {
            return eventIsRead;
        }

        public void setEventIsRead(boolean eventIsRead) {
            this.eventIsRead = eventIsRead;
        }

        public boolean isSystemIsRead() {
            return systemIsRead;
        }

        public void setSystemIsRead(boolean systemIsRead) {
            this.systemIsRead = systemIsRead;
        }
    }
}
