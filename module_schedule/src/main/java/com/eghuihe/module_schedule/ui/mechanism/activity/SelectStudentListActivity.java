package com.eghuihe.module_schedule.ui.mechanism.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.mechanism.adapter.StudentRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.mvp.SelectStudentListContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.SelectStudentListPresenter;
import com.huihe.base_lib.model.StudentBean;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.ui.widget.title.OnCommonTitleListener;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

public class SelectStudentListActivity extends BaseMvpRvRefreshTitleActivity<StudentRvAdapter, SelectStudentListPresenter>
        implements SelectStudentListContract.View {

    public static final String KEY_GOOD_ID = "studycard_id";
    public static final String KEY_STUDENT_LIST = "student_list";
    private String studycard_id;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("选择学生");
        commonTitle.setRightTitleText("确定");
        commonTitle.setOnCommonTitleListener(new OnCommonTitleListener() {
            @Override
            public void onLeftTitleClicked(View view) {
                finish();
            }

            @Override
            public void onRightTitleClicked(View view) {
                if (adapter != null) {
                    List<StudentBean> checkedStudentList = adapter.getCheckedStudentList();
                    if (checkedStudentList == null || checkedStudentList.size() == 0) {
                        ToastUtils.showShortToast(SelectStudentListActivity.this, "请选择学生");
                        return;
                    }
                    Intent intent = new Intent();
                    setResult(Activity.RESULT_OK, intent);
                    intent.putExtra(KEY_STUDENT_LIST, JsonUtil.toJson(checkedStudentList));
                    AppManager.getInstance().finishActivity(SelectStudentListActivity.class);
                }
            }
        });
    }

    @Override
    protected SelectStudentListPresenter createPresenter() {
        return new SelectStudentListPresenter();
    }

    @Override
    protected void doRefresh() {

        getPresenter().queryStudentList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "2",
                studycard_id,
                "mechanism_offline",
                getPageSize(),
                getCurrentPage()

        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryStudentList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "2",
                studycard_id,
                "mechanism_offline",
                getPageSize(),
                getCurrentPage()

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
    protected StudentRvAdapter getAdapter() {
        return new StudentRvAdapter(R.layout.item_student, this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            studycard_id = intent.getStringExtra(KEY_GOOD_ID);
        }
        triggerRefreshData();
    }

    @Override
    public void onStudentInfo(List<StudentBean> studentBeans) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(studentBeans);

            }
        } else {
            if (adapter != null) {
                adapter.addData(studentBeans);
            }
        }
        if (studentBeans == null || studentBeans.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }
}
