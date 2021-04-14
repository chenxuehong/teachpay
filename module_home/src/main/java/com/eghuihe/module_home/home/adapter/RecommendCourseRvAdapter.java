package com.eghuihe.module_home.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_home.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.NumberUtils;

import java.util.List;

public class RecommendCourseRvAdapter extends CommonRVAdapter<MasterSetPriceEntity> {
    public RecommendCourseRvAdapter(int layoutId, Context context, List<MasterSetPriceEntity> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void covert(ViewHolder viewHolder, MasterSetPriceEntity masterSetPriceEntity, int position) {
        try {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 更多机构课程
//                    Map<String, String> params = new HashMap<>();
//                    params.put(ArgumentsConfig.KEY_MECHANISM_ID, masterMechanismEntity.getId());
//                    ActivityToActivity.toActivity(ARouterConfig.HOME_TEACHINGPAYMECHANISMCOURSELISTACTIVITY, params);
                    EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_DETAIL, masterSetPriceEntity));
                }
            });
            String title = masterSetPriceEntity.getTitle();
            String course_num = masterSetPriceEntity.getCourse_num();
            if (TextUtils.isEmpty(course_num)) {
                course_num = "1";
            }

            if (position == getItemCount() - 1) {
                viewHolder.setVisible(R.id.item_mechanism_recommend_course_line, false);
            } else {
                viewHolder.setVisible(R.id.item_mechanism_recommend_course_line, true);
            }
            viewHolder.setText(R.id.item_mechanism_recommend_course_tv_Recommend_title,
                    "【"
                            .concat(course_num)
                            .concat("节】")
                            .concat(title));
            // 单价
            String original_price = masterSetPriceEntity.getOriginal_price();
            original_price = String.valueOf(NumberUtils.tranToTwoDecimal(original_price));
            // 原价
            String amout = masterSetPriceEntity.getAmout();
            // 默认价格
            String default_discount_price = masterSetPriceEntity.getDefault_discount_price();

            if (NumberUtils.tranToTwoDecimal(original_price)>0){
                // 参与单价课活动
                // 显示单价课价格 original_price
                viewHolder.setText(R.id.item_mechanism_recommend_course_tv_Recommend_discount_amount,
                        NumberUtils.transMoney(original_price).concat("/节"));
                viewHolder.setStrikeThrough(R.id.item_mechanism_recommend_course_tv_Recommend_amount,
                        NumberUtils.transMoney(amout));
                viewHolder.setText(R.id.item_mechanism_recommend_course_tv_Recommend_type, "单节支付");
            }else {
                // 不参加单价课价格
                // 显示默认折扣价格和原价
                viewHolder.setText(R.id.item_mechanism_recommend_course_tv_Recommend_discount_amount,
                        NumberUtils.transMoney(default_discount_price));
                viewHolder.setStrikeThrough(R.id.item_mechanism_recommend_course_tv_Recommend_amount,
                        NumberUtils.transMoney(amout));
                viewHolder.setText(R.id.item_mechanism_recommend_course_tv_Recommend_type, "推荐");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean container(MasterSetPriceEntity masterSetPriceEntity, String type) {
        MasterSetPriceEntity.Map map = masterSetPriceEntity.getMap();
        if (map != null) {
            List<BusinessActivityTypeEntity> activityEntityList = map.getActivityEntityList();
            if (activityEntityList != null) {
                for (int i = 0; i < activityEntityList.size(); i++) {
                    BusinessActivityTypeEntity businessActivityTypeEntity = activityEntityList.get(i);
                    if (type.equals(businessActivityTypeEntity.getType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
