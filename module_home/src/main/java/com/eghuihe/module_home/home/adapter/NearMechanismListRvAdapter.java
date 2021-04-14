package com.eghuihe.module_home.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatRatingBar;

import com.eghuihe.module_home.R;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.activity.H5TitleActivity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.service.LocationService;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.GeometryUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearMechanismListRvAdapter extends EmptyRVAdapter<MasterMechanismModel.MasterMechanismEntity> {

    private final LocationService locationService;

    public NearMechanismListRvAdapter(int layoutId, Context context, LocationService locationService) {
        super(layoutId, context);
        this.locationService = locationService;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterMechanismModel.MasterMechanismEntity masterMechanismEntity, int position) {
        RoundedImageView ivMechanismLogo = viewHolder.getView(R.id.item_near_mechanism_course_iv_cover);
        AppCompatRatingBar appCompatRatingBar = viewHolder.getView(R.id.item_near_mechanism_course_ratingBar);
        Float avg_rating = masterMechanismEntity.getAvg_rating();
        avg_rating = avg_rating == null ? 0.0f : avg_rating;
        if (appCompatRatingBar != null)
            appCompatRatingBar.setRating(avg_rating);
        String mechanism_name = masterMechanismEntity.getMechanism_name();
        String mechanism_logo = masterMechanismEntity.getMechanism_logo();
        String mechanism_addr = masterMechanismEntity.getMechanism_addr();
        double longitude = masterMechanismEntity.getLongitude();
        double latitude = masterMechanismEntity.getLatitude();
        String categoriesChild = masterMechanismEntity.getCategories_child();
        boolean cooperative = masterMechanismEntity.isCooperative();
        if (cooperative) {
            GlideTools.loadRoundedImage(context, mechanism_logo, DensityUtils.dp2px(context, 5), ivMechanismLogo);
        } else {
            ivMechanismLogo.setImageResource(R.mipmap.near_mechanism);
        }
        viewHolder.setText(R.id.item_near_mechanism_course_tv_mechanism_name,
                mechanism_name);
        double distance = GeometryUtil.GetLongDistance(
                Double.valueOf(LoginHelper.getLongitude()),
                Double.valueOf(LoginHelper.getLatitude()),
                longitude,
                latitude);
        String distanceStr = NumberUtils.getDistance(distance);
        viewHolder.setText(R.id.item_near_mechanism_course_tv_address,
                mechanism_addr);
        viewHolder.setText(R.id.item_near_mechanism_course_tv_distance,
                distanceStr);
        viewHolder.setVisible(R.id.item_near_mechanism_course_tv_See_more_courses, false);
        viewHolder.setVisible(R.id.item_near_mechanism_course_rv_Recommend, false);
        MasterMechanismModel.MasterMechanismEntity.Map map = masterMechanismEntity.getMap();
        if (map != null) {
            List<MasterSetPriceEntity> masterSetPriceEntities = map.getMasterSetPriceEntity();
            if (masterSetPriceEntities != null && masterSetPriceEntities.size() > 2) {
                viewHolder.setVisible(R.id.item_near_mechanism_course_tv_See_more_courses, true);
            }
            if (masterSetPriceEntities != null && masterSetPriceEntities.size() > 0) {
                viewHolder.setVisible(R.id.item_near_mechanism_course_rv_Recommend, true);
                initRecommendCourseRvAdapter(viewHolder, masterSetPriceEntities);
            }
        }
        viewHolder.setText(R.id.item_near_mechanism_course_tv_childcategories, categoriesChild);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cooperative) {
                    String mechanism_id = masterMechanismEntity.getId();
                    EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_DETAIL, mechanism_id));
                } else {
                    if (locationService != null)
                        locationService.initPoiDetailSearch(masterMechanismEntity.getUid(), new LocationService.OnPoiSearchResultListener() {
                            @Override
                            public void OnPoiSearchResult(List<MasterMechanismModel.MasterMechanismEntity> mechanismDetailInfoEntities) {

                            }

                            @Override
                            public void OnPoiDetailSearchResult(String detailUrl) {
                                if (TextUtils.isEmpty(detailUrl)){
                                    ToastUtils.showShortToast(context,"暂无详情");
                                }else {
                                    Intent intent3 = new Intent(((BaseActivity) context), H5TitleActivity.class);
                                    intent3.putExtra(H5TitleActivity.KET_URL,
                                            detailUrl);
                                    context.startActivity(intent3); //启动调用
                                }

                            }
                        });

                }
            }
        });
        viewHolder.setOnClickListener(R.id.item_near_mechanism_course_tv_See_more_courses, new View.OnClickListener() {
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
        RecyclerViewFixed rvRecommendCourses = viewHolder.getView(R.id.item_near_mechanism_course_rv_Recommend);
        rvRecommendCourses.setScrollingEnabled(false);
        rvRecommendCourses.setVertical(1);
        rvRecommendCourses.setAdapter(new RecommendCourseRvAdapter(R.layout.item_mechanism_recommend_course, context, masterSetPriceEntities));
    }

    private List<MasterSetPriceEntity> getCourseList(List<MasterSetPriceEntity> masterSetPriceEntities, int count) {
        if (masterSetPriceEntities != null && masterSetPriceEntities.size() > 2) {
            masterSetPriceEntities = masterSetPriceEntities.subList(0, count);
        }
        return masterSetPriceEntities;
    }
}
