package com.eghuihe.module_home.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_home.R;
import com.eghuihe.module_home.R2;
import com.eghuihe.module_home.home.mvp.SingleLessonPayActivityContract;
import com.eghuihe.module_home.home.mvp.SingleLessonPayActivityPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.adapter.TeachPayCourseListRvAdapter;
import com.huihe.base_lib.ui.widget.AutoFitImageView;
import com.huihe.base_lib.ui.widget.DragFloatActionButton;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.qcloud.tim.uikit.TUIKit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 三科联报活动页面
 */
@Route(path = ARouterConfig.HOME_SKLBACTIVITYDETAILACTIVITY)
public class SklbActivityDetailActivity extends BaseMvpTitleActivity<SingleLessonPayActivityPresenter>
        implements SingleLessonPayActivityContract.View {

    @BindView(R2.id.acivity_single_lesson_pay_activity_rv_courseList)
    RecyclerViewFixed rvCourseList;
    @BindView(R2.id.acivity_single_lesson_pay_activity_dragFloatActionBtn)
    DragFloatActionButton dragFloatActionButton;
    @BindView(R2.id.acivity_single_lesson_pay_activity_tv_content)
    TextView tvContent;
    @BindView(R2.id.acivity_single_lesson_pay_activity_tv_title)
    TextView tvTitle;
    private BaseDialog mInputInviteCodeDialog;
    private String invite_code;

    @OnClick({
            R2.id.acivity_single_lesson_pay_activity_tv_sklb_pay,
    })
    public void onViewClicked(View view) {

        if (R.id.acivity_single_lesson_pay_activity_tv_sklb_pay == view.getId()) {
            showInputInviteCodeDialog();
        }
    }

    private void showInputInviteCodeDialog() {
        mInputInviteCodeDialog = new BaseDialog(AppManager.getInstance().currentActivity()) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_invite_code;
            }

            @Override
            protected void initEvents() {
                final EditText etCode = (EditText) getView(R.id.dialog_tip_et_code);
                TextView tvTipCancel = (TextView) getView(R.id.dialog_tip_cancel);
                TextView tvTipOk = (TextView) getView(R.id.dialog_tip_ok);
                tvTipCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        invite_code = etCode.getText().toString().trim();
                        pay();
                    }
                });
                tvTipOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        invite_code = etCode.getText().toString().trim();
                        pay();
                    }
                });
            }
        };
        mInputInviteCodeDialog.setPerWidth(246f / 375);
        mInputInviteCodeDialog.setCancelOutside(false);
        mInputInviteCodeDialog.show();
    }

    private void pay() {
        // 三科联报支付
        Map<String, String> params = new HashMap<>();
        params.put(ArgumentsConfig.KEY_TYPE, "coupon_177");
        params.put(ArgumentsConfig.KEY_INVITE_CODE, invite_code);
        ActivityToActivity.toActivity(
                ARouterConfig.SCHEDULE_ACTIVITIESPAYACTIVITY,
                params);
    }

    private String type;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("活动详情");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.acivity_sklb_activity;
    }

    @Override
    protected SingleLessonPayActivityPresenter createPresenter() {
        return new SingleLessonPayActivityPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getStringExtra(ArgumentsConfig.KEY_TYPE);
            tvTitle.setText("三科联报课程");
            tvContent.setText("1. 注册账号购买后，自动发放，可在我的-卡卷中查看\\n2. 全免券有效期12个月，可购买平台特定课程。在参与活动之前未联系过机构（含体验课咨询），才可使用代金券购买该机构精品课\\n3. 每位交付保用户仅限购买1次\\n本次活动最终解释权归交付保所有");

        }
        dragFloatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TUIKit.startChat(SklbActivityDetailActivity.this, AppConfigs.kefuGroupId, "客服");
            }
        });
    }

    @Override
    protected void initData() {
        getPresenter().queryActivityListPageByType(
                1,
                10,
                "2",
                type,
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude()
        );
    }


    @Override
    public void onCoureList(List<MasterSetPriceEntity> masterSetPriceEntities) {
        rvCourseList.setVertical(1);
        TeachPayCourseListRvAdapter teachPayCourseListRvAdapter = new TeachPayCourseListRvAdapter(
                R.layout.item_query_mechanism_course,
                getContext(), R.layout.layout_no_data);
        teachPayCourseListRvAdapter.showLine(true);
        rvCourseList.setAdapter(teachPayCourseListRvAdapter);
        teachPayCourseListRvAdapter.setData(masterSetPriceEntities);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (mInputInviteCodeDialog!=null){
                mInputInviteCodeDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
