package com.eghuihe.module_schedule.ui.mechanism.activity;

import android.content.Intent;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.mechanism.adapter.StudentListRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.mvp.StudentListContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.StudentListPresenter;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.ClassStudentBean;
import com.huihe.base_lib.model.CourseMessageBean;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.SchedulingMessageBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.qcloud.tim.uikit.TUIKit;

import java.util.List;

public class StudentListActivity extends BaseMvpRvRefreshTitleActivity<StudentListRvAdapter, StudentListPresenter>
        implements StudentListContract.View, StudentListRvAdapter.OnListener {

    private MasterSetPriceEntity masterSetPriceEntity;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("班级学员");
    }

    @Override
    protected void doRefresh() {
        getPresenter().queryStudentInfoList(
                "mechanism_offline",
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                masterSetPriceEntity.getId(),
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryStudentInfoList(
                "mechanism_offline",
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                masterSetPriceEntity.getId(),
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(this, 15);
    }

    @Override
    protected StudentListRvAdapter getAdapter() {
        return new StudentListRvAdapter(R.layout.item_class_student, this, this);
    }

    @Override
    protected StudentListPresenter createPresenter() {
        return new StudentListPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(ArgumentsConfig.KEY_MASTERSETPRICEENTITY);
            masterSetPriceEntity = JsonUtil.fromJson(json, MasterSetPriceEntity.class);
        }
    }

    @Override
    protected void initData() {
        triggerRefreshData();
    }

    @Override
    public void onChat(ClassStudentBean classStudentBean) {
        ClassStudentBean.Map map = classStudentBean.getMap();
        String studycard_id = classStudentBean.getId();
        if (map != null) {
            UserInfoEntity userinfo = map.getUserinfo();
            if (userinfo != null) {
                String user_id = userinfo.getUser_id();
                String nick_name = userinfo.getNick_name();
                TUIKit.startSchedulingChat(
                        this,
                        user_id,
                        nick_name,
                        studycard_id,
                        new SchedulingMessageBean(
                                masterSetPriceEntity.getId(),
                                "",
                                "",
                                "",
                                "",
                                "classMessage"
                        )
                );
            }
        }
    }

    @Override
    public void onStudentInfoList(List<ClassStudentBean> classStudentBeans) {
        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(classStudentBeans);
            }
        } else {
            if (adapter != null) {
                adapter.addData(classStudentBeans);
            }
        }

        if (classStudentBeans == null || classStudentBeans.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }
}
