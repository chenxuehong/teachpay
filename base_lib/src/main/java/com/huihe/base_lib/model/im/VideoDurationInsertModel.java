package com.huihe.base_lib.model.im;

import com.huihe.base_lib.model.base.JsonResult;

public class VideoDurationInsertModel extends JsonResult<VideoDurationInsertModel.VideoDurationInsertEntity> {

    public static class VideoDurationInsertEntity{


        /**
         * is_video : true
         * appointment_id : 200
         * video_id : 9384
         */

        private boolean is_video;
        private String appointment_id;
        private String video_id;

        public boolean isIs_video() {
            return is_video;
        }

        public void setIs_video(boolean is_video) {
            this.is_video = is_video;
        }

        public String getAppointment_id() {
            return appointment_id;
        }

        public void setAppointment_id(String appointment_id) {
            this.appointment_id = appointment_id;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }
    }
}
