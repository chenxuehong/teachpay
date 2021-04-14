package com.eghuihe.module_schedule.ui.student.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRatingBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.adapter.SummaryPhotoRvAdapter;
import com.eghuihe.module_schedule.ui.student.mvp.ScheduleCommentContract;
import com.eghuihe.module_schedule.ui.student.mvp.ScheduleCommentPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
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
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 课程评价
 */
@Route(path = ARouterConfig.SCHEDULE_SCHEDULECOMMENTACTIVITY)
public class ScheduleCommentActivity extends BaseMvpTitleActivity<ScheduleCommentPresenter>
        implements ScheduleCommentContract.View {

    @BindView(R2.id.activity_schedule_comment_tv_title)
    TextView tvTitle;
    @BindView(R2.id.activity_schedule_comment_et_content)
    EditText etContent;
    @BindView(R2.id.activity_schedule_comment_rv_photos)
    RecyclerViewFixed rvPhotos;
    @BindView(R2.id.activity_schedule_comment_rating_service)
    AppCompatRatingBar ratingService;
    @BindView(R2.id.activity_schedule_comment_rating_course)
    AppCompatRatingBar ratingCourse;
    @BindView(R2.id.activity_schedule_comment_rating_facilities)
    AppCompatRatingBar ratingFacilitie;
    @BindView(R2.id.activity_schedule_comment_rating_teacher_resources)
    AppCompatRatingBar ratingTeacher;
    @BindView(R2.id.activity_schedule_comment_rating_cost)
    AppCompatRatingBar ratingCost;
    private SummaryPhotoRvAdapter summaryPhotoRvAdapter;
    private StudentScheduleModel.StudentScheduleEntity studentScheduleEntity;

    @OnClick({
            R2.id.activity_schedule_comment_tv_comment
    }
    )
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_schedule_comment_tv_comment) {
            if (checkInput()) {

                List<String> photoList = getPhotoList();
                String photo_url = StringUtils.list2String(photoList, ",");
                StudentScheduleModel.StudentScheduleEntity.Map map = studentScheduleEntity.getMap();
                if (map != null) {
                    MasterMechanismModel.MasterMechanismEntity mechanismEntity = map.getMechanismEntity();
                    if (mechanismEntity != null) {
                        String id = mechanismEntity.getId();
                        float courseRating = ratingCourse.getRating();
                        float facilitieRating = ratingFacilitie.getRating();
                        float teacherRating = ratingTeacher.getRating();
                        float costRating = ratingCost.getRating();
                        float serviceRating = ratingService.getRating();
                        float average_score = courseRating + facilitieRating + teacherRating + costRating + serviceRating;
                        average_score = average_score / 5;
                        getPresenter().masterCommentInsert(
                                studentScheduleEntity.getMaster_set_price_id(),
                                studentScheduleEntity.getAppointment_id(),
                                id,
                                null,
                                etContent.getText().toString().trim(),
                                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                                "teach_paypal_course",
                                photo_url,
                                String.valueOf(ratingCourse.getRating()),
                                String.valueOf(ratingFacilitie.getRating()),
                                String.valueOf(ratingTeacher.getRating()),
                                String.valueOf(ratingCost.getRating()),
                                String.valueOf(ratingService.getRating()),
                                String.valueOf(average_score),
                                studentScheduleEntity.getMaster_id(),
                                studentScheduleEntity.getId()
                        );
                    }
                }

            }
        }
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etContent.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入评价内容");
            return false;
        }
        return true;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("写评价");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_schedule_comment;
    }

    @Override
    protected ScheduleCommentPresenter createPresenter() {
        return new ScheduleCommentPresenter();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(ArgumentsConfig.KEY_STUDENT_SCHEDULE_ENTITY);
            studentScheduleEntity = JsonUtil.fromJson(json, StudentScheduleModel.StudentScheduleEntity.class);
            if (studentScheduleEntity != null)
                tvTitle.setText(studentScheduleEntity.getTitle());
        }
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
                    Intent intent = new Intent(ScheduleCommentActivity.this, PhotoViewActivity.class);
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

                        ToastUtils.showShortToast(ScheduleCommentActivity.this,
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCommentSuccess() {

        ToastUtils.showShortToast(this, "评价成功");
        EventBusUtils.sendEvent(new Event(EventAction.STIDENT_COMMENT_SUCCESS));
        finish();
    }
}
