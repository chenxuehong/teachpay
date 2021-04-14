package com.eghuihe.module_main.main.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_main.R;
import com.eghuihe.module_main.R2;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.adapter.BasePagerAdapter;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.ConvertUtils;
import com.huihe.base_lib.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARouterConfig.MAIN_GUIDEACTIVITY)
public class GuideActivity extends BaseActivity {

    @BindView(R2.id.activity_guide_vp)
    ViewPager viewPager;

    private Integer[] guideIcons = {
            R.mipmap.guide_1,
            R.mipmap.guide_2,
            R.mipmap.guide_3
    };
    private List<Integer> guideIconList;
    private View indicatorView;

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.acivity_guide;
    }

    @Override
    protected void initData() {
        guideIconList = ConvertUtils.toList(guideIcons);
        viewPager.setOffscreenPageLimit(guideIconList.size());
        List<View> views = new ArrayList<>();
        for (int i = 0; i < guideIconList.size(); i++) {
            View view = View.inflate(this, R.layout.item_guide, null);
            ImageView ivIcon = view.findViewById(R.id.item_guide_iv_icon);
            ImageView ivEnter = view.findViewById(R.id.item_guide_iv_enter);
            LinearLayout llIndicator = view.findViewById(R.id.item_guide_ll_indicator);
            if (i == guideIconList.size() - 1) {
                ivEnter.setVisibility(View.VISIBLE);
                ivEnter.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityToActivity.toActivity(ARouterConfig.ME_LOGINACTIVITY);
                            }
                        });
            } else {
                for (int indicatorCount = 0; indicatorCount < guideIconList.size(); indicatorCount++) {
                    View indicatorView = new View(this);
                    llIndicator.addView(indicatorView);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) indicatorView.getLayoutParams();
                    layoutParams.height = DensityUtils.dp2px(this, 6);
                    layoutParams.width = DensityUtils.dp2px(this, 15);
                    layoutParams.rightMargin = DensityUtils.dp2px(this, 22);
                    indicatorView.setLayoutParams(layoutParams);
                    if (i == indicatorCount) {
                        indicatorView.setBackgroundResource(R.drawable.shape_radius_3_color_ff7300);
                    } else {
                        indicatorView.setBackgroundResource(R.drawable.shape_radius_3_color_fff5f5f5);
                    }
                }
                ivEnter.setVisibility(View.GONE);
            }
            ivIcon.setImageResource(guideIconList.get(i));
            views.add(view);
        }
        viewPager.setAdapter(new BasePagerAdapter(views));
    }
}
