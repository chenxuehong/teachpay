package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.home.MessageGroupEntity;

public class ClassRecordHistoryModel extends JsonListResult<ClassRecordHistoryModel.ClassRecordHistoryEntity> {
    public static class ClassRecordHistoryEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 413
         * create_time : 2020-05-19 18:13:40
         * update_time : 2020-05-19 18:13:40
         * type : minute
         * status : 1
         * curriculum_num : 0
         * minute_num : 2
         * user_id : 9120
         * group_id : 1304
         * curriculum_id : 6578
         * free_minute : 6
         * map : {"groupinfo":{"entry_password":"","show_time":"","fileds":"","sortName":"","tickets":0,"online_num":13,"owner_id":9099,"fee_standard":0,"orderBy":"","pageSize":10,"recommend_type":"","language":"German","group_type":"Public","idList":[],"type":"online","age_grade":"Childhood","notification":"","update_time":"2020-05-19 18:28:23","people_num":0,"advance_group":false,"is_charge":false,"number_participants":4,"id":1304,"map":null,"introduction":"","create_time":"2020-05-11 15:08:41","startRow":0,"group_name":"六年级一班","is_open":true,"totalItem":0,"totalPage":0,"language_level":"","is_life":false,"watch_duration":6,"label":"","operator_account":"","live_push_addr":"","faceUrl":"http://img.curiousmore.com/E2A-BF331068C08D/Documents/com_tencent_imsdk_data/image/huihe1589528760","earnings":0,"group_id":"1589180920","live_duration":0,"currentPage":0,"status":2},"masterAppointmentInfo":{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":6578,"create_time":"2020-05-19 17:58:02","update_time":"2020-05-19 17:58:02","master_id":9099,"start_time":"2020-05-19 18:00:00","end_time":"2020-05-19 18:30:00","status":2,"type":"open_class","offset":8,"timezone_id":324,"title":"Topic/Talk about friends","group_id":1304,"cover":"http://img.curiousmore.com/B67-78762C03A8FB/Documents/com_tencent_imsdk_data/image/huihe1589882280","age_grade":"Childhood","language_level":"Zero basis","language":"German","group_type":"online","is_appointment":null,"map":null,"entities":null,"timecode":null,"teach_language":null,"classroom_type":"online_video","introduction_cover":"http://img.curiousmore.com/B67-78762C03A8FB/Documents/com_tencent_imsdk_data/image/huihe1589882281,http://img.curiousmore.com/B67-78762C03A8FB/Documents/com_tencent_imsdk_data/image/huihe1589882281,http://img.curiousmore.com/B67-78762C03A8FB/Documents/com_tencent_imsdk_data/image/huihe1589882282","introduction_content":"兔中央音乐学院","local_offset":null,"is_teenagers":null,"user_id":null,"group_ids":null,"connect_peoplenum":1},"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/5415C8D6-5088-41EF-9CBA-2022C0E6A559/Documents/1huihe1558927624.png","background_pic":"http://img.curiousmore.com/0EC-8710B0B7D3DD/Documents/1huihe1585617764.png","birth":1584057600000,"cash":"0.000","cat_coin":"901391.000","chatting_count":220,"city":"滨江区","contacts_num":173,"country":"阿富汗","create_time":1558927515000,"distrib_qrcode":"","duration":0,"hometown":"马扎里沙里夫","identity_auth":true,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"汉语/英语/法语","like_category":"","like_num":4,"local_time":1589437836612,"log_out_time":1589435558000,"mail":"","member_level":2,"message_num":0,"mobile":"13296728663","mother_tongue":"Chinese","national_flag":"","nick_name":"可口可乐","online_state":0,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","pay_pass":"","preference":"交友拍拖","qrcode":"","rating":7,"registr_num":1,"sex":2,"signature":"","update_time":1589425719000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9120","user_id":9120}}
         */

        private int pageSize;
        private int currentPage;
        private int totalItem;
        private int startRow;
        private Object sortName;
        private Object orderBy;
        private Object fileds;
        private int totalPage;
        private String id;
        private String create_time;
        private String update_time;
        private String type;
        private int status;
        private int curriculum_num;
        private int minute_num;
        private String user_id;
        private String group_id;
        private String curriculum_id;
        private int free_minute;
        private MapBean map;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalItem() {
            return totalItem;
        }

        public void setTotalItem(int totalItem) {
            this.totalItem = totalItem;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public Object getSortName() {
            return sortName;
        }

        public void setSortName(Object sortName) {
            this.sortName = sortName;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public Object getFileds() {
            return fileds;
        }

        public void setFileds(Object fileds) {
            this.fileds = fileds;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

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

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCurriculum_num() {
            return curriculum_num;
        }

        public void setCurriculum_num(int curriculum_num) {
            this.curriculum_num = curriculum_num;
        }

        public int getMinute_num() {
            return minute_num;
        }

        public void setMinute_num(int minute_num) {
            this.minute_num = minute_num;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getCurriculum_id() {
            return curriculum_id;
        }

        public void setCurriculum_id(String curriculum_id) {
            this.curriculum_id = curriculum_id;
        }

        public int getFree_minute() {
            return free_minute;
        }

        public void setFree_minute(int free_minute) {
            this.free_minute = free_minute;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public static class MapBean {

            private MessageGroupEntity groupinfo;
            private MasterAppointmentEntity masterAppointmentInfo;
            private UserInfoEntity userinfo;

            public MessageGroupEntity getGroupinfo() {
                return groupinfo;
            }

            public void setGroupinfo(MessageGroupEntity groupinfo) {
                this.groupinfo = groupinfo;
            }

            public MasterAppointmentEntity getMasterAppointmentInfo() {
                return masterAppointmentInfo;
            }

            public void setMasterAppointmentInfo(MasterAppointmentEntity masterAppointmentInfo) {
                this.masterAppointmentInfo = masterAppointmentInfo;
            }

            public UserInfoEntity getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserInfoEntity userinfo) {
                this.userinfo = userinfo;
            }
        }
    }
}
