package com.eghuihe.module_schedule.ui.student.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.UserCouponEntity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.NumberUtils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LiveCouponRvAdapter extends CommonRVAdapter<UserCouponEntity> {

    private OnLisneter onLisneter;

    private LinkedList<UserCouponEntity> checkedCouponList;

    public LiveCouponRvAdapter(int layoutId, Context context, List<UserCouponEntity> data, OnLisneter onLisneter) {
        super(layoutId, context, data);
        this.onLisneter = onLisneter;
        checkedCouponList = new LinkedList<>();
    }

    @Override
    protected void covert(final ViewHolder viewHolder, final UserCouponEntity userCouponEntity, final int position) {
        String coup_name = userCouponEntity.getCoup_name();
        String cash = userCouponEntity.getCash();
        userCouponEntity.setChecked(false);
        cash = TextUtils.isEmpty(cash) ? "0.0" : cash;
        viewHolder.setText(R.id.item_user_coupon_tv_title, coup_name);
        viewHolder.setText(R.id.item_user_coupon_tv_discount, "抵扣".concat(NumberUtils.transMoney(cash)));
        ImageView ivChecked = viewHolder.getView(R.id.item_user_coupon_iv_check);
        final String id = userCouponEntity.getId();
        if (hasIsChecked(id)) {
            ivChecked.setImageResource(R.mipmap.check_state);
        } else {
            ivChecked.setImageResource(R.mipmap.uncheck_state);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onLisneter != null) {
                    if (hasIsChecked(id)) {
                        checkedCouponList.remove(position);
                        ivChecked.setImageResource(R.mipmap.uncheck_state);
                    } else if (!onLisneter.isZero()) {
                        checkedCouponList.add(userCouponEntity);
                        ivChecked.setImageResource(R.mipmap.check_state);
                    }
                    onLisneter.onItemChecked(checkedCouponList);
                }
            }
        });
        viewHolder.setVisible(R.id.item_user_coupon_view_line,
                position != getItemCount() - 1);
    }

    private boolean hasIsChecked(String id) {
        Iterator<UserCouponEntity> iterator = checkedCouponList.iterator();
        while (iterator.hasNext()) {
            UserCouponEntity userCouponEntity = iterator.next();
            String itemId = userCouponEntity.getId();
            return itemId.equals(id);
        }
        return false;
    }

    public interface OnLisneter {
        void onItemChecked(LinkedList<UserCouponEntity> userCouponEntities);

        boolean isZero();
    }
}
