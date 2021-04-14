package com.huihe.base_lib.model.home;

import com.huihe.base_lib.model.base.JsonResult;

public class VideoDurationInsertVideoOrderModel extends JsonResult<VideoDurationInsertVideoOrderModel.VideoDurationInsertVideoOrderEntity> {
    public class VideoDurationInsertVideoOrderEntity{

        /**
         * id : 10197
         * is_audition : true
         * is_video : true
         */

        private String id;
        private boolean is_audition;
        private boolean is_video;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIs_audition() {
            return is_audition;
        }

        public void setIs_audition(boolean is_audition) {
            this.is_audition = is_audition;
        }

        public boolean isIs_video() {
            return is_video;
        }

        public void setIs_video(boolean is_video) {
            this.is_video = is_video;
        }
    }
}
