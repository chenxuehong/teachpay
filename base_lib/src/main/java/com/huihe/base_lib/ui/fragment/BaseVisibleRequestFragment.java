package com.huihe.base_lib.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类，封装了懒加载的实现
 * <p>
 * 1、Viewpager + Fragment情况下，fragment的生命周期因Viewpager的缓存机制而失去了具体意义
 * 该抽象类自定义一个新的回调方法，当fragment可见状态改变时会触发的回调方法
 *
 * @see #onFragmentVisibleChange(boolean)
 */
public abstract class BaseVisibleRequestFragment extends BaseFragment {
    public static final String TAG = BaseVisibleRequestFragment.class.getSimpleName();

    protected static final int WHAT_GET_DATA = 1;
    protected static final long DELAY_TIME = 600;

    private boolean isFragmentVisible;
    private boolean isReuseView;
    public View rootView;
    protected Context mContext;
    private Unbinder unbinder;

    @Override
    protected abstract int getLayoutId();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    //setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
    //如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
    //如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
    //总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //setUserVisibleHint()有可能在fragment的生命周期外被调用


        Log.e(TAG, "setUserVisibleHint()-->" + this.getClass().getName() + ", " + isVisibleToUser);
        if (rootView == null) {
            return;
        }
        if (isVisibleToUser && !isFragmentVisible) {
            isFragmentVisible = true;
            onFragmentVisibleChange(true);
        } else if (!isVisibleToUser) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    protected boolean useButterKnife() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
        Log.e(TAG, "onCreate()-->" + this.getClass().getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        Log.e(TAG, "onCreateView()-->" + this.getClass().getName());
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作

//        Log.e(TAG, "onViewCreated()-->" + this.getClass().getName());
        super.onViewCreated(isReuseView && rootView != null ? rootView : view, savedInstanceState);

        if (getUserVisibleHint()) {
            checkRefresh();
            isFragmentVisible = true;
        } else {
            isFragmentVisible = false;
        }
    }

    @Override
    public void onDestroyView() {

        Log.e(TAG, "onDestroyView()-->" + this.getClass().getName());
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
        Log.e(TAG, "onDestroy()-->" + this.getClass().getName());
    }

    private void initVariable() {
        isFragmentVisible = false;
        rootView = null;
        isReuseView = true;
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     * <p>
     * 可在该回调方法里进行一些ui显示与隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            //延时加载
            mHandler.removeCallbacksAndMessages(null);
            mHandler.sendEmptyMessageDelayed(WHAT_GET_DATA, DELAY_TIME);

        } else {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    protected Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_GET_DATA:
                    mHandler.removeCallbacksAndMessages(null);
                    checkRefresh();
                    break;
            }
            return false;
        }
    });

    protected void checkRefresh() {
        Log.e(TAG, "checkRefresh()-->" + this.getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (getUserVisibleHint()) {
//            onFragmentFirstVisible();
//        }
    }


}
