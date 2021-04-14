package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

public class MechanismCourseScheduleRvAdapter extends EmptyRVAdapter<MasterSetPriceEntity> {
    private OnListener onListener;

    public MechanismCourseScheduleRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(final ViewHolder viewHolder,final MasterSetPriceEntity masterSetPriceEntity, int position) {
        String title = masterSetPriceEntity.getTitle();
        String connect_peoplenum = masterSetPriceEntity.getConnect_peoplenum();
        String appointment_type = masterSetPriceEntity.getAppointment_type();
        String pay_num = masterSetPriceEntity.getPay_num();
        viewHolder.setText(R.id.item_mechanism_course_schedule_tv_title, title);
        if ("fixed_scheduling".equals(appointment_type)){
            if (!TextUtils.isEmpty(connect_peoplenum)) {
                if (!TextUtils.isEmpty(pay_num)) {
                    viewHolder.setText(R.id.item_mechanism_course_schedule_tv_connectPeoplenum,
                            connect_peoplenum
                                    .concat("人/班,")
                                    .concat(pay_num)
                                    .concat("人"));
                } else {
                    viewHolder.setText(R.id.item_mechanism_course_schedule_tv_connectPeoplenum,
                            connect_peoplenum
                                    .concat("人/班"));
                }

            } else if (!TextUtils.isEmpty(pay_num)) {
                viewHolder.setText(R.id.item_mechanism_course_schedule_tv_connectPeoplenum,
                        pay_num.concat("人"));
            }
        }else {
            viewHolder.setText(R.id.item_mechanism_course_schedule_tv_connectPeoplenum,
                    pay_num.concat("人"));
        }
        TextView tvArrange = viewHolder.getView(R.id.item_mechanism_course_schedule_tv_arrange);
        tvArrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 排课
                if (onListener!=null){
                    onListener.onArrangeClicked(viewHolder,masterSetPriceEntity);
                }
            }
        });
    }

    public interface OnListener {
        void onArrangeClicked(ViewHolder viewHolder, MasterSetPriceEntity masterSetPriceEntity);
    }
}
