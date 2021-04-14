package com.huihe.base_lib.utils.manager;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.huihe.base_lib.db.LanguageEntity;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.login.RegisterUserInfoModel;
import com.huihe.base_lib.model.login.WxLoginUserEntity;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.Utils;

import java.lang.reflect.Type;
import java.util.List;


public class LoginHelper {

    public static final String KEY_LOGIN_INFO = "LOGIN_INFO";
    public static final String KEY_ALERTED = "alerted";
    public static final String IS_IM_LOGIN = "is_im_login";
    private static final String KEY_LANGUAGE = "LANGUAGE";
    private static final String KEY_MULTI_SELECT_LANGUAGE = "MULTI_SELECT_LANGUAGE";
    private static final String KEY_LOGIN_NUM = "KEY_LOGIN_NUM";
    private static final String KEY_MULTI_SELECT_COUNTRY = "KEY_MULTI_SELECT_COUNTRY";
    private static final String KEY_NEW_MESSAGE_ALERT_NOTIFY = "KEY_NEW_MESSAGE_ALERT_NOTIFY";
    private static final String KEY_SHOW_MESSAGE_DETAILS_FOR_NOTIFY = "KEY_SHOW_MESSAGE_DETAILS_FOR_NOTIFY";
    private static final String KEY_VOICE_REMINDER = "KEY_VOICE_REMINDER";
    private static final String KEY_VIBRATION_REMINDER = "KEY_VIBRATION_REMINDER";
    private static final String KEY_AUTO_DOWNLOAD_UNDER_WIFI = "KEY_AUTO_DOWNLOAD_UNDER_WIFI";
    private static final String KEY_LIVE_TIME_DIFF = "KEY_LIVE_TIME_DIFF";
    private static final String KEY_IS_SIMPLIFY_VERSION = "is_simplify_version";
    public static final String KEY_IS_POLICYGRANT = "is_PolicyGrant";
    private static RegisterUserInfoModel mRegisterUserInfoModel;
    private static WxLoginUserEntity mWxLoginUserEntity;

    public static WxLoginUserEntity getmWxLoginUserEntity() {
        return mWxLoginUserEntity;
    }

    public static RegisterUserInfoModel getRegisterUserInfoModel() {
        return mRegisterUserInfoModel;
    }

    /**
     * 判断是否登录
     */
    public static boolean isLogin() {
        LoginResultEntity loginInfo = getLoginInfo();
        boolean b = loginInfo != null;
        return b;
    }

    public static void saveUserData(LoginResultEntity model) {
        checkIsNotNull(model);
        DataCacheManager.getInstance().putJsonModel(KEY_LOGIN_INFO, model);
        saveLoginNum();
    }

    public static void saveRegisterInfo(RegisterUserInfoModel registerUserInfoModel) {
        mRegisterUserInfoModel = registerUserInfoModel;
    }

    private static void checkIsNotNull(LoginResultEntity model) {
        if (model == null) {
            throw new IllegalArgumentException("LoginResultModel is null");
        }
    }

    public static LoginResultEntity getLoginInfo() {

        return DataCacheManager.getInstance().getJsonModel(KEY_LOGIN_INFO, LoginResultEntity.class);
    }

    /**
     * 登出操作，情况本地登录用户信息
     */
    public static void clearData() {
        DataCacheManager.getInstance().remove(KEY_LOGIN_INFO);
        DataCacheManager.getInstance().remove(IS_IM_LOGIN);
        DataCacheManager.getInstance().remove(KEY_LANGUAGE);
        DataCacheManager.getInstance().remove(KEY_MULTI_SELECT_LANGUAGE);
        DataCacheManager.getInstance().remove(KEY_MULTI_SELECT_COUNTRY);
        DataCacheManager.getInstance().remove(KEY_LIVE_TIME_DIFF);
        DataCacheManager.getInstance().remove(KEY_IS_SIMPLIFY_VERSION);
        DataCacheManager.getInstance().remove(KEY_NEW_MESSAGE_ALERT_NOTIFY);
        DataCacheManager.getInstance().remove(KEY_SHOW_MESSAGE_DETAILS_FOR_NOTIFY);
        DataCacheManager.getInstance().remove(KEY_VOICE_REMINDER);
        DataCacheManager.getInstance().remove(KEY_VIBRATION_REMINDER);
        DataCacheManager.getInstance().remove(KEY_AUTO_DOWNLOAD_UNDER_WIFI);
        DataCleanManager.clearAllCache(AppManager.getInstance().currentActivity());
        Utils.clearData();
        mWxLoginUserEntity = null;
    }

    public static void saveHomeShowLanguage(LanguageEntity languageEntity) {
        DataCacheManager.getInstance().putString(KEY_LANGUAGE, JsonUtil.toJson(languageEntity));
    }

    public static LanguageEntity getHomeShowLanguage() {
        String language = DataCacheManager.getInstance().getString(KEY_LANGUAGE);
        if (!TextUtils.isEmpty(language)) {
            LanguageEntity languageEntity = JsonUtil.fromJson(language, LanguageEntity.class);
            return languageEntity;
        }
        return null;
    }

