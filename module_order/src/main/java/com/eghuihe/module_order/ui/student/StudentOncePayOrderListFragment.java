package com.eghuihe.module_order.ui.student;

import com.eghuihe.module_order.ui.mvp.MechanismOrderListContract;
import com.eghuihe.module_order.ui.mvp.MechanismOrderListPresenter;
import com.eghuihe.module_order.ui.mechanism.BaseMechanismOrderListFragment;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LongOrderPayInfoEntity;
import com.huihe.base_lib.model.MechanismOrderEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @desc 全额
 */
public class StudentOncePayOrderListFragment extends BaseStudentOrderListFragment<MechanismOrderListPresenter>
        implements MechanismOrderListContract.View {
    @Override
    protected boolean isIntent() {
        return false;
    }

    @Override
    protected void loadData() {

        getPresenter().queryMechanismOrderList(
                null,
                null,
                null,
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                true,
                true,
                getPageSize(),
                getCurrentPage(),
                "mechanism_offline",
                "1",
                null
        );
    }

    @Override
    protected MechanismOrderListPresenter createPresenter() {
        return new MechanismOrderListPresenter();
    }

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
    public void onGetAliOrder(String orderInfo) {

    }

    @Override
    public void onGetWxOrder(WxPayModel.WxPayEntity wxPayEntity) {

    }

    @Override
    public void onGetLongOrderInfoDetail(LongOrderPayInfoEntity longOrderPayInfoEntity) {

    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.PAY_SUCCESS.equals(event.getAction())) {
            triggerAutoRefresh();
        }
    }
}
