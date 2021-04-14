package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DateUtils;

public class MechanismAppointmentOfflineScheduleRvAdapter extends EmptyRVAdapter<MechanismOfflineScheduleEntity> {

    private OnListener onListener;
    public MechanismAppointmentOfflineScheduleRvAdapter(int layoutId, Context context,OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(final ViewHolder viewHolder,final MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity, int position) {
        String start_time = mechanismOfflineScheduleEntity.getStart_time();
        String end_time = mechanismOfflineScheduleEntity.getEnd_time();
        start_time = DateUtils.translateZoneTimeStr(start_time,
                mechanismOfflineScheduleEntity.getOffset(),
                DateUtils.getCurTimeZoneOffset(),
                DateUtils.YMDHMSFormatStr);
        end_time = DateUtils.translateZoneTimeStr(end_time,
                mechanismOfflineScheduleEntity.getOffset(),
                DateUtils.getCurTimeZoneOffset(),
                DateUtils.YMDHMSFormatStr);
        String startHM = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.HMFormatStr);
        String endHM = DateUtils.getOtherDateStr(end_time, DateUtils.YMDHMSFormatStr, DateUtils.HMFormatStr);
        viewHolder.setText(R.id.item_mechanism_appointment_schedule_tv_duration, startHM.concat("\n-\n").concat(endHM));
        String title = mechanismOfflineScheduleEntity.getTitle();
        viewHolder.setText(R.id.item_mechanism_appointment_schedule_tv_title, title);
        MechanismOfflineScheduleEntity.Map map = mechanismOfflineScheduleEntity.getMap();
        String nickName = "";
        if (map != null) {
            UserInfoEntity masterInfo = map.getMasterInfo();
            if (masterInfo != null) {
                nickName = masterInfo.getNick_name();
            }
        }
        nickName = TextUtils.isEmpty(nickName)?"":nickName;
        String classroom = mechanismOfflineScheduleEntity.getClassroom();
        classroom = TextUtils.isEmpty(classroom)?"":classroom;
        viewHolder.setText(R.id.item_mechanism_appointment_schedule_tv_content,
                nickName.concat(",").concat(classroom));
        viewHolder.setOnClickListener(R.id.item_mechanism_appointment_schedule_tv_edit,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener!=null){
                            onListener.onEdit(viewHolder,mechanismOfflineScheduleEntity);
                        }
                    }
                });
        viewHolder.setOnClickListener(R.id.item_mechanism_appointment_schedule_tv_copy,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener!=null){
                            onListener.onCopy(viewHolder,mechanismOfflineScheduleEntity);
                        }
                    }
                });
    }

    public interface OnListener{
        void onEdit(ViewHolder viewHolder,MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity);
        void onCopy(ViewHolder viewHolder,MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity);
    }
}
