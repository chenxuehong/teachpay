package com.eghuihe.module_user.activities.activity;

import android.content.Intent;
import android.view.View;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.activities.adapter.ActivitiesRvAdapter;
import com.eghuihe.module_user.activities.mvp.ActivitiesContract;
import com.eghuihe.module_user.activities.mvp.ActivitiesPresenter;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.ui.widget.title.OnCommonTitleListener;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;

import java.util.List;

/**
 * @desc 活动列表
 */
public class ActivitiesListActivity extends BaseMvpRvRefreshTitleActivity<ActivitiesRvAdapter, ActivitiesPresenter>
        implements ActivitiesContract.View, ActivitiesRvAdapter.OnListener {

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("活动列表");
        commonTitle.setOnCommonTitleListener(new OnCommonTitleListener() {
            @Override
            public void onLeftTitleClicked(View view) {
                finish();
            }
        });
    }

    @Override
    protected void doRefresh() {
        getPresenter().queryBusinessActivityTypeList(
                getCurrentPage(),
                getPageSize(),
                "2"
        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryBusinessActivityTypeList(
                getCurrentPage(),
                getPageSize(),
                "2"
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
    protected ActivitiesRvAdapter getAdapter() {
        return new ActivitiesRvAdapter(R.layout.item_activity, this, this);
    }

    @Override
    protected ActivitiesPresenter createPresenter() {
        return new ActivitiesPresenter();
    }

    @Override
    protected void initData() {

        triggerRefreshData();
    }

    @Override
    public void showBusinessActivityTypeList(List<BusinessActivityTypeEntity> activityTypeEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(activityTypeEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(activityTypeEntities);
            }
        }

        if (activityTypeEntities == null || activityTypeEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void onViewDetail(BusinessActivityTypeEntity businessActivityTypeEntity) {
        Intent intent = new Intent(this, ActivityDetailActivity.class);
        intent.putExtra(ArgumentsConfig.KEY_BUSINESS_ACTIVITY_TYPE,
                JsonUtil.toJson(businessActivityTypeEntity));
        startActivity(intent);
    }
}
