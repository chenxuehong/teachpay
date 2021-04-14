package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.mvp.UpdateMechanismTeacherContract;
import com.eghuihe.module_user.me.mvp.UpdateMechanismTeacherPresenter;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.picker.SinglePicker;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.QiNiuUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateMechanismTeacherActivity extends BaseMvpTitleActivity<UpdateMechanismTeacherPresenter>
        implements UpdateMechanismTeacherContract.View {

    public static final String KEY_DATA = "data";
    @BindView(R2.id.activity_insert_mechanism_teacher_tv_head)
    TextView tvHead;
    @BindView(R2.id.activity_insert_mechanism_teacher_iv_head)
    CircleImageView ivHead;
    @BindView(R2.id.activity_insert_mechanism_teacher_et_nickName)
    EditText etNickName;
    @BindView(R2.id.activity_insert_mechanism_teacher_tv_sex)
    TextView tvSex;
    @BindView(R2.id.activity_insert_mechanism_teacher_et_tel)
    EditText etTel;
    @BindView(R2.id.activity_insert_mechanism_teacher_et_account)
    EditText etAccount;
    @BindView(R2.id.activity_insert_mechanism_teacher_et_password)
    EditText etPassword;
    @BindView(R2.id.activity_insert_mechanism_teacher_tv_resign)
    TextView tvResign;
    private SinglePicker sexPicker;
    private String sex;
    private String headPic;
    private MasterInfoHomeModel.MasterInfoHomeEntity masterInfoHomeEntity;

    @OnClick({
            R2.id.activity_insert_mechanism_teacher_rl_head,
            R2.id.activity_insert_mechanism_teacher_rl_sex,
            R2.id.activity_insert_mechanism_teacher_tv_finish,
            R2.id.activity_insert_mechanism_teacher_tv_resign
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.activity_insert_mechanism_teacher_rl_head) {
            selectHead();
        } else if (view.getId() == R.id.activity_insert_mechanism_teacher_rl_sex) {
            selectSex();
        } else if (view.getId() == R.id.activity_insert_mechanism_teacher_tv_resign) {
            reSign();
        } else if (view.getId() == R.id.activity_insert_mechanism_teacher_tv_finish) {
            if (checkInput()) {
                commit();
            }
        }
    }

    private void selectHead() {
        PhotoSelectUtils.selectPic(this,
                new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(String path) {
                        showUploading();
                        new QiNiuUtils().uploadPic(path, Long.valueOf((new Date()).getTime()) + ".jpg", new QiNiuUtils.OnUploadListener() {

                            @Override
                            public void onUploadFinish(String picUrl) {
                                showHead(picUrl);
                                closeLoading();
                            }

                            @Override
                            public void onUploadFail(int code, String error) {
                                closeLoading();
                            }
                        });
                    }
                });
    }

    private void showHead(String picUrl) {
        headPic = picUrl;
        GlideTools.loadImage(this, headPic, ivHead);
        if (TextUtils.isEmpty(picUrl)) {
            tvHead.setVisibility(View.VISIBLE);
            ivHead.setVisibility(View.GONE);
        } else {
            tvHead.setVisibility(View.GONE);
            ivHead.setVisibility(View.VISIBLE);
        }
    }

    private void selectSex() {
        String[] sexArr = DataLoader.getInstance().getSexArr();
        try {
            if (sexPicker != null) {
                sexPicker.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void reSign() {
        getPresenter().resignTeacher(
                masterInfoHomeEntity.getId(),
                "4"
        );
    }

    private boolean checkInput() {
        if (ivHead.getVisibility() == View.GONE) {
            ToastUtils.showShortToast(this, "请上传头像");
            return false;
        }
        if (TextUtils.isEmpty(etNickName.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入老师姓名");
            return false;
        }
        if (TextUtils.isEmpty(tvSex.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择老师性别");
            return false;
        }
        if (TextUtils.isEmpty(etTel.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入老师手机号码");
            return false;
        }
        if (TextUtils.isEmpty(etAccount.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入老师登录账号");
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "输入老师登录密码");
            return false;
        }
        return true;
    }

    private void commit() {

        getPresenter().updateMechanismMaster(
                masterInfoHomeEntity.getUser_id(),
                etPassword.getText().toString().trim(),
                etAccount.getText().toString().trim(),
                etNickName.getText().toString().trim(),
                "teach_paypal",
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                headPic,
                etTel.getText().toString().trim(),
                sex
        );
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.update_teacher));
    }

    @Override
    public void onUpdateSuccess() {
        finish();
    }

    @Override
    public void onResignSuccess() {
        ToastUtils.showShortToast(this, "设置成功");
        finish();
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_insert_mechanism_teacher;
    }

    @Override
    protected UpdateMechanismTeacherPresenter createPresenter() {
        return new UpdateMechanismTeacherPresenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initView() {
        super.initView();
        tvResign.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

        masterInfoHomeEntity = getIntentData(KEY_DATA, MasterInfoHomeModel.MasterInfoHomeEntity.class);
        String avatar = masterInfoHomeEntity.getPhoto();
        String full_name = masterInfoHomeEntity.getFull_name();
        sex = masterInfoHomeEntity.getSex();
        String mobile = masterInfoHomeEntity.getMobile();
        String login_name = masterInfoHomeEntity.getLogin_name();
        String login_pass = masterInfoHomeEntity.getLogin_pass();
        showHead(avatar);
        etNickName.setText(full_name);
        tvSex.setText("1".equals(sex) ? "男" : "女");
        etTel.setText(mobile);
        etAccount.setText(login_name);
        etPassword.setText(login_pass);
    }
}
