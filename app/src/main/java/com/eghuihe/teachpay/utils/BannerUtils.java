package com.eghuihe.teachpay.utils;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.utils.TextUtils;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.activity.H5TitleActivity;

public class BannerUtils {
    public static void doItemEvent(Context context, BannerModel.BannerEntity bannerEntity) {
        if (bannerEntity == null) {
            return;
        }
        String type = bannerEntity.getSource_type();
        String type_id = bannerEntity.getType_id();
        String source_url = bannerEntity.getSource_url();
        if ("web".equals(type)) {
            // h5页面
            if (TextUtils.isEmpty(source_url)){
                return;
            }
            Intent intent3 = new Intent(((BaseActivity) context), H5TitleActivity.class);
            intent3.putExtra(H5TitleActivity.KET_URL,
                    source_url);
            ((BaseActivity) context).startActivity(intent3);
        }
//        if ("note".equals(type)) {
//            // 动态详情
//            DynamicDetailActivity.setPosition(-1);
//            ((BaseActivity) context).startActivity(DynamicDetailActivity.class,
//                    new ExtraEntity(DynamicDetailActivity.KEY_NOTEUSER_INFO, type_id));
//        } else if ("news".equals(type)) {
//            // 好奇
//
//        } else if ("User details".equals(type)) {
//            // 个人详情
//            FriendInformationActivity.enterFriendInfoUI(context, String.valueOf(type_id));
//        } else if ("master".equals(type)) {
//            // 外教详情
//            UserAssistantTeacherDetailActivity.enterUserTutorDetail((BaseActivity) context,type_id);
//        } else if ("learningcard".equals(type)) {
//            // 我的学习卡
//            ((BaseActivity) context).startActivity(MineStudyCardActivity.class);
//        } else if ("member".equals(type)) {
//            // 购买会员
//            ((BaseActivity) context).startActivity(VipMemberPayActivity.class);
//        } else if ("recharge".equals(type)) {
//            // 我的猫币
//            ((BaseActivity) context).startActivity(MineCatPayActivity.class);
//        } else if ("web".equals(type)) {
//            // h5页面
//            Intent intent3 = new Intent(((BaseActivity) context), H5TitleActivity.class);
//            intent3.putExtra(H5TitleActivity.KET_URL,
//                    source_url);
//            ((BaseActivity) context).startActivity(intent3);
//        } else if ("mechanism".equals(type)) {
//            // 进入机构详情
//            ((BaseActivity) context).startActivity(MechanismMasterDetailActivity.class, MechanismMasterDetailActivity.KEY_MECHANISM_ID, String.valueOf(type_id));
//        } else if ("experience".equals(type)) {
//            // 从首页活动弹窗点击进入活动专场
//            ((BaseActivity) context).startActivity(SpecialActivitiesActivity.class);
//        }
    }
}
