package com.huihe.base_lib.utils;

import android.text.TextUtils;

import com.huihe.base_lib.utils.manager.DataCacheManager;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String TYPE_MECHANISM = "is_mechanism";
    public static final String TYPE_MECHANISM_TEACHER = "is_mechanism_teacher";
    public static final String TYPE_STUDENT = "is_student";

    public static final String SWITCH_TYPE_STUDENT = "switch_type_student";
    public static final String SWITCH_TYPE_MECHANISM = "switch_type_mechanism";
    private static String switchIdentityType = "switchIdentityType";

    public static void switchIdentity(boolean isStudent) {
        if (isStudent) {
            DataCacheManager.getInstance().putString(switchIdentityType, SWITCH_TYPE_STUDENT);
        } else {
            DataCacheManager.getInstance().putString(switchIdentityType, SWITCH_TYPE_MECHANISM);
        }
    }

    public static boolean isSwitchMechanismIdentity() {
        String mCurSwitchIdentity = DataCacheManager.getInstance().getString(switchIdentityType);
        return TextUtils.isEmpty(mCurSwitchIdentity) || mCurSwitchIdentity.equals(SWITCH_TYPE_MECHANISM);
    }

    public static String getIdentityType() {
        String admin_id = LoginHelper.getLoginInfo().getUserInfoEntity().getAdmin_id();
        String mechanism_id = LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id();
        if (!TextUtils.isEmpty(admin_id) && !"0".equals(admin_id)) {
            // 身份属于机构老师
            return TYPE_MECHANISM_TEACHER;
        } else if (!TextUtils.isEmpty(mechanism_id) && !"0".equals(mechanism_id)) {
            //  身份属于机构
            return TYPE_MECHANISM;
        } else {
            //身份属于学生
            return TYPE_STUDENT;
        }
    }

    public static void clearData() {

        DataCacheManager.getInstance().remove(switchIdentityType);
    }

}