    public static void saveMultiSelectLanguageList(List<LanguageEntity> checkedLanguageList) {
        DataCacheManager.getInstance().putJsonList(KEY_MULTI_SELECT_LANGUAGE, checkedLanguageList);
    }

    public static void saveMultiSelectCountryList(List<String> list) {
        DataCacheManager.getInstance().putJsonList(KEY_MULTI_SELECT_COUNTRY, list);
    }

    public static List<String> getMultiSelectCountryList() {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return DataCacheManager.getInstance().getJsonList(KEY_MULTI_SELECT_COUNTRY, listType);
    }

    public static List<LanguageEntity> getMultiSelectLanguageList() {
        Type listType = new TypeToken<List<LanguageEntity>>() {
        }.getType();
        return DataCacheManager.getInstance().getJsonList(KEY_MULTI_SELECT_LANGUAGE, listType);
    }

    public static int getLoginNum() {
        return DataCacheManager.getInstance().getInt(KEY_LOGIN_NUM, 0);
    }

    public static void saveLoginNum() {
        DataCacheManager.getInstance().putInt(KEY_LOGIN_NUM, getLoginNum() + 1);
    }

    /**
     * 设置页面保存的数据
     *
     * @param isChecked
     */
    public static void setNewMessageAlertNotify(boolean isChecked) {
        DataCacheManager.getInstance().putBoolean(KEY_NEW_MESSAGE_ALERT_NOTIFY, isChecked);
    }

    public static boolean getNewMessageAlertNotify() {
        return DataCacheManager.getInstance().getBoolean(KEY_NEW_MESSAGE_ALERT_NOTIFY);
    }

    public static void setShowMessageDetailsForNotify(boolean isChecked) {
        DataCacheManager.getInstance().putBoolean(KEY_SHOW_MESSAGE_DETAILS_FOR_NOTIFY, isChecked);
    }

    public static boolean getShowMessageDetailsForNotify() {
        return DataCacheManager.getInstance().getBoolean(KEY_SHOW_MESSAGE_DETAILS_FOR_NOTIFY);
    }

    public static void setVoiceReminder(boolean isChecked) {
        DataCacheManager.getInstance().putBoolean(KEY_VOICE_REMINDER, isChecked);
    }

    public static boolean getVoiceReminder() {
        return DataCacheManager.getInstance().getBoolean(KEY_VOICE_REMINDER);
    }

    public static void setVibrationReminder(boolean isChecked) {
        DataCacheManager.getInstance().putBoolean(KEY_VIBRATION_REMINDER, isChecked);
    }

    public static boolean getVibrationReminder() {
        return DataCacheManager.getInstance().getBoolean(KEY_VIBRATION_REMINDER);
    }

    public static void setAutoDownloadUnderWifi(boolean isChecked) {
        DataCacheManager.getInstance().putBoolean(KEY_AUTO_DOWNLOAD_UNDER_WIFI, isChecked);
    }

    public static boolean getAutoDownloadUnderWifi() {
        return DataCacheManager.getInstance().getBoolean(KEY_AUTO_DOWNLOAD_UNDER_WIFI);
    }

    public static void setWxUserInfo(WxLoginUserEntity wxLoginUserEntity) {
        mWxLoginUserEntity = wxLoginUserEntity;
    }

    public static void saveChatTranslateEnable(String id, boolean chatTransEnable) {
        DataCacheManager.getInstance().putBoolean(id, chatTransEnable);
    }

    public static boolean getChatTranslateEnable(String id, boolean chatTransEnable) {
        return DataCacheManager.getInstance().getBoolean(id, chatTransEnable);
    }

    private static String mLatitude = "0.0";
    private static String mLongitude = "0.0";
    private static String mradius = "0.0";
    private static String mdirection = "0.0";
    private static String mClientid;

    public static String getClientid() {
        return mClientid;
    }

    public static String getRadius() {
        return mradius;
    }

    public static String getDirection() {
        return mdirection;
    }

    public static String getLatitude() {
        return mLatitude;
    }

    public static void setLatitude(String mLatitude) {
        LoginHelper.mLatitude = mLatitude;
    }

    public static void setLongitude(String mLongitude) {
        LoginHelper.mLongitude = mLongitude;
    }

    public static String getLongitude() {
        return mLongitude;
    }

    public static void saveLocation(String latitude, String longitude, String radius, String direction) {
        mLatitude = latitude;
        mLongitude = longitude;
        mradius = radius;
        mdirection = direction;
    }

    public static void setClientid(String clientid) {
        mClientid = clientid;
    }

    public static void saveBoolean(String key, boolean value) {
        DataCacheManager.getInstance().putBoolean(key, value);
    }
    public static boolean getBoolean(String key) {
       return DataCacheManager.getInstance().getBoolean(key);
    }

    private static String mCity;
    private static String mAddrStr;

    public static void setAddrStr(String addrStr) {
       mAddrStr = addrStr;
    }

    public static String getAddrStr() {
        return mAddrStr;
    }

    public static String getCity() {
        return mCity;
    }

    public static void saveCity(String city) {
        mCity= city;
    }
    public static boolean isMySelf(String userId) {
        return getLoginInfo().getUserInfoEntity().getUser_id().equals(userId);
    }
}