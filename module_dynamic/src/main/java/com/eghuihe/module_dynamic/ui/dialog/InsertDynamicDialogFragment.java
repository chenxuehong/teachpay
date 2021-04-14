package com.eghuihe.module_dynamic.ui.dialog;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.R2;
import com.eghuihe.module_dynamic.ui.activity.TeachPayStudentSelectSchedulePackageActivity;
import com.eghuihe.module_dynamic.ui.mvp.DynamicInsertContract;
import com.eghuihe.module_dynamic.ui.mvp.DynamicInsertPresenter;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.adapter.PicRvAdapter;
import com.huihe.base_lib.ui.dialog.BaseMvpDialogFragment;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.QiNiuUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InsertDynamicDialogFragment extends BaseMvpDialogFragment<DynamicInsertPresenter>
        implements PicRvAdapter.OnListener, DynamicInsertContract.View {

    private static final int REQUEST_CODE_SELECT_COURSE = 1001;
    @BindView(R2.id.dialog_insert_dynamic_et_content)
    EditText etContent;
    @BindView(R2.id.dialog_insert_dynamic_rv_pics)
    RecyclerViewFixed rvPics;
    @BindView(R2.id.dialog_insert_dynamic_ll)
    LinearLayout llDynamic;
    @BindView(R2.id.dialog_insert_dynamic_tv_selectCourse)
    TextView tvSelectCourse;

    private PicRvAdapter picRvAdapter;
    private boolean isVideo;
    private CustomPopupWindow customPopupWindow;
    private MasterSetPriceEntity masterSetPriceEntity;
    private String master_set_price_id;
    private String categories;

    @OnClick({
            R2.id.dialog_insert_dynamic_tv_cancel,
            R2.id.dialog_insert_dynamic_tv_fuba,
            R2.id.dialog_insert_dynamic_tv_selectCourse
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.dialog_insert_dynamic_tv_cancel) {
            dismiss();
        } else if (view.getId() == R.id.dialog_insert_dynamic_tv_fuba) {
            if (checkInput())
                insertDynamic();
        } else if (view.getId() == R.id.dialog_insert_dynamic_tv_selectCourse) {
            selectCourse();
        }
    }

    private void selectCourse() {

        startActivityForResult(new Intent(getContext(), TeachPayStudentSelectSchedulePackageActivity.class), REQUEST_CODE_SELECT_COURSE);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etContent.getText().toString().trim())) {
            ToastUtils.showShortToast(getContext(), "请输入内容");
            return false;
        }
        return true;
    }

    private void insertDynamic() {
        if (masterSetPriceEntity != null) {
            master_set_price_id = masterSetPriceEntity.getId();
            categories = masterSetPriceEntity.getCategories();
        }
        getPresenter().noteUserInsert(
                etContent.getText().toString().trim(),
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                1,
                getPics(),
                isVideo ? 0 : 1,
                categories,
                master_set_price_id
        );
    }

    private String getPics() {
        if (picRvAdapter != null) {
            List<String> data = picRvAdapter.getData();
            List<String> picList = new ArrayList<>();
            if (data != null) {
                for (int i = 0; i < data.size(); i++) {
                    String path = data.get(i);
                    if (!PicRvAdapter.UPLOAD.equals(path)) {
                        picList.add(path);
                    }
                }
            }
            return StringUtils.list2String(picList, ",");
        }
        return "";
    }

    @Override
    protected DynamicInsertPresenter createPresenter() {
        return new DynamicInsertPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_insert_dynamic;
    }

    @Override
    public void uploadPic(String path) {
        isVideo = false;
        PhotoSelectUtils.selectMutiPicOrVideo(
                this,
                llDynamic,
                new PhotoSelectUtils.OnCallBack() {

                    @Override
                    protected void updateLoadImage(List<String> picList) {
                        isVideo = false;
                        List<String> realPicOrVideoList = new ArrayList<>();
                        realPicOrVideoList.addAll(picList);
                        upLoadMutiVideoOrPic(realPicOrVideoList);
                    }

                    @Override
                    protected void updateLoadVideo(List<String> videoList) {
                        isVideo = true;
                        List<String> realPicOrVideoList = new ArrayList<>();
                        realPicOrVideoList.addAll(videoList);
                        upLoadMutiVideoOrPic(realPicOrVideoList);
                    }

                    @Override
                    protected void updateLoadVideo(String path) {
                        isVideo = true;
                        List<String> realPicOrVideoList = new ArrayList<>();
                        realPicOrVideoList.add(path);
                        upLoadMutiVideoOrPic(realPicOrVideoList);
                    }
                }
        );
    }

    private void upLoadMutiVideoOrPic(List<String> realPicOrVideoList) {
        showUploading();
        if (!isVideo) {
            upLoadMutiPic(realPicOrVideoList);
        } else {
            upLoadMutivideo(realPicOrVideoList);
        }
    }

    private void upLoadMutiPic(List<String> realPicOrVideoList) {
        final List<String> commitImgs = new ArrayList<>();
        new QiNiuUtils().loadMutiPic(0, realPicOrVideoList, new QiNiuUtils.OnUpMutiloadListener() {

            @Override
            public void loadFinish() {
                StringBuffer imgsSb = new StringBuffer();
                if (commitImgs != null && commitImgs.size() > 0) {
                    for (int i = 0; i < commitImgs.size(); i++) {
                        if (i == commitImgs.size() - 1) {
                            imgsSb.append(commitImgs.get(i));
                        } else {
                            imgsSb.append(commitImgs.get(i)).append(",");
                        }
                    }
                }
                if (picRvAdapter != null) {
                    picRvAdapter.setVideo(isVideo);
                    commitImgs.add(PicRvAdapter.UPLOAD);
                    picRvAdapter.setData(commitImgs);
                }
                closeLoading();
            }

            @Override
            public void loadPic(int position, String path) {
                if (commitImgs != null) {
                    commitImgs.add(path);
                }
            }

            @Override
            public void loadError(int position) {

            }
        });
    }

    private void upLoadMutivideo(List<String> realPicOrVideoList) {
        final List<String> commitImgs = new ArrayList<>();
        new QiNiuUtils().upLoadMutiVideo(0, realPicOrVideoList, new QiNiuUtils.OnUpMutiloadListener() {

            @Override
            public void loadFinish() {
                StringBuffer imgsSb = new StringBuffer();
                if (commitImgs != null && commitImgs.size() > 0) {
                    for (int i = 0; i < commitImgs.size(); i++) {
                        if (i == commitImgs.size() - 1) {
                            imgsSb.append(commitImgs.get(i));
                        } else {
                            imgsSb.append(commitImgs.get(i)).append(",");
                        }
                    }
                }
                if (picRvAdapter != null) {
                    picRvAdapter.setVideo(isVideo);
                    commitImgs.add(PicRvAdapter.UPLOAD);
                    picRvAdapter.setData(commitImgs);

                }
                closeLoading();
            }

            @Override
            public void loadPic(int position, String path) {
                if (commitImgs != null) {
                    commitImgs.add(path);
                }
            }

            @Override
            public void loadError(int position) {

            }
        });
    }

    @Override
    protected void initView() {
        super.initView();
        initPicRvAdapter();
    }

    private void initPicRvAdapter() {
        rvPics.setVertical(4);
        rvPics.setScrollingEnabled(false);
        rvPics.addGridViewItemDecoration(4, DensityUtils.dp2px(getContext(), 12));
        List<String> picList = new ArrayList<>();
        picList.add(PicRvAdapter.UPLOAD);
        picRvAdapter = new PicRvAdapter(R.layout.item_img, getContext(), picList, this);
        rvPics.setAdapter(picRvAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_SELECT_COURSE == requestCode) {
            // 选择课程
            if (data != null) {
                String json = data.getStringExtra(ArgumentsConfig.KEY_STUDENTCOURSEPACKAGEENTITY);
                StudentCoursePackageEntity studentCoursePackageEntity = JsonUtil.fromJson(json, StudentCoursePackageEntity.class);
                StudentCoursePackageEntity.Map map = studentCoursePackageEntity.getMap();
                if (map != null) {
                    masterSetPriceEntity = map.getMasterSetPriceEntity();
                    if (masterSetPriceEntity != null) {
                        String title = masterSetPriceEntity.getTitle();
                        tvSelectCourse.setText(title);
                    }
                }
            }
        } else {
            PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onInsertSuccess() {
        ToastUtils.showSuccess("发布成功");
        EventBusUtils.sendEvent(new Event(EventAction.DYNAMIC_LIST_FRESH));
        dismiss();
    }

    @Override
    public void onDestroy() {
        try {
            customPopupWindow = PhotoSelectUtils.getCustomPopupWindow();
            if (customPopupWindow != null) {
                customPopupWindow.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
