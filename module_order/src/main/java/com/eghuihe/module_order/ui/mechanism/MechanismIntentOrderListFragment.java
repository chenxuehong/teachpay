package com.eghuihe.module_order.ui.mechanism;

import com.eghuihe.module_order.ui.adapter.MechanismOrderRvAdapter;
import com.eghuihe.module_order.ui.mvp.MechanismOrderListContract;
import com.eghuihe.module_order.ui.mvp.MechanismOrderListPresenter;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LongOrderPayInfoEntity;
import com.huihe.base_lib.model.MechanismOrderEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.pay.PayUtils;
import com.huihe.base_lib.utils.pay.alipay.AlipayUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @desc 意向订单
 */
public class MechanismIntentOrderListFragment extends BaseMechanismOrderListFragment<MechanismOrderListPresenter>
        implements MechanismOrderListContract.View {

    private CustomPopupWindow customPopupWindow;

    @Override
    protected boolean isIntent() {
        return true;
    }

    @Override
    protected MechanismOrderListPresenter createPresenter() {
        return new MechanismOrderListPresenter();
    }

    @Override
    protected void loadData() {

        getPresenter().queryMechanismOrderList(
                "study_card",
                null,
                null,
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                false,
                false,
                getPageSize(),
                getCurrentPage(),
                null,
                null,
                "1"
        );
    }

    private MechanismOrderEntity mMechanismOrderEntity;

    @Override
    public void MechanismOrderList(List<MechanismOrderEntity> mechanismOrderEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(mechanismOrderEntities);
                adapter.setOnChildListener(new MechanismOrderRvAdapter.OnChildListener() {
                    @Override
                    public void onOncePayClicked(ViewHolder viewHolder, MechanismOrderEntity mechanismOrderEntity) {
                        mMechanismOrderEntity = mechanismOrderEntity;
                        getPresenter().queryLongOrderPayInfo(
                                mechanismOrderEntity.getUser_study_card_id()
                        );
                    }
                });
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
        AlipayUtils.getInstance(getContext()).pay(orderInfo,
                null);
    }

    @Override
    public void onGetWxOrder(WxPayModel.WxPayEntity wxPayEntity) {
        EventBusUtils.sendEvent(new Event(EventAction.WX_PAY, wxPayEntity));
    }

    @Override
    public void onGetLongOrderInfoDetail(LongOrderPayInfoEntity longOrderPayInfoEntity) {
        // 275
        final String payCourseCount = longOrderPayInfoEntity.getPayCourseCount();
        final String payNum = longOrderPayInfoEntity.getPayNum();
        customPopupWindow = PayUtils.popPayWindow(getContext(), payNum, new PayUtils.OnListener() {
            @Override
            public void alipay() {
                getPresenter().getLongOrder(
                        LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                        "specialty_sheets",
                        "android",
                        "1",
                        mMechanismOrderEntity.getMaster_id(),
                        "机构端转长单支付宝支付",
                        mMechanismOrderEntity.getStudycard_id(),
                        "mechanism_offline",
                        true,
                        mMechanismOrderEntity.getUser_study_card_id(),
                        "mechanism",
                        "ali",
                        mMechanismOrderEntity.getMechanism_id()
                );
            }

            @Override
            public void weixinPay() {
                getPresenter().getWxLongOrder(
                        LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                        "specialty_sheets",
                        "android",
                        "1",
                        mMechanismOrderEntity.getMaster_id(),
                        "机构端转长单微信支付",
                        mMechanismOrderEntity.getStudycard_id(),
                        "mechanism_offline",
                        true,
                        mMechanismOrderEntity.getUser_study_card_id(),
                        "mechanism",
                        "wx",
                        mMechanismOrderEntity.getMechanism_id()
                );
            }

        });
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

    @Override
    public void onDestroyView() {
        try {
            if (customPopupWindow != null) {
                customPopupWindow.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
    }
}
