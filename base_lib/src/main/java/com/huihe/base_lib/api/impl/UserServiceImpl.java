package com.huihe.base_lib.api.impl;

import android.util.Log;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.UserService;
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
import com.huihe.base_lib.net.HttpEngineCore;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.SchedulersUtils;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UserServiceImpl {

    private static UserService getUserService() {
        return HttpEngineCore.getRetrofit().create(UserService.class);
    }

    public static DisposableObserver userDeviceInsert(
            String model,
            String clientid,
            String user_id,
            String unique_id,
            String teach_pay_token,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("model", model); // 设备型号
            obj.putOpt("name", "android");
            obj.putOpt("teach_pay_rid", clientid);
            obj.putOpt("user_id", user_id);
            obj.putOpt("uuid", unique_id);
            obj.putOpt("teach_pay_token", teach_pay_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().userDeviceInsert(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver login(
            String loginName,
            String password,
            String login_type,
            Boolean is_teach_paypal,
            DisposableObserver<LoginResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("login_name", loginName);
            obj.putOpt("login_pass", password);
            obj.putOpt("login_type", login_type);
            obj.putOpt("is_teach_paypal", is_teach_paypal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().login(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver codeLogin(
            String loginName,
            String verification_code,
            Boolean is_teach_paypal,
            DisposableObserver<LoginResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("login_name", loginName);
            obj.putOpt("verification_code", verification_code);
            obj.putOpt("login_type", "2");
            obj.putOpt("is_teach_paypal", is_teach_paypal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());

        SchedulersUtils.onBackUI(
                getUserService().login(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver getBannerData(
            String type,
            String state,
            DisposableObserver<BannerModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().getBannerData(type, state)
                , subscriber);
        return subscriber;
    }

    public static void updateUserMobile(
            String token,
            String user_id,
            String mobile,
            String vali_code,
            DisposableObserver<InsertInfoResultModel> subscriber) {

        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("mobile", mobile);
            obj.putOpt("vali_code", vali_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateUserInfoByCode(body)
                , subscriber);
    }

    public static void updateUserPayPass(
            String user_id,
            String pay_pass,
            String vali_code,
            DisposableObserver<InsertInfoResultModel> subscriber) {

        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("pay_pass", pay_pass);
            obj.putOpt("vali_code", vali_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateUserInfo(body)
                , subscriber);
    }

    public static DisposableObserver getMasterCommentList(
            Integer currentPage,
            Integer pageSize,
            String appointment_id,
            String type,
            DisposableObserver<MasterCommentModel> subscriber) {

        SchedulersUtils.onBackUI(
                getUserService().getMasterCommentList(
                        currentPage,
                        pageSize,
                        appointment_id,
                        type), subscriber);
        return subscriber;
    }

    public static void masterCommentDelete(
            String id,
            String token,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().masterCommentDelete(id)
                , subscriber);
    }

    public static void insertSubMasterComment(
            String token,
            String user_id,
            String reply_id,
            String parent_id,
            String master_id,
            boolean is_reply,
            String content,
            String mechanism_id,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("reply_id", reply_id);
            obj.putOpt("parent_id", parent_id);
            obj.putOpt("master_id", master_id);
            obj.putOpt("content", content);
            obj.putOpt("is_reply", is_reply);
            obj.putOpt("mechanism_id", mechanism_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertMasterComment(body)
                , subscriber);
    }


    public static DisposableObserver sendSms(
            String mobile,
            String type,
            String mail,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().sendSms(mobile, type, mail)
                , subscriber);
        return subscriber;
    }

    public static void validateSms(
            String code,
            String mobile,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {

        SchedulersUtils.onBackUI(
                getUserService().validateSms(code, mobile)
                , subscriber);
    }


    public static void updateUser(String login_name, String user_id, String login_pass, Boolean is_teach_paypal, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("login_name", login_name);
            obj.putOpt("user_id", user_id);
            obj.putOpt("login_pass", login_pass);
            obj.putOpt("is_teach_paypal", is_teach_paypal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());

        SchedulersUtils.onBackUI(
                getUserService().updateUser(body)
                , subscriber);
    }


    public static DisposableObserver updateUserInfo(
            String user_id,
            String nick_name,
            String avatar,
            Integer sex,
            String birth,
            String country,
            String city,
            String hometown,
            String languages,
            String mother_tongue,
            String invite_code,
            Boolean is_teach_paypal,
            DisposableObserver<InsertInfoResultModel> subscriber) {

        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("nick_name", nick_name);
            obj.putOpt("avatar", avatar);
            obj.putOpt("sex", sex);
            obj.putOpt("birth", birth);
            obj.putOpt("country", country);
            obj.putOpt("city", city);
            obj.putOpt("hometown", hometown);
            obj.putOpt("languages", languages);
            obj.putOpt("mother_tongue", mother_tongue);
            obj.putOpt("invite_code", invite_code);
            obj.putOpt("is_teach_paypal", is_teach_paypal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHellper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateUserInfo(body)
                , subscriber);
        return subscriber;
    }

    public static void updateUserPrivacyInfo(String user_id, String token, Boolean is_mobile, Boolean is_id, Boolean is_name, Boolean is_mail, DisposableObserver<InsertInfoResultModel> subscriber) {

        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("is_mobile", is_mobile);
            obj.putOpt("is_id", is_id);
            obj.putOpt("is_name", is_name);
            obj.putOpt("is_mail", is_mail);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateUserInfo(body)
                , subscriber);
    }

    public static DisposableObserver register(String verification_code, String login_name, NetworkSubscriber<RegisterUserInfoModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("verification_code", verification_code);
            obj.putOpt("login_name", login_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());

        SchedulersUtils.onBackUI(
                getUserService().register(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver sendMail(
            String mail,
            String type,
            NetworkSubscriber<GetMailCodeModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().sendMail(mail, type)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryUserInfo(
            String user_id,
            NetworkSubscriber<UserInfoModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryUserInfo(user_id)
                , subscriber);
        return subscriber;
    }


    public static DisposableObserver queryUserCompany(String token, String user_id, NetworkSubscriber<UserCompanyModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryUserCompany(user_id)
                , subscriber);
        return subscriber;
    }

    public static void insertUserCompany(String user_id, String token, String company_name, String company_industry, String duty, String start_time, String end_time, boolean is_visible, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
//        String company_name, String company_industry, String duty, String start_time, String end_time, boolean is_visible
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("company_name", company_name);
            obj.putOpt("company_industry", company_industry);
            obj.putOpt("duty", duty);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("is_visible", is_visible);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertUserCompany(body)
                , subscriber);
    }

    public static void userCompanyUpdate(String user_id, String token, String id, String company_name, String company_industry, String duty, String start_time, String end_time, boolean is_visible, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
//        String company_name, String company_industry, String duty, String start_time, String end_time, boolean is_visible
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("id", id);
            obj.putOpt("company_name", company_name);
            obj.putOpt("company_industry", company_industry);
            obj.putOpt("duty", duty);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("is_visible", is_visible);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().userCompanyUpdate(body)
                , subscriber);
    }

    public static void userCompanyDelete(String token, String id, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().userCompanyDelete(id)
                , subscriber);
    }

    public static DisposableObserver userSchoolQueryListPage(
            String token,
            String user_id,
            NetworkSubscriber<UserSchoolModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().userSchoolQueryListPage(user_id)
                , subscriber);
        return subscriber;
    }

    public static void userSchoolInsert(String user_id, String token, String start_time,
                                        String end_time, String school_name, boolean is_visible, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("school_name", school_name);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("is_visible", is_visible);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().userSchoolInsert(body)
                , subscriber);
    }

    public static void userSchoolUpdate(String user_id, String token, String id, String start_time, String end_time, String school_name, boolean is_visible, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("id", id);
            obj.putOpt("school_name", school_name);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("is_visible", is_visible);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().userSchoolUpdate(body)
                , subscriber);
    }

    public static void userSchoolDelete(String id, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().userSchoolDelete(id)
                , subscriber);
    }

    public static void validateEmail(String token, String code, String mail, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().validateEmail(code, mail)
                , subscriber);
    }

    public static void insertSystemFeedBack(String user_id, String token, String content, String pic, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("content", content);
            obj.putOpt("pic", pic);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertSystemFeedBack(body)
                , subscriber);

    }

    public static DisposableObserver wxOpenidAppVer(String wx_openid, Boolean is_teach_paypal, NetworkSubscriber<WxOpenidAppVerModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("wx_openid", wx_openid);
            obj.putOpt("is_teach_paypal", is_teach_paypal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().wxOpenidAppVer(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver wxLogin(
            String login_name,
            String verification_code,
            String nick_name,
            String avatar,
            Integer sex,
            String wx_openid,
            Boolean is_teach_paypal,
            NetworkSubscriber<WxLoginModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("login_name", login_name);
            obj.putOpt("verification_code", verification_code);
            obj.putOpt("nick_name", nick_name);
            obj.putOpt("avatar", avatar);
            obj.putOpt("sex", sex);
            obj.putOpt("wx_openid", wx_openid);
            obj.putOpt("is_teach_paypal", is_teach_paypal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().wxLogin(body)
                , subscriber);
        return subscriber;

    }

    public static void updateUserLoginPass(
            String login_name,
            String verification_code,
            String login_pass,
            Boolean is_teach_paypal,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("login_name", login_name);
            obj.putOpt("login_pass", login_pass);
            obj.putOpt("verification_code", verification_code);
            obj.putOpt("is_teach_paypal", is_teach_paypal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateUser(body)
                , subscriber);
    }

    /**
     * @desc 修改机构信息
     */
    public static DisposableObserver updateMasterMechanism(
            String id,
            String user_id,
            String categories,
            String categories_child,
            String mechanism_logo,
            String mechanism_name,
            String mechanism_addr,
            Double longitude,
            Double latitude,
            String mechanism_telephone,
            String contact_telephone,
            String contacts,
            String introduction_pic,
            String introduction_content,
            String support_means,
            String facade_view,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("user_id", user_id);
            obj.putOpt("categories", categories);
            obj.putOpt("categories_child", categories_child);
            obj.putOpt("mechanism_logo", mechanism_logo);
            obj.putOpt("mechanism_name", mechanism_name);
            obj.putOpt("mechanism_addr", mechanism_addr);
            obj.putOpt("longitude", longitude);
            obj.putOpt("latitude", latitude);
            obj.putOpt("mechanism_telephone", mechanism_telephone);
            obj.putOpt("contact_telephone", contact_telephone);
            obj.putOpt("contacts", contacts);
            obj.putOpt("introduction_pic", introduction_pic);
            obj.putOpt("introduction_content", introduction_content);
            obj.putOpt("support_means", support_means);
            obj.putOpt("facade_view", facade_view);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateMasterMechanism(body)
                , subscriber);
        return subscriber;
    }

    /**
     * 查询机构信息
     */
    public static DisposableObserver queryMechanismInfo(
            String user_id,
            Integer Status,
            String type,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismInfo(user_id, Status, type)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismDetailInfoListById(
            String id,
            String type,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismDetailInfoById(id, type)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMasterSetPrice(
            String mechanism_id,
            String status,
            String type,
            NetworkSubscriber<MasterSetPriceModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMasterSetPrice(
                        mechanism_id,
                        status,
                        type)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertMasterSetPrice(
            String user_id,
            String title,
            String appointment_type,
            String label,
            String course_num,
            String amout,
            String discount_amout,
            String introduction_content,
            Integer status,
            String titile_url,
            Boolean first_free,
            String type,
            String connect_peoplenum,
            String mechanism_id,
            String face_url,
            String introduction_url,
            String duration,
            String price_list,
            Boolean is_attend_activities,
            String start_time,
            String end_time,
            String default_discount_price,
            String categories,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("title", title);
            obj.putOpt("label", label);
            obj.putOpt("appointment_type", appointment_type);
            obj.putOpt("course_num", course_num);
            obj.putOpt("amout", amout);
            obj.putOpt("discount_amout", discount_amout);
            obj.putOpt("user_id", user_id);
            obj.putOpt("introduction_content", introduction_content);
            obj.putOpt("status", status);
            obj.putOpt("titile_url", titile_url);
            obj.putOpt("first_free", first_free);
            obj.putOpt("type", type);
            obj.putOpt("connect_peoplenum", connect_peoplenum);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("face_url", face_url);
            obj.putOpt("introduction_url", introduction_url);
            obj.putOpt("duration", duration);
            obj.putOpt("price_list", price_list);
            obj.putOpt("is_attend_activities", is_attend_activities);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("default_discount_price", default_discount_price);
            obj.putOpt("categories", categories);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertMasterSetPrice(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver updateMasterSetPriceActivityInfo(
            String id,
            String user_id,
            String appointment_type,
            String title,
            String label,
            String course_num,
            String amout,
            String discount,
            String discount_amout,
            String introduction_content,
            Integer status,
            String titile_url,
            Boolean first_free,
            String type,
            String connect_peoplenum,
            String mechanism_id,
            String face_url,
            String introduction_url,
            String duration,
            String price_list,
            Boolean is_attend_activities,
            String start_time,
            String end_time,
            String default_discount_price,
            String categories,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("title", title);
            obj.putOpt("appointment_type", appointment_type);
            obj.putOpt("label", label);
            obj.putOpt("course_num", course_num);
            obj.putOpt("amout", amout);
            obj.putOpt("discount", discount);
            obj.putOpt("discount_amout", discount_amout);
            obj.putOpt("user_id", user_id);
            obj.putOpt("introduction_content", introduction_content);
            obj.putOpt("status", status);
            obj.putOpt("titile_url", titile_url);
            obj.putOpt("first_free", first_free);
            obj.putOpt("type", type);
            obj.putOpt("connect_peoplenum", connect_peoplenum);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("face_url", face_url);
            obj.putOpt("introduction_url", introduction_url);
            obj.putOpt("duration", duration);
            obj.putOpt("price_list", price_list);
            obj.putOpt("is_attend_activities", is_attend_activities);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("activity_price", default_discount_price);
            obj.putOpt("categories", categories);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateMasterSetPriceActivityInfo(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<CommodityModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismCourseList(
                        mechanism_id,
                        type,
                        status,
                        appointment_type,
                        currentPage,
                        pageSize
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismCourseListById(
            String id,
            NetworkSubscriber<CommodityOldModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismCourseListById(
                        id
                )
                , subscriber);
        return subscriber;
    }

    /**
     * @param id
     * @param status
     * @param subscriber
     * @desc 下架商品
     */
    public static void sold0utExclusiveCourse(
            String id,
            String status,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("status", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateMasterSetPrice(body)
                , subscriber);
    }

    public static DisposableObserver aliPayExclusive(
            String studycard_id,
            String user_id,
            String rcharge_type,
            String amount,
            String course_num,
            String member_duration,
            String mechanism_id,
            String study_type,
            String rcharge_abstract,
            String master_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("amount", amount);
            obj.putOpt("source", "android");
            obj.putOpt("course_num", course_num);
            obj.putOpt("member_duration", member_duration);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("study_type", study_type);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("master_id", master_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().getAliPayOrder(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver wxPayExclusive(
            String studycard_id,
            String user_id,
            String rcharge_type,
            String amount,
            String course_num,
            String member_duration,
            String mechanism_id,
            String study_type,
            String rcharge_abstract,
            String master_id,
            NetworkSubscriber<WxPayModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("amount", amount);
            obj.putOpt("source", "android");
            obj.putOpt("course_num", course_num);
            obj.putOpt("member_duration", member_duration);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("study_type", study_type);
            obj.putOpt("master_id", master_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().getWxPayOrder(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver closeAccount(String user_id, String status, NetworkSubscriber subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("status", status);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().closeAccount(
                        body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryNearByCourse(
            Integer status,
            Integer currentPage,
            Integer pageSize,
            String latitude,
            String longitude,
            String type,
            NetworkSubscriber<MasterSetPriceModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("status", status);
            obj.putOpt("currentPage", currentPage);
            obj.putOpt("pageSize", pageSize);
            obj.putOpt("latitude", latitude);
            obj.putOpt("longitude", longitude);
            obj.putOpt("type", type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().queryNearByCourse(
                        status,
                        currentPage,
                        pageSize,
                        latitude,
                        longitude,
                        type)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismByType(
            Integer pageSize,
            Integer currentPage,
            String latitude,
            String longitude,
            String sortName,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismByType(
                        pageSize,
                        currentPage,
                        latitude,
                        longitude,
                        sortName,
                        "teach_paypal")
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismByEs(
            String mechanism_name,
            Integer currentPage,
            Integer pageSize,
            String latitude,
            String longitude,
            String sortName,
            String categories,
            String categories_child,
            String type,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("mechanism_name", mechanism_name);
            obj.putOpt("currentPage", currentPage);
            obj.putOpt("pageSize", pageSize);
            obj.putOpt("latitude", latitude);
            obj.putOpt("longitude", longitude);
            obj.putOpt("sortName", sortName);
            obj.putOpt("categories", categories);
            obj.putOpt("categories_child", categories_child);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismByEs(
                        mechanism_name,
                        currentPage,
                        pageSize,
                        latitude,
                        longitude,
                        sortName,
                        categories,
                        categories_child,
                        type)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver settleTeachPayMechanism(
            String user_id,
            String type,
            String categories,
            String mechanism_logo,
            String mechanism_name,
            String mechanism_addr,
            Boolean is_support_teach_paypal,
            double longitude,
            double latitude,
            String mechanism_telephone,
            String categories_child,
            String contact_telephone,
            String contacts,
            String payee_logon_id,
            String introduction_content,
            String support_means,
            String introduction_pic,
            String facade_view,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {

        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("type", type);
            obj.putOpt("categories", categories);
            obj.putOpt("mechanism_logo", mechanism_logo);
            obj.putOpt("mechanism_name", mechanism_name);
            obj.putOpt("mechanism_addr", mechanism_addr);
            obj.putOpt("is_support_teach_paypal", is_support_teach_paypal);
            obj.putOpt("longitude", longitude);
            obj.putOpt("latitude", latitude);
            obj.putOpt("mechanism_telephone", mechanism_telephone);
            obj.putOpt("categories_child", categories_child);
            obj.putOpt("contact_telephone", contact_telephone);
            obj.putOpt("contacts", contacts);
            obj.putOpt("payee_logon_id", payee_logon_id);
            obj.putOpt("introduction_content", introduction_content);
            obj.putOpt("support_means", support_means);
            obj.putOpt("introduction_pic", introduction_pic);
            obj.putOpt("facade_view", facade_view);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertMasterMechanism(
                        body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver payOneCourseOrder(
            String user_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String binding_mechanism_id,
            NetworkSubscriber<OneCourseOrderModel> subscriber) {

        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("source", "android");
            obj.putOpt("member_duration", member_duration);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("binding_mechanism_id", binding_mechanism_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().payOneCourseOrder(
                        body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver userFollowMechanismInsert(
            String user_id,
            String mechanism_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("mechanism_id", mechanism_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().userFollowMechanismInsert(
                        body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismMasterInfoList(
            String mechanism_id,
            Integer currentPage,
            Integer pageSize,
            String type,
            String status,
            NetworkSubscriber<MasterInfoHomeModel> subscriber) {

        SchedulersUtils.onBackUI(
                getUserService().queryMechanismMasterInfoList(
                        mechanism_id,
                        currentPage,
                        pageSize,
                        type,
                        status
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertMechanismMaster(
            String login_pass,
            String login_name,
            String nick_name,
            String register_platform,
            String mechanism_id,
            String avatar,
            String mobile,
            String sex,
            String status,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("login_pass", login_pass);
            obj.putOpt("login_name", login_name);
            obj.putOpt("nick_name", nick_name);
            obj.putOpt("register_platform", register_platform);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("avatar", avatar);
            obj.putOpt("mobile", mobile);
            obj.putOpt("sex", sex);
            obj.putOpt("status", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertMechanismMaster(
                        body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver updateMechanismMaster(
            String user_id,
            String login_pass,
            String login_name,
            String nick_name,
            String register_platform,
            String mechanism_id,
            String avatar,
            String mobile,
            String sex,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("login_pass", login_pass);
            obj.putOpt("login_name", login_name);
            obj.putOpt("nick_name", nick_name);
            obj.putOpt("register_platform", register_platform);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("avatar", avatar);
            obj.putOpt("mobile", mobile);
            obj.putOpt("sex", sex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertMechanismMaster(
                        body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryStudentList(
            String mechanism_id,
            String status,
            String studycard_id,
            String type,
            Integer pageSize,
            Integer currentPage,
            DisposableObserver<StudentModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryStudentList(
                        mechanism_id,
                        status,
                        studycard_id,
                        type,
                        pageSize,
                        currentPage)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertMechanismCourse(
            String type,
            String source,
            String mechanism_id,
            String master_id,
            String title,
            String start_time,
            String end_time,
            String offset,
            String service_type,
            String master_set_price_id,
            String identity_type,
            String study_card_ids,
            String classroom,
            Integer number_of_lessons,
            String status,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("type", type);
            obj.putOpt("source", source);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("master_id", master_id);
            obj.putOpt("title", title);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("offset", offset);
            obj.putOpt("service_type", service_type);
            obj.putOpt("master_set_price_id", master_set_price_id);
            obj.putOpt("identity_type", identity_type);
            obj.putOpt("study_card_ids", study_card_ids);
            obj.putOpt("classroom", classroom);
            obj.putOpt("number_of_lessons", number_of_lessons);
            obj.putOpt("status", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertMechanismCourse(
                        body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismOfflineSchedule(
            String mechanism_id,
            String type,
            String status,
            String start_time,
            String end_time,
            String offset,
            String identity_type,
            String create_type,
            NetworkSubscriber<MechanismOfflineScheduleModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismOfflineSchedule(
                        mechanism_id,
                        type,
                        status,
                        start_time,
                        end_time,
                        offset,
                        identity_type,
                        create_type
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismOfflineStatusSchedule(
            String mechanism_id,
            String type,
            String status,
            String identity_type,
            Integer currentPage,
            Integer pageSize,
            String start_time,
            String end_time,
            String offset,
            NetworkSubscriber<MechanismOfflineScheduleModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismOfflineStatusSchedule(
                        mechanism_id,
                        type,
                        status,
                        identity_type,
                        currentPage,
                        pageSize,
                        start_time,
                        end_time,
                        offset
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertOfflineCourse(
            String type,
            String source,
            String mechanism_id,
            String master_id,
            String title,
            String start_time,
            String end_time,
            String offset,
            String service_type,
            String master_set_price_id,
            String identity_type,
            String classroom,
            String connect_peoplenum,
            String number_of_lessons,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("type", type);
            obj.putOpt("source", source);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("master_id", master_id);
            obj.putOpt("title", title);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("offset", offset);
            obj.putOpt("service_type", service_type);
            obj.putOpt("master_set_price_id", master_set_price_id);
            obj.putOpt("identity_type", identity_type);
            obj.putOpt("classroom", classroom);
            obj.putOpt("connect_peoplenum", connect_peoplenum);
            obj.putOpt("number_of_lessons", number_of_lessons);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertOfflineCourse(
                        body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertSummaryOffline(
            String appointment_id,
            String content,
            String master_id,
            String mechanism_id,
            String photo_url,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("appointment_id", appointment_id);
            obj.putOpt("content", content);
            obj.putOpt("master_id", master_id);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("photo_url", photo_url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertSummaryOffline(
                        body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryStudentOfflineSchedule(
            String user_id,
            String type,
            String status,
            Boolean is_comment,
            Integer currentPage,
            Integer pageSize,
            DisposableObserver<StudentScheduleModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryStudentOfflineSchedule(
                        user_id,
                        type,
                        status,
                        is_comment,
                        currentPage,
                        pageSize
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryStudentOfflineSchedulePackageDetail(
            String study_card_id,
            Integer currentPage,
            Integer pageSize,
            DisposableObserver<StudentScheduleModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryStudentOfflineSchedulePackageDetail(
                        study_card_id,
                        currentPage,
                        pageSize
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver sign(
            String id,
            String status,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("status", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateUserAppointment(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver studentScheduleAlipay(
            String user_id,
            String rcharge_type,
            String amount,
            String source,
            String course_num,
            String master_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String appointment_id,
            String user_study_card_id,
            String coupon_id,
            String points,
            String pay_type,
            String mechanism_id,
            String user_appointment_id,
            String title,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("amount", amount);
            obj.putOpt("source", source);
            obj.putOpt("course_num", course_num);
            obj.putOpt("master_id", master_id);
            obj.putOpt("member_duration", member_duration);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("study_type", study_type);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("appointment_id", appointment_id);
            obj.putOpt("user_study_card_id", user_study_card_id);
            obj.putOpt("coupon_id", coupon_id);
            obj.putOpt("points", points);
            obj.putOpt("pay_type", pay_type);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("user_appointment_id", user_appointment_id);
            obj.putOpt("title", title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().getAliOrder(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver payOntimeCourseAly(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String pay_type,
            String mechanism_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("source", source);
            obj.putOpt("course_num", course_num);
            obj.putOpt("master_id", master_id);
            obj.putOpt("member_duration", member_duration);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("study_type", study_type);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("pay_type", pay_type);
            obj.putOpt("mechanism_id", mechanism_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().getAliOrder(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver payOntimeCourseWx(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String pay_type,
            String mechanism_id,
            NetworkSubscriber<WxPayModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("source", source);
            obj.putOpt("course_num", course_num);
            obj.putOpt("master_id", master_id);
            obj.putOpt("member_duration", member_duration);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("study_type", study_type);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("pay_type", pay_type);
            obj.putOpt("mechanism_id", mechanism_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().getWxOrder(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver studentScheduleWxpay(
            String user_id,
            String rcharge_type,
            String amount,
            String source,
            String course_num,
            String master_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String appointment_id,
            String user_study_card_id,
            String coupon_id,
            String points,
            String pay_type,
            String mechanism_id,
            String user_appointment_id,
            String title,
            DisposableObserver<WxPayModel> subscriber) {

        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("amount", amount);
            obj.putOpt("source", source);
            obj.putOpt("course_num", course_num);
            obj.putOpt("master_id", master_id);
            obj.putOpt("member_duration", member_duration);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("study_type", study_type);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("appointment_id", appointment_id);
            obj.putOpt("user_study_card_id", user_study_card_id);
            obj.putOpt("coupon_id", coupon_id);
            obj.putOpt("points", points);
            obj.putOpt("pay_type", pay_type);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("user_appointment_id", user_appointment_id);
            obj.putOpt("title", title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().getWxOrder(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertTeachPayMechanismComment(
            String mastersetprice_id,
            String appointment_id,
            String mechanism_id,
            String content,
            String user_id,
            String type,
            String photo_url,
            String course_quality,
            String environment,
            String faculty,
            String cost_effectiveness,
            String attitude,
            String average_score,
            String master_id,
            String user_appointment_id,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("mastersetprice_id", mastersetprice_id);
            obj.putOpt("appointment_id", appointment_id);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("content", content);
            obj.putOpt("user_id", user_id);
            obj.putOpt("type", type);
            obj.putOpt("photo_url", photo_url);
            obj.putOpt("course_quality", course_quality);
            obj.putOpt("environment", environment);
            obj.putOpt("faculty", faculty);
            obj.putOpt("cost_effectiveness", cost_effectiveness);
            obj.putOpt("attitude", attitude);
            obj.putOpt("average_score", average_score);
            obj.putOpt("master_id", master_id);
            obj.putOpt("user_appointment_id", user_appointment_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertTeachPayMechanismComment(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryStudentExclusiveCoursesList(
            String type,
            String user_id,
            Integer currentPage,
            Integer pageSize,
            DisposableObserver<StudentCoursePackageModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryStudentExclusiveCoursesList(
                        type,
                        user_id,
                        currentPage,
                        pageSize
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismOfflineAppointmentSchedule(
            String create_type,
            String end_time,
            String identity_type,
            String master_set_price_id,
            String mechanism_id,
            String offset,
            String start_time,
            String type,
            NetworkSubscriber<MechanismOfflineScheduleModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismOfflineAppointmentSchedule(
                        create_type,
                        end_time,
                        identity_type,
                        master_set_price_id,
                        mechanism_id,
                        offset,
                        start_time,
                        type
                )
                , subscriber);
        return subscriber;

    }

    public static DisposableObserver insertMechanismUserAppointment(
            String user_id,
            String appointment_id,
            String study_card_id,
            String offline_mobile,
            String title,
            String classroom,
            String number_of_lessons,
            String source,
            String coupon_id,
            String point,
            String mechanism_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("appointment_id", appointment_id);
            obj.putOpt("study_card_id", study_card_id);
            obj.putOpt("offline_mobile", offline_mobile);
            obj.putOpt("title", title);
            obj.putOpt("classroom", classroom);
            obj.putOpt("number_of_lessons", number_of_lessons);
            obj.putOpt("source", source);
            obj.putOpt("coupon_id", coupon_id);
            obj.putOpt("point", point);
            obj.putOpt("mechanism_id", mechanism_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertMechanismUserAppointment(
                        body
                )
                , subscriber);

        return subscriber;
    }

    public static DisposableObserver queryMechanismOrderList(
            String type,
            String rcharge_type,
            String user_id,
            String mechanism_id,
            Boolean finished,
            Boolean is_one_time_payment,
            Integer pageSize,
            Integer currentPage,
            String study_type,
            String course_num,
            String status,
            DisposableObserver<MechanismOrderModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("type", type);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("user_id", user_id);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("finished", finished);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("pageSize", pageSize);
            obj.putOpt("currentPage", currentPage);
            obj.putOpt("study_type", study_type);
            obj.putOpt("course_num", course_num);
            obj.putOpt("status", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismOrderList(
                        type,
                        rcharge_type,
                        user_id,
                        mechanism_id,
                        finished,
                        is_one_time_payment,
                        pageSize,
                        currentPage,
                        study_type,
                        course_num,
                        status
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver getLongOrder(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String user_study_card_id,
            String paying_for_identity,
            String pay_type,
            String mechanism_id,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("source", source);
            obj.putOpt("course_num", course_num);
            obj.putOpt("master_id", master_id);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("study_type", study_type);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("user_study_card_id", user_study_card_id);
            obj.putOpt("paying_for_identity", paying_for_identity);
            obj.putOpt("pay_type", pay_type);
            obj.putOpt("mechanism_id", mechanism_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().getAliOrder(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver getWxLongOrder(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String user_study_card_id,
            String paying_for_identity,
            String pay_type,
            String mechanism_id,
            DisposableObserver<WxPayModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("source", source);
            obj.putOpt("course_num", course_num);
            obj.putOpt("master_id", master_id);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("study_type", study_type);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("user_study_card_id", user_study_card_id);
            obj.putOpt("paying_for_identity", paying_for_identity);
            obj.putOpt("pay_type", pay_type);
            obj.putOpt("mechanism_id", mechanism_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().getWxOrder(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismAverageScore(String mechanism_id, NetworkSubscriber<MechanismAverageScoreModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismAverageScore(
                        mechanism_id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismCommentList(
            String mechanism_id,
            String type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<MechanismCommentModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismCommentList(
                        mechanism_id,
                        type,
                        currentPage,
                        pageSize
                )
                , subscriber);
        return subscriber;

    }


    public static DisposableObserver insertUserCollection(
            String user_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertUserCollection(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryUserCouponList(
            String user_id,
            String status,
            Integer pageSize,
            Integer currentPage,
            NetworkSubscriber<UserCouponModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryUserCouponList(
                        user_id,
                        status,
                        pageSize,
                        currentPage
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryPayDetails(
            String course_num,
            String studycard_id,
            String user_study_card_id,
            String user_id,
            String mechanism_id,
            Boolean is_one_time_payment,
            DisposableObserver<PayDetailModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("course_num", course_num);
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("user_study_card_id", user_study_card_id);
            obj.putOpt("user_id", user_id);
            obj.putOpt("mechanism_id", mechanism_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().queryPayDetails(
                        course_num,
                        studycard_id,
                        user_study_card_id,
                        user_id,
                        mechanism_id,
                        is_one_time_payment
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryTeachPayUserStatistics(String user_id, NetworkSubscriber<UserStatisticsModel> subscriber) {

        SchedulersUtils.onBackUI(
                getUserService().queryTeachPayUserStatistics(
                        user_id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryTeachPaypalDetail(String user_id, NetworkSubscriber<TeachPaypalDetailModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryTeachPaypalDetail(
                        user_id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryTeachPaypalUserGoldType(
            String user_id,
            String status,
            Boolean is_teach_paypal,
            NetworkSubscriber<TeachPaypalUserGoldTypeModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryTeachPaypalUserGoldType(
                        user_id,
                        status,
                        is_teach_paypal
                )
                , subscriber);
        return subscriber;

    }

    public static DisposableObserver insertLoginSignIn(
            String user_id,
            NetworkSubscriber<InsertSignDayModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertLoginSignIn(
                        user_id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryLongOrderPayInfo(
            String id,
            DisposableObserver<LongOrderPayInfoModel> subscriber) {

        SchedulersUtils.onBackUI(
                getUserService().queryLongOrderPayInfo(
                        id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver querySummaryList(String appointment_id, DisposableObserver<SummaryInfoModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().querySummaryList(
                        appointment_id
                )
                , subscriber);
        return subscriber;
    }

    /**
     * @param id
     * @param subscriber
     * @return
     * @desc 学生取消课程
     */
    public static DisposableObserver updateCancelCourse(
            String id,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateCancelCourse(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver getUserPointsList(
            Integer currentPage,
            Integer pageSize,
            String user_id,
            Boolean is_earnings,
            String start_time,
            DisposableObserver<MyPointsModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().getUserPointsList(
                        currentPage,
                        pageSize,
                        user_id,
                        is_earnings,
                        start_time
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismOfflineDetailsCount(
            String mechanism_id,
            DisposableObserver<MechanismIncomeStatisticsModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismOfflineDetailsCount(
                        mechanism_id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismOfflineDetails(
            String mechanism_id,
            String study_type,
            Boolean finished,
            Integer currentPage,
            Integer pageSize,
            DisposableObserver<MechanismIncomeListModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismOfflineDetails(
                        mechanism_id,
                        study_type,
                        finished,
                        currentPage,
                        pageSize
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryIsMechanismCourse(
            String mechanism_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryIsMechanismCourse(
                        mechanism_id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver resignTeacher(
            String id,
            String status,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("status", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().resignTeacher(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismCourseCommentList(
            String mastersetprice_id,
            String type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<MechanismCommentModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismCourseCommentList(
                        mastersetprice_id,
                        type,
                        currentPage,
                        pageSize
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertH5GetCoupon(
            String login_name,
            String verification_code,
            String nick_name,
            String sex,
            String preference,
            String relationships,
            String age,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("login_name", login_name);
            obj.putOpt("verification_code", verification_code);
            obj.putOpt("nick_name", nick_name);
            obj.putOpt("sex", sex);
            obj.putOpt("preference", preference);
            obj.putOpt("relationships", relationships);
            obj.putOpt("age", age);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertH5GetCoupon(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver mechanismClassroomInsert(
            String mechanism_id,
            String name,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("name", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().mechanismClassroomInsert(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver mechanismClassroomUpdate(
            String id,
            String name,
            String status,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("name", name);
            obj.putOpt("status", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().mechanismClassroomUpdate(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryManagerClassroomListPage(
            String mechanism_id,
            Integer pageSize,
            Integer currentPage,
            DisposableObserver<ClassRoomModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryManagerClassroomListPage(
                        mechanism_id,
                        pageSize,
                        currentPage
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismClassroom(
            String mechanism_id,
            String start_time,
            String status,
            DisposableObserver<MechanismClassroomSelectModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismClassroom(
                        mechanism_id,
                        start_time,
                        status
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertUserGrouping(
            String user_id,
            String master_set_price_id,
            String study_card_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("master_set_price_id", master_set_price_id);
            obj.putOpt("study_card_id", study_card_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertUserGrouping(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver getMechanismUserList(
            Integer currentPage,
            Integer pageSize,
            String mechanism_id,
            DisposableObserver<MechanismUserModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().getMechanismUserList(
                        currentPage,
                        pageSize,
                        mechanism_id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver mechanismUserStatus(
            String id,
            Boolean is_new,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("is_new", is_new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().mechanismUserStatus(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver deleteMasterAppointment(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().deleteMasterAppointment(
                        id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver getNewsInformationInfoList(
            Integer curPage,
            Integer pageSize,
            String user_id,
            String classfiy,
            String status,
            DisposableObserver<NoteUserModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().getNewsInformationInfoList(curPage, pageSize, user_id, classfiy, status, true)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryNoteNews(
            String oper_id,
            NetworkSubscriber<NoteMessageModel> subscriber) {

        SchedulersUtils.onBackUI(
                getUserService().queryNoteNews(oper_id)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver setBackground(
            String user_id,
            String background_pic,
            DisposableObserver<InsertInfoResultModel> subscriber) {

        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("background_pic", background_pic);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateUserInfo(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver setMotherTongue(
            String user_id,
            String mother_tongue,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("mother_tongue", mother_tongue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateUserInfo(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver deleteNoteUser(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("subscriber", subscriber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().deleteNoteUser(id)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertHistory(
            String user_id,
            String history_id,
            String operation_type,
            String history_type,
            String destination,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {

        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("history_id", history_id);
            obj.putOpt("operation_type", operation_type);
            obj.putOpt("history_type", history_type);
            obj.putOpt("destination", destination);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertHistory(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertNoteComment(
            String user_id,
            Boolean is_reply,
            String reply_id,
            String parent_id,
            String note_id,
            String content,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("is_reply", is_reply);
            obj.putOpt("reply_id", reply_id);
            obj.putOpt("parent_id", parent_id);
            obj.putOpt("note_id", note_id);
            obj.putOpt("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertNoteComment(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryNoteUserfindById(
            String id,
            NetworkSubscriber<NoteUserModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().queryNoteUserfindById(id)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryHistory(
            String user_id,
            String history_id,
            String operation_type,
            String history_type,
            String destination,
            NetworkSubscriber<LikeInfoModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("history_id", history_id);
            obj.putOpt("operation_type", operation_type);
            obj.putOpt("history_type", history_type);
            obj.putOpt("destination", destination);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().queryHistory(
                        user_id,
                        history_id,
                        operation_type,
                        history_type
                ), subscriber);
        return subscriber;
    }

    public static DisposableObserver noteUserInsert(
            String content,
            String user_id,
            Integer status,
            String picts,
            Integer style,
            String classfiy,
            String master_set_price_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("content", content);
            obj.putOpt("user_id", user_id);
            obj.putOpt("status", status);
            obj.putOpt("picts", picts);
            obj.putOpt("style", style);
            obj.putOpt("classfiy", classfiy);
            obj.putOpt("master_set_price_id", master_set_price_id);
            obj.putOpt("is_teach_paypal", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());

        SchedulersUtils.onBackUI(
                getUserService().noteUserInsert(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryNoteCommentDetailsListPage(
            String note_id,
            String parent_id,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<NoteCommentDetailListModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryNoteCommentDetailsListPage(
                        note_id,
                        parent_id,
                        currentPage,
                        pageSize)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryHistoryListPage(
            Integer status,
            String history_id,
            String operation_type,
            String history_type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<HistoryListModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryHistoryListPage(
                        status,
                        history_id,
                        operation_type,
                        history_type,
                        currentPage,
                        pageSize)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryShareHistoryListPage(
            String history_id,
            String operation_type,
            String history_type,
            int currentPage,
            int pageSize,
            NetworkSubscriber<ShareHistoryListModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryShareHistoryListPage(
                        history_id,
                        operation_type,
                        history_type,
                        currentPage,
                        pageSize)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver deleteNoteComment(String id, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().deleteNoteComment(id)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismCategoryChildList(
            NetworkSubscriber<MechanismCategoryModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismCategoryChildList()
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver versionIteration(
            String version,
            String platform,
            NetworkSubscriber<VersionIterationModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("version", version);
            obj.putOpt("platform", platform);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().versionIteration(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertMechanismClasses(
            String mechanism_id,
            String name,
            String master_set_price_id,
            String student_count_max,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("name", name);
            obj.putOpt("master_set_price_id", master_set_price_id);
            obj.putOpt("student_count_max", student_count_max);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertMechanismClasses(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismClasses(
            String mechanism_id,
            Integer currentPage,
            Integer pageSize,
            String master_set_price_id,
            Boolean is_scheduling_over,
            String status,
            NetworkSubscriber<MechanismClassModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismClasses(
                        mechanism_id,
                        currentPage,
                        pageSize,
                        master_set_price_id,
                        is_scheduling_over,
                        status
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver arrangeMechanismFixCourse(
            String id,
            String type,
            String weekOfDays,
            Boolean is_repeat,
            String start_time,
            String end_time,
            String master_id,
            String start_date,
            String date,
            String classroom,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("type", type);
            obj.putOpt("weekOfDays", weekOfDays);
            obj.putOpt("is_repeat", is_repeat);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("master_id", master_id);
            obj.putOpt("start_date", start_date);
            obj.putOpt("date", date);
            obj.putOpt("classroom", classroom);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().arrangeMechanismFixCourse(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryStudentInfoList(
            String type,
            String mechanism_id,
            String studycard_id,
            Integer currentPage,
            Integer pageSize,
            DisposableObserver<ClassStudentModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryStudentInfoList(
                        type,
                        mechanism_id,
                        studycard_id,
                        currentPage,
                        pageSize
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver updateUserAppointmentUserConfirm(
            String id,
            Boolean whether,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("whether", whether);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateUserAppointmentUserConfirm(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMasterAppointmentStatusById(String id, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMasterAppointmentStatusById(
                        id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver updateMergerClass(String id, String merger_ids, String status, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("merger_ids", merger_ids);
            obj.putOpt("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateMergerClass(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertCopyCourse(
            String id,
            String start_time,
            String end_time,
            String dates,
            String type,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("dates", dates);
            obj.putOpt("type", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertCopyCourse(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryBusinessActivityTypeList(
            Integer currentPage,
            Integer pageSize,
            String status,
            DisposableObserver<BusinessActivityTypeModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryBusinessActivityTypeList(
                        currentPage,
                        pageSize,
                        status
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertBusinessSinglePaymentActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String price,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("is_new_customers", is_new_customers);
            obj.putOpt("master_set_price_ids", master_set_price_ids);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("type", type);
            obj.putOpt("tags", tags);
            obj.putOpt("price", price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertBusinessActivity(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertBusinessSalesCourseActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String coupon_time,
            String discount_amount,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("is_new_customers", is_new_customers);
            obj.putOpt("master_set_price_ids", master_set_price_ids);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("type", type);
            obj.putOpt("tags", tags);
            obj.putOpt("coupon_time", coupon_time);
            obj.putOpt("discount_amount", discount_amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertBusinessActivity(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertBusinessGroupingActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String number_of_people,
            String each_time_percentage,
            String each_time_percentage_max,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("is_new_customers", is_new_customers);
            obj.putOpt("master_set_price_ids", master_set_price_ids);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("type", type);
            obj.putOpt("tags", tags);
            obj.putOpt("number_of_people", number_of_people);
            obj.putOpt("each_time_percentage", each_time_percentage);
            obj.putOpt("each_time_percentage_max", each_time_percentage_max);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertBusinessActivity(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertBusinessExperienceSpecialsActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String coupon_time,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("is_new_customers", is_new_customers);
            obj.putOpt("master_set_price_ids", master_set_price_ids);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("start_time", start_time);
            obj.putOpt("end_time", end_time);
            obj.putOpt("type", type);
            obj.putOpt("tags", tags);
            obj.putOpt("coupon_time", coupon_time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertBusinessActivity(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryActivityListPageByType(
            Integer currentPage,
            Integer pageSize,
            String status,
            String type,
            String latitude,
            String longitude,
            DisposableObserver<MasterSetPriceModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryActivityListPageByType(
                        currentPage,
                        pageSize,
                        status,
                        type,
                        latitude,
                        longitude
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver buyCouponByAli(
            String user_id,
            String rcharge_type,
            String source,
            String rcharge_abstract,
            Boolean is_one_time_payment,
            String pay_type,
            String invite_code,
            String course_num,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("source", source);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("pay_type", pay_type);
            obj.putOpt("invite_code", invite_code);
            obj.putOpt("course_num", course_num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().buyCouponByAli(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver buyCouponByWx(
            String user_id,
            String rcharge_type,
            String source,
            String rcharge_abstract,
            Boolean is_one_time_payment,
            String pay_type,
            String invite_code,
            String course_num,
            DisposableObserver<WxPayModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("source", source);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("pay_type", pay_type);
            obj.putOpt("invite_code", invite_code);
            obj.putOpt("course_num", course_num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().buyCouponByWx(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryStudentOrderList(
            String study_type,
            String user_id,
            Integer pageSize,
            Integer currentPage,
            String status,
            DisposableObserver<MechanismOrderModel> subscriber) {

        SchedulersUtils.onBackUI(
                getUserService().queryStudentOrderList(
                        study_type,
                        user_id,
                        pageSize,
                        currentPage,
                        status
                )
                , subscriber);
        return subscriber;

    }

    public static DisposableObserver useCoupon(
            String id,
            String master_set_price_id,
            String user_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
            obj.putOpt("master_set_price_id", master_set_price_id);
            obj.putOpt("user_id", user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().useCoupon(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver getLiveMasterSetPriceList(
            Integer currentPage,
            Integer pageSize,
            String mechanism_id,
            String status, DisposableObserver<LiveModel> subscriber) {

        SchedulersUtils.onBackUI(
                getUserService().getLiveMasterSetPriceList(
                        currentPage,
                        pageSize,
                        mechanism_id,
                        status
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertMasterSetPriceStream(
            String master_set_price_ids,
            String start_time,
            String title,
            String mechanism_id,
            String live_stream_prices,
            String living_single_session_prices,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("master_set_price_ids", master_set_price_ids);
            obj.putOpt("start_time", start_time);
            obj.putOpt("title", title);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("live_stream_prices", live_stream_prices);
            obj.putOpt("living_single_session_prices", living_single_session_prices);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertMasterSetPriceStream(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMasterSetPriceDisplay(
            Integer currentPage,
            Integer pageSize,
            String status,
            String live_streaming_id,
            NetworkSubscriber<MasterSetPriceDisplayModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMasterSetPriceDisplay(
                        currentPage,
                        pageSize,
                        status,
                        live_streaming_id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver insertMasterSetPriceDisplay(
            String master_set_price_ids,
            String mechanism_id,
            String live_stream_prices,
            String live_streaming_id,
            String living_single_session_prices,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("master_set_price_ids", master_set_price_ids);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("live_stream_prices", live_stream_prices);
            obj.putOpt("live_streaming_id", live_streaming_id);
            obj.putOpt("living_single_session_prices", living_single_session_prices);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().insertMasterSetPriceDisplay(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver updateMasterSetPriceDisplay(
            String master_set_price_id,
            Boolean is_live_streaming,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("master_set_price_id", master_set_price_id);
            obj.putOpt("is_live_streaming", is_live_streaming);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateMasterSetPriceDisplay(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver updateOpenLiving(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateOpenLiving(body)
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver updateCloseLiving(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateCloseLiving(body)
                , subscriber);
        return subscriber;

    }

    public static DisposableObserver queryMasterSetPriceStreamDetail(
            String id,
            NetworkSubscriber<LiveDetailModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMasterSetPriceStreamDetail(
                        id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver payOneCourseLiveStreamByAli(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String coupon_ids,
            String pay_type,
            String mechanism_id,
            String title,
            String type,
            String live_streaming_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("source", source);
            obj.putOpt("course_num", course_num);
            obj.putOpt("member_duration", member_duration);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("study_type", study_type);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("coupon_ids", coupon_ids);
            obj.putOpt("pay_type", pay_type);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("title", title);
            obj.putOpt("type", type);
            obj.putOpt("live_streaming_id", live_streaming_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().getAliOrder(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver payOneCourseLiveStreamByWx(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String coupon_ids,
            String pay_type,
            String mechanism_id,
            String title,
            String type,
            String live_streaming_id,
            NetworkSubscriber<WxPayModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("user_id", user_id);
            obj.putOpt("rcharge_type", rcharge_type);
            obj.putOpt("source", source);
            obj.putOpt("course_num", course_num);
            obj.putOpt("member_duration", member_duration);
            obj.putOpt("rcharge_abstract", rcharge_abstract);
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("study_type", study_type);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("coupon_ids", coupon_ids);
            obj.putOpt("pay_type", pay_type);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("title", title);
            obj.putOpt("type", type);
            obj.putOpt("live_streaming_id", live_streaming_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().getWxOrder(
                        body
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryPayLiveStreamDetails(
            String studycard_id,
            String user_id,
            String mechanism_id,
            Boolean is_one_time_payment,
            String live_streaming_id,
            NetworkSubscriber<PayDetailModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("studycard_id", studycard_id);
            obj.putOpt("user_id", user_id);
            obj.putOpt("mechanism_id", mechanism_id);
            obj.putOpt("is_one_time_payment", is_one_time_payment);
            obj.putOpt("live_streaming_id", live_streaming_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());

        SchedulersUtils.onBackUI(
                getUserService().queryPayLiveStreamDetails(
                        studycard_id,
                        user_id,
                        mechanism_id,
                        is_one_time_payment,
                        live_streaming_id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMechanismDisplayIsLiving(
            String live_streaming_id,
            String status,
            NetworkSubscriber<MasterSetPriceModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMechanismDisplayIsLiving(
                        live_streaming_id,
                        status
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver queryMasterSetPriceDisplayDetail(
            String id,
            String live_streaming_id,
            NetworkSubscriber<MasterSetPriceDisplayModel> subscriber) {
        SchedulersUtils.onBackUI(
                getUserService().queryMasterSetPriceDisplayDetail(
                        id,
                        live_streaming_id
                )
                , subscriber);
        return subscriber;
    }

    public static DisposableObserver updateAddClick(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        JSONObject obj = new JSONObject();
        try {
            obj.putOpt("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HttpHelper", "requestBody-json:" + obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
        SchedulersUtils.onBackUI(
                getUserService().updateAddClick(
                        body
                )
                , subscriber);
        return subscriber;
    }
}