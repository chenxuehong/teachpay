package com.huihe.base_lib.utils.amin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.huihe.base_lib.model.msg.LiveCustomMsgEntity;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.amin.iml.AbsAnimationListener;
import com.huihe.base_lib.utils.manager.AppManager;


/**
 * view的动画
 */
public class AnimationUtils {
    public static void startScaleAnimation(View view, float startScale, float endScale, int duration, int RepeatCount, final AbsAnimationListener listener) {
        ScaleAnimation scaleAni = new ScaleAnimation(startScale, endScale, startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        //设置动画执行的时间，单位是毫秒
        scaleAni.setDuration(duration);

        // 设置动画重复次数
        // -1或者Animation.INFINITE表示无限重复，正数表示重复次数，0表示不重复只播放一次
        scaleAni.setRepeatCount(RepeatCount);

        // 设置动画模式（Animation.REVERSE设置循环反转播放动画,Animation.RESTART每次都从头开始）
        scaleAni.setRepeatMode(Animation.REVERSE);

        // 启动动画
        view.startAnimation(scaleAni);
        scaleAni.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                listener.onAnimationStart();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listener.onAnimationEnd();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void startTranslateAnimation(View view, float formXValue, float toXValue, float formYValue, float toYValue, int duration, int RepeatCount, int RepeatMode, final AbsAnimationListener listener) {

        TranslateAnimation animation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, formXValue,
                TranslateAnimation.RELATIVE_TO_PARENT, toXValue,
                TranslateAnimation.RELATIVE_TO_SELF, formYValue
                , TranslateAnimation.RELATIVE_TO_SELF, toYValue);
        animation.setDuration(duration);
        animation.setRepeatCount(RepeatCount);
        animation.setRepeatMode(RepeatMode);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (listener != null)
                    listener.onAnimationStart();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null)
                    listener.onAnimationEnd();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    public static void showGiftMsgAnim(final View animView, final ViewGroup viewGroup, final TextView tvAmiGiftCount, final String giftNum) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        viewGroup.addView(animView, params);
        int animViewWidth = DensityUtils.getScreenWidth(AppManager.getInstance().currentActivity());
        final int fromX = -animViewWidth;
        final int toX = DensityUtils.dp2px(AppManager.getInstance().currentActivity(), 30);
        final int parentViewLoc[] = new int[2];
        viewGroup.getLocationOnScreen(parentViewLoc);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(fromX, toX);
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new OvershootInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                animView.setTranslationX(value);
            }
        });
        tvAmiGiftCount.setText("x" + giftNum);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                // 礼物数量缩放动画
                final ValueAnimator giftCountValueAnimator = ValueAnimator.ofFloat(1f, 1.5f, 1f);
                giftCountValueAnimator.setDuration(300);
                giftCountValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {


                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        tvAmiGiftCount.setScaleX(value);
                        tvAmiGiftCount.setScaleY(value);
                    }
                });
                giftCountValueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (animView != null) {
                            final int fromY = 0;
                            final int toY = -animView.getMeasuredHeight();
                            LogUtils.i("onAnimationEnd", "fromY = " + fromY);
                            LogUtils.i("onAnimationEnd", "toY = " + toY);
                            final ValueAnimator valueAnimator = ValueAnimator.ofFloat(fromY, toY);
                            valueAnimator.setDuration(500);
                            valueAnimator.setInterpolator(new LinearInterpolator());
                            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    float value = (Float) animation.getAnimatedValue();
                                    LogUtils.i("onAnimationUpdate", "fromY - value === " + (fromY - value));
                                    float alf = 1 - Math.abs(fromY - value) / (Math.abs(fromY - toY));
                                    LogUtils.i("onAnimationUpdate", "value === " + value);
                                    LogUtils.i("onAnimationUpdate", "alf1 === " + alf);
                                    if (animView != null) {
                                        animView.setTranslationY(value);
                                        if (alf >= 0 && alf <= 1) {
                                            animView.setAlpha(alf);
                                        }
                                    }
                                }
                            });
                            valueAnimator.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    if (viewGroup != null) {
                                        viewGroup.removeAllViews();
                                    }
                                }
                            });
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (valueAnimator != null)
                                        valueAnimator.start();
                                }
                            }, 1000);
                        }

                    }
                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (giftCountValueAnimator != null)
                            giftCountValueAnimator.start();
                    }
                }, 1000);


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
    }

    public static void showEnterOrExitRoomMsgAnim(final View enterAnimView, final FrameLayout viewGroup, LiveCustomMsgEntity liveCustomMsgEntity) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        viewGroup.addView(enterAnimView, params);
        final int screenWidth = DensityUtils.getScreenWidth(AppManager.getInstance().currentActivity());
        final int fromX = screenWidth;
        final int toX = DensityUtils.dp2px(AppManager.getInstance().currentActivity(), 30);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(fromX, toX);
        valueAnimator.setDuration(800);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                LogUtils.i("onAnimationUpdate", "value1 === " + value);
                enterAnimView.setTranslationX(value);
                float alf = 1 - (value - toX) / (fromX - toX);
                LogUtils.i("onAnimationUpdate", "alf1 === " + alf);
                enterAnimView.setAlpha(alf);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                final int fromX = DensityUtils.dp2px(AppManager.getInstance().currentActivity(), 30);
                final int toX = -screenWidth;
                final ValueAnimator valueAnimator = ValueAnimator.ofFloat(fromX, toX);
                valueAnimator.setDuration(1500);
                valueAnimator.setInterpolator(new OvershootInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (Float) animation.getAnimatedValue();
                        LogUtils.i("onAnimationUpdate", "value2 === " + value);
                        enterAnimView.setTranslationX(value);
                    }
                });
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewGroup.removeView(enterAnimView);
                    }
                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        valueAnimator.start();
                    }
                }, 1500);


            }
        });
        valueAnimator.start();
    }

    public static void startRotateAnimation(View view, float fromDegrees, float toDegerees, long duration, int repeatCount, boolean fillAfter) {
        Animation animation = new RotateAnimation(fromDegrees, toDegerees);
        animation.setDuration(duration);
        animation.setRepeatCount(repeatCount);//动画的重复次数
        animation.setFillAfter(fillAfter);//设置为true，动画转化结束后被应用
        view.startAnimation(animation);//开始动画
    }

    public static void startPressAnimation(final View view, final float startScale, final float endScale, final int duration, final int RepeatCount) {
        ScaleAnimation scaleAni = new ScaleAnimation(startScale, endScale, startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        //设置动画执行的时间，单位是毫秒
        scaleAni.setDuration(duration);

        // 设置动画重复次数
        // -1或者Animation.INFINITE表示无限重复，正数表示重复次数，0表示不重复只播放一次
        scaleAni.setRepeatCount(RepeatCount);

        // 设置动画模式（Animation.REVERSE设置循环反转播放动画,Animation.RESTART每次都从头开始）
        scaleAni.setRepeatMode(Animation.REVERSE);

        // 启动动画
        view.startAnimation(scaleAni);
        scaleAni.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ScaleAnimation scaleAni = new ScaleAnimation(endScale, startScale, endScale, startScale,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);

                //设置动画执行的时间，单位是毫秒
                scaleAni.setDuration(duration);

                // 设置动画重复次数
                // -1或者Animation.INFINITE表示无限重复，正数表示重复次数，0表示不重复只播放一次
                scaleAni.setRepeatCount(RepeatCount);

                // 设置动画模式（Animation.REVERSE设置循环反转播放动画,Animation.RESTART每次都从头开始）
                scaleAni.setRepeatMode(Animation.REVERSE);

                // 启动动画
                view.startAnimation(scaleAni);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    // 按钮模拟心脏跳动
    public static void playHeartbeatAnimation(final View heartbeatView) {
        AnimationSet swellAnimationSet = new AnimationSet(true);
        swellAnimationSet.addAnimation(new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f));
        swellAnimationSet.addAnimation(new AlphaAnimation(1.0f, 0.9f));

        swellAnimationSet.setDuration(500);
        swellAnimationSet.setInterpolator(new AccelerateInterpolator());
        swellAnimationSet.setFillAfter(true);

        swellAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AnimationSet shrinkAnimationSet = new AnimationSet(true);
                shrinkAnimationSet.addAnimation(new ScaleAnimation(1.1f, 1.0f, 1.1f, 1.0f, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
                shrinkAnimationSet.addAnimation(new AlphaAnimation(0.9f, 1.0f));
                shrinkAnimationSet.setDuration(1000);
                shrinkAnimationSet.setInterpolator(new DecelerateInterpolator());
                shrinkAnimationSet.setFillAfter(false);
                heartbeatView.startAnimation(shrinkAnimationSet);// 动画结束时重新开始，实现心跳的View
            }
        });

        heartbeatView.startAnimation(swellAnimationSet);
    }

    public static void startLoadingAnimation(Context context, final View loadingView,int duration) {
        final AnimationSet loadingAnimationSet = new AnimationSet(true);
        loadingAnimationSet.addAnimation(new ScaleAnimation(1.0f, 1.0f * DensityUtils.getScreenWidth(context), 1.0f, 1.0f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
        loadingAnimationSet.addAnimation(new AlphaAnimation(1f, 0.5f));
        loadingAnimationSet.setDuration(duration);
        loadingAnimationSet.setRepeatCount(Animation.INFINITE);
        loadingAnimationSet.setStartTime(SystemClock.currentThreadTimeMillis() + 1000);
        loadingAnimationSet.setRepeatMode(Animation.RESTART);
        loadingView.startAnimation(loadingAnimationSet);
    }
}