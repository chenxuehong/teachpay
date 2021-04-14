package com.eghuihe.module_schedule.ui.student.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.student.adapter.LiveCouponRvAdapter;
import com.eghuihe.module_schedule.ui.student.mvp.LiveCoursePayContract;
import com.eghuihe.module_schedule.ui.student.mvp.LiveCoursePayPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.PayDetailEntity;
import com.huihe.base_lib.model.UserCouponEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.BaseMvpActivity;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.pay.alipay.AlipayUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterConfig.SCHEDULE_LIVECOURSEPAYACTIVITY)
public class LiveCoursePayActivity extends BaseMvpActivity<LiveCoursePayPresenter>
        implements LiveCoursePayContract.View, LiveCouponRvAdapter.OnLisneter {

    @BindView(R2.id.activity_live_course_pay_tv_amount)
    TextView tvAmount;
    @BindView(R2.id.activity_live_course_pay_tv_userCoupon)
    TextView tvUserCoupon;
    @BindView(R2.id.activity_live_course_pay_iv_paybao)
    ImageView ivAliPay;
    @BindView(R2.id.activity_live_course_pay_iv_weixinpay)
    ImageView ivWxpay;
    @BindView(R2.id.activity_live_course_pay_iv_cor)
    ImageView ivCor;
    @BindView(R2.id.activity_live_course_pay_rv_userCoupon)
    RecyclerViewFixed rvUserCoupon; // 抵扣劵列表

    private boolean isAliPay;
    private boolean mIsAgree;
    private MasterSetPriceEntity masterSetPriceEntity;
    private String originalPrice;
    private String coupon_ids;
    private boolean isLiving;

    @OnClick({
            R2.id.activity_live_course_pay_ll_paybao,
            R2.id.activity_live_course_pay_ll_weixinpay,
            R2.id.activity_live_course_pay_tv_comfirm_pay,
            R2.id.activity_live_course_pay_iv_cor,
            R2.id.activity_live_course_pay_tv_close
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.activity_live_course_pay_ll_paybao) {
            // 支付宝支付
            selectAliPay(true);
        } else if (view.getId() == R.id.activity_live_course_pay_ll_weixinpay) {
            // 微信支付
            selectAliPay(false);
        } else if (view.getId() == R.id.activity_live_course_pay_iv_cor) {
            // 选中
            selectAgreement(!mIsAgree);
        } else if (view.getId() == R.id.activity_live_course_pay_tv_close) {
            // 关闭
            finish();
        } else if (view.getId() == R.id.activity_live_course_pay_tv_comfirm_pay) {
            // 确认支付
            if (checkInput()) {
                if (isAliPay) {
                    aliPay();
                } else {
                    wxPay();
                }
            }
        }
    }

    private boolean checkInput() {
        if (!mIsAgree) {
            ToastUtils.showShortToast(this, "请先同意协议");
            return false;
        }
        return true;
    }

    private void wxPay() {
        if (masterSetPriceEntity != null) {
            String course_num = masterSetPriceEntity.getCourse_num();
            getPresenter().payOneCourseLiveStreamByWx(
                    LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                    "study_card",
                    "android",
                    course_num,
                    masterSetPriceEntity.getDuration(),
                    !masterSetPriceEntity.getIs_one_time_payment() ? "课程全额购买" : "课程单节付",
                    masterSetPriceEntity.getId(),
                    "mechanism_offline",
                    masterSetPriceEntity.getIs_one_time_payment(),
                    getCoupon_ids(),
                    "wx",
                    masterSetPriceEntity.getMechanism_id(),
                    masterSetPriceEntity.getTitle(),
                    masterSetPriceEntity.getIs_one_time_payment() ? "single_session_deposit" :
                            isLiving?"live_stream_full":null,
                    masterSetPriceEntity.getLive_streaming_id()
            );
        }
    }

    public String getCoupon_ids() {
        return TextUtils.isEmpty(coupon_ids)?"0":coupon_ids;
    }

    private void aliPay() {
        if (masterSetPriceEntity != null) {
            String course_num = masterSetPriceEntity.getCourse_num();
            getPresenter().payOneCourseLiveStreamByAli(
                    LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                    "study_card",
                    "android",
                    course_num,
                    masterSetPriceEntity.getDuration(),
                    !masterSetPriceEntity.getIs_one_time_payment() ? "课程全额购买" : "课程单节付",
                    masterSetPriceEntity.getId(),
                    "mechanism_offline",
                    masterSetPriceEntity.getIs_one_time_payment(),
                    getCoupon_ids(),
                    "ali",
                    masterSetPriceEntity.getMechanism_id(),
                    masterSetPriceEntity.getTitle(),
                    masterSetPriceEntity.getIs_one_time_payment() ? "single_session_deposit" :
                            isLiving?"live_stream_full":null,
                    masterSetPriceEntity.getLive_streaming_id()
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
    protected LiveCoursePayPresenter createPresenter() {
        return new LiveCoursePayPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_course_pay;
    }

    @Override
    protected void initData() {
        selectAliPay(true);
        selectAgreement(false);
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(ArgumentsConfig.KEY_MASTERSETPRICEENTITY);
            masterSetPriceEntity = JsonUtil.fromJson(json, MasterSetPriceEntity.class);
            String live_streaming_id = masterSetPriceEntity.getLive_streaming_id();
            isLiving = !TextUtils.isEmpty(live_streaming_id) && !"0".equals(live_streaming_id);
            getPresenter().queryPayLiveStreamDetails(
                    masterSetPriceEntity.getId(),
                    LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                    masterSetPriceEntity.getMechanism_id(),
                    masterSetPriceEntity.getIs_one_time_payment(),
                    masterSetPriceEntity.getLive_streaming_id()
            );
        }
    }

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
    public void onAliOrderInfo(String orderInfo) {
        AlipayUtils.getInstance(this).pay(
                orderInfo, null
        );
    }

    @Override
    public void onWxOrderInfo(WxPayModel.WxPayEntity wxPayEntity) {
        EventBusUtils.sendEvent(new Event(EventAction.WX_PAY, wxPayEntity));
    }

    @Override
    public void onShowLivePayDetail(PayDetailEntity livePayDetailEntity) {
        originalPrice = livePayDetailEntity.getOriginalPrice();
        tvAmount.setText(originalPrice);
        resetData();
        List<UserCouponEntity> userCouponEntities = livePayDetailEntity.getUserCouponEntities();
        if (userCouponEntities == null || userCouponEntities.size() == 0) {
            tvUserCoupon.setText("暂无抵扣劵可用");
        } else {
            rvUserCoupon.setVertical(1);
            rvUserCoupon.setScrollingEnabled(false);
            rvUserCoupon.setAdapter(new LiveCouponRvAdapter(R.layout.item_user_coupon, this, userCouponEntities, this));
        }
    }

    @Override
    public void onItemChecked(LinkedList<UserCouponEntity> userCouponEntities) {

        // 获取最终支付价格
        float finalAmount = getFinalAmount(userCouponEntities);
        tvAmount.setText(String.valueOf(finalAmount));
        // 获取拼接后的多个抵扣劵id字符串，用逗号隔开  支付时需要使用该参数
        coupon_ids = getCoupon_ids(userCouponEntities);
        // 获取抵扣右标题
        String title = getRightTitle(userCouponEntities);
        tvUserCoupon.setText(title);
    }

    private String getRightTitle(LinkedList<UserCouponEntity> userCouponEntities) {
        if (userCouponEntities.size() == 0) {
            return "未使用抵扣劵";
        } else {
            float totalCouponPrice = getTotalCouponPrice(userCouponEntities);
            return "抵扣".concat(NumberUtils.transMoney(String.valueOf(totalCouponPrice)));
        }
    }

    /**
     * @param userCouponEntities
     * @return
     * @desc 获取选中的多个抵扣劵id拼接后的字符串
     */
    private String getCoupon_ids(LinkedList<UserCouponEntity> userCouponEntities) {
        Iterator<UserCouponEntity> iterator = userCouponEntities.iterator();
        List<String> idList = new ArrayList<>();
        while (iterator.hasNext()) {
            UserCouponEntity userCouponEntity = iterator.next();
            String id = userCouponEntity.getId();
            idList.add(id);
        }
        return StringUtils.list2String(idList, ",");
    }

    boolean isZero;

    @Override
    public boolean isZero() {
        return isZero;
    }

    /**
     * @param userCouponEntities
     * @return
     * @desc 计算出最终支付价格
     */
    private float getFinalAmount(LinkedList<UserCouponEntity> userCouponEntities) {
        // 原价
        float originalPriCeconverted = NumberUtils.tranToTwoDecimal(originalPrice);
        // 总的抵扣金额
        float totalCouponPrice = getTotalCouponPrice(userCouponEntities);
        float diffPrice = originalPriCeconverted - totalCouponPrice;
        return diffPrice <= 0 ? 0.0f : diffPrice;
    }

    private float getTotalCouponPrice(LinkedList<UserCouponEntity> userCouponEntities) {
        float totalCouponPrice = 0.0f;
        Iterator<UserCouponEntity> iterator = userCouponEntities.iterator();
        while (iterator.hasNext()) {
            UserCouponEntity couponEntity = iterator.next();
            String cash = couponEntity.getCash();
            float cashConverted = NumberUtils.tranToTwoDecimal(cash);
            totalCouponPrice += cashConverted;
        }
        return totalCouponPrice;
    }

    private void resetData() {
        isZero = false;
        coupon_ids = null;
    }
}
