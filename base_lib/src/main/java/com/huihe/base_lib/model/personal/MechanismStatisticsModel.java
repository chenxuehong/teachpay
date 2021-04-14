package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class MechanismStatisticsModel extends JsonListResult<MechanismStatisticsModel.MechanismStatisticsEntity> {
    public static class MechanismStatisticsEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 9
         * create_time : 2020-07-01 10:51:06
         * update_time : 2020-07-01 15:40:48
         * mechanism_name : 英杰外语培训班
         * user_id : 4260
         * mechanism_addr : 杭州市杭海路601号三堡产业大厦-A座-1509
         * mechanism_logo : http://img.huihejituan.com/1593572181822.png
         * mechanism_telephone : 13093793169
         * mechanism_language : null/null
         * mechanism_advantage : 鱼鱼鱼，隐隐约约，古古怪怪
         * introduction_pic : http://img.huihejituan.com/1593572182677.jpg,http://img.huihejituan.com/1593572183525.jpg
         * introduction_content : 呼呼呼吸
         * contacts : 陈小姐
         * contact_telephone : 138672660941
         * contact_information :
         * contacts_title : 英语外教
         * status : 2
         * support_means : http://img.huihejituan.com/1593572184303.jpg
         * mechanism_no : 1593571866763
         * sort_weight : 0
         * latitude : 30.2758686432
         * longitude : 120.2342040143
         * map : {"open_class_num":0,"private_num":0,"sale_total_num":1,"teachers_num":1,"history_total_num":0,"single_class_history_num":0,"cross_border_num":0,"jointly_class_num":0,"single_class_num":0,"private_educationCount":0,"mechanism_offline_num":1,"live_num":0,"major_master_num":0,"private_education_historyCount":0,"schedule_total_num":0,"mother_tongue_num":0,"open_class_history_num":0}
         * type :
         */

        private String id;
        private String create_time;
        private String update_time;
        private String mechanism_name;
        private String user_id;
        private String mechanism_addr;
        private String mechanism_logo;
        private String mechanism_telephone;
        private String mechanism_language;
        private String mechanism_advantage;
        private String introduction_pic;
        private String introduction_content;
        private String contacts;
        private String contact_telephone;
        private String contact_information;
        private String contacts_title;
        private Integer status;
        private String support_means;
        private String mechanism_no;
        private double latitude;
        private double longitude;
        private MapBean map;
        private String type;

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

        public String getMechanism_name() {
            return mechanism_name;
        }

        public void setMechanism_name(String mechanism_name) {
            this.mechanism_name = mechanism_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMechanism_addr() {
            return mechanism_addr;
        }

        public void setMechanism_addr(String mechanism_addr) {
            this.mechanism_addr = mechanism_addr;
        }

        public String getMechanism_logo() {
            return mechanism_logo;
        }

        public void setMechanism_logo(String mechanism_logo) {
            this.mechanism_logo = mechanism_logo;
        }

        public String getMechanism_telephone() {
            return mechanism_telephone;
        }

        public void setMechanism_telephone(String mechanism_telephone) {
            this.mechanism_telephone = mechanism_telephone;
        }

        public String getMechanism_language() {
            return mechanism_language;
        }

        public void setMechanism_language(String mechanism_language) {
            this.mechanism_language = mechanism_language;
        }

        public String getMechanism_advantage() {
            return mechanism_advantage;
        }

        public void setMechanism_advantage(String mechanism_advantage) {
            this.mechanism_advantage = mechanism_advantage;
        }

        public String getIntroduction_pic() {
            return introduction_pic;
        }

        public void setIntroduction_pic(String introduction_pic) {
            this.introduction_pic = introduction_pic;
        }

        public String getIntroduction_content() {
            return introduction_content;
        }

        public void setIntroduction_content(String introduction_content) {
            this.introduction_content = introduction_content;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getContact_telephone() {
            return contact_telephone;
        }

        public void setContact_telephone(String contact_telephone) {
            this.contact_telephone = contact_telephone;
        }

        public String getContact_information() {
            return contact_information;
        }

        public void setContact_information(String contact_information) {
            this.contact_information = contact_information;
        }

        public String getContacts_title() {
            return contacts_title;
        }

        public void setContacts_title(String contacts_title) {
            this.contacts_title = contacts_title;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSupport_means() {
            return support_means;
        }

        public void setSupport_means(String support_means) {
            this.support_means = support_means;
        }

        public String getMechanism_no() {
            return mechanism_no;
        }

        public void setMechanism_no(String mechanism_no) {
            this.mechanism_no = mechanism_no;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class MapBean {
            /**
             * open_class_num : 0
             * private_num : 0
             * sale_total_num : 1
             * teachers_num : 1
             * history_total_num : 0
             * single_class_history_num : 0
             * cross_border_num : 0
             * jointly_class_num : 0
             * single_class_num : 0
             * private_educationCount : 0
             * mechanism_offline_num : 1
             * live_num : 0
             * major_master_num : 0
             * private_education_historyCount : 0
             * schedule_total_num : 0
             * mother_tongue_num : 0
             * open_class_history_num : 0
             */

            private String open_class_num;
            private String private_num;
            private String sale_total_num;
            private String teachers_num;
            private String history_total_num;
            private String single_class_history_num;
            private String cross_border_num;
            private String jointly_class_num;
            private String single_class_num;
            private String private_educationCount;
            private String mechanism_offline_num;
            private String live_num;
            private String major_master_num;
            private String private_education_historyCount;
            private String schedule_total_num;
            private String mother_tongue_num;
            private String open_class_history_num;
            private String private_educationEarnings;
            private String mechanism_offlineEarnings;
            private String liveEarnings;
            private String masterEarnings;
            private String totalEarnings;

            public String getPrivate_educationEarnings() {
                return private_educationEarnings;
            }

            public String getMechanism_offlineEarnings() {
                return mechanism_offlineEarnings;
            }

            public String getLiveEarnings() {
                return liveEarnings;
            }

            public String getMasterEarnings() {
                return masterEarnings;
            }

            public String getTotalEarnings() {
                return totalEarnings;
            }

            public String getOpen_class_num() {
                return open_class_num;
            }

            public void setOpen_class_num(String open_class_num) {
                this.open_class_num = open_class_num;
            }

            public String getPrivate_num() {
                return private_num;
            }

            public void setPrivate_num(String private_num) {
                this.private_num = private_num;
            }

            public String getSale_total_num() {
                return sale_total_num;
            }

            public void setSale_total_num(String sale_total_num) {
                this.sale_total_num = sale_total_num;
            }

            public String getTeachers_num() {
                return teachers_num;
            }

            public void setTeachers_num(String teachers_num) {
                this.teachers_num = teachers_num;
            }

            public String getHistory_total_num() {
                return history_total_num;
            }

            public void setHistory_total_num(String history_total_num) {
                this.history_total_num = history_total_num;
            }

            public String getSingle_class_history_num() {
                return single_class_history_num;
            }

            public void setSingle_class_history_num(String single_class_history_num) {
                this.single_class_history_num = single_class_history_num;
            }

            public String getCross_border_num() {
                return cross_border_num;
            }

            public void setCross_border_num(String cross_border_num) {
                this.cross_border_num = cross_border_num;
            }

            public String getJointly_class_num() {
                return jointly_class_num;
            }

            public void setJointly_class_num(String jointly_class_num) {
                this.jointly_class_num = jointly_class_num;
            }

            public String getSingle_class_num() {
                return single_class_num;
            }

            public void setSingle_class_num(String single_class_num) {
                this.single_class_num = single_class_num;
            }

            public String getPrivate_educationCount() {
                return private_educationCount;
            }

            public void setPrivate_educationCount(String private_educationCount) {
                this.private_educationCount = private_educationCount;
            }

            public String getMechanism_offline_num() {
                return mechanism_offline_num;
            }

            public void setMechanism_offline_num(String mechanism_offline_num) {
                this.mechanism_offline_num = mechanism_offline_num;
            }

            public String getLive_num() {
                return live_num;
            }

            public void setLive_num(String live_num) {
                this.live_num = live_num;
            }

            public String getMajor_master_num() {
                return major_master_num;
            }

            public void setMajor_master_num(String major_master_num) {
                this.major_master_num = major_master_num;
            }

            public String getPrivate_education_historyCount() {
                return private_education_historyCount;
            }

            public void setPrivate_education_historyCount(String private_education_historyCount) {
                this.private_education_historyCount = private_education_historyCount;
            }

            public String getSchedule_total_num() {
                return schedule_total_num;
            }

            public void setSchedule_total_num(String schedule_total_num) {
                this.schedule_total_num = schedule_total_num;
            }

            public String getMother_tongue_num() {
                return mother_tongue_num;
            }

            public void setMother_tongue_num(String mother_tongue_num) {
                this.mother_tongue_num = mother_tongue_num;
            }

            public String getOpen_class_history_num() {
                return open_class_history_num;
            }

            public void setOpen_class_history_num(String open_class_history_num) {
                this.open_class_history_num = open_class_history_num;
            }
        }
    }
}
