package com.eghuihe.module_user.activities.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.activities.mvp.UseCouponMechanismCourseDetailContract;
import com.eghuihe.module_user.activities.mvp.UseCouponMechanismCourseDetailContractPresenter;
import com.eghuihe.module_user.me.adapter.CourseDescriptionRvAdapter;
import com.eghuihe.module_user.me.adapter.ExclusiveCourseSyllabusRvAdapter;
import com.eghuihe.module_user.me.adapter.MechanismCourseCommentRvAdapter;
import com.eghuihe.module_user.me.mvp.TeachPayMechanismCourseDetailContract;
import com.eghuihe.module_user.me.mvp.TeachPayMechanismCourseDetailPresenter;
import com.eghuihe.module_user.utils.MyDialogUtils;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.CourseMessageBean;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismCommentEntity;
import com.huihe.base_lib.model.RecordingCourseSyllabusEntity;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.UseCouponParam;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.BaseMvpActivity;
import com.huihe.base_lib.ui.activity.H5TitleActivity;
import com.huihe.base_lib.ui.activity.PhotoViewActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.adapter.ExpandRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.GeometryUtil;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.amin.AnimationConstants;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.pay.alipay.AlipayUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 教付保 机构课程详情
 */
public class UseCouponMechanismCourseDetailActivity extends BaseMvpActivity<UseCouponMechanismCourseDetailContractPresenter>
        implements UseCouponMechanismCourseDetailContract.View {

    public static final String KEY_DATA = ArgumentsConfig.MECHANISM_COURSE_DETAIL;
    @BindView(R2.id.activity_course_detail_onsale2_fl_container)
    FrameLayout flContainer;
    @BindView(R2.id.activity_course_detail_onsale2_SmartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.activity_course_detail_onsale2_rv)
    RecyclerViewFixed recyclerViewFixed;
    @BindView(R2.id.activity_live_course_fl_status)
    FrameLayout flStatus;
    @BindView(R2.id.activity_course_detail_onsale2_ll_pay)
    LinearLayout llPay;
    private MasterSetPriceEntity exclusiveCoursesEntity;
    private TextView tvJoinTeachPay;

    private RefreshLayout mRefreshLayout;
    private LoadPagerManager loadPagerManager;
    private MechanismCourseCommentRvAdapter commentRvAdapter;
    private ExpandRVAdapter expandRVAdapter;
    private UseCouponParam useCouponParam;

    @OnClick({
            R2.id.activity_live_course_fl_titleBack,
            R2.id.activity_course_detail_onsale2_tv_useCoupon,
            R2.id.activity_course_detail_onsale2_tv_consult
    })
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view.getId())) {
            if (view.getId() == R.id.activity_live_course_fl_titleBack) {
                finish();
            }  else if (view.getId() == R.id.activity_course_detail_onsale2_tv_consult) {
                consult();
            } else if (view.getId() == R.id.activity_course_detail_onsale2_tv_useCoupon) {
               useCoupon();
            }
        }
    }

    private void consult() {
        MasterSetPriceEntity.Map map = exclusiveCoursesEntity.getMap();
        if (map != null) {
            MasterMechanismModel.MasterMechanismEntity masterMechanismEntity = map.getMasterMechanismEntity();
            if (masterMechanismEntity != null) {
                String mechanism_logo = masterMechanismEntity.getMechanism_logo();
                String user_id = masterMechanismEntity.getUser_id();
                String group_id = exclusiveCoursesEntity.getId()
                        .concat(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id())
                        .concat(AppConfigs.kefuGroupId);
                String title = exclusiveCoursesEntity.getTitle();
                List<String> memberList = new ArrayList<>();
                memberList.add(user_id);
                memberList.add(AppConfigs.kefuGroupId);
                memberList.add(String.valueOf(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id()));

                TUIKit.consult(
                        this,
                        group_id,
                        "课程咨询",
                        "Work",
                        mechanism_logo,
                        memberList,
                        new CourseMessageBean(
                                exclusiveCoursesEntity.getId(),
                                exclusiveCoursesEntity.getFace_url(),
                                exclusiveCoursesEntity.getOriginal_price(),
                                exclusiveCoursesEntity.getDiscount_amout(),
                                exclusiveCoursesEntity.getTitle(),
                                exclusiveCoursesEntity.getCourse_num(),
                                exclusiveCoursesEntity.getIs_attend_activities(),
                                "commodityMessage"
                        ));
            }
        }
    }

    private void useCoupon() {
        if (useCouponParam!=null){
            getPresenter().useCoupon(
                    useCouponParam.id,
                    useCouponParam.master_set_price_id,
                    useCouponParam.user_id
            );
        }

    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teach_pay_course_detail_onsale2;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String json = intent.getStringExtra(KEY_DATA);
        exclusiveCoursesEntity = JsonUtil.fromJson(json, MasterSetPriceEntity.class);
        getPresenter().queryMechanismCourseListById(exclusiveCoursesEntity.getId());
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent!=null){
            String json = intent.getStringExtra(ArgumentsConfig.KEY_USECOUPONPARAM);
            useCouponParam = JsonUtil.fromJson(json, UseCouponParam.class);
        }
        loadPagerManager = new LoadPagerManager();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout = refreshLayout;
                if (loadPagerManager != null) {
                    loadPagerManager.scrollToFirst();
                }
                doRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout = refreshLayout;
                if (loadPagerManager != null) {
                    loadPagerManager.currentPageUp();
                }
                doLoadMore();
            }
        });
        recyclerViewFixed.setVertical(1);
        flStatus.getLayoutParams().height = DensityUtils.getStatusHeight();
    }

    private void doRefresh() {

        if (exclusiveCoursesEntity != null) {
            getPresenter().queryMechanismCourseCommentList(
                    exclusiveCoursesEntity.getId(),
                    "teach_paypal_course",
                    getCurrentPage(),
                    getPageSize()
            );
        }
    }

    private void doLoadMore() {
        if (exclusiveCoursesEntity != null) {
            getPresenter().queryMechanismCourseCommentList(
                    exclusiveCoursesEntity.getId(),
                    "teach_paypal_course",
                    getCurrentPage(),
                    getPageSize()
            );
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

    private void bindData() {
        String mechanism_id = exclusiveCoursesEntity.getMechanism_id();
        llPay.setVisibility(View.VISIBLE);
        String my_mechanism_id = LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id();
        String myMechanism_id = LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id();
        myMechanism_id = TextUtils.isEmpty(myMechanism_id) ? "0" : myMechanism_id;
        mechanism_id = TextUtils.isEmpty(mechanism_id) ? "0" : mechanism_id;

        if (!"0".equals(mechanism_id)
                && !"0".equals(myMechanism_id)
                && mechanism_id.equals(myMechanism_id)) {
            // 属于机构
            llPay.setVisibility(View.GONE);
        } else {
            MasterSetPriceEntity.Map map = exclusiveCoursesEntity.getMap();
            if (map != null) {
                UserInfoEntity masterinfo = map.getMasterinfo();
                if (masterinfo != null) {
                    if (LoginHelper.isMySelf(masterinfo.getUser_id())) {
                        // 课程创建者
                        llPay.setVisibility(View.GONE);
                    }
                }
            }
        }

        commentRvAdapter = new MechanismCourseCommentRvAdapter(R.layout.item_mechanism_course_comment, this, R.layout.layout_no_data);
        expandRVAdapter = new ExpandRVAdapter(commentRvAdapter);

        View headView = View.inflate(this, R.layout.layout_teach_pay_course_onsale_head, null);
        initHeadView(headView);
        expandRVAdapter.addHanderView(headView);
        BusinessActivityTypeEntity experience_specials = container(exclusiveCoursesEntity, "experience_specials");
        if (experience_specials != null) {
            addLineView(expandRVAdapter);
            addActivities(expandRVAdapter);
        }
        addLineView(expandRVAdapter);
        addCourseDescption(expandRVAdapter);

        addLineView(expandRVAdapter);
        if (mechanism_id == null || "0".equals(mechanism_id)) {
            initTeacherView(expandRVAdapter);
        } else {
            // 机构信息
            View mechanismView = View.inflate(this, R.layout.layout_mechanism_info, null);
            initMechanismInfoView(mechanismView);
            expandRVAdapter.addHanderView(mechanismView);
            // 机构老师
            addMechanismTeacherInfoView(expandRVAdapter);
        }

        addLineView(expandRVAdapter);

        // 添加课程信息
        addCourseInfoView(expandRVAdapter, exclusiveCoursesEntity);

        // 添加课程大纲
        String title_url = exclusiveCoursesEntity.getTitile_url();
        if (!TextUtils.isEmpty(title_url)) {
            String[] titleurl = title_url.split("#\\$\\*");
            if (titleurl != null && titleurl.length > 0) {
                View courseSyllabusView = View.inflate(this, R.layout.layout_course_syllabus_lview, null);
                RecyclerViewFixed rvCourseSyllabus = courseSyllabusView.findViewById(R.id.layout_course_syllabus_lview_rv_course_syllabus);
                rvCourseSyllabus.setVertical(1);
                rvCourseSyllabus.setScrollingEnabled(false);
                CommonRVAdapter courseSyllabuRvAdapter = new ExclusiveCourseSyllabusRvAdapter(R.layout.item_exclusive_course_detail_syllabus, this, new ArrayList<>());
                rvCourseSyllabus.setAdapter(courseSyllabuRvAdapter);
                initCourseSyllabus(courseSyllabuRvAdapter, exclusiveCoursesEntity);
                expandRVAdapter.addHanderView(courseSyllabusView);
            }
        }
        View courseCommentView = View.inflate(this, R.layout.layout_course_comment_view, null);
        expandRVAdapter.addHanderView(courseCommentView);
        expandRVAdapter.notifyDataSetChanged();
        recyclerViewFixed.setAdapter(expandRVAdapter);
    }

    /**
     * @desc 添加活动
     * @param expandRVAdapter
     */
    private void addActivities(ExpandRVAdapter expandRVAdapter) {
        View activitiesView = View.inflate(this,R.layout.layout_activityes,null);
        activitiesView.findViewById(R.id.layout_activityes_iv_activities).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap<>();
                params.put(ArgumentsConfig.KEY_TYPE, "experience_specials");
                ActivityToActivity.toActivity(ARouterConfig.HOME_NEWACTIVITYDETAILACTIVITY,params);
            }
        });
        expandRVAdapter.addHanderView(activitiesView);
    }

    /**
     * @param expandRVAdapter
     * @desc 课程简介
     */
    private void addCourseDescption(ExpandRVAdapter expandRVAdapter) {
        String introduction_content = exclusiveCoursesEntity.getIntroduction_content();
        View courseDescView = View.inflate(this, R.layout.layout_course_desc_lview, null);
        TextView tvDesc = courseDescView.findViewById(R.id.layout_course_descriptionview_tv_course_description);
        String amout = exclusiveCoursesEntity.getAmout();
        amout = NumberUtils.transMoney(amout);
        tvDesc.setText(introduction_content.concat("\n【机构套餐价:").concat(amout).concat("】"));
        expandRVAdapter.addHanderView(courseDescView);
    }

    private void addLineView(ExpandRVAdapter expandRVAdapter) {
        View lineView = View.inflate(this, R.layout.layout_line, null);
        expandRVAdapter.addHanderView(lineView);
    }

    private void initMechanismInfoView(View mechanismView) {
        MasterSetPriceEntity.Map map = exclusiveCoursesEntity.getMap();
        if (map != null) {
            MasterMechanismModel.MasterMechanismEntity masterMechanismEntity = map.getMasterMechanismEntity();
            if (masterMechanismEntity != null) {
                RoundedImageView ivMechanismPhoto = mechanismView.findViewById(R.id.layout_mechanism_info_iv_mechanism_photo);
                TextView tvMechanismName = mechanismView.findViewById(R.id.layout_mechanism_info_tv_mechanism_name);
                TextView tvMechanismTel = mechanismView.findViewById(R.id.layout_mechanism_info_tv_mechanism_tel);
                TextView tvMechanismAddress = mechanismView.findViewById(R.id.layout_mechanism_info_tv_mechanism_address);
                TextView tvMechanismDistance = mechanismView.findViewById(R.id.layout_mechanism_info_tv_mechanism_distance);
                String mechanism_logo = masterMechanismEntity.getMechanism_logo();
                String mechanism_name = masterMechanismEntity.getMechanism_name();
                String mechanism_telephone = masterMechanismEntity.getMechanism_telephone();
                Double latitude = masterMechanismEntity.getLatitude();
                Double longitude = masterMechanismEntity.getLongitude();
                String mechanism_addr = masterMechanismEntity.getMechanism_addr();
                GlideTools.loadImage(this, mechanism_logo, ivMechanismPhoto);
                tvMechanismName.setText(mechanism_name);
                tvMechanismTel.setText(mechanism_telephone);
                tvMechanismAddress.setText(mechanism_addr);
                double distance = GeometryUtil.GetLongDistance(
                        Double.valueOf(LoginHelper.getLongitude()),
                        Double.valueOf(LoginHelper.getLatitude()),
                        longitude,
                        latitude);
                String distanceStr = NumberUtils.getDistance(distance);
                tvMechanismDistance.setText(distanceStr);
                mechanismView.setOnClickListener(new OnDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {
                        // 进入机构详情
                        String mechanism_id = masterMechanismEntity.getId();
                        EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_DETAIL, mechanism_id));
                    }
                });
            }
        }
    }

    private void addMechanismTeacherInfoView(ExpandRVAdapter expandRVAdapter) {

        MasterSetPriceEntity.Map map = exclusiveCoursesEntity.getMap();
        if (map != null) {
            List<UserInfoEntity> mechanismEntityMasterList = map.getMechanismEntityMasterList();
            if (mechanismEntityMasterList == null) {
                mechanismEntityMasterList = new ArrayList<>();
            }
            if (mechanismEntityMasterList.size() > 0) {
                View mechanismTeacherView = View.inflate(this, R.layout.layout_mechanism_teacher_info, null);
                TextView tvTeacherNum = mechanismTeacherView.findViewById(R.id.layout_mechanism_teacher_info_tv_num);
                RecyclerViewFixed recyclerViewFixed = mechanismTeacherView.findViewById(R.id.layout_mechanism_teacher_info_rv);
                recyclerViewFixed.setHorizontal();
                recyclerViewFixed.setScrollingEnabled(false);
                tvTeacherNum.setText(String.format(getResources().getString(R.string.lecturer_Param),
                        "("
                                .concat(String.valueOf(mechanismEntityMasterList.size()))
                                .concat(")")));
                EmptyRVAdapter emptyRVAdapter = new EmptyRVAdapter<UserInfoEntity>(R.layout.item_mechanism_teacher, this, R.layout.layout_no_data) {
                    @Override
                    protected void convert(ViewHolder viewHolder, UserInfoEntity masterInfoEntity, int position) {
                        CircleImageView ivHead = viewHolder.getView(R.id.item_mechanism_teacher_iv_head);
                        TextView tvType = viewHolder.getView(R.id.item_mechanism_teacher_tv_type);
                        tvType.setVisibility(View.GONE);
                        TextView tvNickName = viewHolder.getView(R.id.item_mechanism_teacher_tv_nickName);
                        String avatar = masterInfoEntity.getAvatar();
                        String nick_name = masterInfoEntity.getNick_name();
                        tvNickName.setText(nick_name);
                        GlideTools.loadImage(context, avatar, ivHead);
                    }
                };

                recyclerViewFixed.setAdapter(emptyRVAdapter);
                emptyRVAdapter.setData(mechanismEntityMasterList);
                expandRVAdapter.addHanderView(mechanismTeacherView);
            }
        }
    }

    private static final String SP_LINE = "#\\$\\*";

    private void initHeadView(View headView) {
        ImageView ivCourseCover = headView.findViewById(R.id.layout_teach_pay_course_onsale_head_iv_course_cover);
        TextView tvTitle = headView.findViewById(R.id.layout_teach_pay_course_onsale_head_tv_course_title);
        TextView tvPayCount = headView.findViewById(R.id.layout_teach_pay_course_onsale_head_tv_course_payCount);
        TextView tvCategories = headView.findViewById(R.id.layout_teach_pay_course_onsale_head_tv_categories);
        TextView tvDiscoverAmount = headView.findViewById(R.id.layout_teach_pay_course_onsale_head_tv_discover_amount);
        TextView tvAmount = headView.findViewById(R.id.layout_teach_pay_course_onsale_head_tv_amount);
        TextView tvSinglePaymentAmount = headView.findViewById(R.id.layout_teach_pay_course_onsale_head_tv_single_payment_amount);
        TextView tvEdit = headView.findViewById(R.id.layout_teach_pay_course_onsale_head_tv_edit);
        TagFlowLayout tagFlowLayoutActivities = headView.findViewById(R.id.layout_teach_pay_course_onsale_head_TagFlowLayout_activities);
        tvJoinTeachPay = headView.findViewById(R.id.layout_teach_pay_course_onsale_head_tv_join_teachPay);
        final String cover = exclusiveCoursesEntity.getFace_url();
        String title = exclusiveCoursesEntity.getTitle();
        String label = exclusiveCoursesEntity.getLabel();
        String categories = exclusiveCoursesEntity.getCategories();
        String pay_num = exclusiveCoursesEntity.getPay_num();
        tvTitle.setText(title);
        String course_num = exclusiveCoursesEntity.getCourse_num();
        course_num = TextUtils.isEmpty(course_num) ? "1" : course_num;
        tvTitle.setText("【".concat(course_num).concat("节】").concat(title));
        if (TextUtils.isEmpty(categories)) {
            tvCategories.setText(label);
        } else {
            tvCategories.setText(TextUtils.isEmpty(label) ?
                    categories : categories.concat("/").concat(label));
        }
        ivCourseCover.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ivCourseCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(cover)) {
                    Intent intent = new Intent(UseCouponMechanismCourseDetailActivity.this, PhotoViewActivity.class);
                    intent.putExtra(PhotoViewActivity.CURRENT_POSITION, 0);
                    List<String> list = new ArrayList<>();
                    list.add(cover);
                    intent.putExtra(PhotoViewActivity.URLS, JsonUtil.toJson(list));
                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    int centerX = (int) (location[0] + view.getMeasuredWidth() / 2);
                    int centery = (int) (location[1] + view.getMeasuredHeight() / 2);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTX, centerX);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTY, centery);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_ENABLE, true);
                    startActivity(intent);
                }

            }
        });
        GlideTools.loadImage(this, cover, ivCourseCover);
        tvEdit.setVisibility(View.GONE);
        tvPayCount.setText(String.format(getResources().getString(R.string.purchased_num), String.valueOf(pay_num)));
        tvDiscoverAmount.setText(NumberUtils.transMoney("0"));
        // 单价
        String original_price = exclusiveCoursesEntity.getOriginal_price();
        original_price = String.valueOf(NumberUtils.tranToTwoDecimal(original_price));
        // 原价
        String amout = exclusiveCoursesEntity.getAmout();
        // 折扣价
        String discount_amout = exclusiveCoursesEntity.getDiscount_amout();
        discount_amout = String.valueOf(NumberUtils.tranToTwoDecimal(discount_amout));
        // 默认价格
        String default_discount_price = exclusiveCoursesEntity.getDefault_discount_price();
        if (Float.valueOf(discount_amout) > 0) {
            tvDiscoverAmount.setText(NumberUtils.transMoney(discount_amout));
            tvAmount.setText(NumberUtils.transMoney(amout));
            tvAmount.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        } else {
            tvDiscoverAmount.setText(NumberUtils.transMoney(default_discount_price));
            tvAmount.setText(NumberUtils.transMoney(amout));
            tvAmount.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        }

        tvSinglePaymentAmount.setVisibility(View.GONE);
        MasterSetPriceEntity.Map map = exclusiveCoursesEntity.getMap();
        tvJoinTeachPay.setVisibility(View.VISIBLE);
        BusinessActivityTypeEntity single_payment = container(exclusiveCoursesEntity, "single_payment");
        List<Integer> activitiesIconList = new ArrayList<>();
        if (single_payment != null) {
            tvSinglePaymentAmount.setText(NumberUtils.transMoney(original_price).concat("/节"));
            tvSinglePaymentAmount.setVisibility(View.VISIBLE);
            activitiesIconList.add(R.mipmap.single_pay);
        }
        BusinessActivityTypeEntity experience_specials = container(exclusiveCoursesEntity, "experience_specials");
        if (experience_specials != null) {
            activitiesIconList.add(R.mipmap.experience_specials);
        }
        BusinessActivityTypeEntity grouping = container(exclusiveCoursesEntity, "grouping");
        if (grouping != null) {
            activitiesIconList.add(R.mipmap.grouping_back);
        }
        if (activitiesIconList != null && activitiesIconList.size() > 0) {
            tagFlowLayoutActivities.setVisibility(View.VISIBLE);
            tagFlowLayoutActivities.setMaxSelectCount(0);
            tagFlowLayoutActivities.setAdapter(new TagAdapter<Integer>(activitiesIconList) {
                @Override
                public View getView(FlowLayout mInflater, int position, Integer id) {
                    ImageView iv = (ImageView) mInflater.inflate(
                            getContext(),
                            R.layout.layout_activityes_iv_icon,
                            null);
                    iv.setImageResource(id);
                    return iv;
                }
            });
        }
        if (map != null) {
            List<BusinessActivityTypeEntity> activityEntityList = map.getActivityEntityList();
            if (activityEntityList != null && activityEntityList.size() > 0) {
                tvJoinTeachPay.setVisibility(View.GONE);
            }
        }
        tvJoinTeachPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 加入教付保
                getPresenter().userFollowMechanismInsert(
                        LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                        exclusiveCoursesEntity.getMechanism_id()
                );
            }
        });
    }

    private BusinessActivityTypeEntity container(MasterSetPriceEntity masterSetPriceEntity, String type) {
        MasterSetPriceEntity.Map map = masterSetPriceEntity.getMap();
        if (map != null) {
            List<BusinessActivityTypeEntity> activityEntityList = map.getActivityEntityList();
            if (activityEntityList != null) {
                for (int i = 0; i < activityEntityList.size(); i++) {
                    BusinessActivityTypeEntity businessActivityTypeEntity = activityEntityList.get(i);
                    if (type.equals(businessActivityTypeEntity.getType())) {
                        return businessActivityTypeEntity;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param expandRVAdapter
     * @desc 老师介绍
     */
    private void initTeacherView(ExpandRVAdapter expandRVAdapter) {

        MasterSetPriceEntity.Map map = exclusiveCoursesEntity.getMap();
        if (map != null) {
            UserInfoEntity masterinfo = map.getMasterinfo();
            if (masterinfo != null) {
                View teacherView = View.inflate(this, R.layout.layout_mechanism_teacher, null);
                String photo = masterinfo.getAvatar();
                String full_name = masterinfo.getNick_name();
                CircleImageView ivCouver = teacherView.findViewById(R.id.layout_mechnism_teacher_iv_cover);
                TextView tvFullName = teacherView.findViewById(R.id.layout_mechnism_teacher_tv_fullName);
                tvFullName.setText(full_name);
                GlideTools.loadImage(this, photo, ivCouver);
                expandRVAdapter.addHanderView(teacherView);
            }
        }
    }

    private void addCourseInfoView(ExpandRVAdapter expandRVAdapter, MasterSetPriceEntity exclusiveCoursesEntity) {
        String introduction_url = exclusiveCoursesEntity.getIntroduction_url();
        List<String> imgs = new ArrayList<>();
        if (!TextUtils.isEmpty(introduction_url)) {
            String[] split = introduction_url.split(",");
            if (split != null) {
                for (int i = 0; i < split.length; i++) {
                    if (!TextUtils.isEmpty(split[i]))
                        imgs.add(split[i]);
                }
            }
        }
        if (imgs.size() > 0) {
            View courseDetailView = View.inflate(this, R.layout.layout_course_detailview, null);
            RecyclerViewFixed rvCourseDetail = courseDetailView.findViewById(R.id.layout_course_detailview_rv_course_detail);
            rvCourseDetail.setVertical(1);
            rvCourseDetail.setScrollingEnabled(false);
            rvCourseDetail.setHasFixedSize(true);
            CourseDescriptionRvAdapter descriptionRvAdapter = new CourseDescriptionRvAdapter(R.layout.item_desc_img, this);
            rvCourseDetail.setAdapter(descriptionRvAdapter);
            descriptionRvAdapter.setData(imgs);
            expandRVAdapter.addHanderView(courseDetailView);
            descriptionRvAdapter.notifyDataSetChanged();
        }
    }

    private void initCourseSyllabus(CommonRVAdapter realRvAdapter, MasterSetPriceEntity exclusiveCoursesEntity) {
        String title_url = exclusiveCoursesEntity.getTitile_url();
        if (!TextUtils.isEmpty(title_url)) {
            List<RecordingCourseSyllabusEntity> exclusiveCourseSyllabusEntities = new ArrayList<>();
            String[] titleurl = title_url.split("#\\$\\*");
            if (titleurl != null) {
                for (int i = 0; i < titleurl.length; i++) {
                    if (!TextUtils.isEmpty(titleurl[i])) {
                        String[] split = titleurl[i].split("\\$\\*");
                        RecordingCourseSyllabusEntity exclusiveCourseSyllabusEntity = new RecordingCourseSyllabusEntity(
                                split[0],
                                split.length == 2 ? split[1] : ""
                        );
                        exclusiveCourseSyllabusEntities.add(exclusiveCourseSyllabusEntity);
                    }

                }
            }
            if (realRvAdapter != null)
                realRvAdapter.setData(exclusiveCourseSyllabusEntities);
        }

    }

    @Override
    public void onMechanismCourseInfo(List<MasterSetPriceEntity> masterSetPriceEntities) {
        if (masterSetPriceEntities != null && masterSetPriceEntities.size() > 0) {
            exclusiveCoursesEntity = masterSetPriceEntities.get(0);
        }
        bindData();
    }


    @Override
    public void onUserFollowMechanismInsert() {
        ToastUtils.showShortToast(this, "已收到您的请求，我们会尽快邀请!");
    }

    @Override
    public void onMechanismCourseCommentList(List<MechanismCommentEntity> commentEntities) {

        if (getCurrentPage() == 1) {
            if (commentRvAdapter != null) {
                commentRvAdapter.setData(commentEntities);
            }
        } else {
            if (commentRvAdapter != null) {
                commentRvAdapter.addData(commentEntities);
            }
        }

        if (commentEntities == null || commentEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
        expandRVAdapter.notifyDataSetChanged();
    }

    @Override
    public int getPageSize() {
        if (loadPagerManager != null) {
            return loadPagerManager.getPageSize();
        }
        return 10;
    }

    @Override
    public int getCurrentPage() {
        if (loadPagerManager != null) {
            return loadPagerManager.getCurrentPage();
        }
        return 1;
    }

    @Override
    public void onUseCouponSuccess() {
        ToastUtils.showShortToast(this,"兑换成功，前往课程表预约吧!");
        finish();
    }

    @Override
    protected UseCouponMechanismCourseDetailContractPresenter createPresenter() {
        return new UseCouponMechanismCourseDetailContractPresenter();
    }

}
