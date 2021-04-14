package com.eghuihe.module_dynamic.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.R2;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ARouterUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.AppManager;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class TeachPayStudentSelectSchedulePackageActivity extends BaseTitleActivity {


    @BindView(R2.id.activity_fragmentlayout_container)
    FrameLayout flContainer;

    private BaseFragment studentSchedulePackageFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_TEACHPAYSTUDENTSCHEDULEPACKAGEFRAGMENT);

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("选择课程");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_framelayout;
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle data = new Bundle();
        data.putBoolean(ArgumentsConfig.KEY_IS_SELECT,true);
        studentSchedulePackageFragment.setArguments(data);
        getSupportFragmentManager().beginTransaction().replace(
                flContainer.getId(),
                studentSchedulePackageFragment
                
        ).commitNowAllowingStateLoss();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event){
        if (EventAction.SEND_DATA_STUDENTCOURSEPACKAGEENTITY.equals(event.getAction())){
            Object data = event.getData();
            if (data instanceof String){
                String json = (String) data;
                StudentCoursePackageEntity studentCoursePackageEntity = JsonUtil.fromJson(json, StudentCoursePackageEntity.class);
                // 携带数据
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                intent.putExtra(ArgumentsConfig.KEY_STUDENTCOURSEPACKAGEENTITY, JsonUtil.toJson(studentCoursePackageEntity));
                AppManager.getInstance().finishActivity(TeachPayStudentSelectSchedulePackageActivity.class);
            }
        }
    }
}
