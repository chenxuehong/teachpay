package com.huihe.base_lib.model.push;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonResult;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;

import java.util.List;

public class NoteMessageModel extends JsonResult<NoteMessageModel.NoteMessageEntity> {

    public static class NoteMessageEntity{

        private List<NotenewsBean> notenews;


        public List<NotenewsBean> getNotenews() {
            return notenews;
        }

        public void setNotenews(List<NotenewsBean> notenews) {
            this.notenews = notenews;
        }

        public static class NotenewsBean {
            /**
             * new_time : 2020-03-18 12:05:37
             * noteinfo : {"comment_count":7,"video_duration":"","fileds":"","sortName":"","orderBy":"","pageSize":10,"content":"新华社日内瓦3月14日电（记者刘曲）世界卫生组织14日公布的最新数据显示，中国以外新冠肺炎确诊病例达到61518例。","cover":"","is_read":false,"picts":"","watch_count":0,"classfiy":"其他","id":3479,"oper_id":0,"map":{"likeinfo":[{"country":"中国","mail":"","registr_num":0,"rating":0,"local_time":1584354513871,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":false,"create_time":1554712462000,"is_member":false,"is_mobile":false,"is_high_quality":true,"preference":"交友拍拖","birth":811526400000,"is_id":false,"overseas_identity_name":"境外工作人员","online_state":0,"message_num":0,"is_mail":false,"user_id":4260,"admin_id":0,"nick_name":"唐世鹏","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Spanish","city":"天津","signature":"","qrcode":"","is_robot":true,"duration":0,"update_time":1584325713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":8,"hometown":"阿富汗·马扎里沙里夫","background_pic":"http://img.curiousmore.com/1584354512798.jpg","languages":"中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","sex":1,"distrib_qrcode":"","mobile":"13956398572","avatar":"http://img.curiousmore.com/1584104232184.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","member_level":0,"log_out_time":1584131023000,"cat_coin":"9.000","identity_auth":false,"is_unread":false},{"country":"中国","mail":"","registr_num":8,"rating":0,"local_time":1583336842657,"intro":"再艰难，只要内心坚定，一定会越来越好的","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2390,"is_help":true,"create_time":1554740185000,"is_member":true,"is_mobile":false,"is_high_quality":false,"preference":"语言学习","birth":745459200000,"is_id":false,"overseas_identity_name":"本国境友","online_state":1,"message_num":0,"is_mail":false,"user_id":36,"admin_id":0,"nick_name":"后浪","overseas_auth":false,"chatting_count":45,"invite_code":"","like_category":"","mother_tongue":"Chinese","city":"江干区","signature":"跨境、境外社交领导者","qrcode":"http://www.huihejituan.com/tripPictstorage/user/36/qrcode/1bcc6997-ff34-30bd-9df7-a5f19a138e63_200_200.jpg","is_robot":false,"duration":0,"update_time":1583336842000,"is_teenagers":false,"lable":"{帅哥,旅游达人}","account_state":1,"like_num":2,"hometown":"德兴市","background_pic":"http://img.curiousmore.com/086D98D0-755F-44E8-AA3C-3A26177193A3/Documents/1huihe1557333807.png","languages":"英语/中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/China.png","sex":1,"distrib_qrcode":"http://www.huihejituan.com/tripPictstorage/user/36/qrcode/a134e723-a93a-3906-9f0d-af2784b201bc_200_200.jpg","mobile":"18658876977","avatar":"http://img.curiousmore.com/D32-A583E15C3A5C/Documents/1huihe1583337046.png","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=36","member_level":3,"log_out_time":1577251098000,"cat_coin":"988924.000","identity_auth":false,"is_unread":false}],"noteCommentEntities":[{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-17 04:44:17","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"哦哦哦！","is_reply":false,"update_time":"2020-03-17 04:44:17","reply_id":9191,"user_id":9191,"parent_id":0,"id":622,"oper_id":0,"currentPage":0,"map":{"replyinfo":null,"userinfo":{"country":"中国","mail":"1319817447@qq.com","registr_num":0,"rating":0,"local_time":1584245713336,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":true,"create_time":1564128114000,"is_member":false,"is_mobile":false,"is_high_quality":false,"preference":"交友拍拖","birth":1546272000000,"is_id":false,"overseas_identity_name":"学生","online_state":1,"message_num":0,"is_mail":false,"user_id":9191,"admin_id":0,"nick_name":"kitychen","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Chinese","city":"北京","signature":"","qrcode":"","is_robot":false,"duration":0,"update_time":1584245713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":15,"hometown":"中国·北京","background_pic":"http://img.curiousmore.com/1583322403182.jpg","languages":"","national_flag":"","sex":1,"distrib_qrcode":"","mobile":"18106548078","avatar":"http://img.curiousmore.com/1583727854606.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","member_level":0,"log_out_time":1584065885000,"cat_coin":"569.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-17 04:44:11","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"哦哦好纠结坎坎坷坷军火车站","is_reply":false,"update_time":"2020-03-17 04:44:11","reply_id":9191,"user_id":9191,"parent_id":0,"id":621,"oper_id":0,"currentPage":0,"map":{"replyinfo":null,"userinfo":{"country":"中国","mail":"1319817447@qq.com","registr_num":0,"rating":0,"local_time":1584245713336,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":true,"create_time":1564128114000,"is_member":false,"is_mobile":false,"is_high_quality":false,"preference":"交友拍拖","birth":1546272000000,"is_id":false,"overseas_identity_name":"学生","online_state":1,"message_num":0,"is_mail":false,"user_id":9191,"admin_id":0,"nick_name":"kitychen","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Chinese","city":"北京","signature":"","qrcode":"","is_robot":false,"duration":0,"update_time":1584245713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":15,"hometown":"中国·北京","background_pic":"http://img.curiousmore.com/1583322403182.jpg","languages":"","national_flag":"","sex":1,"distrib_qrcode":"","mobile":"18106548078","avatar":"http://img.curiousmore.com/1583727854606.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","member_level":0,"log_out_time":1584065885000,"cat_coin":"569.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-17 04:44:01","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"哦哦哦","is_reply":false,"update_time":"2020-03-17 04:44:01","reply_id":9191,"user_id":9191,"parent_id":0,"id":620,"oper_id":0,"currentPage":0,"map":{"replyinfo":null,"userinfo":{"country":"中国","mail":"1319817447@qq.com","registr_num":0,"rating":0,"local_time":1584245713336,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":true,"create_time":1564128114000,"is_member":false,"is_mobile":false,"is_high_quality":false,"preference":"交友拍拖","birth":1546272000000,"is_id":false,"overseas_identity_name":"学生","online_state":1,"message_num":0,"is_mail":false,"user_id":9191,"admin_id":0,"nick_name":"kitychen","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Chinese","city":"北京","signature":"","qrcode":"","is_robot":false,"duration":0,"update_time":1584245713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":15,"hometown":"中国·北京","background_pic":"http://img.curiousmore.com/1583322403182.jpg","languages":"","national_flag":"","sex":1,"distrib_qrcode":"","mobile":"18106548078","avatar":"http://img.curiousmore.com/1583727854606.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","member_level":0,"log_out_time":1584065885000,"cat_coin":"569.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-16 10:27:25","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"好","is_reply":false,"update_time":"2020-03-16 10:27:25","reply_id":9191,"user_id":4260,"parent_id":0,"id":609,"oper_id":0,"currentPage":0,"map":{"replyinfo":null,"userinfo":{"country":"中国","mail":"","registr_num":0,"rating":0,"local_time":1584354513871,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":false,"create_time":1554712462000,"is_member":false,"is_mobile":false,"is_high_quality":true,"preference":"交友拍拖","birth":811526400000,"is_id":false,"overseas_identity_name":"境外工作人员","online_state":0,"message_num":0,"is_mail":false,"user_id":4260,"admin_id":0,"nick_name":"唐世鹏","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Spanish","city":"天津","signature":"","qrcode":"","is_robot":true,"duration":0,"update_time":1584325713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":8,"hometown":"阿富汗·马扎里沙里夫","background_pic":"http://img.curiousmore.com/1584354512798.jpg","languages":"中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","sex":1,"distrib_qrcode":"","mobile":"13956398572","avatar":"http://img.curiousmore.com/1584104232184.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","member_level":0,"log_out_time":1584131023000,"cat_coin":"9.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-16 10:26:36","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"哦哦好纠结坎坎坷坷军火","is_reply":false,"update_time":"2020-03-16 10:26:36","reply_id":9191,"user_id":4260,"parent_id":0,"id":607,"oper_id":0,"currentPage":0,"map":{"replyinfo":null,"userinfo":{"country":"中国","mail":"","registr_num":0,"rating":0,"local_time":1584354513871,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":false,"create_time":1554712462000,"is_member":false,"is_mobile":false,"is_high_quality":true,"preference":"交友拍拖","birth":811526400000,"is_id":false,"overseas_identity_name":"境外工作人员","online_state":0,"message_num":0,"is_mail":false,"user_id":4260,"admin_id":0,"nick_name":"唐世鹏","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Spanish","city":"天津","signature":"","qrcode":"","is_robot":true,"duration":0,"update_time":1584325713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":8,"hometown":"阿富汗·马扎里沙里夫","background_pic":"http://img.curiousmore.com/1584354512798.jpg","languages":"中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","sex":1,"distrib_qrcode":"","mobile":"13956398572","avatar":"http://img.curiousmore.com/1584104232184.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","member_level":0,"log_out_time":1584131023000,"cat_coin":"9.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-16 10:26:23","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"U","is_reply":false,"update_time":"2020-03-16 10:26:23","reply_id":9191,"user_id":4260,"parent_id":0,"id":606,"oper_id":0,"currentPage":0,"map":{"replyinfo":null,"userinfo":{"country":"中国","mail":"","registr_num":0,"rating":0,"local_time":1584354513871,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":false,"create_time":1554712462000,"is_member":false,"is_mobile":false,"is_high_quality":true,"preference":"交友拍拖","birth":811526400000,"is_id":false,"overseas_identity_name":"境外工作人员","online_state":0,"message_num":0,"is_mail":false,"user_id":4260,"admin_id":0,"nick_name":"唐世鹏","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Spanish","city":"天津","signature":"","qrcode":"","is_robot":true,"duration":0,"update_time":1584325713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":8,"hometown":"阿富汗·马扎里沙里夫","background_pic":"http://img.curiousmore.com/1584354512798.jpg","languages":"中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","sex":1,"distrib_qrcode":"","mobile":"13956398572","avatar":"http://img.curiousmore.com/1584104232184.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","member_level":0,"log_out_time":1584131023000,"cat_coin":"9.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-16 10:26:14","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"好么","is_reply":false,"update_time":"2020-03-16 10:26:14","reply_id":9191,"user_id":4260,"parent_id":0,"id":605,"oper_id":0,"currentPage":0,"map":{"replyinfo":null,"userinfo":{"country":"中国","mail":"","registr_num":0,"rating":0,"local_time":1584354513871,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":false,"create_time":1554712462000,"is_member":false,"is_mobile":false,"is_high_quality":true,"preference":"交友拍拖","birth":811526400000,"is_id":false,"overseas_identity_name":"境外工作人员","online_state":0,"message_num":0,"is_mail":false,"user_id":4260,"admin_id":0,"nick_name":"唐世鹏","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Spanish","city":"天津","signature":"","qrcode":"","is_robot":true,"duration":0,"update_time":1584325713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":8,"hometown":"阿富汗·马扎里沙里夫","background_pic":"http://img.curiousmore.com/1584354512798.jpg","languages":"中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","sex":1,"distrib_qrcode":"","mobile":"13956398572","avatar":"http://img.curiousmore.com/1584104232184.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","member_level":0,"log_out_time":1584131023000,"cat_coin":"9.000","identity_auth":false,"is_unread":false}},"status":1}],"is_like":false,"giftinfo":[{"country":"中国","mail":"","registr_num":8,"rating":0,"local_time":1583336842657,"intro":"再艰难，只要内心坚定，一定会越来越好的","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2390,"is_help":true,"create_time":1554740185000,"is_member":true,"is_mobile":false,"is_high_quality":false,"preference":"语言学习","birth":745459200000,"is_id":false,"overseas_identity_name":"本国境友","online_state":1,"message_num":0,"is_mail":false,"user_id":36,"admin_id":0,"nick_name":"后浪","overseas_auth":false,"chatting_count":45,"invite_code":"","like_category":"","mother_tongue":"Chinese","city":"江干区","signature":"跨境、境外社交领导者","qrcode":"http://www.huihejituan.com/tripPictstorage/user/36/qrcode/1bcc6997-ff34-30bd-9df7-a5f19a138e63_200_200.jpg","is_robot":false,"duration":0,"update_time":1583336842000,"is_teenagers":false,"lable":"{帅哥,旅游达人}","account_state":1,"like_num":2,"hometown":"德兴市","background_pic":"http://img.curiousmore.com/086D98D0-755F-44E8-AA3C-3A26177193A3/Documents/1huihe1557333807.png","languages":"英语/中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/China.png","sex":1,"distrib_qrcode":"http://www.huihejituan.com/tripPictstorage/user/36/qrcode/a134e723-a93a-3906-9f0d-af2784b201bc_200_200.jpg","mobile":"18658876977","avatar":"http://img.curiousmore.com/D32-A583E15C3A5C/Documents/1huihe1583337046.png","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=36","member_level":3,"log_out_time":1577251098000,"cat_coin":"988924.000","identity_auth":false,"is_unread":false}],"giftCount":1,"noteDetailsComment":[{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-17 04:44:17","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"哦哦哦！","is_reply":false,"update_time":"2020-03-17 04:44:17","reply_id":9191,"user_id":9191,"parent_id":0,"id":622,"oper_id":0,"currentPage":0,"map":{"is_like":false,"subComment":[],"subcount":0,"userinfo":{"country":"中国","mail":"1319817447@qq.com","registr_num":0,"rating":0,"local_time":1584245713336,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":true,"create_time":1564128114000,"is_member":false,"is_mobile":false,"is_high_quality":false,"preference":"交友拍拖","birth":1546272000000,"is_id":false,"overseas_identity_name":"学生","online_state":1,"message_num":0,"is_mail":false,"user_id":9191,"admin_id":0,"nick_name":"kitychen","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Chinese","city":"北京","signature":"","qrcode":"","is_robot":false,"duration":0,"update_time":1584245713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":15,"hometown":"中国·北京","background_pic":"http://img.curiousmore.com/1583322403182.jpg","languages":"","national_flag":"","sex":1,"distrib_qrcode":"","mobile":"18106548078","avatar":"http://img.curiousmore.com/1583727854606.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","member_level":0,"log_out_time":1584065885000,"cat_coin":"569.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-17 04:44:11","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"哦哦好纠结坎坎坷坷军火车站","is_reply":false,"update_time":"2020-03-17 04:44:11","reply_id":9191,"user_id":9191,"parent_id":0,"id":621,"oper_id":0,"currentPage":0,"map":{"is_like":false,"subComment":[],"subcount":0,"userinfo":{"country":"中国","mail":"1319817447@qq.com","registr_num":0,"rating":0,"local_time":1584245713336,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":true,"create_time":1564128114000,"is_member":false,"is_mobile":false,"is_high_quality":false,"preference":"交友拍拖","birth":1546272000000,"is_id":false,"overseas_identity_name":"学生","online_state":1,"message_num":0,"is_mail":false,"user_id":9191,"admin_id":0,"nick_name":"kitychen","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Chinese","city":"北京","signature":"","qrcode":"","is_robot":false,"duration":0,"update_time":1584245713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":15,"hometown":"中国·北京","background_pic":"http://img.curiousmore.com/1583322403182.jpg","languages":"","national_flag":"","sex":1,"distrib_qrcode":"","mobile":"18106548078","avatar":"http://img.curiousmore.com/1583727854606.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","member_level":0,"log_out_time":1584065885000,"cat_coin":"569.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-17 04:44:01","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"哦哦哦","is_reply":false,"update_time":"2020-03-17 04:44:01","reply_id":9191,"user_id":9191,"parent_id":0,"id":620,"oper_id":0,"currentPage":0,"map":{"is_like":false,"subComment":[],"subcount":0,"userinfo":{"country":"中国","mail":"1319817447@qq.com","registr_num":0,"rating":0,"local_time":1584245713336,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":true,"create_time":1564128114000,"is_member":false,"is_mobile":false,"is_high_quality":false,"preference":"交友拍拖","birth":1546272000000,"is_id":false,"overseas_identity_name":"学生","online_state":1,"message_num":0,"is_mail":false,"user_id":9191,"admin_id":0,"nick_name":"kitychen","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Chinese","city":"北京","signature":"","qrcode":"","is_robot":false,"duration":0,"update_time":1584245713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":15,"hometown":"中国·北京","background_pic":"http://img.curiousmore.com/1583322403182.jpg","languages":"","national_flag":"","sex":1,"distrib_qrcode":"","mobile":"18106548078","avatar":"http://img.curiousmore.com/1583727854606.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","member_level":0,"log_out_time":1584065885000,"cat_coin":"569.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-16 10:27:25","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"好","is_reply":false,"update_time":"2020-03-16 10:27:25","reply_id":9191,"user_id":4260,"parent_id":0,"id":609,"oper_id":0,"currentPage":0,"map":{"is_like":false,"subComment":[],"subcount":0,"userinfo":{"country":"中国","mail":"","registr_num":0,"rating":0,"local_time":1584354513871,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":false,"create_time":1554712462000,"is_member":false,"is_mobile":false,"is_high_quality":true,"preference":"交友拍拖","birth":811526400000,"is_id":false,"overseas_identity_name":"境外工作人员","online_state":0,"message_num":0,"is_mail":false,"user_id":4260,"admin_id":0,"nick_name":"唐世鹏","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Spanish","city":"天津","signature":"","qrcode":"","is_robot":true,"duration":0,"update_time":1584325713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":8,"hometown":"阿富汗·马扎里沙里夫","background_pic":"http://img.curiousmore.com/1584354512798.jpg","languages":"中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","sex":1,"distrib_qrcode":"","mobile":"13956398572","avatar":"http://img.curiousmore.com/1584104232184.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","member_level":0,"log_out_time":1584131023000,"cat_coin":"9.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-16 10:26:36","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"哦哦好纠结坎坎坷坷军火","is_reply":false,"update_time":"2020-03-16 10:26:36","reply_id":9191,"user_id":4260,"parent_id":0,"id":607,"oper_id":0,"currentPage":0,"map":{"is_like":false,"subComment":[],"subcount":0,"userinfo":{"country":"中国","mail":"","registr_num":0,"rating":0,"local_time":1584354513871,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":false,"create_time":1554712462000,"is_member":false,"is_mobile":false,"is_high_quality":true,"preference":"交友拍拖","birth":811526400000,"is_id":false,"overseas_identity_name":"境外工作人员","online_state":0,"message_num":0,"is_mail":false,"user_id":4260,"admin_id":0,"nick_name":"唐世鹏","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Spanish","city":"天津","signature":"","qrcode":"","is_robot":true,"duration":0,"update_time":1584325713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":8,"hometown":"阿富汗·马扎里沙里夫","background_pic":"http://img.curiousmore.com/1584354512798.jpg","languages":"中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","sex":1,"distrib_qrcode":"","mobile":"13956398572","avatar":"http://img.curiousmore.com/1584104232184.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","member_level":0,"log_out_time":1584131023000,"cat_coin":"9.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-16 10:26:23","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"U","is_reply":false,"update_time":"2020-03-16 10:26:23","reply_id":9191,"user_id":4260,"parent_id":0,"id":606,"oper_id":0,"currentPage":0,"map":{"is_like":false,"subComment":[],"subcount":0,"userinfo":{"country":"中国","mail":"","registr_num":0,"rating":0,"local_time":1584354513871,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":false,"create_time":1554712462000,"is_member":false,"is_mobile":false,"is_high_quality":true,"preference":"交友拍拖","birth":811526400000,"is_id":false,"overseas_identity_name":"境外工作人员","online_state":0,"message_num":0,"is_mail":false,"user_id":4260,"admin_id":0,"nick_name":"唐世鹏","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Spanish","city":"天津","signature":"","qrcode":"","is_robot":true,"duration":0,"update_time":1584325713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":8,"hometown":"阿富汗·马扎里沙里夫","background_pic":"http://img.curiousmore.com/1584354512798.jpg","languages":"中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","sex":1,"distrib_qrcode":"","mobile":"13956398572","avatar":"http://img.curiousmore.com/1584104232184.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","member_level":0,"log_out_time":1584131023000,"cat_coin":"9.000","identity_auth":false,"is_unread":false}},"status":1},{"note_id":3479,"fileds":"","like_count":0,"sortName":"","create_time":"2020-03-16 10:26:14","startRow":0,"totalItem":0,"totalPage":0,"orderBy":"","pageSize":10,"content":"好么","is_reply":false,"update_time":"2020-03-16 10:26:14","reply_id":9191,"user_id":4260,"parent_id":0,"id":605,"oper_id":0,"currentPage":0,"map":{"is_like":false,"subComment":[],"subcount":0,"userinfo":{"country":"中国","mail":"","registr_num":0,"rating":0,"local_time":1584354513871,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":false,"create_time":1554712462000,"is_member":false,"is_mobile":false,"is_high_quality":true,"preference":"交友拍拖","birth":811526400000,"is_id":false,"overseas_identity_name":"境外工作人员","online_state":0,"message_num":0,"is_mail":false,"user_id":4260,"admin_id":0,"nick_name":"唐世鹏","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Spanish","city":"天津","signature":"","qrcode":"","is_robot":true,"duration":0,"update_time":1584325713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":8,"hometown":"阿富汗·马扎里沙里夫","background_pic":"http://img.curiousmore.com/1584354512798.jpg","languages":"中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","sex":1,"distrib_qrcode":"","mobile":"13956398572","avatar":"http://img.curiousmore.com/1584104232184.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","member_level":0,"log_out_time":1584131023000,"cat_coin":"9.000","identity_auth":false,"is_unread":false}},"status":1}],"userinfo":{"country":"中国","mail":"1319817447@qq.com","registr_num":0,"rating":0,"local_time":1584245713336,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":true,"create_time":1564128114000,"is_member":false,"is_mobile":false,"is_high_quality":false,"preference":"交友拍拖","birth":1546272000000,"is_id":false,"overseas_identity_name":"学生","online_state":1,"message_num":0,"is_mail":false,"user_id":9191,"admin_id":0,"nick_name":"kitychen","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Chinese","city":"北京","signature":"","qrcode":"","is_robot":false,"duration":0,"update_time":1584245713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":15,"hometown":"中国·北京","background_pic":"http://img.curiousmore.com/1583322403182.jpg","languages":"","national_flag":"","sex":1,"distrib_qrcode":"","mobile":"18106548078","avatar":"http://img.curiousmore.com/1583727854606.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","member_level":0,"log_out_time":1584065885000,"cat_coin":"569.000","identity_auth":false,"is_unread":false}},"like_count":2,"create_time":"2020-03-18 04:05:37","img_width":0,"startRow":0,"totalItem":0,"totalPage":0,"img_height":0,"collect_count":0,"url":"http://www.curiousmore.com:8768/eg-api/push/noteshare.html?note_id=3479","share_count":0,"user_id":9191,"location":"","style":1,"currentPage":0,"status":2}
             * type : notelike
             * userinfo : {"country":"中国","mail":"","registr_num":0,"rating":0,"local_time":1584354513871,"intro":"","advertising_position":false,"is_name":false,"oper_id":0,"cash":"0.000","contacts_num":2,"is_help":false,"create_time":1554712462000,"is_member":false,"is_mobile":false,"is_high_quality":true,"preference":"交友拍拖","birth":811526400000,"is_id":false,"overseas_identity_name":"境外工作人员","online_state":0,"message_num":0,"is_mail":false,"user_id":4260,"admin_id":0,"nick_name":"唐世鹏","overseas_auth":false,"chatting_count":0,"invite_code":"","like_category":"","mother_tongue":"Spanish","city":"天津","signature":"","qrcode":"","is_robot":true,"duration":0,"update_time":1584325713000,"is_teenagers":false,"lable":"","account_state":1,"like_num":8,"hometown":"阿富汗·马扎里沙里夫","background_pic":"http://img.curiousmore.com/1584354512798.jpg","languages":"中文","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","sex":1,"distrib_qrcode":"","mobile":"13956398572","avatar":"http://img.curiousmore.com/1584104232184.jpg","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","member_level":0,"log_out_time":1584131023000,"cat_coin":"9.000","identity_auth":false,"is_unread":false}
             */

