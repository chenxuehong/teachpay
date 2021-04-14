package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DensityUtils;

import java.util.List;

public class StoreTypeRvAdapter extends CommonRVAdapter<String> {

    private TextView curTvStoreTypeName;
    private String curStoreTypeName;

    public String getCurStoreTypeName() {
        return curStoreTypeName;
    }

    public StoreTypeRvAdapter(int layoutId, Context context, List<String> data) {
        super(layoutId, context, data);
        curStoreTypeName = null;
    }

    @Override
    protected void covert(ViewHolder viewHolder, final String type, int position) {
        viewHolder.setText(R.id.item_mechanism_store_type_tv_name, type);
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        layoutParams.height = (int) ((DensityUtils.getScreenWidth(context) - DensityUtils.dp2px(context, 60)) / 4f);
        viewHolder.itemView.setLayoutParams(layoutParams);
        viewHolder.setOnClickListener(R.id.item_mechanism_store_type_tv_name,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (curTvStoreTypeName != null) {
                            curTvStoreTypeName.setEnabled(true);
                        }
                        TextView tvStoreTypeName = viewHolder.getView(R.id.item_mechanism_store_type_tv_name);
                        tvStoreTypeName.setEnabled(false);
                        curTvStoreTypeName = tvStoreTypeName;
                        curStoreTypeName = type;
                    }
                });
    }
}
