package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.adapter.MechanismCommentRvAdapter;
import com.eghuihe.module_user.me.adapter.TeachPayMechanismTeacherRvAdapter;
import com.eghuihe.module_user.me.mvp.TeachPayMechanismDetailContract;
import com.eghuihe.module_user.me.mvp.TeachPayMechanismDetailPresenter;
import com.eghuihe.module_user.utils.MapUtils;
import com.eghuihe.module_user.widget.RadarMapView.model.RadarMapEntry;
import com.eghuihe.module_user.widget.RadarMapView.view.RadarMapView;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismAverageScoreEntity;
import com.huihe.base_lib.model.MechanismCommentEntity;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.event.MechanismCoursesEvent;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.activity.PhotoViewActivity;
import com.huihe.base_lib.ui.adapter.TeachPayCourseListRvAdapter;
import com.huihe.base_lib.ui.widget.banner.OnItemClickListener;
import com.huihe.base_lib.ui.widget.banner.XBanner;
import com.huihe.base_lib.ui.widget.fitViewPager.transformers.ScalePageTransformer;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.ConvertUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.GeometryUtil;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.amin.AnimationConstants;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = ARouterConfig.ME_MECHANISM_DETAIL)
public class TeachPayMechanismDetailActivity extends BaseMvpTitleActivity<TeachPayMechanismDetailPresenter>
        implements TeachPayMechanismDetailContract.View {

    public static final String KEY_MECHANISM_ID = "mechanism_id";
    public static final String KEY_USER_ID = "user_id";
    private String mechanism_id; // 机构id

    @BindView(R2.id.activity_mechanism_master_detail_SmartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R2.id.activity_mechanism_master_detail_xbanner)
    XBanner xBanner;
    @BindView(R2.id.activity_mechanism_master_detail_tv_mechanism_name)
    TextView tvMechanismName;
    @BindView(R2.id.activity_mechanism_master_detail_tv_type)
    TextView tvMechanismType;
    @BindView(R2.id.activity_mechanism_master_detail_tv_distance)
    TextView tvMechanismDistance;
    @BindView(R2.id.activity_mechanism_master_detail_tv_mechanism_tel)
    TextView tvMechanismTel;
    @BindView(R2.id.activity_mechanism_master_detail_tv_mechanism_address)
    TextView tvMechanismAddr;
    @BindView(R2.id.activity_mechanism_master_detail_tv_mechanism_Detail_introduce)
    TextView tvMechanismDetailIntroduce;

    @BindView(R2.id.activity_mechanism_master_detail_tv_mechanism_Teacher_strength)
    TextView tvMechanismTeacherStrength;
    @BindView(R2.id.activity_mechanism_master_detail_rv_mechanism_teacher)
    RecyclerViewFixed rvMechanismTeachers;
    @BindView(R2.id.activity_mechanism_master_detail_ll_mechanism_Teacher_strength)
    LinearLayout llMechanismTeachers;
    @BindView(R2.id.activity_mechanism_master_detail_tv_mechanism_course_title)
    TextView tvCourseTitle;
    @BindView(R2.id.activity_mechanism_master_detail_tv_more_mechanism_course)
    TextView tvMoreCourse;
    @BindView(R2.id.activity_mechanism_master_detail_rv_mechanism_info)
    RecyclerViewFixed rvMechanismInfo;
    @BindView(R2.id.activity_mechanism_master_detail_tv_join_teachPay)
    TextView tvJoinTeachPay;
    @BindView(R2.id.activity_mechanism_master_detail_tv_comment_title)
    TextView tvCommentTitle;
    @BindView(R2.id.include_mechanism_course_comment_head_tv_average_score)
    TextView tvAverage_score;
    @BindView(R2.id.include_mechanism_course_comment_head_tv_attitude)
    TextView tvAttitude;
    @BindView(R2.id.include_mechanism_course_comment_head_tv_course_quality)
    TextView tvCourse_quality;
    @BindView(R2.id.include_mechanism_course_comment_head_tv_environment)
    TextView tvEnvironmen;
    @BindView(R2.id.include_mechanism_course_comment_head_tv_faculty)
    TextView tvFaculty;
    @BindView(R2.id.include_mechanism_course_comment_head_tv_cost)
    TextView tvCost;
    @BindView(R2.id.include_mechanism_course_comment_head_rv_commentList)
    RecyclerViewFixed rvCommentList;

    @BindView(R2.id.include_mechanism_course_comment_head_RadarMapView)
    RadarMapView radarMapView;

    private RefreshLayout mRefreshLayout;
    private LoadPagerManager loadPagerManager;
    private TeachPayCourseListRvAdapter courseOnSaleRvAdapter;
    private List<MasterInfoHomeModel.MasterInfoHomeEntity> appointmentEntities;
    private MasterMechanismModel.MasterMechanismEntity masterMechanismEntity;
    private String user_id;
    private MechanismCommentRvAdapter mechanismCommentRvAdapter;

    @OnClick({
            R2.id.activity_mechanism_master_detail_tv_mechanism_address,
            R2.id.activity_mechanism_master_detail_tv_more_mechanism_course
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_mechanism_master_detail_tv_mechanism_address) {
            // 导航
            Double latitude = masterMechanismEntity.getLatitude();
            Double longitude = masterMechanismEntity.getLongitude();
            MapUtils.startNavigation(this,
                    latitude,
                    longitude);
        } else if (view.getId() == R.id.activity_mechanism_master_detail_tv_more_mechanism_course) {
            // 更多机构课程
            Map<String, String> params = new HashMap<>();
            params.put(ArgumentsConfig.KEY_MECHANISM_ID, mechanism_id);
            ActivityToActivity.toActivity(ARouterConfig.HOME_TEACHINGPAYMECHANISMCOURSELISTACTIVITY, params);
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Mechanism_Detail));
        customerTitle.setRightImg(R.mipmap.share_black);
        customerTitle.setImgRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 分享
                Map<String, String> map = new HashMap<>();
                map.put(ArgumentsConfig.KEY_URL,
                        "");
                map.put(ArgumentsConfig.KEY_TITLE,
                        masterMechanismEntity.getMechanism_name());
                map.put(ArgumentsConfig.KEY_IMGURL,
                        masterMechanismEntity.getMechanism_logo());
                map.put(ArgumentsConfig.KEY_WXPATH,
                        AppConfigs.Cooperation.ZH.MECHANISM_DETAIL_WX_PATH
                                .concat(masterMechanismEntity.getId()));
                EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_DETAIL_SHARE, map));
            }
        });
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_mechanism_master_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        mechanism_id = getIntent().getStringExtra(KEY_MECHANISM_ID);
        user_id = getIntent().getStringExtra(KEY_USER_ID);
        loadPagerManager = new LoadPagerManager();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (loadPagerManager != null) {
                    loadPagerManager.scrollToFirst();
                }
                mRefreshLayout = refreshLayout;
                doRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (loadPagerManager != null) {
                    loadPagerManager.currentPageUp();
                }
                mRefreshLayout = refreshLayout;
                doLoadMore();
            }
        });

        tvJoinTeachPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 加入教付保
                getPresenter().userFollowMechanismInsert(
                        LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                        TeachPayMechanismDetailActivity.this.mechanism_id
                );
            }
        });
    }

    private void doRefresh() {
        getPresenter().queryMechanismCommentList(
                mechanism_id,
                "teach_paypal_course",
                loadPagerManager.getCurrentPage(),
                loadPagerManager.getPageSize());
    }

    private void doLoadMore() {
        getPresenter().queryMechanismCommentList(mechanism_id,
                "teach_paypal_course",
                loadPagerManager.getCurrentPage(),
                loadPagerManager.getPageSize());
    }

    @Override
    protected void initData() {
        getPresenter().queryMechanismMasterInfoList(
                mechanism_id,
                null,
                null,
                "teach_paypal",
                "2"
        );
        getPresenter().queryMechanismDetailInfoListById(
                mechanism_id,
                "teach_paypal"
        );
        getPresenter().queryMechanismCourseList(
                mechanism_id,
                "mechanism_offline",
                "2",
                null,
                1,
                3
        );
        getPresenter().queryMechanismAverageScore(mechanism_id);
        getPresenter().queryMechanismCommentList(mechanism_id,
                "teach_paypal_course",
                loadPagerManager.getCurrentPage(),
                loadPagerManager.getPageSize());
    }

    @Override
    public boolean isShowLoading() {
        return true;
    }

    private void bindMechanismDetailInfo(MasterMechanismModel.MasterMechanismEntity masterMechanismEntity) {

        String introduction_pic = masterMechanismEntity.getIntroduction_pic();
        xBanner.setViewPagerClipChildren(true);
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                BannerModel.BannerEntity entity = (BannerModel.BannerEntity) model;
                ImageView imageView = (ImageView) view;
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                GlideTools.loadImage(TeachPayMechanismDetailActivity.this, entity.getPic(), imageView);
            }
        });
        xBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                String[] split = introduction_pic.split(",");
                if (split != null && split.length == 1) {
                    if (TextUtils.isEmpty(split[0])) {
                        split = null;
                    }
                }
                if (split == null) {
                    return;
                }
                BannerModel.BannerEntity entity = (BannerModel.BannerEntity) model;
                Intent intent = new Intent(TeachPayMechanismDetailActivity.this, PhotoViewActivity.class);
                intent.putExtra(PhotoViewActivity.CURRENT_POSITION, position);
                intent.putExtra(PhotoViewActivity.URLS, JsonUtil.toJson(ConvertUtils.toList(split)));
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                int centerX = (int) (location[0] + view.getMeasuredWidth() / 2);
                int centery = (int) (location[1] + view.getMeasuredHeight() / 2);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTX, centerX);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTY, centery);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_ENABLE, true);
                startActivity(intent);
            }
        });
        if (!TextUtils.isEmpty(introduction_pic)) {
            String[] split = introduction_pic.split(",");
            if (split != null && split.length == 1) {
                if (TextUtils.isEmpty(split[0])) {
                    split = null;
                }
            }
            if (split != null && split.length > 0) {
                List<BannerModel.BannerEntity> bannerEntityList = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    BannerModel.BannerEntity bannberEntity = new BannerModel.BannerEntity();
                    bannberEntity.setPic(split[i]);
                    bannerEntityList.add(bannberEntity);
                }
                //刷新数据之后，需要重新设置是否支持自动轮播
                xBanner.setAutoPlayAble(bannerEntityList.size() > 1);
                xBanner.setBannerData(bannerEntityList);
                xBanner.setCustomPageTransformer(new ScalePageTransformer());
            }
        }

        String mechanism_name = masterMechanismEntity.getMechanism_name();
        String categoriesChild = masterMechanismEntity.getCategories_child();
        tvMechanismName.setText(mechanism_name);
        double longitude = masterMechanismEntity.getLongitude();
        double latitude = masterMechanismEntity.getLatitude();
        double distance = GeometryUtil.GetLongDistance(
                Double.valueOf(LoginHelper.getLongitude()),
                Double.valueOf(LoginHelper.getLatitude()),
                longitude,
                latitude);
        String distanceStr = NumberUtils.getDistance(distance);
        tvMechanismDistance.setText(distanceStr);
        tvMechanismType.setText(categoriesChild);
        String mechanism_telephone = masterMechanismEntity.getMechanism_telephone();
        tvMechanismTel.setText(mechanism_telephone);
        String mechanism_addr = masterMechanismEntity.getMechanism_addr();
        tvMechanismAddr.setText(mechanism_addr);
        String introduction_content = masterMechanismEntity.getIntroduction_content();
        if (!TextUtils.isEmpty(introduction_content)) {
            tvMechanismDetailIntroduce.setText(introduction_content);
        } else {
            tvMechanismDetailIntroduce.setText("暂无机构介绍");
        }

        String myMechanism_id = LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id();
        myMechanism_id = TextUtils.isEmpty(myMechanism_id) ? "0" : myMechanism_id;
        mechanism_id = TextUtils.isEmpty(mechanism_id) ? "0" : mechanism_id;

        if (!"0".equals(mechanism_id)
                && !"0".equals(myMechanism_id)
                && mechanism_id.equals(myMechanism_id)) {
            tvJoinTeachPay.setVisibility(View.GONE);
        } else {
            tvJoinTeachPay.setVisibility(View.VISIBLE);
            Boolean is_support_teach_paypal = masterMechanismEntity.getIs_support_teach_paypal();
            if (is_support_teach_paypal != null && is_support_teach_paypal) {
                tvJoinTeachPay.setVisibility(View.GONE);
            } else {
                tvJoinTeachPay.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void closeLoading() {
        super.closeLoading();
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadMore();
            mRefreshLayout.finishRefresh();
        }
    }

    private void finishRefreshWithNoMoreData() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefreshWithNoMoreData();
        }
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefreshWithNoMoreData();
        }
    }

    private void finishLoadMoreWithNoMoreData() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Subscribe
    public void refreshData(MechanismCoursesEvent mechanismCoursesEvent) {
        doRefresh();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (xBanner != null) {
            xBanner.stopAutoPlay();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (xBanner != null) {
            xBanner.startAutoPlay();
        }
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    public void onUserFollowMechanismInsert() {
//        tvJoinTeachPay.setVisibility(View.GONE);
        ToastUtils.showShortToast(this, "已收到您的请求，我们会尽快邀请!");
    }

    @Override
    public void onMechanismMasters(List<MasterInfoHomeModel.MasterInfoHomeEntity> mechanismMastersEntities) {
        appointmentEntities = mechanismMastersEntities;
        initTeacherAdapter();
    }

    private void initTeacherAdapter() {

        if (rvMechanismTeachers != null) {
            rvMechanismTeachers.setHorizontal();
            rvMechanismTeachers.setScrollingEnabled(false);
            if (appointmentEntities == null) {
                appointmentEntities = new ArrayList<>();
            }
            if (appointmentEntities == null || appointmentEntities.size() == 0) {
                llMechanismTeachers.setVisibility(View.GONE);
            } else {
                tvMechanismTeacherStrength.setText(String.format(getResources().getString(R.string.lecturer_Param),
                        "("
                                .concat(String.valueOf(appointmentEntities.size()))
                                .concat(")")));
                TeachPayMechanismTeacherRvAdapter mechanismTeacherRvAdapter = new TeachPayMechanismTeacherRvAdapter(
                        R.layout.item_mechanism_teacher,
                        this, R.layout.layout_no_data);
                rvMechanismTeachers.setAdapter(mechanismTeacherRvAdapter);
                mechanismTeacherRvAdapter.setData(appointmentEntities);
            }

        }
    }

    @Override
    public void onMechanismInfo(MasterMechanismModel.MasterMechanismEntity masterMechanismEntity) {
        this.masterMechanismEntity = masterMechanismEntity;
        bindMechanismDetailInfo(masterMechanismEntity);
        doRefresh();
    }

    @Override
    public void onMasterSetPriceList(List<MasterSetPriceEntity> masterSetPriceEntities) {

        tvMoreCourse.setVisibility(masterSetPriceEntities != null && masterSetPriceEntities.size() > 3 ? View.VISIBLE : View.GONE);
        initCourseAdapter();
        if (masterSetPriceEntities != null && masterSetPriceEntities.size() > 3) {
            masterSetPriceEntities = masterSetPriceEntities.subList(0, 3);
        }
        if (courseOnSaleRvAdapter != null) {
            courseOnSaleRvAdapter.setData(masterSetPriceEntities);
        }
    }

    @Override
    public void onMechanismAverageScore(MechanismAverageScoreEntity mechanismAverageScoreEntity) {

        tvCommentTitle.setText("学生评价(".concat(mechanismAverageScoreEntity.getCommentCount()).concat(")"));
        tvAverage_score.setText(String.valueOf(mechanismAverageScoreEntity.getAverage_score()));
        tvAttitude.setText(String.valueOf(mechanismAverageScoreEntity.getAttitude()));
        tvEnvironmen.setText(String.valueOf(mechanismAverageScoreEntity.getEnvironment()));
        tvCourse_quality.setText(String.valueOf(mechanismAverageScoreEntity.getCourse_quality()));
        tvFaculty.setText(String.valueOf(mechanismAverageScoreEntity.getFaculty()));
        tvCost.setText(String.valueOf(mechanismAverageScoreEntity.getCost_effectiveness()));

        ArrayList<RadarMapEntry> entries = new ArrayList<>();
        Float attitude = mechanismAverageScoreEntity.getAttitude() == null ? 0.0f : mechanismAverageScoreEntity.getAttitude();
        Float course_quality = mechanismAverageScoreEntity.getCourse_quality() == null ? 0.0f : mechanismAverageScoreEntity.getCourse_quality();
        Float environment = mechanismAverageScoreEntity.getEnvironment() == null ? 0.0f : mechanismAverageScoreEntity.getEnvironment();
        Float faculty = mechanismAverageScoreEntity.getFaculty() == null ? 0.0f : mechanismAverageScoreEntity.getFaculty();
        Float cost = mechanismAverageScoreEntity.getCost_effectiveness() == null ? 0.0f : mechanismAverageScoreEntity.getCost_effectiveness();
        entries.add(new RadarMapEntry("性价比", cost));
        entries.add(new RadarMapEntry("喜爱程度", attitude));
        entries.add(new RadarMapEntry("课程质量", course_quality));
        entries.add(new RadarMapEntry("环境设施", environment));
        entries.add(new RadarMapEntry("师资力量", faculty));
        radarMapView.setData(entries);

    }

    @Override
    public void onMechanismCommentList(List<MechanismCommentEntity> mechanismCommentEntities) {
        if (loadPagerManager != null) {

            if (loadPagerManager.getCurrentPage() == 1) {
                initMechanismCommentAdapter();
                if (mechanismCommentRvAdapter != null) {
                    mechanismCommentRvAdapter.setData(mechanismCommentEntities);
                }
            } else {
                if (mechanismCommentRvAdapter != null) {
                    mechanismCommentRvAdapter.addData(mechanismCommentEntities);
                }
            }
        }

        if (loadPagerManager != null) {
            if (mechanismCommentEntities == null || mechanismCommentEntities.size() < loadPagerManager.getPageSize()) {
                if (loadPagerManager.getCurrentPage() == 1) {
                    finishRefreshWithNoMoreData();
                } else {
                    finishLoadMoreWithNoMoreData();
                }
            }

        }
    }

    private void initMechanismCommentAdapter() {
        rvCommentList.setVertical(1);
        rvCommentList.setScrollingEnabled(false);
        mechanismCommentRvAdapter = new MechanismCommentRvAdapter(R.layout.item_mechanism_comment, this, R.layout.layout_no_data);
        rvCommentList.setAdapter(mechanismCommentRvAdapter);
    }

    private void initCourseAdapter() {
        rvMechanismInfo.setVertical(1);
        rvMechanismInfo.setScrollingEnabled(false);
        courseOnSaleRvAdapter =  new TeachPayCourseListRvAdapter(R.layout.item_query_mechanism_course, this,R.layout.layout_no_data);
        courseOnSaleRvAdapter.showLine(true);
        rvMechanismInfo.setAdapter(courseOnSaleRvAdapter);
    }

    @Override
    protected TeachPayMechanismDetailPresenter createPresenter() {
        return new TeachPayMechanismDetailPresenter();
    }
}
