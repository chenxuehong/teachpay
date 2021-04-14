package com.eghuihe.module_user.activities.activity;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.activities.adapter.UseCouponCourseListRvAdapter;
import com.eghuihe.module_user.activities.mvp.UseCouponActivitiesListContract;
import com.eghuihe.module_user.activities.mvp.UseCouponActivitiesListPresenter;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.UseCouponParam;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;
public class UseCouponActivitiesListActivity extends BaseMvpRvRefreshTitleActivity<EmptyRVAdapter, UseCouponActivitiesListPresenter>
        implements UseCouponActivitiesListContract.View {

    private static final int REQUEST_CODE_USECOUPON = 100;
    private int mSpanCount = 1;
    private String id;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("活动课程");
    }

    @Override
    protected void doRefresh() {

        getPresenter().queryActivityListPageByType(
                getCurrentPage(),
                getPageSize(),
                "2",
                "experience_specials",
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude()
        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryActivityListPageByType(
                getCurrentPage(),
                getPageSize(),
                "2",
                "experience_specials",
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude()
        );
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
    public void onCoureList(List<MasterSetPriceEntity> masterSetPriceEntities) {
        mSpanCount = 1;
        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(masterSetPriceEntities);
                adapter.setOnClickListener(new EmptyRVAdapter.OnItemClickListener<MasterSetPriceEntity>() {

                    @Override
                    public void onItemClicked(View v, MasterSetPriceEntity masterSetPriceEntity, int position) {

                        UseCouponParam useCouponParam = new UseCouponParam(id, masterSetPriceEntity.getId(), LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id());
                        Intent intent = new Intent(getContext(),UseCouponMechanismCourseDetailActivity.class);
                        intent.putExtra(ArgumentsConfig.KEY_USECOUPONPARAM, JsonUtil.toJson(useCouponParam));
                        intent.putExtra(ArgumentsConfig.MECHANISM_COURSE_DETAIL, JsonUtil.toJson(masterSetPriceEntity));
                        startActivityForResult(intent,REQUEST_CODE_USECOUPON);
                    }

                    @Override
                    public void onItemLongClicked(View v, MasterSetPriceEntity masterSetPriceEntity, int position) {

                    }
                });
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

    @Override
    protected UseCouponCourseListRvAdapter getAdapter() {
        return new UseCouponCourseListRvAdapter(R.layout.item_query_mechanism_course, this);
    }

    @Override
    protected UseCouponActivitiesListPresenter createPresenter() {
        return new UseCouponActivitiesListPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        getSmartRefreshLayout().setBackgroundColor(getResources().getColor(R.color.color_88e6e6e6));
        Intent intent = getIntent();
        if (intent!=null){
            id = intent.getStringExtra(ArgumentsConfig.KEY_ID);
        }
    }

    @Override
    protected void initData() {
        triggerRefreshData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_USECOUPON){
            // 兑换课程后
            finish();
        }
    }
}
