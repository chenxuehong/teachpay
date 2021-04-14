package com.eghuihe.module_home.home.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_home.R;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.adapter.TeachPayCourseListRvAdapter;
import com.eghuihe.module_home.home.mvp.TeachPayCourseListContract;
import com.eghuihe.module_home.home.mvp.TeachPayCourseListPresenter;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;
@Route(path = ARouterConfig.HOME_TEACHPAYCOURSELISTACTIVITY)
public class TeachPayCourseListActivity extends BaseMvpRvRefreshTitleActivity<TeachPayCourseListRvAdapter, TeachPayCourseListPresenter>
        implements TeachPayCourseListContract.View {

    private int mSpanCount = 1;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("机构热门课程");
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
    protected TeachPayCourseListPresenter createPresenter() {
        return new TeachPayCourseListPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        getSmartRefreshLayout().setBackgroundColor(getResources().getColor(R.color.color_88e6e6e6));
    }

    @Override
    protected void initData() {

        triggerRefreshData();
    }

    @Override
    protected void doRefresh() {

        getPresenter().queryNearByCourse(
                2,
                getCurrentPage(),
                getPageSize(),
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude(),
                "mechanism_offline "
        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryNearByCourse(
                2,
                getCurrentPage(),
                getPageSize(),
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude(),
                "mechanism_offline "
        );
    }

    @Override
    public void showHotMechanismCourseList(List<MasterSetPriceEntity> masterSetPriceEntities) {

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
