package com.eghuihe.module_schedule.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.utils.DensityUtils;

import java.util.List;

/**
 * @desc 课程表
 */
public class DateScheduleView<T> extends LinearLayout {
    int totalColumn = 3; // 默认列数为3
    int totalRow = 2; // 默认行数为2
    int lineHeight = DensityUtils.dp2px(BaseApplication.getInstance(), 1); // 分割线高度
    private Context context;

    public DateScheduleView(Context context) {
        this(context, null);
    }

    public DateScheduleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateScheduleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        removeAllViews();
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(getResources().getColor(R.color.mainColor));
        addHorizontalLine();
        initAmView();
        initPmView();
    }

    private void addHorizontalLine() {
        View line = new View(context);
        line.setBackgroundColor(getResources().getColor(R.color.mainColor));
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, lineHeight);
        addView(line, lineParams);
    }

    private void initAmView() {
        LinearLayout amLinearLayout = new LinearLayout(context);
        amLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addLeftTitle(amLinearLayout, true);
        addRightLinearLayout(amLinearLayout, true);
        addView(amLinearLayout, params);
    }

    private void addLeftTitle(LinearLayout linearLayout, boolean isAm) {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.mainColor));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, DensityUtils.dp2px(context, 16));
        textView.setText(isAm ? "上午" : "下午");
        linearLayout.setGravity(Gravity.BOTTOM);
        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        params.width = DensityUtils.dp2px(context, 60);
        params.rightMargin = DensityUtils.dp2px(context, 1);
        params.bottomMargin = DensityUtils.dp2px(context, 1);
        textView.setBackgroundColor(getResources().getColor(R.color.white));
        linearLayout.addView(textView, params);
    }

    private void addRightLinearLayout(LinearLayout linearLayout, boolean isAm) {
        LinearLayout rightLinearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams rightParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        rightLinearLayout.setOrientation(LinearLayout.VERTICAL);
        addRightRowLinearLayouts(rightLinearLayout, isAm);
        linearLayout.addView(rightLinearLayout, rightParams);
    }

    private void addRightRowLinearLayouts(LinearLayout rightLinearLayout, boolean isAm) {

        List<T> dataList = getDataList(isAm);
        if (dataList != null && dataList.size() > 2) {
            totalRow = (dataList.size() - 1) / totalColumn + 1;
        }
        for (int i = 0; i < totalRow; i++) {
            addRightRowLinearLayout(rightLinearLayout, isAm, i);
        }
    }

    private void addRightRowLinearLayout(LinearLayout rightLinearLayout, boolean isAm, int rowIndex) {
        LinearLayout rightRowLinearLayout = new LinearLayout(context);
        rightRowLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams rightRowParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        rightRowParams.weight = 1;
        addItemViews(rightRowLinearLayout, isAm, rowIndex);
        rightLinearLayout.addView(rightRowLinearLayout, rightRowParams);
    }

    private void addItemViews(LinearLayout rightRowLinearLayout, boolean isAm, int rowIndex) {
        for (int i = 0; i < totalColumn; i++) {
            FrameLayout itemView = new FrameLayout(context);
            LinearLayout.LayoutParams param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            param.weight = 1;
            param.height = DensityUtils.dp2px(context, 100);
            param.width = 0;
            param.bottomMargin = lineHeight;
            param.rightMargin = lineHeight;
            if (onScheduleListener != null) {
                List<T> dataList = getDataList(isAm);
                T t = getItemData(dataList, rowIndex, i);
                if (t != null)
                    onScheduleListener.onScheduleItem(itemView, t);
            }
            itemView.setBackgroundColor(getResources().getColor(R.color.white));
            rightRowLinearLayout.addView(itemView, param);
        }
    }

    private T getItemData(List<T> dataList, int rowIndex, int columnIndex) {
        if (dataList != null) {
            int index = rowIndex * totalColumn + columnIndex;
            if (index < dataList.size()) {
                return dataList.get(index);
            }
        }
        return null;
    }

    private void initPmView() {
        LinearLayout pmLinearLayout = new LinearLayout(context);
        pmLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addLeftTitle(pmLinearLayout, false);
        addRightLinearLayout(pmLinearLayout, false);
        addView(pmLinearLayout, params);
    }

    private OnScheduleListener onScheduleListener;

    private List<ItemBean> itemBeans;

    public List<T> getDataList(boolean isAm) {
        if (itemBeans != null) {
            for (int i = 0; i < itemBeans.size(); i++) {
                ItemBean itemBean = itemBeans.get(i);
                if (itemBean.isAm == isAm) {
                    return itemBean.tList;
                }
            }
        }
        return null;
    }

    public void setOnScheduleListener(List<ItemBean> itemBeans, OnScheduleListener<T> onScheduleListener) {
        this.itemBeans = itemBeans;
        this.onScheduleListener = onScheduleListener;
        initView();
    }

    public interface OnScheduleListener<T> {
        void onScheduleItem(FrameLayout itemView, T t);
    }

    public static class ItemBean<T> {
        public boolean isAm;
        public List<T> tList;

        public ItemBean(boolean isAm, List<T> tList) {
            this.isAm = isAm;
            this.tList = tList;
        }
    }
}
