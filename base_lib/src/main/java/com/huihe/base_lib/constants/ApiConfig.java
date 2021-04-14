package com.huihe.base_lib.constants;


/**
 * Created by Admin on 2017/2/21.
 */
public interface ApiConfig {

    //    String END_POINT = "https://curiousmore.com/qmore/";
//    String END_POINT = "http://192.168.1.103:8768/eg-api/";
    String END_POINT = "http://101.37.66.151:8768/eg-api/"; // 正式地址
//    String END_POINT = "http://139.186.169.185:8768/eg-api/"; // 测试地址

    interface User {
        String LOGIN = "user/user/login";
        String BANNER_DATA = "user/exhibitionPic/query";
        String UPDATE_INFO = "user/userInfo/update";
        String QUERY_MASTER_COMMENT_LIST_PAGE = "user/masterComment/queryListPage";
        String MASTER_COMMENT_DELETE = "user/masterComment/delete";
        String MASTER_COMMENT_INSERT = "user/masterComment/insert";
        String QUERY_SUMMARY_LIST = "user/masterSummary/querySummaryList";
        String INSERT_USERAPPOINTMENT = "user/userAppointment/insert";

        String PAY_ALIPAY = "user/pay/aliPay";
        String PAY_WXPAY = "user/pay/wxPay";
        String SMS_SEND = "user/sms/send";
        String VILIDATE_SMS = "user/sms/validate";

        String UPDATE_USER = "user/user/update";
        String USER_REGISTER = "user/user/register";
        String SEND_MAIL = "user/mail/send";
        String QUERY_USER_INFO = "user/userInfo/findById";
        String QUERY_USERCOMPANY = "user/userCompany/query";
        String USER_COMPANY_INSERT = "user/userCompany/insert";
        String USER_COMPANY_UPDATE = "user/userCompany/update";
        String USER_COMPANY_DELETE = "user/userCompany/delete";
        String USER_SCHOOL_QUERY_LISTPAGE = "user/userSchool/queryListPage";
        String USER_SCHOOL_INSERT = "user/userSchool/insert";
        String USER_SCHOOL_UPDATE = "user/userSchool/update";
        String USER_SCHOOL_DELETE = "user/userSchool/delete";

        String VALIDATE_EMAIL = "user/mail/validate";

        String USERDEVICE_INSERT = "user/userDevice/insert";
        String OPENID_APP_VERIFICATION = "user/user/openIdAppVerification";
        String OPENID_LOGIN_OR_REGIST = "user/user/openIdLoginOrRegist";
        String USERAPPOINTMENT_UPDATE = "user/userAppointment/update";
        String USERINFO_UPDATE_USERINFO_BYCODE = "user/userInfo/updateUserInfoByCode";

        String MASTERMECHANISM_INSERT = "user/mastermechanism/insert";
        String MASTERMECHANISM_UPDATE = "user/mastermechanism/update";
        String MASTERMECHANISM_QUERY = "user/mastermechanism/queryMechanismListPageNew";
        String MASTERMECHANISM_QUERY_MECHANISMLISTPAGE = "user/mastermechanism/queryMechanismListPageNew";

        String MASTER_SET_PRICE_INSERT = "user/masterSetPrice/insert";

        //        masterSetPrice/queryCommodityList==={
        String MASTER_SET_PRICE_QUERY_LIST_PAGE = "user/masterSetPrice/queryCourseListPage";
        String MASTER_SET_PRICE_QUERY_COMMODITY_LIST = "user/masterSetPrice/queryCommodityList";
        String MASTER_SET_PRICE_UPDATE_ACTIVITYINFO = "user/masterSetPrice/updateActivityInfo";
        String MASTER_SET_PRICE_UPDATE = "user/masterSetPrice/update";

        String USER_UPDATE = "user/user/update";
        String QUERY_MY_STUDYCORELIST = "user/userStudyCard/queryExclusiveCoursesList";
        String MASTERINFO_QUERY_LISTPAGE = "user/masterInfo/queryListPage";

