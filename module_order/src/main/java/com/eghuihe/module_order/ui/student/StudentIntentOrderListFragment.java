package com.eghuihe.module_order.ui.student;

import android.text.TextUtils;

import com.eghuihe.module_order.ui.adapter.StudentOrderRvAdapter;
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
public class StudentIntentOrderListFragment extends BaseStudentOrderListFragment<MechanismOrderListPresenter>
        implements MechanismOrderListContract.View {

    private CustomPopupWindow payDialog;

    private MechanismOrderEntity mMechanismOrderEntity;

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
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                null,
                false,
                false,
                getPageSize(),
                getCurrentPage(),
                null,
                null,
                "1"
        );
    }

    @Override
    public void MechanismOrderList(List<MechanismOrderEntity> mechanismOrderEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(mechanismOrderEntities);
                adapter.setOnChildListener(new StudentOrderRvAdapter.OnChildListener() {
                    @Override
                    public void onOncePayClicked(ViewHolder viewHolder, MechanismOrderEntity mechanismOrderEntity) {

                        mMechanismOrderEntity = mechanismOrderEntity;
                        getPresenter().queryLongOrderPayInfo(mMechanismOrderEntity.getUser_study_card_id());
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
        if (!TextUtils.isEmpty(orderInfo)) {
            AlipayUtils.getInstance(getContext()).pay(orderInfo,
                    null);
        }

    }

    @Override
    public void onGetWxOrder(WxPayModel.WxPayEntity wxPayEntity) {
        if (wxPayEntity != null)
            EventBusUtils.sendEvent(new Event(EventAction.WX_PAY, wxPayEntity));
    }

    @Override
    public void onGetLongOrderInfoDetail(LongOrderPayInfoEntity longOrderPayInfoEntity) {
        String payNum = longOrderPayInfoEntity.getPayNum();
        String payCourseCount = longOrderPayInfoEntity.getPayCourseCount();
        payDialog = PayUtils.popPayWindow(
                getContext(),
                payNum,
                new PayUtils.OnListener() {
                    @Override
                    public void alipay() {
                        getPresenter().getLongOrder(
                                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                                "specialty_sheets",
                                "android",
                                payCourseCount,
                                mMechanismOrderEntity.getMaster_id(),
                                "学生端转长单支付宝支付",
                                mMechanismOrderEntity.getStudycard_id(),
                                "mechanism_offline",
                                true,
                                mMechanismOrderEntity.getUser_study_card_id(),
                                "user",
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
                                "学生端转长单微信支付",
                                mMechanismOrderEntity.getStudycard_id(),
                                "mechanism_offline",
                                true,
                                mMechanismOrderEntity.getUser_study_card_id(),
                                "user",
                                "wx",
                                mMechanismOrderEntity.getMechanism_id()
                        );
                    }

                });
    }

    @Override
    public void onDestroyView() {
        try {
            if (payDialog != null) {
                payDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
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
