package com.eghuihe.module_home.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.eghuihe.module_home.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ActivityEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.GeometryUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class HotMechanismAdapter extends EmptyRVAdapter<MasterSetPriceEntity> {
    private static final String SP_LINE = "#\\$\\*";

    public HotMechanismAdapter(int layoutId, Context context, int emptyDataLayoutId) {
        super(layoutId, context, emptyDataLayoutId);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterSetPriceEntity masterSetPriceEntity, int position) {
        FrameLayout flCover = viewHolder.getView(R.id.item_hot_mechanism_course_fl_cover);
        RoundedImageView ivCover = viewHolder.getView(R.id.item_hot_mechanism_course_iv_cover);
        ViewGroup.LayoutParams layoutParams = flCover.getLayoutParams();
        layoutParams.width = DensityUtils.getScreenWidth(context) / 3 - DensityUtils.dp2px(context, 15);
        layoutParams.height = layoutParams.width * 101 / 108;
        flCover.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams itemLayoutParams = viewHolder.itemView.getLayoutParams();
        itemLayoutParams.width = DensityUtils.getScreenWidth(context) / 3;
        viewHolder.itemView.setLayoutParams(itemLayoutParams);

        String face_url = masterSetPriceEntity.getFace_url();
        String language = masterSetPriceEntity.getLanguage();
        String label = masterSetPriceEntity.getLabel();
        String amount = masterSetPriceEntity.getAmout();
        String title = masterSetPriceEntity.getTitle();
        String titile_url = masterSetPriceEntity.getTitile_url();
        String pay_num = masterSetPriceEntity.getPay_num();
        String course_num = masterSetPriceEntity.getCourse_num();
        GlideTools.loadRoundedImage(context, face_url, DensityUtils.dp2px(context, 5), ivCover);
        String categories = masterSetPriceEntity.getCategories();
        viewHolder.setVisible(R.id.item_hot_mechanism_course_iv_all_discount, masterSetPriceEntity.getIs_attend_activities() == null ? false : masterSetPriceEntity.getIs_attend_activities());
        if (!TextUtils.isEmpty(categories)) {
            viewHolder.setText(R.id.item_hot_mechanism_course_tv_language,
                    TextUtils.isEmpty(label) ?
                            categories : categories.concat("/").concat(label));
        } else {
            viewHolder.setText(R.id.item_hot_mechanism_course_tv_language, label);
        }
        double latitude = masterSetPriceEntity.getLatitude();
        double longitude = masterSetPriceEntity.getLongitude();
        double distance = GeometryUtil.GetLongDistance(
                Double.valueOf(LoginHelper.getLongitude()),
                Double.valueOf(LoginHelper.getLatitude()),
                longitude,
                latitude);
        String distanceStr = NumberUtils.getDistance(distance);
        viewHolder.setText(R.id.item_hot_mechanism_course_tv_distance,
                distanceStr);
        viewHolder.setText(R.id.item_hot_mechanism_course_tv_label,
                "【".concat(course_num).concat("节】").concat(title));
        viewHolder.setText(R.id.item_hot_mechanism_course_tv_num,
                "已售:".concat(pay_num));
        String price_list = masterSetPriceEntity.getPrice_list();
        viewHolder.setText(R.id.item_hot_mechanism_course_tv_amount,
                NumberUtils.transMoney("0.0").concat("/节"));
        MasterSetPriceEntity.Map map = masterSetPriceEntity.getMap();
        viewHolder.setVisible(R.id.item_hot_mechanism_course_tv_discount_amount,
                false);
        if (map != null) {
            String couponPrice = "0.0";
            List<MasterSetPriceEntity.Map.PriceEntity> priceList = map.getPriceList();
            if (priceList != null && priceList.size() > 0) {
                MasterSetPriceEntity.Map.PriceEntity priceEntity = priceList.get(0);
                couponPrice = priceEntity.getPrice();
                float twoDecimal = NumberUtils.tranToTwoDecimal(couponPrice);
                viewHolder.setText(R.id.item_hot_mechanism_course_tv_amount,
                        NumberUtils.transMoney(String.valueOf(twoDecimal)).concat("/节"));
            }
            ActivityEntity activityEntity = map.getActivityEntity();
            if (activityEntity != null) {
                String price = activityEntity.getPrice();
                if (!TextUtils.isEmpty(price)) {
                    viewHolder.setText(R.id.item_hot_mechanism_course_tv_amount,
                            NumberUtils.transMoney(price).concat("/节"));
                    viewHolder.setStrikeThrough(R.id.item_hot_mechanism_course_tv_discount_amount,
                            NumberUtils.transMoney(couponPrice).concat("/节"));
                    viewHolder.setVisible(R.id.item_hot_mechanism_course_tv_discount_amount,
                            true);
                }
            }

        }

        viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_DETAIL, masterSetPriceEntity));
            }
        });
    }

    @Override
    protected void convertEmptyView(ViewHolder viewHolder, int i) {
        viewHolder.setText(R.id.tv_no_data,
                context.getResources().getString(R.string.No_hot_courses));
    }
}
