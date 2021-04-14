package com.eghuihe.module_order.ui.adapter;

import android.content.Context;
import android.view.View;

import com.eghuihe.module_order.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismOrderEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.adapter.PinnedHeaderAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MechanismOrderRvAdapter extends PinnedHeaderAdapter<MechanismOrderEntity> {

    private boolean isIntent;
    private static final int VIEW_TYPE_ITEM_TIME = 0;
    private static final int VIEW_TYPE_ITEM_CONTENT = 1;

    public MechanismOrderRvAdapter(int layoutId, Context context, Boolean isIntent) {
        super(layoutId, context);
        this.isIntent = isIntent;
    }


    @Override
    public boolean isPinnedPosition(int position) {
        return getItemViewType(position) == VIEW_TYPE_ITEM_TIME;
    }

    @Override
    public void hideView(View pinnedHeaderView) {
        pinnedHeaderView.findViewById(R.id.item_mechanism_order_ll).setVisibility(View.GONE);
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
            viewHolder.setVisible(R.id.item_mechanism_order_tv_date, false);
        } else {
            viewHolder.setVisible(R.id.item_mechanism_order_tv_date, true);
        }
        viewHolder.setText(R.id.item_mechanism_order_tv_date,
                DateUtils.getOtherDateStr(mechanismOrderEntity.getFinished_time(),
                        DateUtils.YMDHMSFormatStr,DateUtils.YMDFormatStr));
        viewHolder.setVisible(R.id.item_mechanism_order_tv_is_once_pay, isIntent);
        MechanismOrderEntity.Map map = mechanismOrderEntity.getMap();
        String title = "";
        String childTitle = mechanismOrderEntity.getTitle();
        String amount = "0.0";
        String real_amount = mechanismOrderEntity.getAmount();
        if (map != null) {
            UserInfoEntity userinfo = map.getUserinfo();
            if (userinfo != null) {
                String nickName = userinfo.getNick_name();
                String avatar = userinfo.getAvatar();
                viewHolder.setText(R.id.item_mechanism_order_tv_nickName, nickName);
                CircleImageView ivHead = viewHolder.getView(R.id.item_mechanism_order_iv_head);
                GlideTools.loadImage(context, avatar, ivHead);
            }
            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                title = masterSetPriceEntity.getTitle();
                amount = masterSetPriceEntity.getAmout();
                String face_url = masterSetPriceEntity.getFace_url();
                RoundedImageView ivCover = viewHolder.getView(R.id.item_mechanism_order_iv_course_cover);
                GlideTools.loadImage(context,face_url,ivCover);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_DETAIL,masterSetPriceEntity));
                    }
                });
            }
        }
        viewHolder.setText(R.id.item_mechanism_order_tv_course_title, title);
        viewHolder.setText(R.id.item_mechanism_order_tv_course_childTitle, childTitle);
        viewHolder.setText(R.id.item_mechanism_order_tv_amount, "原价:".concat(amount));
        viewHolder.setText(R.id.item_mechanism_order_tv_discount_amount, "实付:".concat(real_amount));
        viewHolder.setOnClickListener(R.id.item_mechanism_order_tv_is_once_pay, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 转一次支付
                if (onChildListener != null) {
                    onChildListener.onOncePayClicked(viewHolder, mechanismOrderEntity);
                }
            }
        });

    }

    private OnChildListener onChildListener;

    public void setOnChildListener(OnChildListener onChildListener) {
        this.onChildListener = onChildListener;
    }

    public interface OnChildListener {
        void onOncePayClicked(ViewHolder viewHolder, MechanismOrderEntity mechanismOrderEntity);
    }
}