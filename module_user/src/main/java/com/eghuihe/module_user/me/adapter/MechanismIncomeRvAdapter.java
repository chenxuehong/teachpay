package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.MechanismIncomeEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.NumberUtils;

public class MechanismIncomeRvAdapter extends EmptyRVAdapter<MechanismIncomeEntity> {

    public MechanismIncomeRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
    }

    @Override
    protected void convert(ViewHolder viewHolder, MechanismIncomeEntity mechanismIncomeEntity, int position) {

        String title = mechanismIncomeEntity.getTitle();
        String amount = mechanismIncomeEntity.getAmount();
        String status = mechanismIncomeEntity.getStatus();
        status = TextUtils.isEmpty(status) ? "2" : status;
        float amountFloat = NumberUtils.tranToTwoDecimal(amount);
        if (!TextUtils.isEmpty(title)) {
            viewHolder.setText(R.id.item_mechanism_income_tv_title, title);
        }
        viewHolder.setText(R.id.item_mechanism_income_tv_amount,
                NumberUtils.transMoney(String.valueOf(amountFloat)));
        if ("2".equals(status)) {
            viewHolder.setTextColor(R.id.item_mechanism_income_tv_settled, context.getResources().getColor(R.color.color_FF7300));
            viewHolder.setText(R.id.item_mechanism_income_tv_settled, "未结算");
        } else {
            viewHolder.setTextColor(R.id.item_mechanism_income_tv_settled, context.getResources().getColor(R.color.color_666666));
            viewHolder.setText(R.id.item_mechanism_income_tv_settled, "已结算");
        }
    }
}
