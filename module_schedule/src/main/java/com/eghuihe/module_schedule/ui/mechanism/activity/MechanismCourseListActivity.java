package com.eghuihe.module_schedule.ui.mechanism.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ARouterUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class MechanismCourseListActivity extends BaseTitleActivity {
    public static final String KEY_COURSE_ENTITY = "course_entity";

    @BindView(R2.id.activity_fragmentlayout_container)
    FrameLayout flContainer;

    private BaseFragment teachPayMechanismCourseFragment = ARouterUtils.getFragment(ARouterConfig.ME_TEACHPAY_MECHANISM_COURSE);
    private String appointment_type;

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_framelayout;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("机构课程");
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent!=null){
            appointment_type = intent.getStringExtra(ArgumentsConfig.KEY_APPOINTMENT_TYPE);
        }
    }

    @Override
    protected void initData() {
        Bundle arg = new Bundle();
        arg.putBoolean(ArgumentsConfig.KEY_IS_SELECT, true);
        arg.putString(ArgumentsConfig.KEY_STATUS, "2");
        arg.putString(ArgumentsConfig.KEY_MECHANISM_ID, LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id());
        arg.putString(ArgumentsConfig.KEY_APPOINTMENT_TYPE, appointment_type);
        teachPayMechanismCourseFragment.setArguments(arg);
        getSupportFragmentManager().beginTransaction().replace(flContainer.getId(), teachPayMechanismCourseFragment).commitNowAllowingStateLoss();
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.MECHANISM_COURSE_SELECT.equals(event.getAction())) {
            MasterSetPriceEntity masterSetPriceEntity = (MasterSetPriceEntity) event.getData();
            // 携带数据
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            intent.putExtra(KEY_COURSE_ENTITY, JsonUtil.toJson(masterSetPriceEntity));
            AppManager.getInstance().finishActivity(MechanismCourseListActivity.class);
        }
    }
}
