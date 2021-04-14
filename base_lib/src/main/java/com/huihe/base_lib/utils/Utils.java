package com.huihe.base_lib.utils;

import android.text.TextUtils;

import com.huihe.base_lib.utils.manager.DataCacheManager;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IntDef;

public class Utils {
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

    public static int getIdentityType() {
        String admin_id = "";
        String mechanism_id = "";
        if (LoginHelper.getLoginInfo() != null
            && LoginHelper.getLoginInfo().getUserInfoEntity()!=null ) {
            admin_id = LoginHelper.getLoginInfo().getUserInfoEntity().getAdmin_id();
            mechanism_id = LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id();
        }
        if (!TextUtils.isEmpty(admin_id) && !"0".equals(admin_id)) {
            // 身份属于机构老师
            return IdentityType.IS_MECHANISM_TEACHER;
        } else if (!TextUtils.isEmpty(mechanism_id) && !"0".equals(mechanism_id)) {
            //  身份属于机构
            return IdentityType.IS_MECHANISM;
        } else {
            //身份属于学生
            return IdentityType.IS_STUDENT;
        }


    }

    public static void clearData() {

        DataCacheManager.getInstance().remove(switchIdentityType);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({IdentityType.IS_MECHANISM,IdentityType.IS_MECHANISM_TEACHER,IdentityType.IS_STUDENT, })
    public @interface IdentityType {
        int IS_MECHANISM = 1;
        int IS_MECHANISM_TEACHER = 2;
        int IS_STUDENT = 3;
    }
}