        String MASTERSETPRICE_QUERYNEARBYCOURSE = "user/masterSetPrice/queryNearByCourse";
        String MASTERMECHANISM_QUERY_MECHANISMBYTYPE = "user/mastermechanism/queryMechanismByType";
        String RECHARGERECORD_PAYONECOURSE = "user/rechargerecord/payOneCourse";
        String USERFOLLOWMECHANISM_INSERT = "user/userFollowMechanism/insert";
        String USER_INSERT_MECHANISM_MASTER = "user/user/insertMechanismMaster";
        String USERSTUDYCARD_QUERYSTUDENTLIST = "user/userStudyCard/queryStudentList";
        String MASTER_APPOINTMENT_INSERTMECHANISMCOURSE = "user/masterAppointment/insertMechanismCourse";
        String MASTERAPPOINTMENT_QUERY_MECHANISMOFFLINESCHEDULE = "user/masterAppointment/queryMechanismOfflineSchedule";
        String MASTERAPPOINTMENT_INSERTOFFLINECOURSE = "user/masterAppointment/insertOfflineCourse";
        String MASTERSUMMARY_INSERT_SUMMARYOFFLINE = "user/masterSummary/insertSummaryOffline";
        String USERAPPOINTMENT_QUERY_OFFLINESCHEDULE = "user/userAppointment/queryOfflineSchedule";
        String RECHARGERECORD_QUERY_MECHANISMLIST = "user/rechargerecord/queryMechanismList";
        String MASTERCOMMENT_QUERY_MECHANISMAVERAGESCORE = "user/masterComment/queryMechanismAverageScore";
        String MASTERCOMMENT_QUERY_MECHANISMLIST = "user/masterComment/queryMechanismList";
        String USERCOUPON_INSERT_USERCOLLECTION = "user/userCoupon/insertUserCollection";
        String USERCOUPON_QUERYLISTPAGE = "user/userCoupon/queryListPage";
        String RECHARGERECORD_QUERY_PAYDETAILS = "user/rechargerecord/queryPayDetails";
        String PAY_PAYPRE = "user/pay/payPre";
        String RECHARGERECORD_QUERYLISTPAGE_BY_STATUS = "user/rechargerecord/queryListPageByStatus";
        String USERINFO_QUERYTEACHPAYUSERSTATISTICS = "user/userInfo/queryTeachPayUserStatistics";
        String SIGNIN_QUERYTEACHPAYPALDETAIL = "user/signIn/queryTeachPaypalDetail";
        String USERGOLDTYPE_QUERYTEACHPAYPAL = "user/userGoldType/queryTeachPaypal";
        String SIGNIN_INSERTLOGIN = "user/signIn/insertLogin";
        String USERSTUDYCARD_QUERYPAYINFO = "user/userStudyCard/queryPayInfo";
        String USERAPPOINTMENT_UPDATECANCELCOURSE = "user/userAppointment/updateCancelCourse";
        String USERPOINTS_QUERY_LISTPAGE = "user/userPoints/queryListPage";
        String RECHARGERECORD_QUERYMECHANISMOFFLINEDETAILSCOUNT = "user/rechargerecord/queryMechanismOfflineDetailsCount";
        String RECHARGERECORD_QUERYMECHANISMOFFLINEDETAILS = "user/rechargerecord/queryMechanismOfflineDetails";
        String MASTERSETPRICE_QUERY_ISCOURSE = "user/masterSetPrice/queryIsCourse";
        String MASTERINFO_UPDATEMECHANISMMASTERINFO = "user/masterInfo/updateMechanismMasterInfo";
        String USER_INSERTH5GETCOUPON = "user/user/insertH5GetCoupon";
        String MECHANISMCLASSROOM_INSERT = "user/mechanismClassroom/insert";
        String MECHANISMCLASSROOM_UPDATE = "user/mechanismClassroom/update";
        String MECHANISMCLASSROOM_QUERYMANAGERLISTPAGE = "user/mechanismClassroom/queryManagerListPage";
        String MECHANISMCLASSROOM_QUERYLISTPAGE = "user/mechanismClassroom/queryListPage";
        String USERGROUPING_INSERTUSERGROUPING = "user/userGrouping/insertUserGrouping";
        String MECHANISMUSER_QUERYLISTPAGE = "user/mechanismUser/queryListPage";
        String MECHANISMUSER_UPDATE = "user/mechanismUser/update";
        String MASTERAPPOINTMENT_DELETE = "user/masterAppointment/delete";
        String MECHANISMCATEGORY_QUERYLISTPAGECHILD = "user/mechanismCategory/queryListPageChild";
        String MASTER_SET_PRICE_QUERY = "user/masterSetPrice/query";
        String MECHANISMCLASSES_INSERT = "user/mechanismClasses/insert";
        String MECHANISMCLASSES_QUERYLISTPAGE = "user/mechanismClasses/queryListPage";
        String MECHANISMCLASSES_INSERTMECHANISMCOURSE = "user/mechanismClasses/insertMechanismCourse";
        String USERSTUDYCARD_QUERYSTUDENTINFO = "user/userStudyCard/queryStudentInfo";
        String USERAPPOINTMENT_UPDATEUSERCONFIRM = "user/userAppointment/updateUserConfirm";
        String MASTERAPPOINTMENT_QUERYSTATUSBYID = "user/masterAppointment/queryStatusById";
        String MECHANISMCLASSES_UPDATEMERGERCLASS = "user/mechanismClasses/updateMergerClass";
        String MASTERAPPOINTMENT_INSERTCOPYCOURSE = "user/masterAppointment/insertCopyCourse";
        String BUSINESSACTIVITYTYPE_QUERYLISTPAGE = "user/businessActivityType/queryListPage";
        String BUSINESSACTIVITY_INSERTACTIVITY = "user/businessActivity/insertActivity";
        String MASTERSETPRICE_QUERYACTIVITYLISTPAGEBYTYPE = "user/masterSetPrice/queryActivityListPageByType";
        String USERCOUPON_INSERTSTUDYCARDBYCOUP = "user/userCoupon/insertStudyCardByCoup";
        String MASTERSETPRICESTREAM_QUERYLISTPAGE = "user/masterSetPriceStream/queryListPage";
        String MASTERSETPRICESTREAM_INSERT = "user/masterSetPriceStream/insert";
        String MASTERSETPRICEDISPLAY_QUERY = "user/masterSetPrice/queryMechanismDisplay";
        String MASTERSETPRICEDISPLAY_INSERT = "user/masterSetPriceDisplay/insert";
        String MASTERSETPRICEDISPLAY_UPDATE = "user/masterSetPriceDisplay/update";
        String MASTERSETPRICESTREAM_UPDATEOPENLIVING = "user/masterSetPriceStream/updateOpenLiving";
        String MASTERSETPRICESTREAM_UPDATECLOSELIVING = "user/masterSetPriceStream/updateCloseLiving";
        String MASTERSETPRICESTREAM_QUERYDETAIL = "user/masterSetPriceStream/queryDetail";
        String RECHARGERECORD_QUERYPAYLIVESTREAMDETAIL = "user/rechargerecord/queryPayLiveStreamDetails";
        String MASTERSETPRICE_QUERYMECHANISMDISPLAYISLIVING = "user/masterSetPrice/queryMechanismDisplayIsLiving";
        String MASTERSETPRICESTREAM_UPDATEADDCLICK = "user/masterSetPriceStream/updateAddClick";
    }

