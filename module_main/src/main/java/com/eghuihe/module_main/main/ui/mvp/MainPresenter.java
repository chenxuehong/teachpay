package com.eghuihe.module_main.main.ui.mvp;

import android.content.Context;

import com.eghuihe.module_main.R;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.utils.ARouterUtils;
import com.huihe.base_lib.utils.DeviceUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.Utils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.entities_lib.transform.MainTabBean;
import com.tencent.qcloud.tim.uikit.component.fragment.ConversationFragment;
import com.tencent.qcloud.tim.uikit.component.fragment.StudentConversationFragment;
import com.tencent.qcloud.tim.uikit.utils.ThirdPushTokenMgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View>
        implements MainContract.Presenter {

    private HashMap<Integer, List<MainTabBean>> tabListByType = new HashMap<>();
    private BaseFragment mechanismScheduleFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_MECHANISMSCHEDULE_FRAGMENT);
    private BaseFragment mechanismArrangeScheduleFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_MECHANIARRANGESMSCHEDULEFIX_FRAGMENT);
    private BaseFragment studentScheduleFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_STUDENTSCHEDULE_FRAGMENT);
    private BaseFragment dynamicfragment = ARouterUtils.getFragment(ARouterConfig.DYNAMIC_DYNAMICFRAGMENT);
    private BaseFragment meFragment = ARouterUtils.getFragment(ARouterConfig.ME_MEFRAGMENT);
    private BaseFragment homeFragment = ARouterUtils.getFragment(ARouterConfig.HOME_FRAGMENT);
    private BaseFragment incomeCenterFragment = ARouterUtils.getFragment(ARouterConfig.ME_MECHANISM_INCOMECENTERFRAGMENT);
    private BaseFragment teacherScheduleFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_TEACHERSCHEDULE_FRAGMENT);

    @Override
    protected MainContract.Model createModule() {
        return new MainModel();
    }

    public List<MainTabBean> getTabList(int type) {
        List<MainTabBean> mainTabBeans = tabListByType.get(type);
        return mainTabBeans;
    }

    @Override
    public void start() {
        ThirdPushTokenMgr.getInstance().setPushTokenToTIM();
        pushClientId();
        initMainTabs();
    }

    private void initMainTabs() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        tabListByType.clear();
        List<MainTabBean> tabs = new ArrayList<>();
        switch (Utils.getIdentityType()) {
            case Utils.IdentityType.IS_MECHANISM:
                if (Utils.isSwitchMechanismIdentity()) {
                    // 课程表、排课表、收益、我的
                    putMechanismTabs(tabs);
                } else {
                    putStudentTabs(tabs);
                }
                break;
            case Utils.IdentityType.IS_MECHANISM_TEACHER:
                putTeacherTabs(tabs);
                break;
            case Utils.IdentityType.IS_STUDENT:
                putStudentTabs(tabs);
                break;
        }
        tabListByType.put(Utils.getIdentityType(),tabs);
    }

    private void putTeacherTabs(List<MainTabBean> tabs) {
        tabs.add(new MainTabBean(
                getContext().getResources().getString(R.string.Course_table),
                R.mipmap.teaching_pay_schedule_selected,
                R.mipmap.teaching_pay_schedule_unselected,
                teacherScheduleFragment.getClass()
        ));
        tabs.add(new MainTabBean(
                getContext().getResources().getString(R.string.tab_me),
                R.mipmap.teaching_pay_me_selected,
                R.mipmap.teaching_pay_me_unselected,
                meFragment.getClass()
        ));
    }

    private void putStudentTabs(List<MainTabBean> tabs) {
        tabs.add(new MainTabBean(
                getContext().getResources().getString(R.string.tab_home),
                R.mipmap.teaching_pay_home_selected,
                R.mipmap.teaching_pay_home_unselected,
                homeFragment.getClass()
        ));
        tabs.add(new MainTabBean(
                getContext().getResources().getString(R.string.Course_table),
                R.mipmap.teaching_pay_schedule_selected,
                R.mipmap.teaching_pay_schedule_unselected,
                studentScheduleFragment.getClass()
        ));
        tabs.add(new MainTabBean(
                "消息",
                R.mipmap.im_selected,
                R.mipmap.im_unselected,
                StudentConversationFragment.class
        ));
        tabs.add(new MainTabBean(
                "宝宝秀",
                R.mipmap.dynamic_selected,
                R.mipmap.dynamic_unselected,
                dynamicfragment.getClass()
        ));
        tabs.add(new MainTabBean(
                getContext().getResources().getString(R.string.tab_me),
                R.mipmap.teaching_pay_me_selected,
                R.mipmap.teaching_pay_me_unselected,
                meFragment.getClass()
        ));
    }

    private void putMechanismTabs(List<MainTabBean> tabs) {
        tabs.add(new MainTabBean(
                        getContext().getResources().getString(R.string.Course_table),
                        R.mipmap.teaching_pay_schedule_selected,
                        R.mipmap.teaching_pay_schedule_unselected,
                        mechanismScheduleFragment.getClass()
                ));
        tabs.add(new MainTabBean(
                        getContext().getResources().getString(R.string.Arranged_Schedule),
                        R.mipmap.teaching_pay_arrange_schedule_selected,
                        R.mipmap.teaching_pay_arrange_schedule_unselected,
                        mechanismArrangeScheduleFragment.getClass()
                ));
        tabs.add(new MainTabBean(
                        "消息",
                        R.mipmap.im_selected,
                        R.mipmap.im_unselected,
                        ConversationFragment.class
                ));
        tabs.add(new MainTabBean(getContext().getResources().getString(R.string.income),
                        R.mipmap.teaching_pay_income_selected,
                        R.mipmap.teaching_pay_income_unselected,
                        incomeCenterFragment.getClass()
                ));
        tabs.add( new MainTabBean(getContext().getResources().getString(R.string.tab_me),
                        R.mipmap.teaching_pay_me_selected,
                        R.mipmap.teaching_pay_me_unselected,
                        meFragment.getClass()
                ));
        tabListByType.put(Utils.IdentityType.IS_MECHANISM,tabs);
    }

    @Override
    public void queryUserInfo(String user_id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryUserInfo(
                            user_id,
                            new NetworkSubscriber<UserInfoModel>(null){
                                @Override
                                protected void onSuccess(UserInfoModel userInfoModel) {
                                    if (userInfoModel!=null){
                                        LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
                                        loginInfo.setUserInfoEntity(userInfoModel.getData());
                                        LoginHelper.saveUserData(loginInfo);
                                    }
                                }
                            }
                    )
            );
        }
    }

    private void pushClientId() {
        LoginResultEntity data = LoginHelper.getLoginInfo();
        String model = DeviceUtils.getModel();
        String uuid = DeviceUtils.getUUID();
        userDeviceInsert(
                model,
                LoginHelper.getClientid(),
                String.valueOf(data.getUserInfoEntity().getUser_id()),
                uuid,
                ThirdPushTokenMgr.getInstance().getThirdPushToken()
        );
    }

    @Override
    public void userDeviceInsert(
            String model,
            String clientid,
            String user_id,
            String unique_id,
            String teach_pay_token) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().userDeviceInsert(
                            model,
                            clientid,
                            user_id,
                            unique_id,
                            teach_pay_token,
                            new NetworkSubscriber<InsertInfoResultModel>(null){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {

                                    LogUtils.i("userDeviceInsert","success");
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void unDo() {
        super.unDo();
        tabListByType.clear();
    }
}
