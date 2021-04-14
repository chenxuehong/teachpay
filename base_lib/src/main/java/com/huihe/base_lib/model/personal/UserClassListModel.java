package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.home.MessageGroupEntity;

public class UserClassListModel extends JsonListResult<UserClassListModel.UserClassEntity> {
    public class UserClassEntity {


        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 844
         * create_time : 2020-01-13 15:58:50
         * update_time : 2020-01-13 15:58:50
         * master_id : 9099
         * start_time : 2020-01-14 00:00:00
         * end_time : 2020-01-14 00:30:00
         * status : 1
         * type : open_class
         * offset : 8
         * timezone_id : 324
         * title : 话题交流/自我介绍
         * group_id : 897
         * cover : null
         * age_grade : null
         * language_level : null
         * language : null
         * group_type : null
         * is_appointment : null
         * map : {"groupinfo":{"entry_password":"","show_time":"","fileds":"","sortName":"","tickets":0,"online_num":1,"owner_id":9099,"fee_standard":0,"orderBy":"","pageSize":10,"recommend_type":"","language":"Japanese","group_type":"Public","idList":[],"type":"online_video","age_grade":"Childhood","notification":"","update_time":"2020-01-13 15:57:54","people_num":0,"advance_group":false,"is_charge":false,"number_participants":1,"id":897,"map":null,"introduction":"","create_time":"2020-01-13 15:57:54","startRow":0,"group_name":"回家看","is_open":true,"totalItem":0,"totalPage":0,"language_level":"","is_life":false,"watch_duration":0,"label":"话题交流/自我介绍","operator_account":"","live_push_addr":"","faceUrl":"http://img.curiousmore.com/CAB-378A08DEFFB3/Documents/1huihe1578902318.png","earnings":0,"group_id":"1578902274","live_duration":0,"currentPage":0,"status":1}}
         * entities : null
         * timecode : null
         * teach_language : null
         */

        private String id;
        private String create_time;
        private String update_time;
        private String master_id;
        private String start_time;
        private String end_time;
        private int status;
        private String type;
        private Float offset;
        private String timezone_id;
        private String title;
        private String group_id;
        private String cover;
        private Object age_grade;
        private String language_level;
        private String language;
        private Object group_type;
        private Object is_appointment;
        private MapBean map;
        private Object entities;
        private Object timecode;
        private Object teach_language;
        /**
         * entities : null
         * timecode : null
         * teach_language : null
         * classroom_type : online_video
         * introduction_cover :
         * introduction_content :
         * local_offset : null
         * is_teenagers : null
         * user_id : null
         * group_ids : null
         * connect_peoplenum : 0
         */
        private String classroom_type;
        private String introduction_cover;
        private String introduction_content;
        private Object local_offset;
        private Object is_teenagers;
        private Object user_id;
        private Object group_ids;
        private int connect_peoplenum;

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

        public String getMaster_id() {
            return master_id;
        }

        public void setMaster_id(String master_id) {
            this.master_id = master_id;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Float getOffset() {
            return offset;
        }

        public void setOffset(Float offset) {
            this.offset = offset;
        }

        public String getTimezone_id() {
            return timezone_id;
        }

        public void setTimezone_id(String timezone_id) {
            this.timezone_id = timezone_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public Object getAge_grade() {
            return age_grade;
        }

        public void setAge_grade(Object age_grade) {
            this.age_grade = age_grade;
        }

        public String getLanguage_level() {
            return language_level;
        }

        public void setLanguage_level(String language_level) {
            this.language_level = language_level;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Object getGroup_type() {
            return group_type;
        }

        public void setGroup_type(Object group_type) {
            this.group_type = group_type;
        }

        public Object getIs_appointment() {
            return is_appointment;
        }

        public void setIs_appointment(Object is_appointment) {
            this.is_appointment = is_appointment;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public Object getEntities() {
            return entities;
        }

        public void setEntities(Object entities) {
            this.entities = entities;
        }

        public Object getTimecode() {
            return timecode;
        }

        public void setTimecode(Object timecode) {
            this.timecode = timecode;
        }

        public Object getTeach_language() {
            return teach_language;
        }

        public void setTeach_language(Object teach_language) {
            this.teach_language = teach_language;
        }

        public String getClassroom_type() {
            return classroom_type;
        }

        public void setClassroom_type(String classroom_type) {
            this.classroom_type = classroom_type;
        }

        public String getIntroduction_cover() {
            return introduction_cover;
        }

        public void setIntroduction_cover(String introduction_cover) {
            this.introduction_cover = introduction_cover;
        }

        public String getIntroduction_content() {
            return introduction_content;
        }

        public void setIntroduction_content(String introduction_content) {
            this.introduction_content = introduction_content;
        }

        public Object getLocal_offset() {
            return local_offset;
        }

        public void setLocal_offset(Object local_offset) {
            this.local_offset = local_offset;
        }

        public Object getIs_teenagers() {
            return is_teenagers;
        }

        public void setIs_teenagers(Object is_teenagers) {
            this.is_teenagers = is_teenagers;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }

        public Object getGroup_ids() {
            return group_ids;
        }

        public void setGroup_ids(Object group_ids) {
            this.group_ids = group_ids;
        }

        public int getConnect_peoplenum() {
            return connect_peoplenum;
        }

        public void setConnect_peoplenum(int connect_peoplenum) {
            this.connect_peoplenum = connect_peoplenum;
        }

        public  class MapBean {
            /**
             * groupinfo : {"entry_password":"","show_time":"","fileds":"","sortName":"","tickets":0,"online_num":1,"owner_id":9099,"fee_standard":0,"orderBy":"","pageSize":10,"recommend_type":"","language":"Japanese","group_type":"Public","idList":[],"type":"online_video","age_grade":"Childhood","notification":"","update_time":"2020-01-13 15:57:54","people_num":0,"advance_group":false,"is_charge":false,"number_participants":1,"id":897,"map":null,"introduction":"","create_time":"2020-01-13 15:57:54","startRow":0,"group_name":"回家看","is_open":true,"totalItem":0,"totalPage":0,"language_level":"","is_life":false,"watch_duration":0,"label":"话题交流/自我介绍","operator_account":"","live_push_addr":"","faceUrl":"http://img.curiousmore.com/CAB-378A08DEFFB3/Documents/1huihe1578902318.png","earnings":0,"group_id":"1578902274","live_duration":0,"currentPage":0,"status":1}
             */

            private MessageGroupEntity groupinfo;

            public MessageGroupEntity getGroupinfo() {
                return groupinfo;
            }

            public void setGroupinfo(MessageGroupEntity groupinfo) {
                this.groupinfo = groupinfo;
            }
        }
    }
}