    interface Message {
        String SYSTEM_FEEDBACK_INSERT = "message/systemFeedBack/insert";
        String NEWS_INFORMATION_INFO_LIST = "message/noteUser/queryListPage";
        String NOTEUSER_QUERY_NOTE_NEWS = "message/noteUser/queryNoteNews";
        String NOTE_USER_DELETE = "message/noteUser/delete";
        String INSERT_HISTORY = "message/history/insert";
        String INSERT_NOTE_COMMENT = "message/noteComment/insert";
        String QUERY_HISTORY = "message/history/query";
        String NOTE_USER_INSERT = "message/noteUser/insert";
        String QUERY_NOTECOMMENT_DETAILS_LISTPAGE = "message/noteComment/queryDetailsListPage";
        String HISTORY_LIST_QUERY = "message/history/queryListPage";
        String NOTE_COMMENT_DELETE = "message/noteComment/delete";
        String SYSTEM_VERSION_VERSIONITERATION = "message/systemVersion/versionIteration";
    }

    interface News {

    }

    interface Push {

    }


    int type = 0;//0:账户密码，1:支付密码

    boolean isPay = false;

    boolean needRefresh = true;
    boolean selectCityFromPerson = false;
    boolean inited = false;

    int isForciblyUpdate = 0;    //是否强制更新	number	1 强制更新 0 无更新内容 2有更新内容
    int isForciblyUpdateGov = 0;    //是否强制更新	number	1 强制更新 0 无更新内容 2有更新内容 ---政府端

    String versionUpdateDescription = "";     //版本更新描述	string
    String versionUpdateDescriptionGov = "";      //版本更新描述	string ---政府端

    String versionUpdateDescriptionUrl = "";      //新包下载地址	string
    String versionUpdateDescriptionGovUrl = "";   //新包下载地址	string ---政府端

    int watchContactCount = 10;
    int pageSize = 10;
}