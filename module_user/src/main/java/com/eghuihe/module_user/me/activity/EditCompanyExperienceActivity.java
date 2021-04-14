package com.eghuihe.module_user.me.activity;

import android.view.View;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.event.EditUserInfoEvent;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UserCompanyModel;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.EventBus;

public class EditCompanyExperienceActivity extends CompanyExperienceActivity {

    public static final String KEY_USERCOMPANYENTITY = "userCompanyEntity";
    private LoginResultEntity loginResultEntity;
    private UserCompanyModel.UserCompanyEntity userCompanyEntity;

    @Override
    protected void initData() {

        loginResultEntity = LoginHelper.getLoginInfo();
        userCompanyEntity = getIntentData(KEY_USERCOMPANYENTITY, UserCompanyModel.UserCompanyEntity.class);
        etCompanyName.setText(userCompanyEntity.getCompany_name());
        etLineOfBusiness.setText(userCompanyEntity.getCompany_industry());
        etDuty.setText(userCompanyEntity.getDuty());
        tvStartTime.setText(userCompanyEntity.getStart_time());
        tvEndTime.setText(userCompanyEntity.getEnd_time());
        switchPublic.setChecked(userCompanyEntity.isIs_visible());
    }

    @Override
    protected void initRightTitle(CustomerTitle customerTitle) {

        customerTitle.setRightText(getResources().getString(R.string.im_delete));
        customerTitle.setRightTextListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                // 删除
                DialogUtils.showTipDialog(EditCompanyExperienceActivity.this,"", getResources().getString(R.string.Confirm_Delete), getResources().getString(R.string.cancel), getResources().getString(R.string.sure), new DialogUtils.OnListener() {
                    @Override
                    public void okClicked() {
                        UserServiceImpl.userCompanyDelete(loginResultEntity.getUserToken(), userCompanyEntity.getId(), new NetworkSubscriber<InsertInfoResultModel>(EditCompanyExperienceActivity.this) {
                            @Override
                            protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                ToastUtils.showShortToast(EditCompanyExperienceActivity.this, "delete Success");
                                EventBus.getDefault().post(new EditUserInfoEvent());
                                finish();
                            }
                        });
                    }

                    @Override
                    public void cancelClicked() {

                    }
                });

            }
        });
    }

    @Override
    protected void save(String company_name, String company_industry, String duty, String start_time, String end_time, boolean is_visible) {

        UserServiceImpl.userCompanyUpdate(loginResultEntity.getUserInfoEntity().getUser_id(), loginResultEntity.getUserToken(),
                userCompanyEntity.getId(),
                company_name, company_industry, duty, start_time, end_time, is_visible, new NetworkSubscriber<InsertInfoResultModel>(this) {
                    @Override
                    protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                        ToastUtils.showShortToast(EditCompanyExperienceActivity.this, "update Success");
                        EventBus.getDefault().post(new EditUserInfoEvent());
                    }
                });
    }


}
