package com.eghuihe.module_dynamic.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.R2;
import com.eghuihe.module_dynamic.ui.activity.InterestAllianceActivity;
import com.eghuihe.module_dynamic.ui.activity.ResearchInstituteActivity;
import com.eghuihe.module_dynamic.ui.dialog.InsertDynamicDialogFragment;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.ui.fragment.BaseTitleFragment;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.ui.widget.title.OnCommonTitleListener;
import com.huihe.base_lib.utils.ARouterUtils;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterConfig.DYNAMIC_DYNAMICFRAGMENT)
public class DynamicFragment extends BaseTitleFragment {

    @BindView(R2.id.fragment_dynamic_container)
    FrameLayout flContainer;

    @OnClick({
            R2.id.fragment_dynamic_DrawableLeftCenterTextView_xys,
            R2.id.fragment_dynamic_DrawableLeftCenterTextView_interest_alliance
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.fragment_dynamic_DrawableLeftCenterTextView_xys) {
            // 学研社
            Intent intent = new Intent(getContext(), ResearchInstituteActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.fragment_dynamic_DrawableLeftCenterTextView_interest_alliance) {
            // 进入兴趣联盟
            Intent intent = new Intent(getContext(), InterestAllianceActivity.class);
            startActivity(intent);
        }
    }

    private BaseFragment dynamiclistfragment = ARouterUtils.getFragment(ARouterConfig.DYNAMIC_DYNAMICLISTFRAGMENT);
    private InsertDynamicDialogFragment insertDynamicDialogFragment;

    @Override
    protected int getChildLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("宝宝秀");
        commonTitle.showStatusBar(true);
        commonTitle.showLine(true);
        commonTitle.setRightIcon1(R.mipmap.fabu);
        commonTitle.setRightTitleText("发布");
        commonTitle.setOnCommonTitleListener(new OnCommonTitleListener() {
            @Override
            public void onRightTitleClicked(View view) {
                showInsertDynamicDialog();
            }

            @Override
            public void onRightIcon1Clicked(View view) {
                showInsertDynamicDialog();
            }
        });
    }

    private void showInsertDynamicDialog() {
        insertDynamicDialogFragment = new InsertDynamicDialogFragment();
        insertDynamicDialogFragment.show(getChildFragmentManager(), "InsertDynamicDialogFragment");
    }

    @Override
    protected void initView() {
        super.initView();
        getChildFragmentManager().beginTransaction().replace(
                flContainer.getId(),
                dynamiclistfragment
        ).commitNowAllowingStateLoss();
    }

    @Override
    public void onDestroy() {
        if (insertDynamicDialogFragment != null) {
            insertDynamicDialogFragment.dismiss();
        }
        super.onDestroy();
    }
}
