package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.ClassRoomEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

public class ClassRoomRvAdapter extends EmptyRVAdapter<ClassRoomEntity> {
    private OnListener onListener;

    public ClassRoomRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, ClassRoomEntity classRoomEntity, int position) {

        String status = classRoomEntity.getStatus();
        if (!"2".equals(status)) {
            String name = classRoomEntity.getName();
            name = TextUtils.isEmpty(name) ? "" : name;
            viewHolder.setText(R.id.item_classroom_tv_name, name.concat("(已弃用)"));
        } else {
            viewHolder.setText(R.id.item_classroom_tv_name, classRoomEntity.getName());
        }
        viewHolder.setOnClickListener(R.id.item_classroom_fl_edit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListener != null) {
                    onListener.onEditClicked(viewHolder, classRoomEntity);
                }
            }
        });
        viewHolder.setOnClickListener(R.id.item_classroom_fl_delete, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListener != null) {
                    onListener.onDeleteClicked(viewHolder, classRoomEntity);
                }
            }
        });
    }

    public interface OnListener {
        void onEditClicked(ViewHolder viewHolder, ClassRoomEntity classRoomEntity);

        void onDeleteClicked(ViewHolder viewHolder, ClassRoomEntity classRoomEntity);
    }
}
