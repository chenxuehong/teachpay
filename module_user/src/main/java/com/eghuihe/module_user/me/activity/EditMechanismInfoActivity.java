package com.eghuihe.module_user.me.activity;

import android.Manifest;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.ItemBeanView;
import com.eghuihe.module_user.me.adapter.PicRvAdapter;
import com.eghuihe.module_user.me.mvp.EditMechanismInfoContract;
import com.eghuihe.module_user.me.mvp.EditMechanismInfoPresenter;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.activity.LocationActivity;
import com.huihe.base_lib.ui.widget.picker.MultiplePicker;
import com.huihe.base_lib.ui.widget.picker.SinglePicker;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ConvertUtils;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.QiNiuUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class EditMechanismInfoActivity extends BaseMvpTitleActivity<EditMechanismInfoPresenter>
        implements EditMechanismInfoContract.View {

    private static final int ADDRESS_LOCATION_REQUEST_CODE = 100;
    @BindView(R2.id.edit_mechanism_info_itemBeanView_category)
    ItemBeanView itemBeanViewCategory;
    @BindView(R2.id.edit_mechanism_info_itemBeanView_categories_child)
    ItemBeanView itemBeanViewCategoriesChild;
    @BindView(R2.id.edit_mechanism_info_ll_categories_child)
    LinearLayout llCategoriesChild;
    @BindView(R2.id.edit_mechanism_info_et_categories_child)
    EditText etCategoriesChild;
    @BindView(R2.id.edit_mechanism_info_et_contacts)
    EditText etContracts;
    @BindView(R2.id.edit_mechanism_info_et_contact_telephone)
    EditText etContractTelephone;
    @BindView(R2.id.edit_mechanism_info_rv_mechanism_logo)
    RecyclerViewFixed rvMechanismLogo;
    @BindView(R2.id.edit_mechanism_info_rv_facade_view)
    RecyclerViewFixed rvFacadeView;
    @BindView(R2.id.edit_mechanism_info_et_mechanism_name)
    EditText etMechanismName;
    @BindView(R2.id.edit_mechanism_info_itemBeanView_mechanism_addr)
    ItemBeanView itemBeanViewMechanismAddr;
    @BindView(R2.id.edit_mechanism_info_et_mechanism_telephone)
    EditText etMechanismTelephone;
    @BindView(R2.id.edit_mechanism_info_et_introduction_content)
    EditText etIntroductionContent;
    @BindView(R2.id.edit_mechanism_info_et_introduction_pic)
    RecyclerViewFixed rvIntroductionPic;
    @BindView(R2.id.edit_mechanism_info_rv_support_means)
    RecyclerViewFixed rvSupportMeans;
    private PicRvAdapter mMechanismLogoRvAdapter;
    private PicRvAdapter mFacadeViewRvAdapter;
    private PicRvAdapter mIntroductionPiRvAdapter;
    private PicRvAdapter mSupportMeansRvAdapter;
    private double longitude;
    private double latitude;
    private SinglePicker categoryPicker;
    private MultiplePicker mechanismTradeModelPicker;

    @OnClick({
            R2.id.edit_mechanism_info_itemBeanView_category,
            R2.id.edit_mechanism_info_itemBeanView_categories_child,
            R2.id.edit_mechanism_info_ll_mechanism_logo,
            R2.id.edit_mechanism_info_ll_facade_view,
            R2.id.edit_mechanism_info_itemBeanView_mechanism_addr,
            R2.id.edit_mechanism_info_ll_introduction_pic,
            R2.id.edit_mechanism_info_ll_support_means,
            R2.id.edit_mechanism_info_tv_commit
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.edit_mechanism_info_itemBeanView_category) {
            selectMechanismCategory();
        } else if (view.getId() == R.id.edit_mechanism_info_itemBeanView_categories_child) {
            if (TextUtils.isEmpty(itemBeanViewCategory.getRightTitle())) {
                ToastUtils.showShortToast(this, "请先选择类目");
                return;
            }
            selectCategoriesChild();
        } else if (view.getId() == R.id.edit_mechanism_info_ll_mechanism_logo) {
            selectMechanismLogo();
        } else if (view.getId() == R.id.edit_mechanism_info_ll_facade_view) {
            selectFacadeView();
        } else if (view.getId() == R.id.edit_mechanism_info_itemBeanView_mechanism_addr) {
            selectMechanismAddr();
        } else if (view.getId() == R.id.edit_mechanism_info_ll_introduction_pic) {
            selectIntroductionPic();
        } else if (view.getId() == R.id.edit_mechanism_info_ll_support_means) {
            selectSupportMeans();
        } else if (view.getId() == R.id.edit_mechanism_info_tv_commit) {
            if (checkInput()) {
                commitData();
            }
        }
    }

    private void selectMechanismCategory() {
        String[] storeTypeArr = DataLoader.getInstance().getStoreTypeArr();
        categoryPicker = new SinglePicker(
                this,
                storeTypeArr);
        categoryPicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                itemBeanViewCategory.setRightTitle(item);
                String[] categoriesChildArr = DataLoader.getInstance().getCategoriesChildArr(item);
                if (categoriesChildArr == null || categoriesChildArr.length == 0) {
                    itemBeanViewCategoriesChild.setVisibility(View.GONE);
                    llCategoriesChild.setVisibility(View.VISIBLE);
                } else {
                    itemBeanViewCategoriesChild.setRightTitle("");
                    itemBeanViewCategoriesChild.setRightHintTitle("请选择经营类型");
                    itemBeanViewCategoriesChild.setVisibility(View.VISIBLE);
                    llCategoriesChild.setVisibility(View.GONE);
                }
            }
        });
        categoryPicker.show();
    }

    private void selectCategoriesChild() {
        String[] categoriesChildArr = DataLoader.getInstance().getCategoriesChildArr(itemBeanViewCategory.getRightTitle());
        mechanismTradeModelPicker = new MultiplePicker(
                this,
                categoriesChildArr);
        mechanismTradeModelPicker.setOnItemPickListener(new MultiplePicker.OnItemPickListener() {
            @Override
            public void onItemPicked(int count, List<String> items) {
                String string = StringUtils.list2String(items, "/");
                itemBeanViewCategoriesChild.setRightTitle(string);
            }
        });
        mechanismTradeModelPicker.show();
    }

    private void selectMechanismLogo() {

        PhotoSelectUtils.selectPic(this,
                new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(String path) {
                        showUploading();
                        new QiNiuUtils().uploadPic(path, Long.valueOf((new Date()).getTime()) + ".jpg", new QiNiuUtils.OnUploadListener() {


                            @Override
                            public void onUploadFinish(String picUrl) {
                                closeLoading();
                                List<String> list = new ArrayList<>();
                                list.add(picUrl);
                                showMechanismLogo(list);
                            }

                            @Override
                            public void onUploadFail(int code, String error) {
                                closeLoading();
                            }
                        });
                    }
                });
    }

    private void showMechanismLogo(List<String> MechanismLogoList) {
        mMechanismLogoRvAdapter = new PicRvAdapter(R.layout.item_note_img, this, MechanismLogoList);
        rvMechanismLogo.setVertical(3);
        rvMechanismLogo.addGridViewItemDecoration(3, DensityUtils.dp2px(this, 6));
        rvMechanismLogo.setScrollingEnabled(false);
        rvMechanismLogo.setAdapter(mMechanismLogoRvAdapter);
    }

    private boolean hasMechanismLogo() {
        return mMechanismLogoRvAdapter != null && mMechanismLogoRvAdapter.getItemCount() > 0;
    }

    private void selectFacadeView() {
        PhotoSelectUtils.selectPic(this,
                new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(String path) {
                        showUploading();
                        new QiNiuUtils().uploadPic(path, Long.valueOf((new Date()).getTime()) + ".jpg", new QiNiuUtils.OnUploadListener() {
                            @Override
                            public void onUploadFinish(String picUrl) {
                                closeLoading();
                                List<String> list = new ArrayList<>();
                                list.add(picUrl);
                                showFacadeView(list);
                            }

                            @Override
                            public void onUploadFail(int code, String error) {
                                closeLoading();
                            }
                        });
                    }
                });
    }

    private void showFacadeView(List<String> FacadeViewList) {
        mFacadeViewRvAdapter = new PicRvAdapter(R.layout.item_note_img, this, FacadeViewList);
        rvFacadeView.setVertical(3);
        rvFacadeView.addGridViewItemDecoration(3, DensityUtils.dp2px(this, 6));
        rvFacadeView.setScrollingEnabled(false);
        rvFacadeView.setAdapter(mFacadeViewRvAdapter);
    }

    private boolean hasFacadeView() {
        return mFacadeViewRvAdapter != null && mFacadeViewRvAdapter.getItemCount() > 0;
    }

    private void selectMechanismAddr() {
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
        super.onActivityResult(requestCode, resultCode, data);
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADDRESS_LOCATION_REQUEST_CODE) {
            showAddress(data);
        }
    }

    private void showAddress(Intent data) {
        if (data != null) {
            String detailAddress = data.getStringExtra(LocationActivity.KEY_DETAIL_ADDRESS);
            itemBeanViewMechanismAddr.setRightTitle(detailAddress);
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

    private void selectIntroductionPic() {
        PhotoSelectUtils.selectMutiPic(this,
                new PhotoSelectUtils.OnCallBack() {

                    @Override
                    protected void updateLoadImage(List<String> picList) {
                        showUploading();
                        final List<String> commitList = new ArrayList<>();
                        new QiNiuUtils().loadMutiPic(0, picList, new QiNiuUtils.OnUpMutiloadListener() {
                            @Override
                            public void loadFinish() {
                                closeLoading();
                                showIntroductionPic(commitList);
                            }

                            @Override
                            public void loadPic(int position, String path) {
                                if (commitList != null)
                                    commitList.add(path);
                            }

                            @Override
                            public void loadError(int position) {

                            }
                        });
                    }
                });
    }

    private void showIntroductionPic(List<String> IntroductionList) {
        mIntroductionPiRvAdapter = new PicRvAdapter(R.layout.item_note_img, this, IntroductionList);
        rvIntroductionPic.setVertical(3);
        rvIntroductionPic.addGridViewItemDecoration(3, DensityUtils.dp2px(this, 6));
        rvIntroductionPic.setScrollingEnabled(false);
        rvIntroductionPic.setAdapter(mIntroductionPiRvAdapter);
    }

    private boolean hasIntroductionPic() {
        return mIntroductionPiRvAdapter != null && mIntroductionPiRvAdapter.getItemCount() > 0;
    }

    private void selectSupportMeans() {
        PhotoSelectUtils.selectMutiPic(this,
                new PhotoSelectUtils.OnCallBack() {

                    @Override
                    protected void updateLoadImage(List<String> picList) {
                        showUploading();
                        final List<String> commitList = new ArrayList<>();
                        new QiNiuUtils().loadMutiPic(0, picList, new QiNiuUtils.OnUpMutiloadListener() {
                            @Override
                            public void loadFinish() {
                                closeLoading();
                                showSupportMeans(commitList);
                            }

                            @Override
                            public void loadPic(int position, String path) {
                                if (commitList != null)
                                    commitList.add(path);
                            }

                            @Override
                            public void loadError(int position) {

                            }
                        });
                    }
                });
    }

    private void showSupportMeans(List<String> SupportMeanList) {
        mSupportMeansRvAdapter = new PicRvAdapter(R.layout.item_note_img, this, SupportMeanList);
        rvSupportMeans.setVertical(3);
        rvSupportMeans.addGridViewItemDecoration(3, DensityUtils.dp2px(this, 6));
        rvSupportMeans.setScrollingEnabled(false);
        rvSupportMeans.setAdapter(mSupportMeansRvAdapter);
    }

    private boolean hasSupportMeans() {
        return mSupportMeansRvAdapter != null && mSupportMeansRvAdapter.getItemCount() > 0;
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(itemBeanViewCategory.getRightTitle())) {
            ToastUtils.showShortToast(this, "请选择类目");
            return false;
        }
        if (itemBeanViewCategoriesChild.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(itemBeanViewCategoriesChild.getRightTitle())) {
            ToastUtils.showShortToast(this, "请选择经营类别");
            return false;
        }
        if (llCategoriesChild.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(etCategoriesChild.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入经营类别");
            return false;
        }
        if (TextUtils.isEmpty(etContracts.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入联系人姓名");
            return false;
        }
        if (TextUtils.isEmpty(etContractTelephone.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入联系人电话");
            return false;
        }
        if (!hasMechanismLogo()) {
            ToastUtils.showShortToast(this, "请上传机构Logo");
            return false;
        }
        if (!hasFacadeView()) {
            ToastUtils.showShortToast(this, "请上传机构门面照");
            return false;
        }
        if (TextUtils.isEmpty(etMechanismName.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入机构名");
            return false;
        }
        if (TextUtils.isEmpty(itemBeanViewMechanismAddr.getRightTitle())) {
            ToastUtils.showShortToast(this, "请选择机构地址");
            return false;
        }
        if (TextUtils.isEmpty(etMechanismTelephone.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入联系电话");
            return false;
        }
        if (TextUtils.isEmpty(etIntroductionContent.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入机构简介");
            return false;
        }
        if (!hasIntroductionPic()) {
            ToastUtils.showShortToast(this, "请上传图片简介");
            return false;
        }
        if (!hasSupportMeans()) {
            ToastUtils.showShortToast(this, "请上传相关材料证明");
            return false;
        }
        return true;
    }

    private String getChildCategory() {
        if (itemBeanViewCategoriesChild.getVisibility() == View.VISIBLE) {
            return itemBeanViewCategoriesChild.getRightTitle();
        }
        if (llCategoriesChild.getVisibility() == View.VISIBLE) {
            return etCategoriesChild.getText().toString().trim();
        }
        return "";
    }

    private void commitData() {
        List<String> mechanismLogoList = mMechanismLogoRvAdapter.getData();
        String mechanismLogo = StringUtils.list2String(mechanismLogoList, ",");
        List<String> IntroductionPicList = mIntroductionPiRvAdapter.getData();
        String introductionPic = StringUtils.list2String(IntroductionPicList, ",");
        List<String> supportMeansList = mSupportMeansRvAdapter.getData();
        String supportMeans = StringUtils.list2String(supportMeansList, ",");
        List<String> facadeViewList = mFacadeViewRvAdapter.getData();
        String facadeView = StringUtils.list2String(facadeViewList, ",");
        String childCategory = getChildCategory();
        getPresenter().updateMasterMechanism(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                itemBeanViewCategory.getRightTitle(),
                childCategory,
                mechanismLogo,
                etMechanismName.getText().toString().trim(),
                itemBeanViewMechanismAddr.getRightTitle(),
                longitude,
                latitude,
                etMechanismTelephone.getText().toString().trim(),
                etContractTelephone.getText().toString().trim(),
                etContracts.getText().toString().trim(),
                introductionPic,
                etIntroductionContent.getText().toString().trim(),
                supportMeans,
                facadeView
        );
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("修改");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_edit_mechanism_info;
    }

    @Override
    protected EditMechanismInfoPresenter createPresenter() {
        return new EditMechanismInfoPresenter();
    }

    @Override
    protected void initData() {

        getPresenter().queryMechanismInfo(
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                null,
                "teach_paypal"
        );

    }

    @Override
    public void onMechanismInfo(MasterMechanismModel.MasterMechanismEntity masterMechanismEntity) {
        String categories = masterMechanismEntity.getCategories();
        String categories_child = masterMechanismEntity.getCategories_child();
        String mechanism_logo = masterMechanismEntity.getMechanism_logo();
        String mechanism_name = masterMechanismEntity.getMechanism_name();
        String mechanism_addr = masterMechanismEntity.getMechanism_addr();
        longitude = masterMechanismEntity.getLongitude();
        latitude = masterMechanismEntity.getLatitude();
        String mechanism_telephone = masterMechanismEntity.getMechanism_telephone();
        String contact_telephone = masterMechanismEntity.getContact_telephone();
        String contacts = masterMechanismEntity.getContacts();
        String introduction_pic = masterMechanismEntity.getIntroduction_pic();
        String introduction_content = masterMechanismEntity.getIntroduction_content();
        String support_means = masterMechanismEntity.getSupport_means();
        String facade_view = masterMechanismEntity.getFacade_view();
        itemBeanViewCategory.setRightTitle(categories);
        itemBeanViewCategoriesChild.setRightTitle(categories_child);

        String[] categoriesChildArr = DataLoader.getInstance().getCategoriesChildArr(categories);
        if (categoriesChildArr != null && categoriesChildArr.length > 0) {
            llCategoriesChild.setVisibility(View.GONE);
            itemBeanViewCategoriesChild.setVisibility(View.VISIBLE);
        } else {
            llCategoriesChild.setVisibility(View.VISIBLE);
            etCategoriesChild.setText(categories_child);
            itemBeanViewCategoriesChild.setVisibility(View.GONE);
        }
        etContracts.setText(contacts);
        etContractTelephone.setText(contact_telephone);
        if (!TextUtils.isEmpty(mechanism_logo)) {
            String[] split = mechanism_logo.split(",");
            if (split != null) {
                showMechanismLogo(ConvertUtils.toList(split));
            }
        }
        if (!TextUtils.isEmpty(facade_view)) {
            String[] split = facade_view.split(",");
            if (split != null) {
                showFacadeView(ConvertUtils.toList(split));
            }
        }
        etMechanismName.setText(mechanism_name);
        itemBeanViewMechanismAddr.setRightTitle(mechanism_addr);
        etMechanismTelephone.setText(mechanism_telephone);
        etIntroductionContent.setText(introduction_content);
        if (!TextUtils.isEmpty(introduction_pic)) {
            String[] split = introduction_pic.split(",");
            if (split != null) {
                showIntroductionPic(ConvertUtils.toList(split));
            }
        }
        if (!TextUtils.isEmpty(support_means)) {
            String[] split = support_means.split(",");
            if (split != null) {
                showSupportMeans(ConvertUtils.toList(split));
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        try {
            if (categoryPicker != null) {
                categoryPicker.dismiss();
            }
            if (mechanismTradeModelPicker != null) {
                mechanismTradeModelPicker.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onUpdateSuccess() {
        EventBusUtils.sendEvent(new Event(EventAction.TEACH_PAY_UPDATE_MECHANISMINFO));
        ToastUtils.showShortToast(this, "修改成功");
        finish();
    }

}
