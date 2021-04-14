package com.eghuihe.module_schedule.ui.student.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.UserCouponEntity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class UserCouponRvAdapter extends CommonRVAdapter<UserCouponEntity> {

    private OnLisneter onLisneter;

    private List<String> indexList;

    public UserCouponRvAdapter(int layoutId, Context context, List<UserCouponEntity> data, OnLisneter onLisneter) {
        super(layoutId, context, data);
        this.onLisneter = onLisneter;
        indexList = new ArrayList<>();
    }

    @Override
    protected void covert(final ViewHolder viewHolder, final UserCouponEntity userCouponEntity, final int position) {
        String coup_name = userCouponEntity.getCoup_name();
        String discount = userCouponEntity.getDiscount();
        userCouponEntity.setChecked(false);
        discount = TextUtils.isEmpty(discount) ? "0" : discount;
        viewHolder.setText(R.id.item_user_coupon_tv_title, coup_name);
        viewHolder.setText(R.id.item_user_coupon_tv_discount, discount.concat("折"));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onLisneter != null) {
                    if (indexList.contains(String.valueOf(position))) {
                        indexList.remove(String.valueOf(position));
                        userCouponEntity.setChecked(false);
                    } else {
                        indexList.clear();
                        indexList.add(String.valueOf(position));
                        userCouponEntity.setChecked(true);
                    }
                    onLisneter.onItemChecked(userCouponEntity, viewHolder);
                }
            }
        });
        viewHolder.setVisible(R.id.item_user_coupon_view_line,
                position != getItemCount() - 1);
    }

    public interface OnLisneter {
        void onItemChecked(UserCouponEntity userCouponEntity, ViewHolder viewHolder);
    }
}
