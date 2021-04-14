package com.eghuihe.module_user.me.fragment;

import android.content.Intent;
import android.os.Bundle;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.activities.activity.UseCouponActivitiesListActivity;
import com.eghuihe.module_user.me.adapter.MyCardRvAdapter;
import com.eghuihe.module_user.me.mvp.MyCardContract;
import com.eghuihe.module_user.me.mvp.MyCardPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.UserCouponEntity;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

public class MyCardFragment extends BaseMvpRVRefreshFragment<MyCardRvAdapter, MyCardPresenter>
        implements MyCardContract.View, MyCardRvAdapter.Onlistener {

    private static final int REQUEST_CODE_USECOUPON = 100;
    private String status;

    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(getContext(), 13);
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected MyCardRvAdapter createAdapter() {
        return new MyCardRvAdapter(R.layout.item_my_card, getContext(), this);
    }

    @Override
    protected MyCardPresenter createPresenter() {
        return new MyCardPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            status = arguments.getString(ArgumentsConfig.KEY_STATUS);
        }
        triggerAutoRefresh();
    }

    @Override
    public void doRefresh() {
        super.doRefresh();
        getPresenter().queryUserCouponList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                status,
                getPageSize(),
                getCurrentPage()

        );
    }

    @Override
    public void doLoadMore() {
        super.doLoadMore();
        getPresenter().queryUserCouponList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                status,
                getPageSize(),
                getCurrentPage()

        );
    }

    @Override
    public void onUserCouponList(List<UserCouponEntity> userCouponEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(userCouponEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(userCouponEntities);
            }
        }

        if (userCouponEntities == null || userCouponEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void onDestroy() {
        if (adapter != null) {
            adapter.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void useCoupon(ViewHolder viewHolder, UserCouponEntity userCouponEntity) {
        String type = userCouponEntity.getType();
        if ("voucher_redemption".equals(type)) {
            // 兑换6节课
            Intent intent = new Intent(getContext(), UseCouponActivitiesListActivity.class);
            intent.putExtra(ArgumentsConfig.KEY_ID, userCouponEntity.getId());
            startActivityForResult(intent, REQUEST_CODE_USECOUPON);
        } else {
            // 限时抵扣
            ActivityToActivity.toActivity(ARouterConfig.HOME_TEACHPAYCOURSELISTACTIVITY);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_USECOUPON) {
            doRefresh();
        }
    }

}
