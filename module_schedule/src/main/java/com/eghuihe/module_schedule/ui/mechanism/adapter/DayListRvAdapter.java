package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.mechanism.fragment.scheduling.BaseSchedulingFragment;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DensityUtils;

import java.util.List;

public class DayListRvAdapter extends CommonRVAdapter<BaseSchedulingFragment.DateBean> {

    private TextView curtvDay;
    private OnDaySelectListener onDaySelectListener;
    private String selectYMDStr;
    private BaseSchedulingFragment.DateBean curDateBean;

    public DayListRvAdapter(
            int layoutId,
            Context context,
            List<BaseSchedulingFragment.DateBean> data,
            OnDaySelectListener onDaySelectListener, String selectYMDStr) {
        super(layoutId, context, data);
        curtvDay = null;
        this.onDaySelectListener = onDaySelectListener;
        this.selectYMDStr = selectYMDStr;
    }

    @Override
    protected void covert(final ViewHolder viewHolder, final BaseSchedulingFragment.DateBean dateBean, int position) {

        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        layoutParams.width = DensityUtils.getScreenWidth(context) / 7;
        viewHolder.itemView.setLayoutParams(layoutParams);

        String week = dateBean.week;
        final String ymdStr = dateBean.ymdStr;

        String day = DateUtils.getOtherDateStr(ymdStr, DateUtils.YMDFormatStr, DateUtils.DFormatStr2);
        day = String.valueOf(DateUtils.trimZero(day));
        viewHolder.setText(R.id.item_teach_schedule_day_tv_week, week);
        viewHolder.setText(R.id.item_teach_schedule_day_tv_day, day);
        final TextView tvDay = viewHolder.getView(R.id.item_teach_schedule_day_tv_day);

        if (selectYMDStr.equals(ymdStr)) {
            setItemSelected(tvDay, dateBean);
        } else {
            if (curtvDay != null && curDateBean != null) {
                if (curDateBean.isToday) {
                    curtvDay.setEnabled(true);
                }
            }
            if (dateBean.isToday) {
                // #FFCCA2
                tvDay.setEnabled(true);
                tvDay.setBackgroundResource(R.drawable.tday_bg_unselected);
                tvDay.setTextColor(context.getResources().getColor(R.color.color_FF7300));
            } else {
                tvDay.setEnabled(true);
                tvDay.setBackgroundResource(R.drawable.day_bg_unselected);
                tvDay.setTextColor(context.getResources().getColor(R.color.color_FF7300));
            }
        }
        viewHolder.setOnClickListener(R.id.item_teach_schedule_day_tv_day,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        setItemSelected(tvDay, dateBean);
                        selectYMDStr = ymdStr;
                        if (onDaySelectListener != null) {
                            onDaySelectListener.onDayClicked(viewHolder, dateBean);
                        }
                    }
                });
    }

    private void setItemSelected(TextView tvDay, BaseSchedulingFragment.DateBean dateBean) {
        if (curtvDay != null && curDateBean != null) {
            if (curDateBean.isToday) {
                curtvDay.setEnabled(true);
                curtvDay.setBackgroundResource(R.drawable.tday_bg_unselected);
                curtvDay.setTextColor(context.getResources().getColor(R.color.color_FF7300));
            } else {
                curtvDay.setEnabled(true);
                curtvDay.setBackgroundResource(R.drawable.day_bg_unselected);
                curtvDay.setTextColor(context.getResources().getColor(R.color.color_FF7300));
            }
        }
        tvDay.setEnabled(false);
        if (dateBean.isToday){
            tvDay.setBackgroundResource(R.drawable.tday_bg_selected);
            tvDay.setTextColor(context.getResources().getColor(R.color.color_FF7300));
        }else {
            tvDay.setBackgroundResource(R.drawable.day_bg_selected);
            tvDay.setTextColor(context.getResources().getColor(R.color.white));
        }

        this.curtvDay = tvDay;
        this.curDateBean = dateBean;
    }

    public interface OnDaySelectListener {
        void onDayClicked(ViewHolder viewHolder, BaseSchedulingFragment.DateBean dateBean);
    }
}
