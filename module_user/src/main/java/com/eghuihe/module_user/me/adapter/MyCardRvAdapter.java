package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.view.View;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.CommodityCouponEntity;
import com.huihe.base_lib.model.UserCouponEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DateUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MyCardRvAdapter extends EmptyRVAdapter<UserCouponEntity> {

    private Set<ViewHolder> mHolders;
    private Timer mTimer;

    private Onlistener onlistener;

    public MyCardRvAdapter(int layoutId, Context context, Onlistener onlistener) {
        super(layoutId, context);
        mHolders = new HashSet<>();
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (ViewHolder holder : mHolders) {
                    updateTime(holder, holder.getTime());
                }
            }
        }, 0, 1000);
        this.onlistener = onlistener;
    }

    private void updateTime(ViewHolder holder, String time) {
        String curDateStr = DateUtils.getCurDateStr(DateUtils.YMDHMSFormatStr);
        String remainTime = DateUtils.getDistanceTime2(curDateStr, time);
        holder.setText(R.id.item_my_card_tv_time, remainTime);
    }

    @Override
    protected void convert(final ViewHolder viewHolder, final UserCouponEntity userCouponEntity, int position) {

        UserCouponEntity.Map map = userCouponEntity.getMap();
        if (map != null) {
            CommodityCouponEntity commodityCouponEntity = map.getCommodityCouponEntity();
            if (commodityCouponEntity != null) {
                String discount = commodityCouponEntity.getDiscount();
                String name = commodityCouponEntity.getName();
                if ("voucher_redemption".equals(commodityCouponEntity.getType())) {
                    viewHolder.setText(R.id.item_my_card_tv_discount, name);
                    viewHolder.setText(R.id.item_my_card_tv_title, name);
                    viewHolder.setText(R.id.item_my_card_tv_name, "兑换6节课");
                } else {
                    viewHolder.setText(R.id.item_my_card_tv_discount, discount);
                    viewHolder.setText(R.id.item_my_card_tv_title, name);
                    viewHolder.setText(R.id.item_my_card_tv_name, "限时抵扣");
                }

            }
        }
        String overdue_time = userCouponEntity.getOverdue_time();
        viewHolder.setTime(overdue_time);
        mHolders.add(viewHolder);
        String status = userCouponEntity.getStatus();
        if ("1".equals(status)) {
            // 未使用
            viewHolder.setText(R.id.item_my_card_tv_status, "立即使用");
            viewHolder.setVisible(R.id.item_my_card_tv_time, true);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onlistener != null) {
                        onlistener.useCoupon(viewHolder,userCouponEntity);
                    }
                }
            });
        } else if ("2".equals(status)) {
            // 已过期
            viewHolder.setText(R.id.item_my_card_tv_status, "已过期");
            viewHolder.setVisible(R.id.item_my_card_tv_time, false);
        } else {
            // 已使用
            viewHolder.setText(R.id.item_my_card_tv_status, "已使用");
            viewHolder.setVisible(R.id.item_my_card_tv_time, false);
        }
        updateTime(viewHolder, overdue_time);
    }

    /**
     * 销毁资源
     */
    public void destroy() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    public interface Onlistener {
        void useCoupon(ViewHolder viewHolder,UserCouponEntity userCouponEntity);
    }
}