            private String new_time;
            private NoteUserEntity noteinfo;
            private String type;
            private UserInfoEntity userinfo;

            public String getNew_time() {
                return new_time;
            }

            public void setNew_time(String new_time) {
                this.new_time = new_time;
            }

            public NoteUserEntity getNoteinfo() {
                return noteinfo;
            }

            public void setNoteinfo(NoteUserEntity noteinfo) {
                this.noteinfo = noteinfo;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public UserInfoEntity getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserInfoEntity userinfo) {
                this.userinfo = userinfo;
            }

            private Notecomment notecomment;

            public Notecomment getNotecomment() {
                return notecomment;
            }

            public void setNotecomment(Notecomment notecomment) {
                this.notecomment = notecomment;
            }

            public static class Notecomment{

                /**
                 * note_id : 3479
                 * fileds :
                 * like_count : 0
                 * sortName :
                 * create_time : null
                 * startRow : 0
                 * totalItem : 0
                 * totalPage : 0
                 * orderBy :
                 * pageSize : 10
                 * content : uii
                 * is_reply : false
                 * update_time : null
                 * reply_id : 0
                 * user_id : 4260
                 * parent_id : 0
                 * id : 0
                 * oper_id : 0
                 * currentPage : 0
                 * map : null
                 * status : 0
                 */

