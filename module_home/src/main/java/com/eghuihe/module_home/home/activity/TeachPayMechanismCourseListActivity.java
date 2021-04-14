package com.eghuihe.module_home.home.activity;

import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_home.R;
import com.huihe.base_lib.ui.adapter.TeachPayCourseListRvAdapter;
import com.eghuihe.module_home.home.mvp.TeachPayMechanismCourseListContract;
import com.eghuihe.module_home.home.mvp.TeachPayMechanismCourseListPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.DensityUtils;

import java.util.List;

@Route(path = ARouterConfig.HOME_TEACHINGPAYMECHANISMCOURSELISTACTIVITY)
public class TeachPayMechanismCourseListActivity extends BaseMvpRvRefreshTitleActivity<TeachPayCourseListRvAdapter, TeachPayMechanismCourseListPresenter>
        implements TeachPayMechanismCourseListContract.View {

    private int mSpanCount = 1;
    private String mechanism_id;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("机构课程");
    }

    @Override
    protected int getSpanCount() {
        return mSpanCount;
    }

    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(this, 15);
    }

    @Override
    protected TeachPayCourseListRvAdapter getAdapter() {
        return new TeachPayCourseListRvAdapter(R.layout.item_query_mechanism_course, this);
    }

    @Override
    protected TeachPayMechanismCourseListPresenter createPresenter() {
        return new TeachPayMechanismCourseListPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        getSmartRefreshLayout().setBackgroundColor(getResources().getColor(R.color.color_88e6e6e6));
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            mechanism_id = intent.getStringExtra(ArgumentsConfig.KEY_MECHANISM_ID);
        }
        triggerRefreshData();
    }

    @Override
    protected void doRefresh() {
        getPresenter().queryMechanismCourseList(
                mechanism_id,
                "mechanism_offline",
                "2",
                null,
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryMechanismCourseList(
                mechanism_id,
                "mechanism_offline",
                "2",
                null,
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    public void showMechanismCourseList(List<MasterSetPriceEntity> masterSetPriceEntities) {

        mSpanCount = 1;
        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(masterSetPriceEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(masterSetPriceEntities);
            }
        }
        if (masterSetPriceEntities == null || masterSetPriceEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }
}
