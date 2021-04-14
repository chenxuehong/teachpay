package com.eghuihe.module_schedule.ui.student.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.student.mvp.ActivitiesPayContract;
import com.eghuihe.module_schedule.ui.student.mvp.ActivitiesPayPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.BaseMvpActivity;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.pay.alipay.AlipayUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterConfig.SCHEDULE_ACTIVITIESPAYACTIVITY)
public class ActivitiesPayActivity extends BaseMvpActivity<ActivitiesPayPresenter>
        implements ActivitiesPayContract.View {

    @BindView(R2.id.activity_course_pay_tv_amount)
    TextView tvAmount;
    @BindView(R2.id.activity_course_pay_iv_paybao)
    ImageView ivAliPay;
    @BindView(R2.id.activity_course_pay_iv_weixinpay)
    ImageView ivWxpay;
    @BindView(R2.id.activity_course_pay_iv_cor)
    ImageView ivCor;
    @BindView(R2.id.activity_course_pay_ll_yh)
    LinearLayout llYh;

    private boolean mIsAgree;

    private boolean isAliPay;
    private String type;
    private String payAount;
    private String invite_code;
    private Boolean is_one_time_payment;
    private String course_num;

    @OnClick({
            R2.id.activity_course_pay_ll_paybao,
            R2.id.activity_course_pay_ll_weixinpay,
            R2.id.activity_course_pay_tv_comfirm_pay,
            R2.id.activity_course_pay_iv_cor,
            R2.id.activity_course_pay_tv_close
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.activity_course_pay_ll_paybao) {
            // 支付宝支付
            selectAliPay(true);
        } else if (view.getId() == R.id.activity_course_pay_ll_weixinpay) {
            // 微信支付
            selectAliPay(false);
        } else if (view.getId() == R.id.activity_course_pay_iv_cor) {
            // 选中
            selectAgreement(!mIsAgree);
        } else if (view.getId() == R.id.activity_course_pay_tv_close) {
            // 关闭
            finish();
        } else if (view.getId() == R.id.activity_course_pay_tv_comfirm_pay) {
            // 确认支付
            if (checkInput()) {
                invite_code = null;
                pay();
            }
        }
    }



    @Override
    protected void initView() {
        super.initView();
        llYh.setVisibility(View.GONE);
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getStringExtra(ArgumentsConfig.KEY_TYPE);
            invite_code = intent.getStringExtra(ArgumentsConfig.KEY_INVITE_CODE);
            if ("coupon_77".equals(type)) {
                payAount = "77.0";
            } else if ("coupon_177".equals(type)) {
                payAount = "177.0";
            } else {
                payAount = "99.0";
            }
            tvAmount.setText(payAount);
        }
    }

    private boolean checkInput() {
        if (!mIsAgree) {
            ToastUtils.showShortToast(this, "请先同意购买协议");
            return false;
        }
        return true;
    }

    private void pay() {
        if ("coupon_177".equals(type)){
            is_one_time_payment = true;
            course_num = "1";
        }else {
            is_one_time_payment = null;
            course_num = null;
        }
        if (isAliPay) {
            getPresenter().buyCouponByAli(
                    LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                    type,
                    "android",
                    "购买新春活动劵",
                    is_one_time_payment,
                    "ali",
                    invite_code,
                    course_num
            );
        } else {
            getPresenter().buyCouponByWx(
                    LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                    type,
                    "android",
                    "购买新春活动劵",
                    is_one_time_payment,
                    "wx",
                    invite_code,
                    course_num
            );
        }
    }

    private void selectAliPay(boolean isAliPay) {
        if (isAliPay) {
            ivAliPay.setImageResource(R.drawable.check_state);
            ivWxpay.setImageResource(R.drawable.uncheck_state);
        } else {
            ivAliPay.setImageResource(R.drawable.uncheck_state);
            ivWxpay.setImageResource(R.drawable.check_state);
        }
        this.isAliPay = isAliPay;
    }

    @Override
    protected ActivitiesPayPresenter createPresenter() {
        return new ActivitiesPayPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_pay;
    }

    @Override
    protected void initData() {
        selectAliPay(true);
        selectAgreement(false);
    }

    private static final String SP_LINE = "#\\$\\*";

    private void selectAgreement(boolean isAgree) {
        ivCor.setImageResource(isAgree ? R.drawable.check_state : R.drawable.uncheck_state);
        mIsAgree = isAgree;
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.PAY_SUCCESS.equals(event.getAction())) {
            ToastUtils.showShortToast(this, "支付成功");
            finish();
        }
    }

    @Override
    public void onGetAliOrderInfo(String orderInfo) {
        AlipayUtils.getInstance(this).pay(
                orderInfo, null
        );
    }

    @Override
    public void onGetWxOrderInfo(WxPayModel.WxPayEntity wxPayEntity) {
        EventBusUtils.sendEvent(new Event(EventAction.WX_PAY, wxPayEntity));
    }

}
