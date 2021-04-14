package com.eghuihe.module_home.home.adapter;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatRatingBar;

import com.eghuihe.module_home.R;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.GeometryUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortMechanismRvAdapter extends EmptyRVAdapter<MasterMechanismModel.MasterMechanismEntity> {

    public SortMechanismRvAdapter(int layoutId, Context context, int emptyDataLayoutId) {
        super(layoutId, context, emptyDataLayoutId);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterMechanismModel.MasterMechanismEntity masterMechanismEntity, int position) {

        RoundedImageView ivMechanismLogo = viewHolder.getView(R.id.item_teaching_pay_home_sort_iv_cover);
        AppCompatRatingBar appCompatRatingBar = viewHolder.getView(R.id.item_teaching_pay_home_sort_ratingBar);
        String mechanism_name = masterMechanismEntity.getMechanism_name();
        String mechanism_logo = masterMechanismEntity.getMechanism_logo();
        String mechanism_addr = masterMechanismEntity.getMechanism_addr();
        Float avg_rating = masterMechanismEntity.getAvg_rating();
        avg_rating = avg_rating == null ? 0.0f : avg_rating;
        appCompatRatingBar.setRating(avg_rating);
        double longitude = masterMechanismEntity.getLongitude();
        double latitude = masterMechanismEntity.getLatitude();
        String categoriesChild = masterMechanismEntity.getCategories_child();
        GlideTools.loadRoundedImage(context, mechanism_logo, DensityUtils.dp2px(context, 5), ivMechanismLogo);
        viewHolder.setText(R.id.item_teaching_pay_home_sort_tv_mechanism_name,
                mechanism_name);
        double distance = GeometryUtil.GetLongDistance(
                Double.valueOf(LoginHelper.getLongitude()),
                Double.valueOf(LoginHelper.getLatitude()),
                longitude,
                latitude);
        String distanceStr = NumberUtils.getDistance(distance);
        viewHolder.setText(R.id.item_teaching_pay_home_sort_tv_address,
                mechanism_addr);
        viewHolder.setText(R.id.item_teaching_pay_home_sort_tv_distance,
                distanceStr);
        viewHolder.setVisible(R.id.item_teaching_pay_home_sort_rv_Recommend, false);
        viewHolder.setVisible(R.id.item_teaching_pay_home_sort_tv_See_more_courses, false);
        MasterMechanismModel.MasterMechanismEntity.Map map = masterMechanismEntity.getMap();
        if (map != null) {
            List<MasterSetPriceEntity> masterSetPriceEntities = map.getMasterSetPriceEntity();
            if (masterSetPriceEntities != null && masterSetPriceEntities.size() > 2) {
                viewHolder.setVisible(R.id.item_teaching_pay_home_sort_tv_See_more_courses, true);
            }
            if (masterSetPriceEntities != null && masterSetPriceEntities.size() > 0) {
                viewHolder.setVisible(R.id.item_teaching_pay_home_sort_rv_Recommend, true);
                initRecommendCourseRvAdapter(viewHolder, masterSetPriceEntities);
            }
        }
        viewHolder.setText(R.id.item_teaching_pay_home_sort_tv_categories, categoriesChild);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mechanism_id = masterMechanismEntity.getId();
                EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_DETAIL, mechanism_id));
            }
        });
        viewHolder.setOnClickListener(R.id.item_teaching_pay_home_sort_tv_See_more_courses, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 更多机构课程
                Map<String, String> params = new HashMap<>();
                params.put(ArgumentsConfig.KEY_MECHANISM_ID, masterMechanismEntity.getId());
                ActivityToActivity.toActivity(ARouterConfig.HOME_TEACHINGPAYMECHANISMCOURSELISTACTIVITY, params);
            }
        });
    }

    private void initRecommendCourseRvAdapter(ViewHolder viewHolder, List<MasterSetPriceEntity> masterSetPriceEntities) {
        masterSetPriceEntities = getCourseList(masterSetPriceEntities, 2);
        RecyclerViewFixed rvRecommendCourses = viewHolder.getView(R.id.item_teaching_pay_home_sort_rv_Recommend);
        rvRecommendCourses.setScrollingEnabled(false);
        rvRecommendCourses.setVertical(1);
        RecommendCourseRvAdapter recommendCourseRvAdapter = new RecommendCourseRvAdapter(R.layout.item_mechanism_recommend_course, context, masterSetPriceEntities);
        rvRecommendCourses.setAdapter(recommendCourseRvAdapter);
    }

    @Override
    protected void convertEmptyView(ViewHolder viewHolder, int i) {
        viewHolder.setText(R.id.tv_no_data, context.getResources().getString(R.string.No_mechanism_data));
    }

    private List<MasterSetPriceEntity> getCourseList(List<MasterSetPriceEntity> masterSetPriceEntities, int count) {
        if (masterSetPriceEntities != null && masterSetPriceEntities.size() > 2) {
            masterSetPriceEntities = masterSetPriceEntities.subList(0, count);
        }
        return masterSetPriceEntities;
    }
}
