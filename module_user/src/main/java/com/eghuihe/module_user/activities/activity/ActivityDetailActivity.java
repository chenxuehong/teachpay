package com.eghuihe.module_user.activities.activity;

import android.content.Intent;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ConvertUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 活动详情
 */
public class ActivityDetailActivity extends BaseTitleActivity {

    private BusinessActivityTypeEntity businessActivityTypeEntity;

    @BindView(R2.id.activity_activity_detail_iv_cover)
    RoundedImageView ivCover;
    @BindView(R2.id.activity_activity_detail_rv)
    RecyclerViewFixed rvList;

    @OnClick({
            R2.id.activity_activity_detail_tv_signUp
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.activity_activity_detail_tv_signUp) {
            Intent intent = new Intent(this,SelectActivityCourseActivity.class);
            intent.putExtra(ArgumentsConfig.KEY_BUSINESS_ACTIVITY_TYPE,JsonUtil.toJson(businessActivityTypeEntity));
            startActivity(intent);
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        super.initTitle(customerTitle);
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(ArgumentsConfig.KEY_BUSINESS_ACTIVITY_TYPE);
            businessActivityTypeEntity = JsonUtil.fromJson(json, BusinessActivityTypeEntity.class);
            customerTitle.setTitle(businessActivityTypeEntity.getTitle());
        }
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_activity_detail;
    }

    @Override
    protected void initData() {

        if (businessActivityTypeEntity != null) {
            if (ivCover != null) {
                GlideTools.loadImage(this, businessActivityTypeEntity.getBackground_image(), ivCover);
            }
            String activity_description = businessActivityTypeEntity.getActivity_description();
            if (!TextUtils.isEmpty(activity_description)) {
                String[] split = activity_description.split("\\?");
                if (split != null) {
                    List<String> activityDescriptionList = ConvertUtils.toList(split);
                    initActivityDescList(activityDescriptionList);
                }
            }
        }
    }

    private void initActivityDescList(List<String> activityDescriptionList) {
        if (rvList != null) {
            rvList.setVertical(1);
            rvList.setScrollingEnabled(false);
            rvList.setAdapter(new CommonRVAdapter<String>(R.layout.item_activity_detail_desc, this, activityDescriptionList) {
                @Override
                protected void covert(ViewHolder viewHolder, String s, int position) {
                    viewHolder.setText(R.id.item_activity_detail_desc_tv_content, s.trim());
                    TextView tvContent = viewHolder.getView(R.id.item_activity_detail_desc_tv_content);
                    if (position % 2 != 0) {
                        // 偶数
                        tvContent.setTextColor(context.getResources().getColor(R.color.color_666666));
                        tvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    } else {
                        // 奇数
                        tvContent.setTextColor(context.getResources().getColor(R.color.color_333333));
                        TextPaint tp = tvContent.getPaint();
                        tp.setFakeBoldText(true);
                        tvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    }
                }
            });
        }
    }
}
