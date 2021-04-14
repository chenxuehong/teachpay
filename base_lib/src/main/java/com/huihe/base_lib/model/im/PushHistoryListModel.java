package com.huihe.base_lib.model.im;

import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.personal.MasterAppointmentEntity;
import com.huihe.base_lib.model.personal.UserIdentityModel;

public class PushHistoryListModel extends JsonListResult<PushHistoryListModel.PushHistoryEntity> {
    public static class PushHistoryEntity {

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 868
         * create_time : 2020-07-28 18:30:00
         * large_type : groupClassNotice
         * opera_type : class_end
         * map : null
         * view_type :
         * type_id : 1303
         * send_id : 0
         * type : auth
         * target_id : 4260
         * user_status : 2
         * master_status : 1
         * title : 课程提醒
         * context : 您的开播课程已结束
         */


        private String id;
        private String create_time;
        private String large_type;
        private String opera_type;
        private MapBean map;
        private String view_type;
        private String type_id;
        private String send_id;
        private String type;
        private String target_id;
        private int user_status;
        private int master_status;
        private String title;
        private String context;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getLarge_type() {
            return large_type;
        }

        public void setLarge_type(String large_type) {
            this.large_type = large_type;
        }

        public String getOpera_type() {
            return opera_type;
        }

        public void setOpera_type(String opera_type) {
            this.opera_type = opera_type;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public String getView_type() {
            return view_type;
        }

        public void setView_type(String view_type) {
            this.view_type = view_type;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getSend_id() {
            return send_id;
        }

        public void setSend_id(String send_id) {
            this.send_id = send_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTarget_id() {
            return target_id;
        }

        public void setTarget_id(String target_id) {
            this.target_id = target_id;
        }

        public int getUser_status() {
            return user_status;
        }

        public void setUser_status(int user_status) {
            this.user_status = user_status;
        }

        public int getMaster_status() {
            return master_status;
        }

        public void setMaster_status(int master_status) {
            this.master_status = master_status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public static class MapBean {
            public UserIdentityModel.UserIdentityEntity userIdentityEntity;
            public MasterAppointmentEntity masterAppointmentEntity;

            public MasterAppointmentEntity getMasterAppointmentEntity() {
                return masterAppointmentEntity;
            }

            public void setMasterAppointmentEntity(MasterAppointmentEntity masterAppointmentEntity) {
                this.masterAppointmentEntity = masterAppointmentEntity;
            }

            public void setUserIdentityEntity(UserIdentityModel.UserIdentityEntity userIdentityEntity) {
                this.userIdentityEntity = userIdentityEntity;
            }

            public UserIdentityModel.UserIdentityEntity getUserIdentityEntity() {
                return userIdentityEntity;
            }
        }
    }
}
