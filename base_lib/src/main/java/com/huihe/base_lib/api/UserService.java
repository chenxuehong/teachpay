package com.huihe.base_lib.api;

import com.huihe.base_lib.constants.ApiConfig;
import com.huihe.base_lib.model.ApkVersion.VersionIterationModel;
import com.huihe.base_lib.model.BusinessActivityTypeModel;
import com.huihe.base_lib.model.ClassRoomModel;
import com.huihe.base_lib.model.ClassStudentModel;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.LiveDetailModel;
import com.huihe.base_lib.model.LiveModel;
import com.huihe.base_lib.model.LoginResultModel;
import com.huihe.base_lib.model.LongOrderPayInfoModel;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.MechanismAverageScoreModel;
import com.huihe.base_lib.model.MechanismCategoryModel;
import com.huihe.base_lib.model.MechanismClassModel;
import com.huihe.base_lib.model.MechanismClassroomSelectModel;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.MechanismIncomeListModel;
import com.huihe.base_lib.model.MechanismIncomeStatisticsModel;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.MechanismOrderModel;
import com.huihe.base_lib.model.MechanismUserModel;
import com.huihe.base_lib.model.MyPointsModel;
import com.huihe.base_lib.model.OneCourseOrderModel;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.StudentCoursePackageModel;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.SummaryInfoModel;
import com.huihe.base_lib.model.TeachPaypalDetailModel;
import com.huihe.base_lib.model.TeachPaypalUserGoldTypeModel;
import com.huihe.base_lib.model.UserCouponModel;
import com.huihe.base_lib.model.UserStatisticsModel;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.dynamic.NoteCommentDetailListModel;
import com.huihe.base_lib.model.dynamic.NoteUserModel;
import com.huihe.base_lib.model.dynamic.ShareHistoryListModel;
import com.huihe.base_lib.model.login.RegisterUserInfoModel;
import com.huihe.base_lib.model.login.WxLoginModel;
import com.huihe.base_lib.model.login.WxOpenidAppVerModel;
import com.huihe.base_lib.model.personal.GetMailCodeModel;
import com.huihe.base_lib.model.personal.HistoryListModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.InsertSignDayModel;
import com.huihe.base_lib.model.personal.LikeInfoModel;
import com.huihe.base_lib.model.personal.MasterCommentModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.personal.UserCompanyModel;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.model.personal.UserSchoolModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.model.push.NoteMessageModel;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.model.study.StudentModel;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    //    @FormUrlEncoded
    @POST(ApiConfig.User.LOGIN)
    Observable<LoginResultModel> login(@Body RequestBody requestBody);

    @GET(ApiConfig.User.BANNER_DATA)
    Observable<BannerModel> getBannerData(@Query("type") String type, @Query("state") String state);

    @POST(ApiConfig.User.UPDATE_INFO)
    Observable<InsertInfoResultModel> updateUserInfo(@Body RequestBody requestBody);

    @POST(ApiConfig.User.USERINFO_UPDATE_USERINFO_BYCODE)
    Observable<InsertInfoResultModel> updateUserInfoByCode(@Body RequestBody requestBody);

    @GET(ApiConfig.User.QUERY_MASTER_COMMENT_LIST_PAGE)
    Observable<MasterCommentModel> getMasterCommentList(@Query("currentPage") Integer currentPage,
                                                        @Query("pageSize") Integer pageSize,
                                                        @Query("appointment_id") String appointment_id,
                                                        @Query("type") String type);

    @GET(ApiConfig.User.MASTER_COMMENT_DELETE)
    Observable<InsertInfoResultModel> masterCommentDelete(@Query("id") String id);

    @POST(ApiConfig.User.MASTER_COMMENT_INSERT)
    Observable<InsertInfoResultModel> insertMasterComment(@Body RequestBody requestBody);

    @GET(ApiConfig.User.QUERY_SUMMARY_LIST)
    Observable<SummaryInfoModel> querySummaryList(
            @Query("appointment_id") String appointment_id);

    @POST(ApiConfig.User.PAY_ALIPAY)
    Observable<InsertInfoResultModel> getAliPayOrder(@Body RequestBody body);

    @POST(ApiConfig.User.PAY_WXPAY)
    Observable<WxPayModel> getWxPayOrder(@Body RequestBody body);

    @GET(ApiConfig.User.SMS_SEND)
    Observable<InsertInfoResultModel> sendSms(@Query("mobile") String mobile, @Query("type") String type, @Query("mail") String mail);

    @GET(ApiConfig.User.VILIDATE_SMS)
    Observable<InsertInfoResultModel> validateSms(@Query("code") String code, @Query("mobile") String mobile);

    @POST(ApiConfig.User.UPDATE_USER)
    Observable<InsertInfoResultModel> updateUser(@Body RequestBody body);

    @POST(ApiConfig.User.USER_REGISTER)
    Observable<RegisterUserInfoModel> register(@Body RequestBody body);

    @GET(ApiConfig.User.SEND_MAIL)
    Observable<GetMailCodeModel> sendMail(@Query("mail") String mail, @Query("type") String type);

    @GET(ApiConfig.User.QUERY_USER_INFO)
    Observable<UserInfoModel> queryUserInfo(@Query("id") String user_id);

    @GET(ApiConfig.User.QUERY_USERCOMPANY)
    Observable<UserCompanyModel> queryUserCompany(@Query("user_id") String user_id);

    @POST(ApiConfig.User.USER_COMPANY_INSERT)
    Observable<InsertInfoResultModel> insertUserCompany(@Body RequestBody body);

    @POST(ApiConfig.User.USER_COMPANY_UPDATE)
    Observable<InsertInfoResultModel> userCompanyUpdate(@Body RequestBody body);

    @GET(ApiConfig.User.USER_COMPANY_DELETE)
    Observable<InsertInfoResultModel> userCompanyDelete(@Query("id") String id);

    @GET(ApiConfig.User.USER_SCHOOL_QUERY_LISTPAGE)
    Observable<UserSchoolModel> userSchoolQueryListPage(@Query("user_id") String user_id);

    @POST(ApiConfig.User.USER_SCHOOL_INSERT)
    Observable<InsertInfoResultModel> userSchoolInsert(@Body RequestBody body);

    @POST(ApiConfig.User.USER_SCHOOL_UPDATE)
    Observable<InsertInfoResultModel> userSchoolUpdate(@Body RequestBody body);

    @GET(ApiConfig.User.USER_SCHOOL_DELETE)
    Observable<InsertInfoResultModel> userSchoolDelete(@Query("id") String id);

    @GET(ApiConfig.User.VALIDATE_EMAIL)
    Observable<InsertInfoResultModel> validateEmail(@Query("code") String code, @Query("mail") String mail);

    @POST(ApiConfig.Message.SYSTEM_FEEDBACK_INSERT)
    Observable<InsertInfoResultModel> insertSystemFeedBack(@Body RequestBody body);

    @POST(ApiConfig.User.OPENID_APP_VERIFICATION)
    Observable<WxOpenidAppVerModel> wxOpenidAppVer(@Body RequestBody body);

    @POST(ApiConfig.User.OPENID_LOGIN_OR_REGIST)
    Observable<WxLoginModel> wxLogin(@Body RequestBody body);

    @POST(ApiConfig.User.USERAPPOINTMENT_UPDATE)
    Observable<InsertInfoResultModel> updateUserAppointment(@Body RequestBody body);

    @POST(ApiConfig.User.MASTERMECHANISM_INSERT)
    Observable<InsertInfoResultModel> insertMasterMechanism(@Body RequestBody body);

    @POST(ApiConfig.User.MASTERMECHANISM_UPDATE)
    Observable<InsertInfoResultModel> updateMasterMechanism(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERMECHANISM_QUERY)
    Observable<MasterMechanismModel> queryMechanismInfo(@Query("user_id") String user_id, @Query("status") Integer status, @Query("type") String type);

    @GET(ApiConfig.User.MASTERMECHANISM_QUERY_MECHANISMLISTPAGE)
    Observable<MasterMechanismModel> queryMechanismDetailInfoById(@Query("id") String id, @Query("type") String type);

    @POST(ApiConfig.User.MASTER_SET_PRICE_INSERT)
    Observable<InsertInfoResultModel> insertMasterSetPrice(@Body RequestBody body);

    @GET(ApiConfig.User.MASTER_SET_PRICE_QUERY_LIST_PAGE)
    Observable<CommodityModel> queryMechanismCourseList(
            @Query("mechanism_id") String mechanism_id,
            @Query("type") String type,
            @Query("status") String status,
            @Query("appointment_type") String appointment_type,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize
    );

    @GET(ApiConfig.User.MASTER_SET_PRICE_QUERY)
    Observable<MasterSetPriceModel> queryMasterSetPrice(
            @Query("mechanism_id") String mechanism_id,
            @Query("status") String status,
            @Query("type") String type);

    @GET(ApiConfig.User.MASTER_SET_PRICE_QUERY_COMMODITY_LIST)
    Observable<CommodityOldModel> queryMechanismCourseListById(
            @Query("id") String id
    );

    @POST(ApiConfig.User.MASTER_SET_PRICE_UPDATE)
    Observable<InsertInfoResultModel> updateMasterSetPrice(@Body RequestBody body);

    @POST(ApiConfig.User.MASTER_SET_PRICE_UPDATE_ACTIVITYINFO)
    Observable<InsertInfoResultModel> updateMasterSetPriceActivityInfo(@Body RequestBody body);

    @POST(ApiConfig.User.USER_UPDATE)
    Observable<InsertInfoResultModel> closeAccount(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERSETPRICE_QUERYNEARBYCOURSE)
    Observable<MasterSetPriceModel> queryNearByCourse(
            @Query("status") Integer status,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("latitude") String latitude,
            @Query("longitude") String longitude,
            @Query("type") String type);

    @GET(ApiConfig.User.MASTERMECHANISM_QUERY_MECHANISMBYTYPE)
    Observable<MasterMechanismModel> queryMechanismByType(
            @Query("pageSize") Integer pageSize,
            @Query("currentPage") Integer currentPage,
            @Query("latitude") String latitude,
            @Query("longitude") String longitude,
            @Query("sortName") String sortName,
            @Query("type") String type
    );

    @GET(ApiConfig.User.MASTERMECHANISM_QUERY_MECHANISMBYTYPE)
    Observable<MasterMechanismModel> queryMechanismByEs(
            @Query("mechanism_name") String mechanism_name,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("latitude") String latitude,
            @Query("longitude") String longitude,
            @Query("sortName") String sortName,
            @Query("categories") String categories,
            @Query("categories_child") String categories_child,
            @Query("type") String type
    );

    @POST(ApiConfig.User.RECHARGERECORD_PAYONECOURSE)
    Observable<OneCourseOrderModel> payOneCourseOrder(@Body RequestBody body);

    @POST(ApiConfig.User.USERFOLLOWMECHANISM_INSERT)
    Observable<InsertInfoResultModel> userFollowMechanismInsert(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERINFO_QUERY_LISTPAGE)
    Observable<MasterInfoHomeModel> queryMechanismMasterInfoList(
            @Query("mechanism_id") String mechanism_id,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("type") String type,
            @Query("status") String status);

    @POST(ApiConfig.User.USER_INSERT_MECHANISM_MASTER)
    Observable<InsertInfoResultModel> insertMechanismMaster(@Body RequestBody body);

    @GET(ApiConfig.User.USERSTUDYCARD_QUERYSTUDENTLIST)
    Observable<StudentModel> queryStudentList(
            @Query("mechanism_id") String mechanism_id,
            @Query("status") String status,
            @Query("studycard_id") String studycard_id,
            @Query("type") String type,
            @Query("pageSize") Integer pageSize,
            @Query("currentPage") Integer currentPage);

    @POST(ApiConfig.User.MASTER_APPOINTMENT_INSERTMECHANISMCOURSE)
    Observable<InsertInfoResultModel> insertMechanismCourse(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERAPPOINTMENT_QUERY_MECHANISMOFFLINESCHEDULE)
    Observable<MechanismOfflineScheduleModel> queryMechanismOfflineSchedule(
            @Query("mechanism_id") String mechanism_id,
            @Query("type") String type,
            @Query("status") String status,
            @Query("start_time") String start_time,
            @Query("end_time") String end_time,
            @Query("offset") String offset,
            @Query("identity_type") String identity_type,
            @Query("create_type") String create_type);

    @POST(ApiConfig.User.MASTERAPPOINTMENT_INSERTOFFLINECOURSE)
    Observable<InsertInfoResultModel> insertOfflineCourse(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERAPPOINTMENT_QUERY_MECHANISMOFFLINESCHEDULE)
    Observable<MechanismOfflineScheduleModel> queryMechanismOfflineStatusSchedule(
            @Query("mechanism_id") String mechanism_id,
            @Query("type") String type,
            @Query("status") String status,
            @Query("identity_type") String identity_type,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("start_time") String start_time,
            @Query("end_time") String end_time,
            @Query("offset") String offset);

    @POST(ApiConfig.User.MASTERSUMMARY_INSERT_SUMMARYOFFLINE)
    Observable<InsertInfoResultModel> insertSummaryOffline(@Body RequestBody body);

    @GET(ApiConfig.User.USERAPPOINTMENT_QUERY_OFFLINESCHEDULE)
    Observable<StudentScheduleModel> queryStudentOfflineSchedule(
            @Query("user_id") String user_id,
            @Query("type") String type,
            @Query("status") String status,
            @Query("is_comment") Boolean is_comment,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @GET(ApiConfig.User.USERAPPOINTMENT_QUERY_OFFLINESCHEDULE)
    Observable<StudentScheduleModel> queryStudentOfflineSchedulePackageDetail(
            @Query("study_card_id") String study_card_id,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @POST(ApiConfig.User.MASTER_COMMENT_INSERT)
    Observable<InsertInfoResultModel> insertTeachPayMechanismComment(@Body RequestBody body);

    @GET(ApiConfig.User.QUERY_MY_STUDYCORELIST)
    Observable<StudentCoursePackageModel> queryStudentExclusiveCoursesList(
            @Query("type") String type,
            @Query("user_id") String user_id,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @GET(ApiConfig.User.MASTERAPPOINTMENT_QUERY_MECHANISMOFFLINESCHEDULE)
    Observable<MechanismOfflineScheduleModel> queryMechanismOfflineAppointmentSchedule(
            @Query("create_type") String create_type,
            @Query("end_time") String end_time,
            @Query("identity_type") String identity_type,
            @Query("master_set_price_id") String master_set_price_id,
            @Query("mechanism_id") String mechanism_id,
            @Query("offset") String offset,
            @Query("start_time") String start_time,
            @Query("type") String type);

    @POST(ApiConfig.User.INSERT_USERAPPOINTMENT)
    Observable<InsertInfoResultModel> insertMechanismUserAppointment(@Body RequestBody body);


    @GET(ApiConfig.User.RECHARGERECORD_QUERY_MECHANISMLIST)
    Observable<MechanismOrderModel> queryMechanismOrderList(
            @Query("type") String type,
            @Query("rcharge_type") String rcharge_type,
            @Query("user_id") String user_id,
            @Query("mechanism_id") String mechanism_id,
            @Query("finished") Boolean finished,
            @Query("is_one_time_payment") Boolean is_one_time_payment,
            @Query("pageSize") Integer pageSize,
            @Query("currentPage") Integer currentPage,
            @Query("study_type") String study_type,
            @Query("course_num") String course_num,
            @Query("status") String status);

    @GET(ApiConfig.User.MASTERCOMMENT_QUERY_MECHANISMAVERAGESCORE)
    Observable<MechanismAverageScoreModel> queryMechanismAverageScore(@Query("mechanism_id") String mechanism_id);

    @GET(ApiConfig.User.MASTERCOMMENT_QUERY_MECHANISMLIST)
    Observable<MechanismCommentModel> queryMechanismCommentList(
            @Query("mechanism_id") String mechanism_id,
            @Query("type") String type,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @POST(ApiConfig.User.USERCOUPON_INSERT_USERCOLLECTION)
    Observable<InsertInfoResultModel> insertUserCollection(@Body RequestBody body);

    @GET(ApiConfig.User.USERCOUPON_QUERYLISTPAGE)
    Observable<UserCouponModel> queryUserCouponList(
            @Query("user_id") String user_id,
            @Query("status") String status,
            @Query("pageSize") Integer pageSize,
            @Query("currentPage") Integer currentPage);

    @GET(ApiConfig.User.RECHARGERECORD_QUERY_PAYDETAILS)
    Observable<PayDetailModel> queryPayDetails(
            @Query("course_num") String course_num,
            @Query("studycard_id") String studycard_id,
            @Query("user_study_card_id") String user_study_card_id,
            @Query("user_id") String user_id,
            @Query("mechanism_id") String mechanism_id,
            @Query("is_one_time_payment") Boolean is_one_time_payment);

    @POST(ApiConfig.User.PAY_PAYPRE)
    Observable<InsertInfoResultModel> getAliOrder(@Body RequestBody body);

    @POST(ApiConfig.User.PAY_PAYPRE)
    Observable<WxPayModel> getWxOrder(@Body RequestBody body);

    @GET(ApiConfig.User.USERINFO_QUERYTEACHPAYUSERSTATISTICS)
    Observable<UserStatisticsModel> queryTeachPayUserStatistics(@Query("user_id") String user_id);

    @GET(ApiConfig.User.SIGNIN_QUERYTEACHPAYPALDETAIL)
    Observable<TeachPaypalDetailModel> queryTeachPaypalDetail(@Query("user_id") String user_id);

    @GET(ApiConfig.User.USERGOLDTYPE_QUERYTEACHPAYPAL)
    Observable<TeachPaypalUserGoldTypeModel> queryTeachPaypalUserGoldType(
            @Query("user_id") String user_id,
            @Query("status") String status,
            @Query("is_teach_paypal") Boolean is_teach_paypal);

    @GET(ApiConfig.User.SIGNIN_INSERTLOGIN)
    Observable<InsertSignDayModel> insertLoginSignIn(
            @Query("user_id") String user_id);

    @GET(ApiConfig.User.USERSTUDYCARD_QUERYPAYINFO)
    Observable<LongOrderPayInfoModel> queryLongOrderPayInfo(@Query("id") String id);

    @POST(ApiConfig.User.USERDEVICE_INSERT)
    Observable<InsertInfoResultModel> userDeviceInsert(@Body RequestBody body);

    @POST(ApiConfig.User.USERAPPOINTMENT_UPDATECANCELCOURSE)
    Observable<InsertInfoResultModel> updateCancelCourse(@Body RequestBody body);

    @GET(ApiConfig.User.USERPOINTS_QUERY_LISTPAGE)
    Observable<MyPointsModel> getUserPointsList(
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("user_id") String user_id,
            @Query("is_earnings") Boolean is_earnings,
            @Query("start_time") String start_time);

    @GET(ApiConfig.User.RECHARGERECORD_QUERYMECHANISMOFFLINEDETAILSCOUNT)
    Observable<MechanismIncomeStatisticsModel> queryMechanismOfflineDetailsCount(
            @Query("mechanism_id") String mechanism_id);

    @GET(ApiConfig.User.RECHARGERECORD_QUERYMECHANISMOFFLINEDETAILS)
    Observable<MechanismIncomeListModel> queryMechanismOfflineDetails(
            @Query("mechanism_id") String mechanism_id,
            @Query("study_type") String study_type,
            @Query("finished") Boolean finished,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize
    );

    @GET(ApiConfig.User.MASTERSETPRICE_QUERY_ISCOURSE)
    Observable<InsertInfoResultModel> queryIsMechanismCourse(
            @Query("mechanism_id") String mechanism_id);

    @POST(ApiConfig.User.MASTERINFO_UPDATEMECHANISMMASTERINFO)
    Observable<InsertInfoResultModel> resignTeacher(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERCOMMENT_QUERY_MECHANISMLIST)
    Observable<MechanismCommentModel> queryMechanismCourseCommentList(
            @Query("mastersetprice_id") String mastersetprice_id,
            @Query("type") String type,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @POST(ApiConfig.User.USER_INSERTH5GETCOUPON)
    Observable<InsertInfoResultModel> insertH5GetCoupon(@Body RequestBody body);

    @POST(ApiConfig.User.MECHANISMCLASSROOM_INSERT)
    Observable<InsertInfoResultModel> mechanismClassroomInsert(@Body RequestBody body);

    @POST(ApiConfig.User.MECHANISMCLASSROOM_UPDATE)
    Observable<InsertInfoResultModel> mechanismClassroomUpdate(@Body RequestBody body);

    @GET(ApiConfig.User.MECHANISMCLASSROOM_QUERYMANAGERLISTPAGE)
    Observable<ClassRoomModel> queryManagerClassroomListPage(
            @Query("mechanism_id") String mechanism_id,
            @Query("pageSize") Integer pageSize,
            @Query("currentPage") Integer currentPage);

    @GET(ApiConfig.User.MECHANISMCLASSROOM_QUERYLISTPAGE)
    Observable<MechanismClassroomSelectModel> queryMechanismClassroom(
            @Query("mechanism_id") String mechanism_id,
            @Query("start_time") String start_time,
            @Query("status") String status);

    @POST(ApiConfig.User.USERGROUPING_INSERTUSERGROUPING)
    Observable<InsertInfoResultModel> insertUserGrouping(@Body RequestBody body);

    @GET(ApiConfig.User.MECHANISMUSER_QUERYLISTPAGE)
    Observable<MechanismUserModel> getMechanismUserList(
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("mechanism_id") String mechanism_id);

    @POST(ApiConfig.User.MECHANISMUSER_UPDATE)
    Observable<InsertInfoResultModel> mechanismUserStatus(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERAPPOINTMENT_DELETE)
    Observable<InsertInfoResultModel> deleteMasterAppointment(@Query("id") String id);

    @GET(ApiConfig.Message.NEWS_INFORMATION_INFO_LIST)
    Observable<NoteUserModel> getNewsInformationInfoList(
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("oper_id") String oper_id,
            @Query("classfiy") String classfiy,
            @Query("status") String status,
            @Query("is_teach_paypal") Boolean is_teach_paypal);

    @GET(ApiConfig.Message.NOTEUSER_QUERY_NOTE_NEWS)
    Observable<NoteMessageModel> queryNoteNews(
            @Query("oper_id") String oper_id);

    @GET(ApiConfig.Message.NOTE_USER_DELETE)
    Observable<InsertInfoResultModel> deleteNoteUser(@Query("id") String id);

    @POST(ApiConfig.Message.INSERT_HISTORY)
    Observable<InsertInfoResultModel> insertHistory(@Body RequestBody body);

    @POST(ApiConfig.Message.INSERT_NOTE_COMMENT)
    Observable<InsertInfoResultModel> insertNoteComment(@Body RequestBody body);

    @GET(ApiConfig.Message.NEWS_INFORMATION_INFO_LIST)
    Observable<NoteUserModel> queryNoteUserfindById(@Query("id") String id);

    @GET(ApiConfig.Message.QUERY_HISTORY)
    Observable<LikeInfoModel> queryHistory(
            @Query("user_id") String user_id,
            @Query("history_id") String history_id,
            @Query("operation_type") String operation_type,
            @Query("history_type") String history_type);

    @POST(ApiConfig.Message.NOTE_USER_INSERT)
    Observable<InsertInfoResultModel> noteUserInsert(@Body RequestBody body);

    @GET(ApiConfig.Message.QUERY_NOTECOMMENT_DETAILS_LISTPAGE)
    Observable<NoteCommentDetailListModel> queryNoteCommentDetailsListPage(
            @Query("note_id") String note_id,
            @Query("parent_id") String parent_id,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @GET(ApiConfig.Message.HISTORY_LIST_QUERY)
    Observable<HistoryListModel> queryHistoryListPage(
            @Query("status") Integer status,
            @Query("history_id") String history_id,
            @Query("operation_type") String operation_type,
            @Query("history_type") String history_type,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @GET(ApiConfig.Message.HISTORY_LIST_QUERY)
    Observable<ShareHistoryListModel> queryShareHistoryListPage(
            @Query("history_id") String history_id,
            @Query("operation_type") String operation_type,
            @Query("history_type") String history_type,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @GET(ApiConfig.Message.NOTE_COMMENT_DELETE)
    Observable<InsertInfoResultModel> deleteNoteComment(@Query("id") String id);

    @GET(ApiConfig.User.MECHANISMCATEGORY_QUERYLISTPAGECHILD)
    Observable<MechanismCategoryModel> queryMechanismCategoryChildList();

    @POST(ApiConfig.Message.SYSTEM_VERSION_VERSIONITERATION)
    Observable<VersionIterationModel> versionIteration(@Body RequestBody body);

    @POST(ApiConfig.User.MECHANISMCLASSES_INSERT)
    Observable<InsertInfoResultModel> insertMechanismClasses(@Body RequestBody body);

    @GET(ApiConfig.User.MECHANISMCLASSES_QUERYLISTPAGE)
    Observable<MechanismClassModel> queryMechanismClasses(
            @Query("mechanism_id") String mechanism_id,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("master_set_price_id") String master_set_price_id,
            @Query("is_scheduling_over") Boolean is_scheduling_over,
            @Query("status") String status);

    @POST(ApiConfig.User.MECHANISMCLASSES_INSERTMECHANISMCOURSE)
    Observable<InsertInfoResultModel> arrangeMechanismFixCourse(@Body RequestBody body);

    @GET(ApiConfig.User.USERSTUDYCARD_QUERYSTUDENTINFO)
    Observable<ClassStudentModel> queryStudentInfoList(
            @Query("type") String type,
            @Query("mechanism_id") String mechanism_id,
            @Query("studycard_id") String studycard_id,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @POST(ApiConfig.User.USERAPPOINTMENT_UPDATEUSERCONFIRM)
    Observable<InsertInfoResultModel> updateUserAppointmentUserConfirm(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERAPPOINTMENT_QUERYSTATUSBYID)
    Observable<InsertInfoResultModel> queryMasterAppointmentStatusById(@Query("id") String id);

    @POST(ApiConfig.User.MECHANISMCLASSES_UPDATEMERGERCLASS)
    Observable<InsertInfoResultModel> updateMergerClass(@Body RequestBody body);

    @POST(ApiConfig.User.MASTERAPPOINTMENT_INSERTCOPYCOURSE)
    Observable<InsertInfoResultModel> insertCopyCourse(@Body RequestBody body);

    @GET(ApiConfig.User.BUSINESSACTIVITYTYPE_QUERYLISTPAGE)
    Observable<BusinessActivityTypeModel> queryBusinessActivityTypeList(
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("status") String status);

    @POST(ApiConfig.User.BUSINESSACTIVITY_INSERTACTIVITY)
    Observable<InsertInfoResultModel> insertBusinessActivity(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERSETPRICE_QUERYACTIVITYLISTPAGEBYTYPE)
    Observable<MasterSetPriceModel> queryActivityListPageByType(
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("status") String status,
            @Query("type") String type,
            @Query("latitude") String latitude,
            @Query("longitude") String longitude);

    @POST(ApiConfig.User.PAY_PAYPRE)
    Observable<InsertInfoResultModel> buyCouponByAli(@Body RequestBody body);

    @POST(ApiConfig.User.PAY_PAYPRE)
    Observable<WxPayModel> buyCouponByWx(@Body RequestBody body);

    @GET(ApiConfig.User.RECHARGERECORD_QUERYLISTPAGE_BY_STATUS)
    Observable<MechanismOrderModel> queryStudentOrderList(
            @Query("study_type") String study_type,
            @Query("user_id") String user_id,
            @Query("pageSize") Integer pageSize,
            @Query("currentPage") Integer currentPage,
            @Query("status") String status);

    @POST(ApiConfig.User.USERCOUPON_INSERTSTUDYCARDBYCOUP)
    Observable<InsertInfoResultModel> useCoupon(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERSETPRICESTREAM_QUERYLISTPAGE)
    Observable<LiveModel> getLiveMasterSetPriceList(
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("mechanism_id") String mechanism_id,
            @Query("status") String status);

    @POST(ApiConfig.User.MASTERSETPRICESTREAM_INSERT)
    Observable<InsertInfoResultModel> insertMasterSetPriceStream(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERSETPRICEDISPLAY_QUERY)
    Observable<MasterSetPriceDisplayModel> queryMasterSetPriceDisplay(
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize,
            @Query("status") String status,
            @Query("live_streaming_id") String live_streaming_id);

    @GET(ApiConfig.User.MASTERSETPRICEDISPLAY_QUERY)
    Observable<MasterSetPriceDisplayModel> queryMasterSetPriceDisplayDetail(
            @Query("id") String id,
            @Query("live_streaming_id") String live_streaming_id);

    @POST(ApiConfig.User.MASTERSETPRICEDISPLAY_INSERT)
    Observable<InsertInfoResultModel> insertMasterSetPriceDisplay(@Body RequestBody body);

    @POST(ApiConfig.User.MASTERSETPRICEDISPLAY_UPDATE)
    Observable<InsertInfoResultModel> updateMasterSetPriceDisplay(@Body RequestBody body);

    @POST(ApiConfig.User.MASTERSETPRICESTREAM_UPDATEOPENLIVING)
    Observable<InsertInfoResultModel> updateOpenLiving(@Body RequestBody body);

    @POST(ApiConfig.User.MASTERSETPRICESTREAM_UPDATECLOSELIVING)
    Observable<InsertInfoResultModel> updateCloseLiving(@Body RequestBody body);

    @GET(ApiConfig.User.MASTERSETPRICESTREAM_QUERYDETAIL)
    Observable<LiveDetailModel> queryMasterSetPriceStreamDetail(@Query("id") String id);

    @GET(ApiConfig.User.RECHARGERECORD_QUERYPAYLIVESTREAMDETAIL)
    Observable<PayDetailModel> queryPayLiveStreamDetails(
            @Query("studycard_id") String studycard_id,
            @Query("user_id") String user_id,
            @Query("mechanism_id") String mechanism_id,
            @Query("is_one_time_payment") Boolean is_one_time_payment,
            @Query("live_streaming_id") String live_streaming_id);

    @GET(ApiConfig.User.MASTERSETPRICE_QUERYMECHANISMDISPLAYISLIVING)
    Observable<MasterSetPriceModel> queryMechanismDisplayIsLiving(
            @Query("live_streaming_id") String live_streaming_id,
            @Query("status") String status);

    @POST(ApiConfig.User.MASTERSETPRICESTREAM_UPDATEADDCLICK)
    Observable<InsertInfoResultModel> updateAddClick(@Body RequestBody body);
}