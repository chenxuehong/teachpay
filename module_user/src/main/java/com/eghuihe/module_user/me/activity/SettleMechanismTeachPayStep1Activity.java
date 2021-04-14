package com.eghuihe.module_user.me.activity;

import android.Manifest;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.bean.params.SettleMechanismTeachPayStep1Bean;
import com.eghuihe.module_user.me.adapter.PicRvAdapter;
import com.eghuihe.module_user.me.adapter.StoreTypeRvAdapter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.model.ExtraEntity;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.activity.H5TitleActivity;
import com.huihe.base_lib.ui.activity.LocationActivity;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ConvertUtils;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.QiNiuUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterConfig.ME_SETTLEMECHANISM)
public class SettleMechanismTeachPayStep1Activity extends BaseTitleActivity {

    private static final int ADDRESS_LOCATION_REQUEST_CODE = 100;
    private StoreTypeRvAdapter storeTypeRvAdapter;
    private PicRvAdapter mMechanismLogoRvAdapter;
    private double longitude;
    private double latitude;
    private PicRvAdapter mFacadeViewRvAdapter;

    @OnClick({
            R2.id.activity_settle_mechanism_teach_pay_step1_iv_institutional_guide,
            R2.id.activity_settle_mechanism_teach_pay_step1_ll_mechanismLogo,
            R2.id.activity_settle_mechanism_teach_pay_step1_ll_facade_view,
            R2.id.activity_settle_mechanism_teach_pay_step1_ll_mechanismLocation,
            R2.id.activity_settle_mechanism_teach_pay_step1_tv_teachPayKnow,
            R2.id.activity_settle_mechanism_teach_pay_step1_next
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_settle_mechanism_teach_pay_step1_iv_institutional_guide) {
            // 机构入驻指南
            Intent intent3 = new Intent(getContext(), H5TitleActivity.class);
            intent3.putExtra(H5TitleActivity.KET_URL,
                    DataLoader.getInstance().getCooperation(AppConfigs.Cooperation.ZH.CHINESE_GUIDE,
                            AppConfigs.Cooperation.EN.ENGLISH_INSTRUCTION));
            startActivity(intent3);
        } else if (view.getId() == R.id.activity_settle_mechanism_teach_pay_step1_ll_mechanismLogo) {
            // 上传机构logo
            selectMechanismLogo();
        } else if (view.getId() == R.id.activity_settle_mechanism_teach_pay_step1_ll_facade_view) {
            // 上传门面图
            selectFacadeView();
        } else if (view.getId() == R.id.activity_settle_mechanism_teach_pay_step1_ll_mechanismLocation) {
            // 上传地址
            selectAddress();
        } else if (view.getId() == R.id.activity_settle_mechanism_teach_pay_step1_tv_teachPayKnow) {
            // 了解教付保
            startActivity(TeachPayIntroduceActivity.class);
        } else if (view.getId() == R.id.activity_settle_mechanism_teach_pay_step1_next) {
            // 完成
            if (checkInput()) {
                nextStep();
            }
        }
    }

    private void nextStep() {
        String mechanismLogo = getMechanismLogo();
        String storeType = getStoreType();
        startActivity(SettleMechanismTeachPayStep2Activity.class,
                new ExtraEntity(SettleMechanismTeachPayStep2Activity.KEY_DATA,
                        new SettleMechanismTeachPayStep1Bean(
                                storeType,
                                mechanismLogo,
                                etMechanismName.getText().toString().trim(),
                                tvMechanismAddress.getText().toString().trim(),
                                aSwitch.isChecked(),
                                longitude,
                                latitude,
                                getFacadeView()
                        )));
    }

