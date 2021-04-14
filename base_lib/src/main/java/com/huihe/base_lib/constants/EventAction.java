package com.huihe.base_lib.constants;

/**
 * Describe：EventBus事件
 * Created by 吴天强 on 2018/10/19.
 */

public interface EventAction {
    // 广告
    String BANNER = "banner";
    // 欢迎页面mob授权
    String SUBMIT_POLICYGRANT = "submitPolicyGrant";

    // 登录
    String CODE_LOGIN = "teachPayCodeLogin"; // 验证码登录
    String PASSWORD_LOGIN = "teachPayPasswordLogin"; // 密码登录
    String LOGIN_SUCCESS = "teachPayLoginSuccess"; // 登录成功
    String LOGOUT_EVENT = "logout"; // 退出登录

    /*首页*/
    // 机构课程详情
    String MECHANISM_COURSE_DETAIL = "teachPayMechanismCourseDetail";
    // 机构直播课程详情
    String MECHANISM_LIVE_COURSE_DETAIL = "teachPayMechanismLiveCourseDetail";
    // 机构详情
    String MECHANISM_DETAIL = "teachPayMechanismDetail";
    String MECHANISM_COURSE_SELECT = "teachPayMechanismCourseSelect";

    // 课程表
    String ARRANGE_COURSE = "arrange_course";
    String MECHANISM_SUMMARY_SUCCESS = "mechanism_summary_success";
    String MECHANISM_SEND_SCHEDULING_MECHANISM_COURSE = "send_scheduling_mechanism_course";
    String STIDENT_COMMENT_SUCCESS = "student_comment_success";
    String PING_GROUP = "PingGroup";
    String INSERT_MECHANISMCLASS_SUCCESS = "InsertMechanismClassSuccess";

    // 三方
    String WX_LOGIN = "teachPaywxLogin"; // 微信登录
    String WX_PAY = "teachPaywxPay"; // 微信支付
    String WX_LOGIN_ACCESSTOKEN = "teachPayAccessToken"; // 微信登录返回code
    String PAY_SUCCESS = "pay_success";
    String PAY_FAIL = "pay_fail";
    String SETTING_MOTHER_TONGUE = "SettingMotherTongue";
    String SELECT_NUM = "select_num";

    // 我的
    String TEACH_PAY_SWITCH_IDENTITIES = "teachPaySwitch_identity";
    String TEACH_PAY_MECHANISM_COURSE = "teachPayMechanismCourse";
    String TEACH_PAY_UPDATE_USERINFO = "teach_pay_update_userinfo";// 更新用户信息
    String TEACH_PAY_UPDATE_MECHANISMINFO = "teach_pay_update_mechanisminfo";// 更新机构信息
    String KEY_TEACHER_ENTITY = "key_teacher_entity";
    String POINTS_LIST = "points_list"; // 积分明细
    String INSERT_MECHANISM_COURSE = "insert_mechanism_course"; // 添加机构课程成功
    String SELECT_MASTERSETPRICE = "selectMastersetPrice"; // 创建和预约直播时选择商品
    String SELECT_PRICEMAP = "selectPriceMap"; // 创建和预约直播时选择商品的直播价格
    String SELECT_SINGLEPRICEMAP = "selectSinglePriceMap"; // 创建和预约直播时选择商品的直播单价
    String CREATE_LIVE = "createLive"; // 创建和预约直播

    // 重置用户信息
    String RESET_USERINFO = "reset_userInfo";

    // 宝宝秀
    String DYNAMIC_LIST_FRESH = "dynamic_list_fresh";
    String DYNAMIC_SHARE_SUCCESS = "dynamicShareSuccess";
    String DYNAMICDETAILCOMMENTLIST_REFRESH = "DynamicDetailCommentListFragment";
    String REFRESH_DYNAMIC_DETAIL_SHARELIST_EVENT = "RefreshDynamicDetailShareList";
    String REFRESH_DYNAMIC_DETAIL_LIKELIST_EVENT = "RefreshDynamicDetailLikeListEvent";
    String SEND_DATA_STUDENTCOURSEPACKAGEENTITY = "send_data_StudentCoursePackageEntity";

    // im腾讯云
    String IM_UNREAD_COUNT = "im_unread_count";
    String IM_MECHANISM_COURSE_ENTER = "IM_mechanism_course_enter";
    String CALENDAR_DAY_LIST = "calendar_daylist";
    String CALENDAR_DATE_LIST = "calendar_datelist";

    String CLOSE_LIVE_SUCCESS = "closeLiveSuccess";
    String COURSE_DETAIL_SHARE = "courseDetailShare";
    String MECHANISM_DETAIL_SHARE = "mechanismDetailShare";
    String BAOBAOXIU_SHARE = "baobaoXiuShare";

}
