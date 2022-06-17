package com.eghuihe.module_schedule.ui.student.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.student.adapter.UserCouponRvAdapter;
import com.eghuihe.module_schedule.ui.student.mvp.CoursePayContract;
import com.eghuihe.module_schedule.ui.student.mvp.CoursePayPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MechanismOrderEntity;
import com.huihe.base_lib.model.PayDetailEntity;
import com.huihe.base_lib.model.UserCouponEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.BaseMvpActivity;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.pay.alipay.AlipayUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterConfig.SCHEDULE_STUDENTORDERPAYACTIVITY)
public class StudentOrderPayActivity extends BaseMvpActivity<CoursePayPresenter>
        implements CoursePayContract.View, UserCouponRvAdapter.OnLisneter {

    @BindView(R2.id.activity_course_pay_tv_amount)
    TextView tvAmount;
    @BindView(R2.id.activity_course_pay_tv_userCoupon)
    TextView tvUserCoupon;
    @BindView(R2.id.activity_course_pay_iv_paybao)
    ImageView ivAliPay;
    @BindView(R2.id.activity_course_pay_tv_userPoint)
    TextView tvUserPoints;
    @BindView(R2.id.activity_course_pay_tv_userPoint_discount)
    TextView tvUserPoinDiscount;
    @BindView(R2.id.activity_course_pay_iv_weixinpay)
    ImageView ivWxpay;
    @BindView(R2.id.activity_course_pay_iv_cor)
    ImageView ivCor;
    @BindView(R2.id.activity_course_pay_iv_userPoint_checkbox)
    ImageView ivUserPointCbx;
    @BindView(R2.id.activity_course_pay_ll_userPoint)
    LinearLayout llUserPointCbx;
    @BindView(R2.id.activity_course_pay_rv_userCoupon)
    RecyclerViewFixed rvUserCoupon;

    private boolean isAliPay;
    private boolean mIsAgree;
    private boolean isPointsChecked;
    private PayDetailEntity payDetailEntity;
    private MechanismOrderEntity mechanismOrderEntity;
    private UserCouponEntity mUserCouponEntity;
    private float originalPriceFloat;

    @OnClick({
            R2.id.activity_course_pay_ll_paybao,
            R2.id.activity_course_pay_ll_weixinpay,
            R2.id.activity_course_pay_tv_comfirm_pay,
            R2.id.activity_course_pay_iv_cor,
            R2.id.activity_course_pay_ll_userPoint,
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
        } else if (view.getId() == R.id.activity_course_pay_ll_userPoint) {
            // 积分选择
            isPointsChecked = !isPointsChecked;
            selectPoints(isPointsChecked);
            usePoints();
        } else if (view.getId() == R.id.activity_course_pay_tv_comfirm_pay) {
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

    private void selectPoints(boolean isPointsChecked) {
        ivUserPointCbx.setImageResource(isPointsChecked ? R.mipmap.check_state : R.mipmap.uncheck_state);
        this.isPointsChecked = isPointsChecked;
        if (llUserPointCbx.getVisibility() == View.VISIBLE) {
            Long points = payDetailEntity.getPoints();
            points = points == null ? 0L : points;
            if (points != null) {
                float amount = points * 1f / 100;
                amount = NumberUtils.tranToTwoDecimal(amount);
                tvUserPoints.setText(
                        !isPointsChecked ? "未使用积分"
                                : ("抵扣".concat(String.valueOf(amount).concat("元"))));
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
        if (payDetailEntity != null) {
            String earnings = payDetailEntity.getOriginalPrice();
            getPresenter().studentScheduleWxpay(
                    LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                    "study_card",
                    null,
                    "android",
                    "1",
                    mechanismOrderEntity.getMaster_id(),
                    "12",
                    "学生购买机构单节课",
                    mechanismOrderEntity.getStudycard_id(),
                    "mechanism_offline",
                    true,
                    mechanismOrderEntity.getAppointment_id(),
                    mechanismOrderEntity.getUser_study_card_id(),
                    mUserCouponEntity != null ? mUserCouponEntity.getId() : null,
                    isPointsChecked ? String.valueOf(payDetailEntity.getPoints()) : null,
                    "wx",
                    mechanismOrderEntity.getMechanism_id(),
                    mechanismOrderEntity.getUser_appointment_id(),
                    mechanismOrderEntity.getTitle());
        }
    }

    private void aliPay() {
        if (payDetailEntity != null) {
            String number_of_lessons = mechanismOrderEntity.getNumber_of_lessons();
            getPresenter().studentScheduleAlipay(
                    LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                    "study_card",
                    null,
                    "android",
                    "1",
                    mechanismOrderEntity.getMaster_id(),
                    "12",
                    "学生购买机构单节课",
                    mechanismOrderEntity.getStudycard_id(),
                    "mechanism_offline",
                    true,
                    mechanismOrderEntity.getAppointment_id(),
                    mechanismOrderEntity.getUser_study_card_id(),
                    mUserCouponEntity != null ? mUserCouponEntity.getId() : null,
                    isPointsChecked ? String.valueOf(payDetailEntity.getPoints()) : null,
                    "ali",
                    mechanismOrderEntity.getMechanism_id(),
                    mechanismOrderEntity.getUser_appointment_id(),
                    mechanismOrderEntity.getTitle());
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
    protected CoursePayPresenter createPresenter() {
        return new CoursePayPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_pay;
    }

    @Override
    protected void initData() {
        selectAliPay(true);
        selectAgreement(false);
        Intent intent = getIntent();
        if (intent != null) {
            String mechanismOrderEntityeJson = intent.getStringExtra(ArgumentsConfig.KEY_MECHANISMORDERENTITY);
            mechanismOrderEntity = JsonUtil.fromJson(mechanismOrderEntityeJson, MechanismOrderEntity.class);
            String number_of_lessons = mechanismOrderEntity.getNumber_of_lessons();
            getPresenter().queryPayDetails(
                    number_of_lessons,
                    mechanismOrderEntity.getStudycard_id(),
                    mechanismOrderEntity.getUser_study_card_id(),
                    LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                    mechanismOrderEntity.getMechanism_id(),
                    true
            );
        }
    }

    private void selectAgreement(boolean isAgree) {
        ivCor.setImageResource(isAgree ? R.drawable.check_state : R.drawable.uncheck_state);
        mIsAgree = isAgree;
    }

    @Override
    public void onAlipayOrder(String orderInfo) {
        if (TextUtils.isEmpty(orderInfo)) {
            ToastUtils.showShortToast(this, "支付成功");
            finish();
        } else {
            AlipayUtils.getInstance(this).pay(
                    orderInfo, null
            );
        }

    }

    @Override
    public void onWxpayOrder(WxPayModel.WxPayEntity wxPayEntity) {
        if (wxPayEntity != null) {
            EventBusUtils.sendEvent(new Event(EventAction.WX_PAY, wxPayEntity));
        } else {
            ToastUtils.showShortToast(this, "支付成功");
            finish();
        }
    }

    @Override
    public void onPayDetailEntity(PayDetailEntity payDetailEntity) {
        this.payDetailEntity = payDetailEntity;
        String originalPrice = payDetailEntity.getOriginalPrice();
        originalPrice = TextUtils.isEmpty(originalPrice) ? "0.0" : originalPrice;
        originalPriceFloat = Float.valueOf(originalPrice);
        tvAmount.setText(originalPrice);
        Long points = payDetailEntity.getPoints();
        points = points == null ? 0L : points;
        if (points <= 0) {
            tvUserPoinDiscount.setText("暂无积分可用");
            llUserPointCbx.setVisibility(View.GONE);
            tvUserPoints.setText("暂无积分可用");
        } else {
            llUserPointCbx.setVisibility(View.VISIBLE);
            float amount = points * 1f / 100;
            amount = NumberUtils.tranToTwoDecimal(amount);
            tvUserPoinDiscount.setText(String.valueOf(points).concat("积分"));
        }
        List<UserCouponEntity> userCouponEntities = payDetailEntity.getUserCouponEntities();
        if (userCouponEntities == null || userCouponEntities.size() == 0) {
            tvUserCoupon.setText("暂无优惠券可用");
        } else {
            rvUserCoupon.setVertical(1);
            rvUserCoupon.setScrollingEnabled(false);
            rvUserCoupon.setAdapter(new UserCouponRvAdapter(R.layout.item_user_coupon, this, userCouponEntities, this));
        }
    }

    private ImageView curIvCheck;

    @Override
    public void onItemChecked(UserCouponEntity userCouponEntity, ViewHolder viewHolder) {
        if (curIvCheck != null) {
            curIvCheck.setImageResource(R.mipmap.uncheck_state);
        }
        ImageView ivCheck = viewHolder.getView(R.id.item_user_coupon_iv_check);
        ivCheck.setImageResource(userCouponEntity.getChecked() ? R.mipmap.check_state : R.mipmap.uncheck_state);
        curIvCheck = ivCheck;

        if (userCouponEntity.getChecked()) {
            String discount = userCouponEntity.getDiscount();
            String coup_name = userCouponEntity.getCoup_name();
            coup_name = TextUtils.isEmpty(coup_name) ? "" : coup_name;
            discount = TextUtils.isEmpty(discount) ? "" : discount;
            tvUserCoupon.setText(coup_name.concat("(").concat(discount).concat("折").concat(")"));
            mUserCouponEntity = userCouponEntity;
        } else {
            tvUserCoupon.setText("未使用优惠券");
            mUserCouponEntity = null;
        }
        useUserCoupon();
    }


    private void usePoints() {
        String originalPrice = payDetailEntity.getOriginalPrice();
        originalPrice = TextUtils.isEmpty(originalPrice) ? "0.0" : originalPrice;
        originalPriceFloat = Float.valueOf(originalPrice);
        // 判断是否使用积分
        // 可使用积分
        Long points = payDetailEntity.getPoints();
        points = points == null ? 0L : points;
        float discount_amount = points * 1f / 100;
        discount_amount = NumberUtils.tranToTwoDecimal(discount_amount);
        if (isPointsChecked && originalPriceFloat > 0) {
            // 使用积分
            if (originalPriceFloat - discount_amount > 0) {
                originalPriceFloat = originalPriceFloat - discount_amount;
            } else {
                originalPriceFloat = 0;
            }
        } else {
            isPointsChecked = false;
            selectPoints(isPointsChecked);
        }

        tvUserCoupon.setText("未使用优惠券");
        mUserCouponEntity = null;
        if (curIvCheck != null) {
            curIvCheck.setImageResource(R.mipmap.uncheck_state);
        }

        tvAmount.setText(String.valueOf(originalPriceFloat));
    }

    private void useUserCoupon() {
        // 原价
        String originalPrice = payDetailEntity.getOriginalPrice();
        originalPrice = TextUtils.isEmpty(originalPrice) ? "0.0" : originalPrice;
        originalPriceFloat = Float.valueOf(originalPrice);
        // 判断是否使用优惠券
        if (mUserCouponEntity != null && originalPriceFloat > 0) {
            // 使用优惠券打折扣
            String discount = mUserCouponEntity.getDiscount();
            discount = TextUtils.isEmpty(discount) ? "0.0" : discount;
            float discountfloat = Float.valueOf(discount);
            originalPriceFloat = originalPriceFloat * discountfloat;
        } else {
            tvUserCoupon.setText("未使用优惠券");
            mUserCouponEntity = null;
            if (curIvCheck != null) {
                curIvCheck.setImageResource(R.mipmap.uncheck_state);
            }
        }
        isPointsChecked = false;
        selectPoints(isPointsChecked);
        tvAmount.setText(String.valueOf(originalPriceFloat));
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
}
