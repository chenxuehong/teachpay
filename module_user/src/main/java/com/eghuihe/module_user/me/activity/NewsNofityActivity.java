package com.eghuihe.module_user.me.activity;

import android.widget.CompoundButton;
import android.widget.Switch;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.manager.LoginHelper;

import butterknife.BindView;


public class NewsNofityActivity extends BaseTitleActivity {


    @BindView(R2.id.newsNofity_switch_New_message)
    Switch mASwitchNewsMessage;
    @BindView(R2.id.newsNofity_switch_message_details)
    Switch mASwitchMessageDetails;
    @BindView(R2.id.newsNofity_switch_Voice_Reminder)
    Switch mASwitchVoiceReminder;
    @BindView(R2.id.newsNofity_switch_Vibration_reminder)
    Switch mASwitchVibrationReminder;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.New_message_notification));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_news_notify;
    }

    @Override
    protected void initView() {
        super.initView();
        mASwitchNewsMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                LoginHelper.setNewMessageAlertNotify(isChecked);
            }
        });
        mASwitchMessageDetails.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LoginHelper.setShowMessageDetailsForNotify(isChecked);
            }
        });
        mASwitchVoiceReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LoginHelper.setVoiceReminder(isChecked);
            }
        });
        mASwitchVibrationReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LoginHelper.setVibrationReminder(isChecked);
            }
        });
    }

    @Override
    protected void initData() {
        mASwitchNewsMessage.setChecked(LoginHelper.getNewMessageAlertNotify());
        mASwitchMessageDetails.setChecked(LoginHelper.getShowMessageDetailsForNotify());
        mASwitchVoiceReminder.setChecked(LoginHelper.getVoiceReminder());
        mASwitchVibrationReminder.setChecked(LoginHelper.getVibrationReminder());
    }
}
