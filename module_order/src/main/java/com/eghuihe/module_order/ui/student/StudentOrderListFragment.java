package com.eghuihe.module_order.ui.student;

import com.eghuihe.module_order.ui.mvp.StudentOrderListContract;
import com.eghuihe.module_order.ui.mvp.StudentOrderListPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.MechanismOrderEntity;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentOrderListFragment extends BaseStudentOrderListFragment2<StudentOrderListPresenter>
        implements StudentOrderListContract.View {

    @Override
    public void MechanismOrderList(List<MechanismOrderEntity> mechanismOrderEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(mechanismOrderEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(mechanismOrderEntities);
            }
        }
        if (mechanismOrderEntities == null || mechanismOrderEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    protected void loadData() {
        getPresenter().queryStudentOrderList(
                "mechanism_offline",
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                getPageSize(),
                getCurrentPage(),
                getStatus()

        );
    }

    @Override
    protected StudentOrderListPresenter createPresenter() {
        return new StudentOrderListPresenter();
    }

    @Override
    public void onPayClicked(ViewHolder viewHolder, MechanismOrderEntity mechanismOrderEntity) {
        Map<String, String> params = new HashMap<>();
        params.put(ArgumentsConfig.KEY_MECHANISMORDERENTITY, JsonUtil.toJson(mechanismOrderEntity));
        ActivityToActivity.toActivity(
                ARouterConfig.SCHEDULE_STUDENTORDERPAYACTIVITY,
                params);
    }
}
