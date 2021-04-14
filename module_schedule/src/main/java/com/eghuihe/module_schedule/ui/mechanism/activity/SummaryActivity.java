package com.eghuihe.module_schedule.ui.mechanism.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.adapter.SummaryPhotoRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.mvp.SummaryContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.SummaryPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.activity.PhotoViewActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.QiNiuUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.amin.AnimationConstants;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARouterConfig.SCHEDULE_SUMMARYACTIVITY)
public class SummaryActivity extends BaseMvpTitleActivity<SummaryPresenter>
        implements SummaryContract.View {

    private MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity;

    @BindView(R2.id.activity_summary_et_content)
    EditText etContent;
    @BindView(R2.id.activity_summary_rv_photos)
    RecyclerViewFixed rvPhotos;
    private SummaryPhotoRvAdapter summaryPhotoRvAdapter;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("课程总结");
        customerTitle.setRightText("提交");
        customerTitle.setRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 提交
                if (checkInput()) {
                    List<String> photoList = getPhotoList();
                    String photo_url = StringUtils.list2String(photoList, ",");
                    getPresenter().insertSummaryOffline(
                            mechanismOfflineScheduleEntity.getId(),
                            etContent.getText().toString().trim(),
                            mechanismOfflineScheduleEntity.getMaster_id(),
                            mechanismOfflineScheduleEntity.getMechanism_id(),
                            photo_url
                    );
                }
            }
        });
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etContent.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入总结内容");
            return false;
        }
        return true;
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_summary;
    }

    @Override
    protected SummaryPresenter createPresenter() {
        return new SummaryPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(ArgumentsConfig.KEY_MECHANISMOFFLINESCHEDULEENTITY);
            mechanismOfflineScheduleEntity = JsonUtil.fromJson(json, MechanismOfflineScheduleEntity.class);
        }
    }

    @Override
    protected void initData() {

        iniPhotoRvAdapter();
    }

    private void iniPhotoRvAdapter() {
        rvPhotos.setVertical(3);
        rvPhotos.addGridViewItemDecoration(3, DensityUtils.dp2px(this, 12));
        List<String> photosList = new ArrayList<>();
        photosList.add(SummaryPhotoRvAdapter.UPLOAD);
        summaryPhotoRvAdapter = new SummaryPhotoRvAdapter(R.layout.item_upload_img, this, photosList);
        summaryPhotoRvAdapter.setOnItemClickListener(new CommonRVAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClicked(View v, String photo, int position) {
                if (SummaryPhotoRvAdapter.UPLOAD.equals(photo)) {
                    selectPhoto();
                } else {
                    Intent intent = new Intent(SummaryActivity.this, PhotoViewActivity.class);
                    intent.putExtra(PhotoViewActivity.CURRENT_POSITION, position);
                    List<String> data = summaryPhotoRvAdapter.getData();
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
        rvPhotos.setAdapter(summaryPhotoRvAdapter);
    }

    private void selectPhoto() {
        PhotoSelectUtils.selectMutiPic(this,
                new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(List<String> supportMeansList) {
                        uploadPhoto(supportMeansList);
                    }

                    @Override
                    protected void updateLoadImage(String path) {
                        List<String> supportMeansList = new ArrayList<>();
                        supportMeansList.add(path);
                        uploadPhoto(supportMeansList);
                    }
                });
    }

    private void uploadPhoto(List<String> photoList) {
        showUploading();
        final List<String> supportMeansList = new ArrayList<>();
        new QiNiuUtils().loadMutiPic(
                0,
                photoList,
                new QiNiuUtils.OnUpMutiloadListener() {
                    @Override
                    public void loadFinish() {

                        closeLoading();
                        showPhotoList(supportMeansList);
                    }

                    @Override
                    public void loadPic(int position, String path) {
                        if (supportMeansList != null)
                            supportMeansList.add(path);
                    }

                    @Override
                    public void loadError(int position) {

                        ToastUtils.showShortToast(SummaryActivity.this,
                                "第".concat(String.valueOf(position)).concat("张").concat("上传失败!"));
                    }
                }
        );
    }

    private void showPhotoList(List<String> supportMeansList) {
        if (summaryPhotoRvAdapter != null) {
            summaryPhotoRvAdapter.addData(supportMeansList, summaryPhotoRvAdapter.getItemCount() - 1);
        }
    }

    private List<String> getPhotoList() {
        List<String> data = new ArrayList<>();
        if (summaryPhotoRvAdapter != null) {
            List<String> photos = summaryPhotoRvAdapter.getData();
            if (photos != null) {
                for (int i = 0; i < photos.size(); i++) {
                    if (!SummaryPhotoRvAdapter.UPLOAD.equals(photos.get(i))) {
                        data.add(photos.get(i));
                    }
                }

            }
        }
        return data;
    }

    @Override
    public void onSuccess() {
        ToastUtils.showShortToast(this, "提交成功");
        EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_SUMMARY_SUCCESS));
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
    }
}
