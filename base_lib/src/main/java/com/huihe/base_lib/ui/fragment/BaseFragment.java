package com.huihe.base_lib.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.huihe.base_lib.R;
import com.huihe.base_lib.model.ExtraEntity;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.widget.dialog.LoadingDialog;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment implements IStateView {

    private static final String TAG = BaseFragment.class.getSimpleName();
    protected View mContainer;
    protected Activity mActivity;
    private boolean isConfigChanged = false;
    private boolean isPrepared;
    private boolean isFirst = false;
    private boolean isVisible;
    private ViewStub emptyView;
    private LoadingDialog loadingDialog;
    private Unbinder unbinder;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        LogUtils.i(TAG, "onActivityCreated()-->" + this.getClass().getName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        LogUtils.i(TAG, "onCreate()-->" + this.getClass().getName());
        loadingDialog = new LoadingDialog(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreateView()-->" + this.getClass().getName());
        this.mContainer = inflater.inflate(R.layout.fragment_base, container, false);
        ((ViewGroup) mContainer.findViewById(R.id.fl_content)).addView(getLayoutInflater().inflate(getLayoutId(), null));
        if (useButterKnife()) {
            unbinder = ButterKnife.bind(this, mContainer);
        }

        if (useEventBus()) {
            EventBusUtils.register(this);
        }
        isConfigChanged = false;
        return mContainer;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LogUtils.i(TAG, "onViewCreated()-->" + this.getClass().getName());
        isFirst = true;
        initView();
    }

    public void showSelectYMDialog() {
        DatePicker datePicker = new DatePicker(getActivity(), DatePicker.YEAR_MONTH);
        datePicker.setLabel(getResources().getString(R.string.year), getResources().getString(R.string.month), getResources().getString(R.string.day));
        datePicker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                String curYMDateStr = year + "-" + month;
                String curYDateStr = DateUtils.getOtherDateStr(curYMDateStr, DateUtils.YMFormatStr, DateUtils.YFormatStr2);
                String curMDateStr = DateUtils.getOtherDateStr(curYMDateStr, DateUtils.YMFormatStr, DateUtils.MFormatStr2);
                int monthOfDay = DateUtils.getMonthOfDay(DateUtils.trimZero(curYDateStr), DateUtils.trimZero(curMDateStr));
                String startTime = curYMDateStr.concat("-01");
                String endTime = curYMDateStr.concat("-").concat(String.valueOf(monthOfDay));
                selectedYM(curYMDateStr, startTime, endTime);
            }
        });
        datePicker.setRangeStart(DateUtils.getCurYear() - 10, 1);
        datePicker.setRangeEnd(DateUtils.getCurYear(), DateUtils.getCurMonth());
        datePicker.setSelectedItem(DateUtils.getCurYear(), DateUtils.getCurMonth());
        datePicker.setResetWhileWheel(false);
        datePicker.show();
    }

    public void selectedYM(String curYMDateStr, String startTime, String endTime) {

    }


    protected void initData() {

    }

    protected void initView() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        LogUtils.i(TAG, "setUserVisibleHint()-->" + this.getClass().getName());
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isFirst && isPrepared) {
            isVisible = true;
            isFirst = false;
            lazyLoad();
        } else {
            isVisible = false;
        }
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            onFragmentOnResume();
        } else {
            //相当于Fragment的onPause
            onFragmentOnPause();
        }
    }

    public void onFragmentOnPause() {

    }

    public void onFragmentOnResume() {

    }

    private void lazyLoad() {
        if (isVisible) {
            LogUtils.i(TAG, "lazyLoad()-->" + this.getClass().getName());
            initData();
            String curYMDateStr = DateUtils.getCurDateStr(DateUtils.YMFormatStr);
            String curYDateStr = DateUtils.getOtherDateStr(curYMDateStr, DateUtils.YMFormatStr, DateUtils.YFormatStr2);
            String curMDateStr = DateUtils.getOtherDateStr(curYMDateStr, DateUtils.YMFormatStr, DateUtils.MFormatStr2);
            int monthOfDay = DateUtils.getMonthOfDay(DateUtils.trimZero(curYDateStr), DateUtils.trimZero(curMDateStr));
            String startTime = curYMDateStr.concat("-01");
            String endTime = curYMDateStr.concat("-").concat(String.valueOf(monthOfDay));
            selectedYM(curYMDateStr, startTime, endTime);
        }
    }

    @Override
    public void onDestroyView() {
        isVisible = false;
        LogUtils.i(TAG, "onDestroyView()-->" + this.getClass().getName());
        if (useEventBus()) {
            EventBusUtils.unRegister(this);
        }
        if (useButterKnife()) {
            if (unbinder != null) {
                unbinder.unbind();
            }
        }
        super.onDestroyView();
    }

    protected boolean useButterKnife() {
        return true;
    }

    protected boolean useEventBus() {
        return false;
    }


    public boolean isFastClick(View view) {
        return EventUtils.isFastDoubleClick(view.getId());
    }

    public void startActivityForResult(Class clazz, ExtraEntity extraEntity, int requestCode) {

        Intent intent = new Intent(getContext(), clazz);
        intent.putExtra(extraEntity.key, new Gson().toJson(extraEntity.entity));
        startActivityForResult(intent, requestCode);

    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(mActivity, clazz);
        startActivity(intent);

    }

    public void startActivityForResult(Class clazz, int requestCode) {
        Intent intent = new Intent(mActivity, clazz);
        startActivityForResult(intent, requestCode);

    }

    public void startActivity(Class clazz, ExtraEntity extraEntity) {
        Intent intent = new Intent(getContext(), clazz);
        intent.putExtra(extraEntity.key, new Gson().toJson(extraEntity.entity));
        startActivity(intent);
    }

    public void startActivity(Class clazz, ExtraEntity[] extraEntitys) {
        Intent intent = new Intent(getContext(), clazz);
        for (int i = 0; i < extraEntitys.length; i++) {
            ExtraEntity extraEntity = extraEntitys[i];
            intent.putExtra(extraEntity.key, new Gson().toJson(extraEntity.entity));
        }
        startActivity(intent);

    }

    public void startActivityForResult(Class clazz, ExtraEntity[] extraEntitys, int requestCode) {
        Intent intent = new Intent(getContext(), clazz);
        for (int i = 0; i < extraEntitys.length; i++) {
            ExtraEntity extraEntity = extraEntitys[i];
            intent.putExtra(extraEntity.key, new Gson().toJson(extraEntity.entity));
        }
        startActivityForResult(intent, requestCode);

    }

    public void startActivity(Class clazz, String key, int value) {
        Intent intent = new Intent(getContext(), clazz);
        intent.putExtra(key, value);
        startActivity(intent);

    }

    public void startActivity(Class clazz, String key, String value) {
        Intent intent = new Intent(getContext(), clazz);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    public void startActivityForResult(Class clazz, String key, String value, int requestCode) {
        Intent intent = new Intent(getContext(), clazz);
        intent.putExtra(key, value);
        startActivityForResult(intent, requestCode);
    }

    protected abstract int getLayoutId();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChanged = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isConfigChanged)
            MobclickAgent.onResume(getActivity());
        setUserVisibleHint(getUserVisibleHint());
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!isConfigChanged)
            MobclickAgent.onResume(getActivity());
    }

    @Override
    public void showLoading() {
        if (isShowLoading()) {
            if (loadingDialog != null) {
                loadingDialog.setTitleText(getResources().getString(R.string.Loading));
                loadingDialog.setCancelable(enableCancelableLoading());
                loadingDialog.show();
            }
        }
        hideEmptyView();
    }

    protected boolean enableCancelableLoading() {
        return true;
    }

    @Override
    public boolean isShowLoading() {
        return true;
    }

    @Override
    public void showUploading() {
        if (loadingDialog != null) {
            loadingDialog.setTitleText(getResources().getString(R.string.Uploading));
            loadingDialog.setCancelable(false);
            loadingDialog.show();
        }
    }

    @Override
    public void retry() {

    }

    @Override
    public void showProgress(String msg) {
        if (loadingDialog != null) {
            loadingDialog.setCancelable(enableCancelableLoading());
            loadingDialog.setTitleText(msg);
            loadingDialog.show();
        }
    }

    @Override
    public void closeLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    //***************************************空页面方法*************************************
    @Override
    public void onEmpty() {
//        if (emptyView == null) {
//            emptyView = mContainer.findViewById(R.id.vs_empty);
//        }
//        emptyView.setVisibility(View.VISIBLE);
//        mContainer.findViewById(R.id.btn_retry).setVisibility(View.VISIBLE);
//        mContainer.findViewById(R.id.iv_empty).setBackgroundResource(R.drawable.kong_error);
//        ((TextView) mContainer.findViewById(R.id.tv_empty)).setText(getString(R.string.no_Data));
//        mContainer.findViewById(R.id.btn_retry).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showLoading();
//                retry();
//            }
//        });
    }

    @Override
    public void onError(String errorMsg) {
        if (emptyView == null) {
            emptyView = mContainer.findViewById(R.id.vs_empty);
        }
        emptyView.setVisibility(View.VISIBLE);
        mContainer.findViewById(R.id.btn_retry).setVisibility(View.VISIBLE);
        mContainer.findViewById(R.id.iv_empty).setBackgroundResource(R.drawable.bg_no_net);
        ((TextView) mContainer.findViewById(R.id.tv_empty)).setText(getString(R.string.Network_request_failed));
        mContainer.findViewById(R.id.btn_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                retry();
            }
        });
    }

    protected void hideEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isPrepared = false;
    }

    @Override
    public Context getContext() {
        return mActivity;
    }

}