    private void selectFacadeView() {
        PhotoSelectUtils.selectPic(this,
                new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(String path) {
                        showUploading();
                        new QiNiuUtils().uploadPic(
                                path,
                                String.valueOf(new Date().getTime()).concat(".png"),
                                new QiNiuUtils.OnUploadListener() {
                                    @Override
                                    public void onUploadFinish(String picUrl) {
                                        closeLoading();
                                        showFacadeView(picUrl);
                                    }

                                    @Override
                                    public void onUploadFail(int code, String error) {
                                        closeLoading();
                                    }
                                });

                    }
                });
    }

    private void showFacadeView(String path) {
        List<String> MechanismLogoList = new ArrayList<>();
        MechanismLogoList.add(path);
        rvFacadeView.setVertical(3);
        rvFacadeView.addGridViewItemDecoration(3, DensityUtils.dp2px(this, 6));
        rvFacadeView.setScrollingEnabled(false);
        mFacadeViewRvAdapter = new PicRvAdapter(R.layout.item_note_img, this, MechanismLogoList);
        rvFacadeView.setAdapter(mFacadeViewRvAdapter);
    }

    private String getStoreType() {
        return storeTypeRvAdapter.getCurStoreTypeName();
    }

    private String getMechanismLogo() {
        String mechanismLogo = "";
        List<String> data = mMechanismLogoRvAdapter.getData();
        if (data != null && data.size() > 0) {
            mechanismLogo = data.get(0);
        }
        return mechanismLogo;
    }

    private String getFacadeView() {
        String facadeView = "";
        List<String> data = mFacadeViewRvAdapter.getData();
        if (data != null && data.size() > 0) {
            facadeView = data.get(0);
        }
        return facadeView;
    }

    @BindView(R2.id.activity_settle_mechanism_teach_pay_step1_rv_store_type)
    RecyclerViewFixed rvStoreType;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step1_rv_mechanismLogo)
    RecyclerViewFixed rvMechanismLogo;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step1_et_mechanismName)
    EditText etMechanismName;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step1_tv_mechanismAddress)
    TextView tvMechanismAddress;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step1_tool_switch)
    Switch aSwitch;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step1_rv_facade_view)
    RecyclerViewFixed rvFacadeView;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Institutional_store_entry));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_settle_mechanism_teach_pay_step_1;
    }

    private void selectMechanismLogo() {
        PhotoSelectUtils.selectPic(this,
                new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(String path) {
                        showUploading();
                        new QiNiuUtils().uploadPic(
                                path,
                                String.valueOf(new Date().getTime()).concat(".png"),
                                new QiNiuUtils.OnUploadListener() {
                                    @Override
                                    public void onUploadFinish(String picUrl) {
                                        closeLoading();
                                        showMechanismLogo(picUrl);
                                    }

                                    @Override
                                    public void onUploadFail(int code, String error) {
                                        closeLoading();
                                    }
                                });

                    }
                });
    }

    private void showMechanismLogo(String path) {
        List<String> MechanismLogoList = new ArrayList<>();
        MechanismLogoList.add(path);
        rvMechanismLogo.setVertical(3);
        rvMechanismLogo.addGridViewItemDecoration(3, DensityUtils.dp2px(this, 6));
        rvMechanismLogo.setScrollingEnabled(false);
        mMechanismLogoRvAdapter = new PicRvAdapter(R.layout.item_note_img, this, MechanismLogoList);
        rvMechanismLogo.setAdapter(mMechanismLogoRvAdapter);
    }

    private void selectAddress() {
        MPermission.with(this).setPermission(new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}).requestPermission(new MPermission.OnCallBack() {
            @Override
            public void valdateSuccess() {
                startActivityForResult(LocationActivity.class, ADDRESS_LOCATION_REQUEST_CODE);
            }

            @Override
            public void valdateFail() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADDRESS_LOCATION_REQUEST_CODE) {
            showAddress(data);
        }
    }

    /**
     * @param data
     * @desc 显示地址
     */
    private void showAddress(@NonNull Intent data) {
        if (data != null) {
            String detailAddress = data.getStringExtra(LocationActivity.KEY_DETAIL_ADDRESS);
            tvMechanismAddress.setText(detailAddress);
            String latlng = data.getStringExtra(LocationActivity.KEY_LATLNG);
            if (!TextUtils.isEmpty(latlng)) {
                String[] latlngArr = latlng.split("-");
                if (latlngArr != null && latlngArr.length == 2) {
                    String l1 = latlngArr[0];
                    String l2 = latlngArr[1];
                    longitude = Double.valueOf(l1);
                    latitude = Double.valueOf(l2);
                }
            }
        }
    }

    private boolean checkInput() {
        if (!isCheckedStoreType()) {
            ToastUtils.showShortToast(this, "请选择门店类型");
            return false;
        }
        if (!hasMechanismLogo()) {
            ToastUtils.showShortToast(this, "请上传机构logo");
            return false;
        }
        if (!hasFacadeView()) {
            ToastUtils.showShortToast(this, "请上传机构门面照");
            return false;
        }
        if (TextUtils.isEmpty(etMechanismName.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请填写机构名");
            return false;
        }
        if (TextUtils.isEmpty(tvMechanismAddress.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择机构地址");
            return false;
        }
        return true;
    }

    @Override
    protected void initData() {
        initStoreTypeRvAdapter();
    }

    private void initStoreTypeRvAdapter() {
        rvStoreType.setVertical(4);
        rvStoreType.addGridViewItemDecoration(4, DensityUtils.dp2px(this, 10));
        rvStoreType.setScrollingEnabled(false);
        String[] storeTypeArr = DataLoader.getInstance().getStoreTypeArr();
        List<String> storeTypeList = ConvertUtils.toList(storeTypeArr);
        storeTypeRvAdapter = new StoreTypeRvAdapter(R.layout.item_mechanism_store_type, this, storeTypeList);
        rvStoreType.setAdapter(storeTypeRvAdapter);
    }


    private boolean isCheckedStoreType() {
        return storeTypeRvAdapter != null && !TextUtils.isEmpty(storeTypeRvAdapter.getCurStoreTypeName());
    }

    private boolean hasMechanismLogo() {
        return mMechanismLogoRvAdapter != null && mMechanismLogoRvAdapter.getItemCount() > 0;
    }

    private boolean hasFacadeView() {
        return mFacadeViewRvAdapter != null && mFacadeViewRvAdapter.getItemCount() > 0;
    }
}
