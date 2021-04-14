package com.huihe.base_lib.model.home;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;

import java.util.List;

public class GroupRecordingModel extends JsonListResult<GroupRecordingModel.GroupRecordingEntity> {
    public static class GroupRecordingEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 21
         * group_id : 1178
         * create_time : 2020-04-16 17:03:36
         * update_time : 2020-04-16 17:03:36
         * record_url : http://1258876522.vod2.myqcloud.com/27687c90vodcq1258876522/484c2dc85285890801414790138/playlist.f5.mp4
         * video_format : mp4
         * cover :
         * user_id : 9191
         * start_time : 2020-04-16 17:00:00
         * end_time : 2020-04-16 17:30:00
         * appointment_id : 5853
         * stream_id : 1400255047_9191
         * status : 2
         * task_id : 77752325
         * map : {"appointmentinfo":{"master_id":9191,"fileds":"","sortName":"","orderBy":"","pageSize":10,"language":"Polish","group_type":"online_video","title":"话题交流/聊聊穿着","type":"open_class","age_grade":"Childhood","timecode":"","cover":"http://img.huihejituan.com/1587027204573.jpg","update_time":"2020-04-16 16:53:32","id":5853,"map":"","teach_language":"","is_appointment":false,"create_time":"2020-04-16 16:53:32","offset":8,"startRow":0,"totalItem":0,"totalPage":0,"language_level":"Zero basis","end_time":"2020-04-16 17:30:00","start_time":"2020-04-16 17:00:00","entities":"","group_id":1178,"timezone_id":324,"currentPage":0,"status":2},"groupVideoEntities":[{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":30,"video_format":"mp4","stream_id":"1400255047_9191","stream_param":"","record_file_id":"5285890801404036727","task_id":"77752325","video_url":"http://1258876522.vod2.myqcloud.com/27687c90vodcq1258876522/4b4d0d245285890801404036727/f0.mp4","status":2,"create_time":"2020-04-16 17:03:46","update_time":"2020-04-16 17:03:46"}],"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1584535512217.jpg","birth":1585843200000,"cash":"0.000","cat_coin":"198.000","chatting_count":0,"city":"爱尔巴桑","contacts_num":2,"country":"阿尔巴尼亚","create_time":1564099314000,"distrib_qrcode":"","duration":0,"hometown":"中国·北京","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1586322630711,"log_out_time":1584037085000,"mail":"1319817447@qq.com","member_level":0,"message_num":0,"mobile":"18106548078","mother_tongue":"Polish","national_flag":"","nick_name":"kitychen1","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1586293830000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}}
         * requestId : f1bfe721-56b5-4295-9e51-d6a11468835b
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
        private String group_id;
        private String create_time;
        private String update_time;
        private String record_url;
        private String video_format;
        private String cover;
        private String user_id;
        private String start_time;
        private String end_time;
        private String appointment_id;
        private String stream_id;
        private int status;
        private String task_id;
        private MapBean map;
        private String requestId;

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

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
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

        public String getRecord_url() {
            return record_url;
        }

        public void setRecord_url(String record_url) {
            this.record_url = record_url;
        }

        public String getVideo_format() {
            return video_format;
        }

        public void setVideo_format(String video_format) {
            this.video_format = video_format;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getAppointment_id() {
            return appointment_id;
        }

        public void setAppointment_id(String appointment_id) {
            this.appointment_id = appointment_id;
        }

        public String getStream_id() {
            return stream_id;
        }

        public void setStream_id(String stream_id) {
            this.stream_id = stream_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public static class MapBean {
            /**
             * appointmentinfo : {"master_id":9191,"fileds":"","sortName":"","orderBy":"","pageSize":10,"language":"Polish","group_type":"online_video","title":"话题交流/聊聊穿着","type":"open_class","age_grade":"Childhood","timecode":"","cover":"http://img.huihejituan.com/1587027204573.jpg","update_time":"2020-04-16 16:53:32","id":5853,"map":"","teach_language":"","is_appointment":false,"create_time":"2020-04-16 16:53:32","offset":8,"startRow":0,"totalItem":0,"totalPage":0,"language_level":"Zero basis","end_time":"2020-04-16 17:30:00","start_time":"2020-04-16 17:00:00","entities":"","group_id":1178,"timezone_id":324,"currentPage":0,"status":2}
             * groupVideoEntities : [{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":30,"video_format":"mp4","stream_id":"1400255047_9191","stream_param":"","record_file_id":"5285890801404036727","task_id":"77752325","video_url":"http://1258876522.vod2.myqcloud.com/27687c90vodcq1258876522/4b4d0d245285890801404036727/f0.mp4","status":2,"create_time":"2020-04-16 17:03:46","update_time":"2020-04-16 17:03:46"}]
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1584535512217.jpg","birth":1585843200000,"cash":"0.000","cat_coin":"198.000","chatting_count":0,"city":"爱尔巴桑","contacts_num":2,"country":"阿尔巴尼亚","create_time":1564099314000,"distrib_qrcode":"","duration":0,"hometown":"中国·北京","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1586322630711,"log_out_time":1584037085000,"mail":"1319817447@qq.com","member_level":0,"message_num":0,"mobile":"18106548078","mother_tongue":"Polish","national_flag":"","nick_name":"kitychen1","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1586293830000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}
             */

            private AppointmentinfoBean appointmentinfo;
            private UserInfoEntity userinfo;
            private List<GroupVideoEntitiesBean> groupVideoEntities;

            public AppointmentinfoBean getAppointmentinfo() {
                return appointmentinfo;
            }

            public void setAppointmentinfo(AppointmentinfoBean appointmentinfo) {
                this.appointmentinfo = appointmentinfo;
            }

            public UserInfoEntity getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserInfoEntity userinfo) {
                this.userinfo = userinfo;
            }

            public List<GroupVideoEntitiesBean> getGroupVideoEntities() {
                return groupVideoEntities;
            }

            public void setGroupVideoEntities(List<GroupVideoEntitiesBean> groupVideoEntities) {
                this.groupVideoEntities = groupVideoEntities;
            }

            public static class AppointmentinfoBean {
                /**
                 * master_id : 9191
                 * fileds :
                 * sortName :
                 * orderBy :
                 * pageSize : 10
                 * language : Polish
                 * group_type : online_video
                 * title : 话题交流/聊聊穿着
                 * type : open_class
                 * age_grade : Childhood
                 * timecode :
                 * cover : http://img.huihejituan.com/1587027204573.jpg
                 * update_time : 2020-04-16 16:53:32
                 * id : 5853
                 * map :
                 * teach_language :
                 * is_appointment : false
                 * create_time : 2020-04-16 16:53:32
                 * offset : 8
                 * startRow : 0
                 * totalItem : 0
                 * totalPage : 0
                 * language_level : Zero basis
                 * end_time : 2020-04-16 17:30:00
                 * start_time : 2020-04-16 17:00:00
                 * entities :
                 * group_id : 1178
                 * timezone_id : 324
                 * currentPage : 0
                 * status : 2
                 */

                private int master_id;
                private String fileds;
                private String sortName;
                private String orderBy;
                private int pageSize;
                private String language;
                private String group_type;
                private String title;
                private String type;
                private String age_grade;
                private String timecode;
                private String cover;
                private String update_time;
                private int id;
                private String map;
                private String teach_language;
                private boolean is_appointment;
                private String create_time;
                private Float offset;
                private int startRow;
                private int totalItem;
                private int totalPage;
                private String language_level;
                private String end_time;
                private String start_time;
                private String entities;
                private int group_id;
                private int timezone_id;
                private int currentPage;
                private int status;

                public int getMaster_id() {
                    return master_id;
                }

                public void setMaster_id(int master_id) {
                    this.master_id = master_id;
                }

                public String getFileds() {
                    return fileds;
                }

                public void setFileds(String fileds) {
                    this.fileds = fileds;
                }

                public String getSortName() {
                    return sortName;
                }

                public void setSortName(String sortName) {
                    this.sortName = sortName;
                }

                public String getOrderBy() {
                    return orderBy;
                }

                public void setOrderBy(String orderBy) {
                    this.orderBy = orderBy;
                }

                public int getPageSize() {
                    return pageSize;
                }

                public void setPageSize(int pageSize) {
                    this.pageSize = pageSize;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getMap() {
                    return map;
                }

                public void setMap(String map) {
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

                public int getStartRow() {
                    return startRow;
                }

                public void setStartRow(int startRow) {
                    this.startRow = startRow;
                }

                public int getTotalItem() {
                    return totalItem;
                }

                public void setTotalItem(int totalItem) {
                    this.totalItem = totalItem;
                }

                public int getTotalPage() {
                    return totalPage;
                }

                public void setTotalPage(int totalPage) {
                    this.totalPage = totalPage;
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

                public int getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(int group_id) {
                    this.group_id = group_id;
                }

                public int getTimezone_id() {
                    return timezone_id;
                }

                public void setTimezone_id(int timezone_id) {
                    this.timezone_id = timezone_id;
                }

                public int getCurrentPage() {
                    return currentPage;
                }

                public void setCurrentPage(int currentPage) {
                    this.currentPage = currentPage;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }

            public static class GroupVideoEntitiesBean {
                /**
                 * pageSize : 10
                 * currentPage : 0
                 * totalItem : 0
                 * startRow : 0
                 * sortName : null
                 * orderBy : null
                 * fileds : null
                 * totalPage : 0
                 * id : 30
                 * video_format : mp4
                 * stream_id : 1400255047_9191
                 * stream_param :
                 * record_file_id : 5285890801404036727
                 * task_id : 77752325
                 * video_url : http://1258876522.vod2.myqcloud.com/27687c90vodcq1258876522/4b4d0d245285890801404036727/f0.mp4
                 * status : 2
                 * create_time : 2020-04-16 17:03:46
                 * update_time : 2020-04-16 17:03:46
                 */

                private int pageSize;
                private int currentPage;
                private int totalItem;
                private int startRow;
                private Object sortName;
                private Object orderBy;
                private Object fileds;
                private int totalPage;
                private int id;
                private String video_format;
                private String stream_id;
                private String stream_param;
                private String record_file_id;
                private String task_id;
                private String video_url;
                private int status;
                private String create_time;
                private String update_time;

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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getVideo_format() {
                    return video_format;
                }

                public void setVideo_format(String video_format) {
                    this.video_format = video_format;
                }

                public String getStream_id() {
                    return stream_id;
                }

                public void setStream_id(String stream_id) {
                    this.stream_id = stream_id;
                }

                public String getStream_param() {
                    return stream_param;
                }

                public void setStream_param(String stream_param) {
                    this.stream_param = stream_param;
                }

                public String getRecord_file_id() {
                    return record_file_id;
                }

                public void setRecord_file_id(String record_file_id) {
                    this.record_file_id = record_file_id;
                }

                public String getTask_id() {
                    return task_id;
                }

                public void setTask_id(String task_id) {
                    this.task_id = task_id;
                }

                public String getVideo_url() {
                    return video_url;
                }

                public void setVideo_url(String video_url) {
                    this.video_url = video_url;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
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
            }
        }
    }
}