                private String note_id;
                private String fileds;
                private Integer like_count;
                private String sortName;
                private Object create_time;

                private String content;
                private boolean is_reply;
                private Object update_time;
                private String reply_id;
                private String user_id;
                private String parent_id;
                private String id;
                private String oper_id;
                private Object map;
                private Integer status;

                public String getNote_id() {
                    return note_id;
                }

                public void setNote_id(String note_id) {
                    this.note_id = note_id;
                }

                public String getFileds() {
                    return fileds;
                }

                public void setFileds(String fileds) {
                    this.fileds = fileds;
                }

                public Integer getLike_count() {
                    return like_count;
                }

                public void setLike_count(Integer like_count) {
                    this.like_count = like_count;
                }

                public String getSortName() {
                    return sortName;
                }

                public void setSortName(String sortName) {
                    this.sortName = sortName;
                }

                public Object getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(Object create_time) {
                    this.create_time = create_time;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public boolean isIs_reply() {
                    return is_reply;
                }

                public void setIs_reply(boolean is_reply) {
                    this.is_reply = is_reply;
                }

                public Object getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(Object update_time) {
                    this.update_time = update_time;
                }

                public String getReply_id() {
                    return reply_id;
                }

                public void setReply_id(String reply_id) {
                    this.reply_id = reply_id;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getParent_id() {
                    return parent_id;
                }

                public void setParent_id(String parent_id) {
                    this.parent_id = parent_id;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getOper_id() {
                    return oper_id;
                }

                public void setOper_id(String oper_id) {
                    this.oper_id = oper_id;
                }


                public Object getMap() {
                    return map;
                }

                public void setMap(Object map) {
                    this.map = map;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }

        }
    }
}
