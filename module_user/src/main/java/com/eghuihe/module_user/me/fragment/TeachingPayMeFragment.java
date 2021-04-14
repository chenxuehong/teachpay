package com.eghuihe.module_user.me.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.activities.activity.ActivitiesListActivity;
import com.eghuihe.module_user.me.activity.ClassRoomManagerActivity;
import com.eghuihe.module_user.me.activity.CustomerManagerActivity;
import com.eghuihe.module_user.me.activity.EditMechanismInfoActivity;
import com.eghuihe.module_user.me.activity.EditUserInfoActivity;
import com.eghuihe.module_user.me.activity.FeedBackActivity;
import com.eghuihe.module_user.me.activity.IncomeCenterActivity;
import com.eghuihe.module_user.me.activity.LiveManagerActivity;
import com.eghuihe.module_user.me.activity.MechanismCourseCommentListActivity;
import com.eghuihe.module_user.me.activity.MyCardActivity;
import com.eghuihe.module_user.me.activity.MyRewardActivity;
import com.eghuihe.module_user.me.activity.SettingActivity;
import com.eghuihe.module_user.me.activity.SwitchIdentityActivity;
import com.eghuihe.module_user.me.activity.TeachPayMechanismTeacherListActivity;
import com.eghuihe.module_user.me.activity.TeachPayWindowActivity;
import com.eghuihe.module_user.me.adapter.ItemViewBeanRvAdapter;
import com.eghuihe.module_user.me.adapter.MeCardViewRvAdapter;
import com.eghuihe.module_user.me.mvp.TeachingPayMeContract;
import com.eghuihe.module_user.me.mvp.TeachingPayMePresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.UserStatisticsModel;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.ItemTvBean;
import com.huihe.base_lib.model.personal.ItemViewBean;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.adapter.ExpandRVAdapter;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.banner.MyBanner;
import com.huihe.base_lib.ui.widget.banner.OnItemClickListener;
import com.huihe.base_lib.ui.widget.banner.XBanner;
import com.huihe.base_lib.ui.widget.fitViewPager.transformers.ScalePageTransformer;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.ui.widget.title.OnCommonTitleListener;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.Utils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARouterConfig.ME_MEFRAGMENT)
public class TeachingPayMeFragment extends BaseMvpFragment<TeachingPayMePresenter>
        implements TeachingPayMeContract.View, CommonRVAdapter.OnItemClickListener<ItemViewBean>, OnItemClickListener {

    @BindView(R2.id.teaching_pay_me_title)
    CommonTitle commonTitle;
    @BindView(R2.id.teaching_pay_me_SmartRefreshLayout_content)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.teaching_pay_me_rv_content)
    RecyclerViewFixed rvContent;
    private ExpandRVAdapter expandRVAdapter;
    private MyBanner myBanner;
    private View headUserInfoView;
    private RecyclerViewFixed rvCardViews;
    private static int REQUEST_CODE_SIGN = 100;

    @Override
    protected TeachingPayMePresenter createPresenter() {
        return new TeachingPayMePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_teaching_pay_me;
    }

    @Override
    protected void initView() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                doRefresh();
            }
        });
        commonTitle.setRightTitleTextColor(getResources().getColor(R.color.white));
        commonTitle.setOnCommonTitleListener(new OnCommonTitleListener() {
            @Override
            public void onLeftTitleClicked(View view) {
                getActivity().finish();
            }

            @Override
            public void onRightTitleClicked(View view) {
                startActivity(SwitchIdentityActivity.class);
            }
        });
        rvContent.setVertical(1);
        rvContent.setScrollingEnabled(false);
    }

    private void doRefresh() {
        initData();
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
        }
    }

    @Override
    protected void initData() {
        List<ItemViewBean> itemViewBeans = new ArrayList<>();
        if (Utils.TYPE_STUDENT.equals(Utils.getIdentityType())) {
            // 学生
            initStudentItemViews(itemViewBeans);
        } else if (Utils.TYPE_MECHANISM.equals(Utils.getIdentityType())) {
            // 机构
            commonTitle.setRightTitleText(getResources().getString(R.string.Switch_identities));
            if (Utils.isSwitchMechanismIdentity()) {
                initMechanismItemViews(itemViewBeans);
            } else {
                initStudentItemViews(itemViewBeans);
            }
        } else {
            initTeacherItemViews(itemViewBeans);
        }
        ItemViewBeanRvAdapter itemViewBeanRvAdapter = new ItemViewBeanRvAdapter(
                R.layout.item_teaching_pay_me_item,
                getContext(),
                itemViewBeans);
        itemViewBeanRvAdapter.setOnItemClickListener(this);
        expandRVAdapter = new ExpandRVAdapter(itemViewBeanRvAdapter);
        addUserInfoView(expandRVAdapter);
        addBanner();
        rvContent.setAdapter(expandRVAdapter);
        getPresenter().start();
    }

    private void initTeacherItemViews(List<ItemViewBean> itemViewBeans) {
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.teachingpay_me_feedback,
                getResources().getString(R.string.Feedback)
        ));
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.teachingpay_me_setting,
                getResources().getString(R.string.setting)
        ));
    }

    private void initMechanismItemViews(List<ItemViewBean> itemViewBeans) {
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.activity_sign,
                "活动报名"
        ));
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.teachingpay_me_order,
                "订单"
        ));
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.mechanism_live_manager,
                "直播管理"
        ));
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.mechanism_window,
                "课程管理"
        ));
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.customer_manager,
                "客户管理"
        ));
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.classroom_manager,
                "教室管理"
        ));
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.teachingpay_me_teacher,
                "老师管理"
        ));
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.teachingpay_me_feedback,
                getResources().getString(R.string.Feedback)
        ));
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.teachingpay_me_setting,
                getResources().getString(R.string.setting)
        ));
    }

    private void initStudentItemViews(List<ItemViewBean> itemViewBeans) {
        if (Utils.TYPE_STUDENT.equals(Utils.getIdentityType())) {
            itemViewBeans.add(new ItemViewBean(
                    R.mipmap.teaching_pay_me_mechanism_settlement,
                    getResources().getString(R.string.institution_settlement)
            ));
        }
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.teachingpay_me_feedback,
                getResources().getString(R.string.Feedback)
        ));
        itemViewBeans.add(new ItemViewBean(
                R.mipmap.teachingpay_me_setting,
                getResources().getString(R.string.setting)
        ));
    }

    private void addUserInfoView(ExpandRVAdapter expandRVAdapter) {
        headUserInfoView = LayoutInflater.from(getContext()).inflate(R.layout.layout_userinfo_view, null);
        CircleImageView ivHead = headUserInfoView.findViewById(R.id.layout_userinfo_view_iv_head);
        TextView tvName = headUserInfoView.findViewById(R.id.layout_userinfo_view_tv_name);
        TextView tvCity = headUserInfoView.findViewById(R.id.layout_userinfo_view_tv_city);
        TextView tvEditInfo = headUserInfoView.findViewById(R.id.layout_userinfo_view_tv_info);
        rvCardViews = headUserInfoView.findViewById(R.id.layout_userinfo_view_rv_cards);
        UserInfoEntity userInfoEntity = LoginHelper.getLoginInfo().getUserInfoEntity();
        if (!Utils.TYPE_MECHANISM.equals(Utils.getIdentityType()) || !Utils.isSwitchMechanismIdentity()) {
            GlideTools.loadImage(getContext(), userInfoEntity.getAvatar(), ivHead);
            tvName.setText(userInfoEntity.getNick_name());
            tvCity.setText(userInfoEntity.getCity());
            tvEditInfo.setText(getResources().getString(R.string.Individual_material));
        }
        if (Utils.TYPE_STUDENT.equals(Utils.getIdentityType()) || !Utils.isSwitchMechanismIdentity()) {
            // 是学生才显示：我的订单、我的卡卷、我的积分
            initCardViews(rvCardViews, new UserStatisticsModel.UserStatisticsEntity("0", "0", "0"));
        }
        headUserInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.TYPE_MECHANISM.equals(Utils.getIdentityType()) && Utils.isSwitchMechanismIdentity()) {
                    // 编辑机构信息
                    startActivity(EditMechanismInfoActivity.class);
                } else {
                    // 编辑用户信息
                    startActivity(EditUserInfoActivity.class);
                }
            }
        });
        expandRVAdapter.addHanderView(headUserInfoView);
    }

    @Override
    public void onUserInfo(UserInfoEntity userInfoEntity) {
        if (headUserInfoView != null) {
            LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
            loginInfo.setUserInfoEntity(userInfoEntity);
            LoginHelper.saveUserData(loginInfo);
            CircleImageView ivHead = headUserInfoView.findViewById(R.id.layout_userinfo_view_iv_head);
            TextView tvName = headUserInfoView.findViewById(R.id.layout_userinfo_view_tv_name);
            TextView tvCity = headUserInfoView.findViewById(R.id.layout_userinfo_view_tv_city);
            TextView tvEditInfo = headUserInfoView.findViewById(R.id.layout_userinfo_view_tv_info);
            if (!Utils.TYPE_MECHANISM.equals(Utils.getIdentityType()) || !Utils.isSwitchMechanismIdentity()) {
                GlideTools.loadImage(getContext(), userInfoEntity.getAvatar(), ivHead);
                tvName.setText(userInfoEntity.getNick_name());
                tvCity.setText(userInfoEntity.getCity());
                tvEditInfo.setText(getResources().getString(R.string.Individual_material));
            }
        }
    }

    private void initCardViews(RecyclerViewFixed rvCardViews, UserStatisticsModel.UserStatisticsEntity userStatisticsEntity) {
        rvCardViews.setVertical(3);
        rvCardViews.addGridViewItemDecoration(3, DensityUtils.dp2px(getContext(), 12));
        List<ItemTvBean> itemTvBeans = new ArrayList<>();
        itemTvBeans.add(new ItemTvBean("我的订单", userStatisticsEntity.getRechargeCount()));
        itemTvBeans.add(new ItemTvBean("我的卡劵", userStatisticsEntity.getCoupNum()));
        itemTvBeans.add(new ItemTvBean("我的积分", userStatisticsEntity.getPoints()));
        MeCardViewRvAdapter meCardViewRvAdapter = new MeCardViewRvAdapter(R.layout.item_student_me_cardview, getContext(), itemTvBeans);
        meCardViewRvAdapter.setOnItemClickListener(new CommonRVAdapter.OnItemClickListener<ItemTvBean>() {
            @Override
            public void onItemClicked(View v, ItemTvBean itemTvBean, int i) {
                if ("我的订单".equals(itemTvBean.title)) {
                    ActivityToActivity.toActivity(ARouterConfig.ORDER_TEACHINGPAYSTUDENTORDER);
                } else if ("我的卡劵".equals(itemTvBean.title)) {
                    startActivity(MyCardActivity.class);
                } else if ("我的积分".equals(itemTvBean.title)) {
                    startActivityForResult(MyRewardActivity.class, REQUEST_CODE_SIGN);
                }
            }

            @Override
            public void onItemLongClicked(View v, ItemTvBean o, int i) {

            }
        });
        rvCardViews.setAdapter(meCardViewRvAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SIGN) {
            getPresenter().queryTeachPayUserStatistics(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id());
        }
    }

    @Override
    public void onUserStatistics(UserStatisticsModel.UserStatisticsEntity userStatisticsEntity) {
        initCardViews(rvCardViews, userStatisticsEntity);
    }

    private void addBanner() {
        View bannerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_teaching_pay_me_banner, null);
        myBanner = bannerView.findViewById(R.id.layout_teaching_pay_me_banner);
        myBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                BannerModel.BannerEntity entity = (BannerModel.BannerEntity) model;
                GlideTools.loadRoundedImage(getContext(), entity.getPic(), DensityUtils.dp2px(getContext(), 12), (ImageView) view);
            }
        });
        expandRVAdapter.addHanderView(bannerView);
    }

    @Override
    public void bannerData(List<BannerModel.BannerEntity> bannerEntities) {
        //刷新数据之后，需要重新设置是否支持自动轮播
        if (myBanner != null) {
            myBanner.setAutoPlayAble(bannerEntities.size() > 1);
            myBanner.setBannerData(bannerEntities);
            myBanner.setCustomPageTransformer(new ScalePageTransformer());
            myBanner.setOnItemClickListener(TeachingPayMeFragment.this);
        }
    }

    @Override
    public void onMechanismInfo(List<MasterMechanismModel.MasterMechanismEntity> mechanismEntities) {
        if (mechanismEntities != null && mechanismEntities.size() > 0) {
            MasterMechanismModel.MasterMechanismEntity masterMechanismEntity = mechanismEntities.get(0);
            Integer status = masterMechanismEntity.getStatus();
            LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
            UserInfoEntity userInfoEntity = LoginHelper.getLoginInfo().getUserInfoEntity();
            if (status != null && status == 2) {
                userInfoEntity.setMechanism_id(masterMechanismEntity.getId());
                userInfoEntity.setIs_mechanism_auditing(false);
                loginInfo.setUserInfoEntity(userInfoEntity);
                LoginHelper.saveUserData(loginInfo);
            } else {
                userInfoEntity.setIs_mechanism_auditing(true);
                loginInfo.setUserInfoEntity(userInfoEntity);
                LoginHelper.saveUserData(loginInfo);
            }
            CircleImageView ivHead = headUserInfoView.findViewById(R.id.layout_userinfo_view_iv_head);
            TextView tvName = headUserInfoView.findViewById(R.id.layout_userinfo_view_tv_name);
            TextView tvCity = headUserInfoView.findViewById(R.id.layout_userinfo_view_tv_city);
            TextView tvEditInfo = headUserInfoView.findViewById(R.id.layout_userinfo_view_tv_info);
            if (Utils.TYPE_MECHANISM.equals(Utils.getIdentityType()) && Utils.isSwitchMechanismIdentity()) {
                String mechanism_name = masterMechanismEntity.getMechanism_name();
                String mechanism_logo = masterMechanismEntity.getMechanism_logo();
                String mechanism_addr = masterMechanismEntity.getMechanism_addr();
                GlideTools.loadImage(getContext(), mechanism_logo, ivHead);
                tvName.setText(mechanism_name);
                tvCity.setText(mechanism_addr);
                tvEditInfo.setText(getResources().getString(R.string.Mechanism_info));
            }
        }

    }

    @Override
    public void onItemClicked(View v, ItemViewBean itemViewBean, int i) {
        if (itemViewBean.iconResId == R.mipmap.teachingpay_me_teacher) {
            // 机构老师
            startActivity(TeachPayMechanismTeacherListActivity.class);
        } else if (itemViewBean.iconResId == R.mipmap.teachingpay_me_income) {
            // 机构收益
            startActivity(IncomeCenterActivity.class);
        } else if (itemViewBean.iconResId == R.mipmap.teachingpay_me_course_comment) {
            // 课程评价
            startActivity(MechanismCourseCommentListActivity.class);
        } else if (itemViewBean.iconResId == R.mipmap.teachingpay_me_course_summary) {
            // 课程总结
        } else if (itemViewBean.iconResId == R.mipmap.teachingpay_me_order) {
            // 订单
            if (Utils.TYPE_MECHANISM.equals(Utils.getIdentityType()) && Utils.isSwitchMechanismIdentity()) {
                ActivityToActivity.toActivity(ARouterConfig.ORDER_TEACHINGPAYMECHANISMORDER);
            } else {
                ActivityToActivity.toActivity(ARouterConfig.ORDER_TEACHINGPAYSTUDENTORDER);
            }
        } else if (itemViewBean.iconResId == R.mipmap.mechanism_window) {
            // 课程管理
            enterMyWindow();
        } else if (itemViewBean.iconResId == R.mipmap.mechanism_live_manager) {
            // 直播管理
            enterLiveManager();
        } else if (itemViewBean.iconResId == R.mipmap.customer_manager) {
            // 客户管理
            startActivity(CustomerManagerActivity.class);
        } else if (itemViewBean.iconResId == R.mipmap.classroom_manager) {
            // 教室管理
            startActivity(ClassRoomManagerActivity.class);
        } else if (itemViewBean.iconResId == R.mipmap.teaching_pay_me_mechanism_settlement) {
            // 机构入驻
            ActivityToActivity.toActivity(ARouterConfig.ME_SETTLEMECHANISM);
        } else if (itemViewBean.iconResId == R.mipmap.teachingpay_me_feedback) {
            // 反馈
            startActivity(FeedBackActivity.class);
        } else if (itemViewBean.iconResId == R.mipmap.teachingpay_me_setting) {
            // 设置
            startActivity(SettingActivity.class);
        } else if (itemViewBean.iconResId == R.mipmap.activity_sign) {
            // 活动报名
            startActivity(ActivitiesListActivity.class);
        }
    }

    private void enterLiveManager() {
        startActivity(LiveManagerActivity.class);
    }

    private void enterMyWindow() {
        Intent intent = new Intent(getContext(), TeachPayWindowActivity.class);
        String mechanism_id = LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id();
        if (!TextUtils.isEmpty(mechanism_id) && !"0".equals(mechanism_id)) {
            intent.putExtra(TeachPayWindowActivity.KEY_MECHANISM_ID,
                    mechanism_id);
        } else {
            intent.putExtra(TeachPayWindowActivity.KEY_USER_ID,
                    String.valueOf(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id()));
        }
        startActivity(intent);
    }

    @Override
    public void onItemLongClicked(View v, ItemViewBean itemViewBean, int i) {

    }

    @Override
    public void onItemClick(XBanner banner, Object model, View view, int position) {
        EventBusUtils.sendEvent(new Event(EventAction.BANNER, model));
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.TEACH_PAY_UPDATE_USERINFO.equals(event.getAction())) {
            // 更新用户信息
            getPresenter().queryUserInfo(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id());
        } else if (EventAction.TEACH_PAY_UPDATE_MECHANISMINFO.equals(event.getAction())) {
            // 更新机构信息
            getPresenter().queryMechanismInfo(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(), null, "teach_paypal");
        }
    }
}
