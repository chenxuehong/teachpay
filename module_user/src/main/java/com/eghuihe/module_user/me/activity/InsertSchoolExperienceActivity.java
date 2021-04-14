package com.eghuihe.module_user.me.activity;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.event.EditUserInfoEvent;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.EventBus;

public class InsertSchoolExperienceActivity extends SchoolExperienceActivity {

    private LoginResultEntity loginResultEntity;

    @Override
    protected void initData() {

        loginResultEntity = LoginHelper.getLoginInfo();
    }

    @Override
    protected void save(String start_time, String end_time, String school_name, boolean is_visible) {

        UserServiceImpl.userSchoolInsert(loginResultEntity.getUserInfoEntity().getUser_id(),loginResultEntity.getUserToken(),
                start_time,end_time,school_name,is_visible,new NetworkSubscriber<InsertInfoResultModel>(InsertSchoolExperienceActivity.this){
                    @Override
                    protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                        ToastUtils.showShortToast(InsertSchoolExperienceActivity.this,"Save Success");
                        EventBus.getDefault().post(new EditUserInfoEvent());
                    }
                });
    }
}
