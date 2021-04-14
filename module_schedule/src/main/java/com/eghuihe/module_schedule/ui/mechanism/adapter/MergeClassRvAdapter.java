package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.MechanismClassEntity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public class MergeClassRvAdapter extends CommonRVAdapter<MechanismClassEntity> {

    private int lastPosition;
    private RadioButton lastRadioButton;
    private OnListener onListener;

    public MergeClassRvAdapter(int layoutId, Context context, List<MechanismClassEntity> data, OnListener onListener) {
        super(layoutId, context, data);
        lastPosition = -1;
        this.onListener = onListener;
    }

    @Override
    protected void covert(ViewHolder viewHolder, final MechanismClassEntity mechanismClassEntity, final int position) {
        final RadioButton radioButton = viewHolder.getView(R.id.item_merge_class_rbtn);
        viewHolder.setText(R.id.item_merge_class_tv_name, mechanismClassEntity.getName());
        radioButton.setChecked(lastPosition == position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastRadioButton != null) {
                    lastRadioButton.setChecked(false);
                }
                radioButton.setChecked(!radioButton.isChecked());
                lastPosition = position;
                lastRadioButton = radioButton;
                if (onListener != null) {
                    onListener.onCheckedItem(radioButton.isChecked() ? mechanismClassEntity : null);
                }
            }
        });
    }

    public interface OnListener {
        void onCheckedItem(MechanismClassEntity mechanismClassEntity);
    }
}
