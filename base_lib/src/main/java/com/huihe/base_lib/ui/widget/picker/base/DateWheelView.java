package com.huihe.base_lib.ui.widget.picker.base;

import android.content.Context;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.widget.picker.model.WheelItem;

public class DateWheelView extends LinearLayout {


    private WheelView wheelViewYear;
    private WheelView wheelViewMonth;
    private TextView tvCancel;
    private TextView tvOk;
    private String year;
    private String month;

    public DateWheelView(Context context) {
        this(context, null);
    }

    public DateWheelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate = View.inflate(context, R.layout.layout_date_wheel_view, this);
        wheelViewYear = inflate.findViewById(R.id.date_wheelview_Year);
        wheelViewMonth = inflate.findViewById(R.id.date_wheelview_Month);
        tvCancel = inflate.findViewById(R.id.date_wheelview_tv_cancel);
        tvOk = inflate.findViewById(R.id.date_wheelview_tv_ok);
        tvCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCancel();
                }
            }
        });

        wheelViewYear.setOnWheelListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int index, String item) {

                year = item;
            }
        });
        wheelViewYear.setCycleDisable(true);
        wheelViewYear.setTextSize(14);
        wheelViewMonth.setOnWheelListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int index, String item) {
                month = item;
            }
        });
        wheelViewMonth.setTextSize(14);
        wheelViewMonth.setCycleDisable(true);
        tvOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (TextUtils.isEmpty(year)) {
                        int centerIndex = wheelViewYear.getItemCount() % 2 == 1 ? ((wheelViewYear.getItemCount() - 1) / 2 +1): wheelViewYear.getItemCount() / 2;
                        WheelItem wheelItem = wheelViewYear.getItems().get(centerIndex);
                        String name = wheelItem.getName();
                        year = name;
                    }
                    if (TextUtils.isEmpty(month)) {
                        int centerIndex = wheelViewMonth.getItemCount() % 2 == 1 ? (wheelViewMonth.getItemCount() - 1) / 2 : wheelViewMonth.getItemCount() / 2;
                        WheelItem wheelItem = wheelViewMonth.getItems().get(centerIndex);
                        String name = wheelItem.getName();
                        month = name;
                    }
                    listener.onConform(year, month);
                }
            }
        });
    }

    public DateWheelView setData(String[] yearArr, String[] monthArr) {
        wheelViewYear.setItems(yearArr);
        wheelViewMonth.setItems(monthArr);
        return this;
    }

    private OnConformClickListener listener;

    public DateWheelView setOnConformClickListener(OnConformClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnConformClickListener {

        void onCancel();

        void onConform(String year, String month);
    }

}
