package com.huihe.base_lib.constants;

/**
 * Created by bruce on 2017/2/10.
 */
public interface AppConfigs {
    //设备唯一标识号
    String SP_KEY_UUID = "uuid";


    String DEBUG_TAG = "https";

    // 外教超市客服IM聊天用户id
    String kefuGroupId = "4000";

    // 客服电话
    String SERVICE_CALL = "0571-81869713";


    String LICENCE_URL = "http://license.vod2.myqcloud.com/license/v1/99ad227d42d4518a68be0ce29901d752/TXLiveSDK.licence";
    String LICENCE_KEY = "c15c9097991cf4aca30f65f65a173a3b";

    String project_name = "teachpay";

    // 测试版可以免费的开关
    boolean isFree = false;

    interface Bugly {
        String APP_ID = "bdd8c91033";
    }

    interface Platform {
        String PLATFORM_ANDROID = "teach_paypal_android";
    }

    interface IM {
        // 腾讯云IM
//        int sdkAppID = 1400472393;
//        int sdkAppID = 1400491168;
        int sdkAppID = 1400491548;
    }

    interface XiaoMi {
        // 小米
        // 使用您在即时通信 IM 控制台上小米推送证书信息里的证书 ID
        long XM_PUSH_BUZID = 15318;
    }

    interface HuaWei {
        long HW_PUSH_BUZID = 15319;
    }

    interface Vivo {
        long VIVO_PUSH_BUZID = 15320;
    }

    interface Oppo {
        long OPPO_PUSH_BUZID = 15321;
    }

    interface BaiduFanYi {
        String APP_ID = "20200901000556753";
        String SECURITY_KEY = "Mq5CvgeXVAZGKaz063OF";
    }

    // 七牛云上传图片 AK SK
    interface QiNiu {
        String QINIU_AK = "q5A1eTnHpRg9rjQNAtj4XAUgWxNQm22twApfy-3l";
        String QINIU_SK = "Aiewnl7xpbb3X-XzskU1rxoP7ak0sxK9YSUwy9Gd";
    }

    interface WeiXinPay {
        String APPID = "wx3ce48e914898f571";
        String AppSecret = "426e24074d22e78e489c32b251e23962";
    }

    interface Cooperation {

        interface ZH {
            String PING_TUAN = "http://joingroup.huihejituan.com/#/";
            String PRIVACY = "http://teachpayuserprivate.huihejituan.com";
            String USER_AGREEMENT = "http://teachpayuser.huihejituan.com";
            String CHINESE_GUIDE = "http://chineseguide.wjcs.site/"; // 中文机构引导
            String MECHANISM_SETTLE_AGREEMENT = "http://teachpaymechisajoin.huihejituan.com"; // 机构入驻协议
            String TEACHPAY_ACTIVE = "http://teachpayactive.huihejituan.com/#/"; // 领取优惠券网址
            String WX_PATH = "/pages/Friend/creatGroup/creatGroup?num="; //拼团分享
            String COURSE_DETAIL_WX_PATH = "/pages/World/classChannel/classChannel?curriculumId="; // 课程详情分享
            String MECHANISM_DETAIL_WX_PATH = "/pages/Meet/jigouxiangqing/jigouxiangqing?id="; // 机构详情分享
            String BAOBAOXIU_WX_PATH = "/pages/My/appointmentClass/appointmentClass?dataBox="; // 宝宝秀分享
        }

        interface EN {
            String PRIVACY = "http://teachpayuserprivate.huihejituan.com";
            String USER_AGREEMENT = "http://teachpayuser.huihejituan.com";
            String ENGLISH_INSTRUCTION = "http://englishinstruction.wjcs.site/";  //英文机构引导
            String MECHANISM_SETTLE_AGREEMENT = "http://teachpaymechisajoin.huihejituan.com"; // 机构入驻协议
        }
    }

}