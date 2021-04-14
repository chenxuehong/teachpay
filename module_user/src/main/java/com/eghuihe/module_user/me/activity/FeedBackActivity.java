package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.activity.PhotoViewActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.QiNiuUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.amin.AnimationConstants;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FeedBackActivity extends BaseTitleActivity {

    @BindView(R2.id.feedback_et_content)
    EditText etFeedBackContent;
    @BindView(R2.id.feedback_rv_imgs)
    RecyclerViewFixed rvImgs;
    private List<String> imgs;
    private LoginResultEntity loginResultEntity;
    private List<String> qnImgs;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Feedback));
        customerTitle.setRightText(getResources().getString(R.string.commit));
        customerTitle.setRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 提交
                if (TextUtils.isEmpty(etFeedBackContent.getText().toString())) {
                    ToastUtils.showShortToast(FeedBackActivity.this, getResources().getString(R.string.please_enter_what_feedback));
                    return;
                }
                imgs.remove("upload");
                qnImgs = new ArrayList<>();
                showUploading();
                new QiNiuUtils().loadMutiPic(0, imgs, new QiNiuUtils.OnUpMutiloadListener() {
                    @Override
                    public void loadFinish() {
                        StringBuffer imgsSb = new StringBuffer();
                        if (qnImgs != null && qnImgs.size() > 0) {
                            for (int i = 0; i < qnImgs.size(); i++) {
                                if (i == qnImgs.size() - 1) {
                                    imgsSb.append(qnImgs.get(i));
                                } else {
                                    imgsSb.append(qnImgs.get(i)).append(",");
                                }
                            }
                        }
                        UserServiceImpl.insertSystemFeedBack(loginResultEntity.getUserInfoEntity().getUser_id(), loginResultEntity.getUserToken(),
                                etFeedBackContent.getText().toString(), imgsSb.toString(), new NetworkSubscriber<InsertInfoResultModel>(null) {

                                    @Override
                                    protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                        ToastUtils.showShortToast(FeedBackActivity.this, "success");
                                        closeLoading();
                                        finish();
                                    }

                                    @Override
                                    public void onComplete() {
                                        closeLoading();
                                    }
                                });
                    }

                    @Override
                    public void loadPic(int position, String path) {
                        if (qnImgs != null)
                            qnImgs.add(path);
                    }

                    @Override
                    public void loadError(int position) {
                    }
                });
            }
        });
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initData() {
        loginResultEntity = LoginHelper.getLoginInfo();
        imgs = new ArrayList<>();
        imgs.add("upload");
        rvImgs.setVertical(3);
        rvImgs.addGridViewItemDecoration(3, 24, 0);
        rvImgs.setAdapter(new CommonRVAdapter<String>(R.layout.item_note_img, this, imgs) {

            @Override
            protected void covert(ViewHolder viewHolder, String path, int position) {

                if ("upload".equals(path)) {
                    viewHolder.getView(R.id.item_note_img_icon_delete).setVisibility(View.GONE);
                    viewHolder.setImageResource(R.id.item_note_img_icon, R.mipmap.note_upload_photo);
                    viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
                        @Override
                        public void onNoDoubleClick(View v) {

                            PhotoSelectUtils.selectMutiPic(FeedBackActivity.this, new PhotoSelectUtils.OnCallBack() {

                                @Override
                                protected void updateLoadImage(List<String> picList) {

                                    imgs.addAll(imgs.size()-1,picList);
                                    notifyDataSetChanged();
                                }

                                @Override
                                protected void updateLoadImage(String path) {
                                    imgs.add(imgs.size()-1,path);
                                    notifyDataSetChanged();
                                }
                            });
                        }
                    });
                } else {
                    viewHolder.getView(R.id.item_note_img_icon_delete).setVisibility(View.VISIBLE);
                    RoundedImageView ivIcon = viewHolder.getView(R.id.item_note_img_icon);
                    GlideTools.loadImage(context, path, ivIcon);
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PhotoViewActivity.class);
                            intent.putExtra(PhotoViewActivity.CURRENT_POSITION, position);
                            List<String> data = getData();
                            List<String> picList = new ArrayList<>();
                            picList.addAll(data);
                            picList.remove("upload");
                            intent.putExtra(PhotoViewActivity.URLS, JsonUtil.toJson(picList));

                            int[] location = new int[2];
                            v.getLocationOnScreen(location);
                            int centerX = (int) (location[0] + v.getMeasuredWidth() / 2);
                            int centery = (int) (location[1] + v.getMeasuredHeight() / 2);
                            intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTX, centerX);
                            intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTY, centery);
                            intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_ENABLE, true);
                            ((BaseActivity) context).startActivity(intent);
                        }
                    });
                    viewHolder.setOnClickListener(R.id.item_note_img_icon_delete, new OnDoubleClickListener() {
                        @Override
                        public void onNoDoubleClick(View v) {
                            imgs.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
    }
}
