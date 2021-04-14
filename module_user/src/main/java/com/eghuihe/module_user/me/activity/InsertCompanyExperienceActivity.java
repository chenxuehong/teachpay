package com.eghuihe.module_user.me.activity;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.event.EditUserInfoEvent;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.EventBus;

public class InsertCompanyExperienceActivity extends CompanyExperienceActivity {

    private LoginResultEntity loginResultEntity;

    @Override
    protected void save(String company_name, String company_industry, String duty, String start_time, String end_time, boolean is_visible) {

        UserServiceImpl.insertUserCompany(loginResultEntity.getUserInfoEntity().getUser_id(), loginResultEntity.getUserToken(), company_name, company_industry, duty, start_time, end_time, is_visible,
                new NetworkSubscriber<InsertInfoResultModel>(InsertCompanyExperienceActivity.this) {
                    @Override
                    protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {

                        ToastUtils.showShortToast(InsertCompanyExperienceActivity.this,"Save Success");
                        EventBus.getDefault().post(new EditUserInfoEvent());
                    }
                });
    }

    @Override
    protected void initData() {
        loginResultEntity = LoginHelper.getLoginInfo();
    }
}
