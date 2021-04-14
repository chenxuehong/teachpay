package com.eghuihe.module_order.ui.adapter;

import android.content.Context;
import android.view.View;

import com.eghuihe.module_order.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismOrderEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.adapter.PinnedHeaderAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class StudentOrderRvAdapter2 extends PinnedHeaderAdapter<MechanismOrderEntity> {

    private static final int VIEW_TYPE_ITEM_TIME = 0;
    private static final int VIEW_TYPE_ITEM_CONTENT = 1;

    private OnChildListener onChildListener;
    public StudentOrderRvAdapter2(int layoutId, Context context,OnChildListener onChildListener) {
        super(layoutId, context);
        this.onChildListener = onChildListener;
    }


    @Override
    public boolean isPinnedPosition(int position) {
        return getItemViewType(position) == VIEW_TYPE_ITEM_TIME;
    }

    @Override
    public void hideView(View pinnedHeaderView) {
        pinnedHeaderView.findViewById(R.id.item_student_order_ll).setVisibility(View.GONE);
    }

    @Override
    public int getItemViewType(int position) {
        List<MechanismOrderEntity> mechanismOrderEntities = getData();
        if (mechanismOrderEntities == null || mechanismOrderEntities.size() == 0) {

            return VIEW_TYPE_ITEM_CONTENT;
        }
        MechanismOrderEntity mechanismOrderEntity = mechanismOrderEntities.get(position);
        String start_time = mechanismOrderEntity.getFinished_time();
        String curYMDate = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
        if (position == 0) {
            // 显示title
            return VIEW_TYPE_ITEM_TIME;
        } else {
            MechanismOrderEntity lastItem = getData().get(position - 1);
            String lastItemStart_time = lastItem.getFinished_time();
            String LastYMDate = DateUtils.getOtherDateStr(lastItemStart_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
            if (curYMDate.equals(LastYMDate)) {
                // 隐藏title
                return VIEW_TYPE_ITEM_CONTENT;
            } else {
                // 显示title
                return VIEW_TYPE_ITEM_TIME;
            }
        }
    }

    @Override
    protected void convert(final ViewHolder viewHolder, final MechanismOrderEntity mechanismOrderEntity, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM_CONTENT) {
            viewHolder.setVisible(R.id.item_student_order2_tv_date, false);
        } else {
            viewHolder.setVisible(R.id.item_student_order2_tv_date, true);
        }
        viewHolder.setText(R.id.item_student_order2_tv_date,
                DateUtils.getOtherDateStr(mechanismOrderEntity.getFinished_time(),
                        DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr));
        MechanismOrderEntity.Map map = mechanismOrderEntity.getMap();
        String title = "";
        String childTitle = mechanismOrderEntity.getTitle();
        String amount = "0.0";
        String real_amount = mechanismOrderEntity.getAmount();
        if (map != null) {
            MasterMechanismModel.MasterMechanismEntity mechanismEntity = map.getMechanismEntity();
            if (mechanismEntity != null) {
                String mechanism_name = mechanismEntity.getMechanism_name();
                String mechanism_logo = mechanismEntity.getMechanism_logo();
                viewHolder.setText(R.id.item_student_order2_tv_mechanism_nickName, mechanism_name);
                CircleImageView ivMechanismLogo = viewHolder.getView(R.id.item_student_order2_iv_mechanism_logo);
                GlideTools.loadImage(context, mechanism_logo, ivMechanismLogo);
            }
            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                title = masterSetPriceEntity.getTitle();
                amount = masterSetPriceEntity.getAmout();
                String face_url = masterSetPriceEntity.getFace_url();
                RoundedImageView ivCover = viewHolder.getView(R.id.item_student_order2_iv_course_cover);
                GlideTools.loadImage(context, face_url, ivCover);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_DETAIL, masterSetPriceEntity));
                    }
                });
            }
        }
        viewHolder.setText(R.id.item_student_order2_tv_course_title, title);
        viewHolder.setText(R.id.item_student_order2_tv_course_childTitle, childTitle);
        String status = mechanismOrderEntity.getStatus();
        if ("1".equals(status)) {
            viewHolder.setText(R.id.item_student_order2_tv_amount, "金额:".concat(NumberUtils.transMoney(real_amount)));
            viewHolder.setText(R.id.item_student_order2_tv_is_pay,"付款");
            viewHolder.setOnClickListener(R.id.item_student_order2_tv_is_pay, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 支付
                    if (onChildListener != null) {
                        onChildListener.onPayClicked(viewHolder, mechanismOrderEntity);
                    }
                }
            });
        } else {
            viewHolder.setText(R.id.item_student_order2_tv_amount, "实付:".concat(NumberUtils.transMoney(real_amount)));
            viewHolder.setText(R.id.item_student_order2_tv_is_pay,"已付款");
        }
        final String mechanism_id = mechanismOrderEntity.getMechanism_id();
        viewHolder.setOnClickListener(R.id.item_student_order2_ll_mechanism, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_DETAIL, mechanism_id));
            }
        });

    }

    public interface OnChildListener {
        void onPayClicked(ViewHolder viewHolder, MechanismOrderEntity mechanismOrderEntity);
    }
}