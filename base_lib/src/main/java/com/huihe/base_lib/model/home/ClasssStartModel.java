package com.huihe.base_lib.model.home;

import com.huihe.base_lib.model.base.JsonResult;

public class ClasssStartModel extends JsonResult<ClasssStartModel.ClassStartEntity> {
    public static class ClassStartEntity{

        /**
         * master_id : 9191
         * fileds :
         * sortName :
         * orderBy :
         * pageSize : 10
         * language : Polish
         * group_type : online_video
         * title : Topic/Talk about music
         * type : open_class
         * age_grade : Childhood
         * timecode :
         * cover : http://img.huihejituan.com/1588900380086.jpg
         * update_time : 2020-05-08 09:13:04
         * is_teenagers : false
         * group_ids : []
         * id : 6237
         * map : null
         * teach_language :
         * is_appointment : false
         * create_time : 2020-05-08 09:13:04
         * offset : 8
         * startRow : 0
         * totalItem : 0
         * totalPage : 0
         * language_level : Zero basis
         * end_time : 2020-05-08 10:00:00
         * local_offset : 0
         * start_time : 2020-05-08 09:30:00
         * entities :
         * group_id : 1281
         * user_id : 0
         * timezone_id : 324
         * currentPage : 0
         * status : 3
         */

        private String master_id;
        private String language;
        private String group_type;
        private String title;
        private String type;
        private String age_grade;
        private String timecode;
        private String cover;
        private String update_time;
        private boolean is_teenagers;
        private String id;
        private Object map;
        private String teach_language;
        private boolean is_appointment;
        private String create_time;
        private Float offset;
        private String language_level;
        private String end_time;
        private String start_time;
        private String entities;
        private String group_id;
        private String user_id;
        private int status;

        public String getMaster_id() {
            return master_id;
        }

        public void setMaster_id(String master_id) {
            this.master_id = master_id;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getGroup_type() {
            return group_type;
        }

        public void setGroup_type(String group_type) {
            this.group_type = group_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAge_grade() {
            return age_grade;
        }

        public void setAge_grade(String age_grade) {
            this.age_grade = age_grade;
        }

        public String getTimecode() {
            return timecode;
        }

        public void setTimecode(String timecode) {
            this.timecode = timecode;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public boolean isIs_teenagers() {
            return is_teenagers;
        }

        public void setIs_teenagers(boolean is_teenagers) {
            this.is_teenagers = is_teenagers;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getMap() {
            return map;
        }

        public void setMap(Object map) {
            this.map = map;
        }

        public String getTeach_language() {
            return teach_language;
        }

        public void setTeach_language(String teach_language) {
            this.teach_language = teach_language;
        }

        public boolean isIs_appointment() {
            return is_appointment;
        }

        public void setIs_appointment(boolean is_appointment) {
            this.is_appointment = is_appointment;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Float getOffset() {
            return offset;
        }

        public void setOffset(Float offset) {
            this.offset = offset;
        }

        public String getLanguage_level() {
            return language_level;
        }

        public void setLanguage_level(String language_level) {
            this.language_level = language_level;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEntities() {
            return entities;
        }

        public void setEntities(String entities) {
            this.entities = entities;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
