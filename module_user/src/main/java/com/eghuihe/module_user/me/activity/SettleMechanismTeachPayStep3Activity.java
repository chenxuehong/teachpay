package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.bean.params.SettleMechanismTeachPayStep2Bean;
import com.eghuihe.module_user.bean.params.SettleMechanismTeachPayStep3Bean;
import com.eghuihe.module_user.me.adapter.CoursePhotoRvAdapter;
import com.eghuihe.module_user.me.mvp.SettleMechanismTeachPayContract;
import com.eghuihe.module_user.me.mvp.SettleMechanismTeachPayPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.activity.PhotoViewActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.widget.AutoFitImageView;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ARouterUtils;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.QiNiuUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.amin.AnimationConstants;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SettleMechanismTeachPayStep3Activity extends BaseMvpTitleActivity<SettleMechanismTeachPayPresenter>
        implements SettleMechanismTeachPayContract.View {
    public static final String KEY_DATA = "data";
    private SettleMechanismTeachPayStep2Bean mechanismTeachPayStep2Bean;

    @BindView(R2.id.activity_settle_mechanism_teach_pay_step3_iv_upload_business_license)
    AutoFitImageView ivUploadBusinessLicense;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step3_rv_support_means)
    RecyclerViewFixed rvSupportMeans;
    private CoursePhotoRvAdapter introduction_picAdapter;
    private String businessLicense;

    @OnClick({
            R2.id.activity_settle_mechanism_teach_pay_step3_iv_upload_business_license,
            R2.id.activity_settle_mechanism_teach_pay_step3_commit
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_settle_mechanism_teach_pay_step3_iv_upload_business_license) {
            uploadBusinessLicense();
        } else if (view.getId() == R.id.activity_settle_mechanism_teach_pay_step3_commit) {
            if (checkInput()) {
                List<String> introduction_picList = getIntroductionPic();
                String introduction_pic = StringUtils.list2String(introduction_picList, ",");
                SettleMechanismTeachPayStep3Bean settleMechanismTeachPayStep3Bean =
                        new SettleMechanismTeachPayStep3Bean(
                                mechanismTeachPayStep2Bean.settleMechanismTeachPayStep1Bean.store_type,
                                mechanismTeachPayStep2Bean.settleMechanismTeachPayStep1Bean.mechanismLogo,
                                mechanismTeachPayStep2Bean.settleMechanismTeachPayStep1Bean.mechanismName,
                                mechanismTeachPayStep2Bean.settleMechanismTeachPayStep1Bean.mechanismAddress,
                                mechanismTeachPayStep2Bean.settleMechanismTeachPayStep1Bean.IsOpenTeachPay,
                                mechanismTeachPayStep2Bean.settleMechanismTeachPayStep1Bean.longitude,
                                mechanismTeachPayStep2Bean.settleMechanismTeachPayStep1Bean.latitude,
                                mechanismTeachPayStep2Bean.mechanismTel,
                                mechanismTeachPayStep2Bean.mechanismTradeModel,
                                mechanismTeachPayStep2Bean.mechanismContractTel,
                                mechanismTeachPayStep2Bean.mechanismContractName,
                                mechanismTeachPayStep2Bean.alipayAccount,
                                mechanismTeachPayStep2Bean.introduction_content,
                                businessLicense,
                                introduction_pic,
                                mechanismTeachPayStep2Bean.settleMechanismTeachPayStep1Bean.facade_view
                        );
                commit(settleMechanismTeachPayStep3Bean);

            }
        }
    }

    private void commit(SettleMechanismTeachPayStep3Bean settleMechanismTeachPayStep3Bean) {
        getPresenter().settleMechanism(
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                "teach_paypal",
                settleMechanismTeachPayStep3Bean.categories,
                settleMechanismTeachPayStep3Bean.mechanism_logo,
                settleMechanismTeachPayStep3Bean.mechanism_name,
                settleMechanismTeachPayStep3Bean.mechanism_addr,
                settleMechanismTeachPayStep3Bean.is_support_teach_paypal,
                settleMechanismTeachPayStep3Bean.longitude,
                settleMechanismTeachPayStep3Bean.latitude,
                settleMechanismTeachPayStep3Bean.mechanism_telephone,
                settleMechanismTeachPayStep3Bean.categories_child,
                settleMechanismTeachPayStep3Bean.contact_telephone,
                settleMechanismTeachPayStep3Bean.contacts,
                settleMechanismTeachPayStep3Bean.payee_logon_id,
                settleMechanismTeachPayStep3Bean.introduction_content,
                settleMechanismTeachPayStep3Bean.support_means,
                settleMechanismTeachPayStep3Bean.introduction_pic,
                settleMechanismTeachPayStep3Bean.facade_view
        );
    }

    private boolean checkInput() {

        if (TextUtils.isEmpty(businessLicense)) {
            ToastUtils.showShortToast(this, "请选择上传营业执照");
            return false;
        }
        if (!hasSupportMeans()) {
            ToastUtils.showShortToast(this, "请选择上传机构图片");
            return false;
        }
        return true;
    }

    /**
     * @desc 上传营业执照
     */
    private void uploadBusinessLicense() {
        PhotoSelectUtils.selectPic(this,
                new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(String path) {
                        showUploading();
                        new QiNiuUtils().uploadPic(
                                path,
                                String.valueOf(new Date().getTime()).concat(".jpg"),
                                new QiNiuUtils.OnUploadListener() {
                                    @Override
                                    public void onUploadFinish(String picUrl) {
                                        showBusinessLicense(picUrl);
                                        closeLoading();
                                    }

                                    @Override
                                    public void onUploadFail(int code, String error) {
                                        closeLoading();
                                        ToastUtils.showShortToast(SettleMechanismTeachPayStep3Activity.this, error);
                                    }
                                });
                    }
                });
    }

    private void showBusinessLicense(String picUrl) {
        businessLicense = picUrl;
        GlideTools.loadRoundedImage(this, picUrl, DensityUtils.dp2px(this, 6), ivUploadBusinessLicense);
    }

    /**
     * @desc 选择上传机构图片
     */
    private void selectSupportMeans() {
        PhotoSelectUtils.selectMutiPic(this,
                new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(List<String> supportMeansList) {
                        uploadSupportMeans(supportMeansList);
                    }

                    @Override
                    protected void updateLoadImage(String path) {
                        List<String> supportMeansList = new ArrayList<>();
                        supportMeansList.add(path);
                        uploadSupportMeans(supportMeansList);
                    }
                });
    }

    /**
     * @desc 上传机构图片
     * (必传: 门面照1张、前台照1张和环境照2张)
     */
    private void uploadSupportMeans(List<String> list) {

        showUploading();
        final List<String> supportMeansList = new ArrayList<>();
        new QiNiuUtils().loadMutiPic(
                0,
                list,
                new QiNiuUtils.OnUpMutiloadListener() {
                    @Override
                    public void loadFinish() {

                        closeLoading();
                        showSupportMeans(supportMeansList);
                    }

                    @Override
                    public void loadPic(int position, String path) {
                        if (supportMeansList != null)
                            supportMeansList.add(path);
                    }

                    @Override
                    public void loadError(int position) {

                        ToastUtils.showShortToast(SettleMechanismTeachPayStep3Activity.this,
                                "第".concat(String.valueOf(position)).concat("张").concat("上传失败!"));
                    }
                }
        );
    }

    private boolean hasSupportMeans() {
        return introduction_picAdapter != null && introduction_picAdapter.getItemCount() > 1;
    }

    private void showSupportMeans(List<String> supportMeansList) {
        if (introduction_picAdapter != null) {
            introduction_picAdapter.addData(supportMeansList, introduction_picAdapter.getItemCount() - 1);
        }
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_settle_mechanism_teach_pay_step_3;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("机构门店入驻");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initData() {
        businessLicense = null;
        mechanismTeachPayStep2Bean = getIntentData(KEY_DATA, SettleMechanismTeachPayStep2Bean.class);
        rvSupportMeans.setVertical(3);
        rvSupportMeans.addGridViewItemDecoration(3, DensityUtils.dp2px(this, 13));
        List<String> supportMeansList = new ArrayList<>();
        supportMeansList.add(CoursePhotoRvAdapter.UPLOAD);
        introduction_picAdapter = new CoursePhotoRvAdapter(R.layout.item_upload_img, this, supportMeansList);
        introduction_picAdapter.setOnItemClickListener(new CommonRVAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClicked(View v, String photo, int position) {
                if (CoursePhotoRvAdapter.UPLOAD.equals(photo)) {
                    selectSupportMeans();
                } else {
                    Intent intent = new Intent(SettleMechanismTeachPayStep3Activity.this, PhotoViewActivity.class);
                    intent.putExtra(PhotoViewActivity.CURRENT_POSITION, position);
                    List<String> data = introduction_picAdapter.getData();
                    intent.putExtra(PhotoViewActivity.URLS, JsonUtil.toJson(data));

                    int[] location = new int[2];
                    v.getLocationOnScreen(location);

                    int centerX = (int) (location[0] + v.getMeasuredWidth() / 2);
                    int centery = (int) (location[1] + v.getMeasuredHeight() / 2);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTX, centerX);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTY, centery);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_ENABLE, true);
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClicked(View v, String photo, int i) {

            }
        });
        rvSupportMeans.setAdapter(introduction_picAdapter);
    }

    private List<String> getIntroductionPic() {
        List<String> data = introduction_picAdapter.getData();
        List<String> IntroductionPics = new ArrayList<>();
        IntroductionPics.addAll(data);
        IntroductionPics.remove(CoursePhotoRvAdapter.UPLOAD);
        return IntroductionPics;
    }

    @Override
    public void onSettleSuccess() {
        ToastUtils.showShortToast(this, "提交成功，请耐心等待审核");
        BaseActivity activity = ARouterUtils.getActivity(ARouterConfig.MAIN_MAINACTIVITY);
        AppManager.getInstance().backTo(activity.getClass());
    }

    @Override
    protected SettleMechanismTeachPayPresenter createPresenter() {
        return new SettleMechanismTeachPayPresenter();
    }
}
