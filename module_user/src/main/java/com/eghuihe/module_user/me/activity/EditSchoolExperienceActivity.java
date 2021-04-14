package com.eghuihe.module_user.me.activity;

import android.view.View;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.event.EditUserInfoEvent;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UserSchoolModel;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.EventBus;

public class EditSchoolExperienceActivity extends SchoolExperienceActivity {

    public static final String KEY_USERSCHOOLENTITY = "userSchoolEntity";
    private LoginResultEntity loginResultEntity;
    private UserSchoolModel.UserSchoolEntity userSchoolEntity;

    @Override
    protected void initRightTitle(CustomerTitle customerTitle) {
        customerTitle.setRightText(getResources().getString(R.string.im_delete));
        customerTitle.setRightTextListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                // 删除
                DialogUtils.showTipDialog(EditSchoolExperienceActivity.this,"",
                        getResources().getString(R.string.Confirm_Delete), getResources().getString(R.string.cancel), getResources().getString(R.string.sure),
                        new DialogUtils.OnListener() {
                    @Override
                    public void okClicked() {
                        UserServiceImpl.userSchoolDelete(userSchoolEntity.getId(),new NetworkSubscriber<InsertInfoResultModel>(EditSchoolExperienceActivity.this){
                            @Override
                            protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                ToastUtils.showShortToast(EditSchoolExperienceActivity.this, "delete Success");
                                finish();
                                EventBus.getDefault().post(new EditUserInfoEvent());
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
    protected void initData() {
        loginResultEntity = LoginHelper.getLoginInfo();
        userSchoolEntity = getIntentData(KEY_USERSCHOOLENTITY, UserSchoolModel.UserSchoolEntity.class);
        etSchoolName.setText(userSchoolEntity.getSchool_name());
        tvStartTime.setText(userSchoolEntity.getStart_time());
        tvEndTime.setText(userSchoolEntity.getEnd_time());
        mSwitch.setChecked(userSchoolEntity.isIs_visible());
    }

    @Override
    protected void save(String start_time, String end_time, String school_name, boolean is_visible) {
        // 修改
        UserServiceImpl.userSchoolUpdate(loginResultEntity.getUserInfoEntity().getUser_id(),loginResultEntity.getUserToken(),userSchoolEntity.getId(),
                start_time,end_time,school_name,is_visible,new NetworkSubscriber<InsertInfoResultModel>(EditSchoolExperienceActivity.this){
                    @Override
                    protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                        ToastUtils.showShortToast(EditSchoolExperienceActivity.this, "update Success");
                        EventBus.getDefault().post(new EditUserInfoEvent());
                    }
                });
    }
}
