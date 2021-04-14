package com.eghuihe.module_home.home.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.eghuihe.module_home.R;
import com.eghuihe.module_home.R2;
import com.eghuihe.module_home.home.mvp.ActivityDetailContract;
import com.eghuihe.module_home.home.mvp.ActivityDetailPresenter;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.picker.SinglePicker;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.ToastUtils;

import butterknife.OnClick;

public class ActivityDetailActivity extends BaseMvpTitleActivity<ActivityDetailPresenter>
        implements ActivityDetailContract.View {

    private BaseDialog getCouponDialog;
    private TextView tvGetCode;
    private EditText etNickName;
    private TextView tvSex;
    private TextView tvRelationships;
    private EditText etAge;
    private EditText etPreference;
    private EditText etPhone;
    private EditText etCode;
    private SinglePicker sexPicker;
    private String sex;
    private SinglePicker relationshipPicker;

    @OnClick({
            R2.id.acivity_activity_detail_iv_get_coupon
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.acivity_activity_detail_iv_get_coupon) {
            showGetCouponDialog();
        }
    }

    CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            if (tvGetCode != null) {
                tvGetCode.setText(String.format(getResources().getString(R.string.twice_get_code_countdowntime), String.valueOf(millisUntilFinished / 1000)));
                tvGetCode.setEnabled(false);
            }
        }

        @Override
        public void onFinish() {
            if (tvGetCode != null) {
                tvGetCode.setText(getResources().getString(R.string.get_code));
                tvGetCode.setEnabled(true);
            }
        }
    };

    private void showGetCouponDialog() {
        getCouponDialog = new BaseDialog(this) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_get_coupon;
            }

            @Override
            protected void initParams() {
                etNickName = (EditText) getView(R.id.dialog_getcoupon_et_nickName);
                tvSex = (TextView) getView(R.id.dialog_getcoupon_tv_sex);
                tvRelationships = (TextView) getView(R.id.dialog_getcoupon_tv_relationships);
                etAge = (EditText) getView(R.id.dialog_getcoupon_et_age);
                etPreference = (EditText) getView(R.id.dialog_getcoupon_et_preference);
                etPhone = (EditText) getView(R.id.dialog_getcoupon_et_phone);
                etCode = (EditText) getView(R.id.dialog_getcoupon_et_code);
                tvGetCode = (TextView) getView(R.id.dialog_getcoupon_tv_Getcode);
            }

            @Override
            protected void initEvents() {
                getView(R.id.dialog_getcoupon_tv_sex).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectSex();
                    }
                });
                getView(R.id.dialog_getcoupon_tv_relationships).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectRelationship();
                    }
                });
                getView(R.id.dialog_getcoupon_tv_getCoupon).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkInput()) {
                            getCoupon();
                        }
                    }
                });
                getView(R.id.dialog_getcoupon_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                getView(R.id.dialog_getcoupon_tv_Getcode).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText etPhone = (EditText) getView(R.id.dialog_getcoupon_et_phone);
                        if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
                            ToastUtils.showShortToast(ActivityDetailActivity.this, "请输入手机号码");
                            return;
                        }
                        getPhoneCode(etPhone.getText().toString().trim());
                    }
                });
            }
        };
        getCouponDialog.setPerWidth(333f / 414);
        getCouponDialog.setCancelOutside(false);
        getCouponDialog.show();
    }

    private void selectSex() {
        String[] sexArr = DataLoader.getInstance().getSexArr();
        sexPicker = new SinglePicker(
                this,
                sexArr);
        sexPicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                tvSex.setText(item);
                if (item.equals(getResources().getString(R.string.sex_nan))) {
                    sex = "1";
                } else {
                    sex = "2";
                }
            }
        });
        sexPicker.show();
    }

    private void selectRelationship() {
        String[] relationshipArr = DataLoader.getInstance().getRelationshipArr();
        relationshipPicker = new SinglePicker(
                this,
                relationshipArr);
        relationshipPicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                tvRelationships.setText(item);
            }
        });
        relationshipPicker.show();
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etNickName.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入宝宝名字");
            return false;
        }
        if (TextUtils.isEmpty(tvSex.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入宝宝性别");
            return false;
        }
        if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(etCode.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入验证码");
            return false;
        }
        return true;
    }

    private void getPhoneCode(String mobile) {
        getPresenter().sendSms(mobile);
    }

    private void getCoupon() {
        getPresenter().insertH5GetCoupon(
                etPhone.getText().toString().trim(),
                etCode.getText().toString().trim(),
                etNickName.getText().toString().trim(),
                sex,
                etPreference.getText().toString().trim(),
                tvRelationships.getText().toString().trim(),
                etAge.getText().toString().trim()
        );
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("活动详情");
    }

    @Override
    public void onInsertSuccess() {
        EventBusUtils.sendEvent(new Event(EventAction.RESET_USERINFO));
        ToastUtils.showShortToast(this, "领劵成功");
        startActivity(TeachPayCourseListActivity.class);
        finish();
    }

    @Override
    public void onGetCodeSuccess() {
        if (countDownTimer != null) {
            countDownTimer.start();
        }
        ToastUtils.showShortToast(this, "发送成功");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.acivity_activity_detail;
    }

    @Override
    protected ActivityDetailPresenter createPresenter() {
        return new ActivityDetailPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        try {
            if (getCouponDialog != null) {
                getCouponDialog.dismiss();
            }
            if (sexPicker != null) {
                sexPicker.dismiss();
            }
            if (relationshipPicker != null) {
                relationshipPicker.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
