package com.huihe.base_lib.constants;

/**
 * Describe：路由页面常量配置 注意：路径至少需要两级 {/xx/xx}
 * Created by 吴天强 on 2018/10/16.
 */

public interface ARouterConfig {

    //************************************Main模块*****************************************/
    /**
     * 主页
     */
    String MAIN_MAINACTIVITY = "/main/MainActivity";
    String MAIN_GUIDEACTIVITY = "/main/GuideActivity";


    //*************************************首页模块*****************************************/
    String HOME_FRAGMENT = "/home/TeachingPayHomeFragment";
    String HOME_NEWACTIVITYDETAILACTIVITY = "/home/NewActivityDetailActivity";
    String HOME_SKLBACTIVITYDETAILACTIVITY = "/home/SklbActivityDetailActivity";
    String HOME_TEACHINGPAYMECHANISMCOURSELISTACTIVITY = "/home/TeachingPayMechanismCourseListActivity";
    String HOME_TEACHPAYCOURSELISTACTIVITY = "/home/TeachPayCourseListActivity";

    //*************************************课程表模块*****************************************/
    String SCHEDULE_MECHANISMSCHEDULE_FRAGMENT = "/schedule/mechanismScheduleFragment";
    String SCHEDULE_TEACHERSCHEDULE_FRAGMENT = "/schedule/teacherScheduleFragment";
    String SCHEDULE_STUDENTSCHEDULE_FRAGMENT = "/schedule/StudentScheduleFragment";
    String SCHEDULE_MECHANIARRANGESMSCHEDULEFIX_FRAGMENT = "/schedule/mechanismArrangeScheduleFixFragment";
    String SCHEDULE_SUMMARYACTIVITY = "/schedule/SummaryActivity";
    String SCHEDULE_ARRANGESCHEDULINGMECHANISMCOURSEACTIVITY = "/schedule/ArrangeSchedulingMechanismCourseActivity";
    String SCHEDULE_TEACHPAYSTUDENTSCHEDULEPACKAGEFRAGMENT = "/schedule/TeachPayStudentSchedulePackageFragment";
    String SCHEDULE_SCHEDULECOMMENTACTIVITY = "/schedule/ScheduleCommentActivity";
    String SCHEDULE_COURSEPAYACTIVITY = "/schedule/CoursePayActivity";
    String SCHEDULE_STUDENTORDERPAYACTIVITY = "/schedule/StudentOrderPayActivity";
    String SCHEDULE_LIVECOURSEPAYACTIVITY = "/schedule/LiveCoursePayActivity";
    String SCHEDULE_COURSEAPPOINTMENTPAY = "/schedule/CourseAppointmentPay";
    String SCHEDULE_COURSEONTIMEPAYACTIVITY = "/schedule/CourseOnTimePayActivity";
    String SCHEDULE_ACTIVITIESPAYACTIVITY = "/schedule/ActivitiesPayActivity";
    String SCHEDULE_SUMMARYDETAILACTIVITY = "/schedule/SummaryDetailActivity";
    String SCHEDULE_CLASSMANAGERACTIVITY = "/schedule/ClassManagerActivity";
    String SCHEDULE_TEACHPAYSTUDENTSCHEDULEPACKAGEDETAILLISTACTIVITY = "/schedule/TeachPayStudentSchedulePackageDetailListActivity";

    //*************************************动态*****************************************/
    String DYNAMIC_DYNAMICLISTFRAGMENT = "/dynamic/DynamicListFragment";
    String DYNAMIC_DYNAMICFRAGMENT = "/dynamic/DynamicFragment";

    //*************************************我的模块*****************************************/
    String ME_MECHANISM_COURSE_DETAIL = "/me/MechanismCourseDetailActivity";
    String ME_MECHANISM_COURSE_DETAIL2 = "/me/MechanismCourseDetail2Activity";
    String ME_CLASSROOMMANAGERACTIVITY = "/me/ClassRoomManagerActivity";
    String ME_MEFRAGMENT = "/me/meFragment";
    String ME_SPLASHACTIVITY = "/me/SplashActivity";
    String ME_SETTLEMECHANISM = "/me/SettleMechanism";
    String ME_MECHANISM_DETAIL = "/me/MechanismDetailActivity";
    String ME_MECHANISM_INCOMECENTERFRAGMENT = "/me/IncomeCenterFragment";
    String ME_LOGINACTIVITY = "/me/loginActivity";
    String ME_TEACHPAY_MECHANISM_COURSE = "/me/teachpayMechanismCourse";
    String ME_TEACHPAY_MECHANISM_TEACHER_LIST_ACTIVITY = "/me/TeachPayMechanismTeacherListActivity";
    String ME_UPDATETEACHPAYMECHANISMCOURSEACTIVITY = "/me/UpdateTeachPayMechanismCourseActivity";
    String ME_MECHANISMCOURSECOMMENTLISTACTIVITY = "/me/MechanismCourseCommentListActivity";
    String ME_MOTHERTONGUESETTINGACTIVITY = "/me/MotherTongueSettingActivity";

    //*************************************订单*****************************************/
    String ORDER_TEACHINGPAYMECHANISMORDER = "/order/TeachingPayMechanismOrder";
    String ORDER_TEACHINGPAYSTUDENTORDER = "/order/TeachingPayStudentOrder";

    //*************************************im腾讯云*****************************************/

    interface SERVICE {
        String SELF_UPDATE_SERVICE = "/appstore/selfupdate/SelfUpdateService";
    }
}
