package com.eghuihe.module_home.home.activity;

import android.content.Intent;
import android.view.View;
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
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.adapter.TeachPayCourseListRvAdapter;
import com.huihe.base_lib.ui.widget.AutoFitImageView;
import com.huihe.base_lib.ui.widget.DragFloatActionButton;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.qcloud.tim.uikit.TUIKit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 活动页面
 */
@Route(path = ARouterConfig.HOME_NEWACTIVITYDETAILACTIVITY)
public class NewActivityDetailActivity extends BaseMvpTitleActivity<SingleLessonPayActivityPresenter>
        implements SingleLessonPayActivityContract.View {

    @BindView(R2.id.acivity_single_lesson_pay_activity_rv_courseList)
    RecyclerViewFixed rvCourseList;
    @BindView(R2.id.acivity_single_lesson_pay_activity_dragFloatActionBtn)
    DragFloatActionButton dragFloatActionButton;
    @BindView(R2.id.acivity_single_lesson_pay_activity_iv_bg1)
    AutoFitImageView ivBg1;
    @BindView(R2.id.acivity_single_lesson_pay_activity_iv_bg2)
    AutoFitImageView ivBg2;
    @BindView(R2.id.acivity_single_lesson_pay_activity_iv_bg3)
    AutoFitImageView ivBg3;
    @BindView(R2.id.acivity_single_lesson_pay_activity_tv_content)
    TextView tvContent;
    @BindView(R2.id.acivity_single_lesson_pay_activity_tv_title)
    TextView tvTitle;
    @BindView(R2.id.acivity_single_lesson_pay_activity_ll_experience_specials_pay)
    LinearLayout llExperienceSpecialsPay;

    @OnClick({
            R2.id.acivity_single_lesson_pay_activity_tv_77_pay,
            R2.id.acivity_single_lesson_pay_activity_tv_177_pay
    })
    public void onViewClicked(View view) {

        if (R.id.acivity_single_lesson_pay_activity_tv_77_pay == view.getId()) {

            // 77元支付
            Map<String, String> params = new HashMap<>();
            params.put(ArgumentsConfig.KEY_TYPE, "coupon_77");
            ActivityToActivity.toActivity(
                    ARouterConfig.SCHEDULE_ACTIVITIESPAYACTIVITY,
                    params);
        } else if (R.id.acivity_single_lesson_pay_activity_tv_177_pay == view.getId()) {

            // 177元支付
            Map<String, String> params = new HashMap<>();
            params.put(ArgumentsConfig.KEY_TYPE, "coupon_177");
            ActivityToActivity.toActivity(
                    ARouterConfig.SCHEDULE_ACTIVITIESPAYACTIVITY,
                    params);
        }
    }

    private String type;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("活动详情");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.acivity_single_lesson_pay_activity;
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
            if ("single_payment".equals(type)) {
                llExperienceSpecialsPay.setVisibility(View.GONE);
                tvTitle.setText("附近单节支付商品");
                ivBg1.setImageResource(R.mipmap.single_pay_bg_1);
                ivBg2.setImageResource(R.mipmap.single_pay_bg_2);
                ivBg3.setImageResource(R.mipmap.single_pay_bg_3);
                tvContent.setText("1. 点击我要报名即可参加活动\n2. 每节课可以先上课后付费，上一节付一节。\n3. 可使用优惠券支付本次活动最终解释权归教付保所有。");
            } else if ("grouping".equals(type)) {
                llExperienceSpecialsPay.setVisibility(View.GONE);
                tvTitle.setText("附近拼团商品");
                ivBg1.setImageResource(R.mipmap.activity_grouping_bg1);
                ivBg2.setImageResource(R.mipmap.activity_grouping_bg2);
                ivBg3.setVisibility(View.GONE);
                tvContent.setText("1.报名同一机构同一课程包，每满4人，可返现5%，最多返现20%\n2.单节付课程每次课程独立计算。（全额购课程按整单计算。）\n3.邀请链接有效期24H。\n4.团内成员单次课程结束之后，方可算作有效成员。每满4人完成单次课时，即进行一次返现结算。\n例如：小A作为团长邀请6人组团报同一机构单节付课程。当有4人完成单次课程时，已完成的4人均可获得5%的返现，第5人和第6人完成单次课时后，也可获得5%的返现。\n5.拼单课程不可使用优惠券结算。\n本次活动最终解释权归教付保所有。");
            } else if ("experience_specials".equals(type)) {
                llExperienceSpecialsPay.setVisibility(View.VISIBLE);
                tvTitle.setText("附近新春特惠商品");
                ivBg1.setImageResource(R.mipmap.activity_cj_bg1);
                ivBg2.setImageResource(R.mipmap.activity_cj_bg2);
                ivBg3.setVisibility(View.GONE);
                tvContent.setText("1.注册账号购买后，自动发放，可在我的-卡券中查看。\n2. 全免券有效期12个月，可购买平台特定课程。在参与活动之前未联系过机构（含体验课咨询），才可使用代金券购买该机构精品课。\n3.每位教付保用户仅限购买1次\n本次活动最终解释权归教付保所有");
            }
        }
        dragFloatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TUIKit.startChat(NewActivityDetailActivity.this, AppConfigs.kefuGroupId, "客服");
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
}
