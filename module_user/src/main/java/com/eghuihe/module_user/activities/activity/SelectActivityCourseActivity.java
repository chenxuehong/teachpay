package com.eghuihe.module_user.activities.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.activities.adapter.ActivityCourseRvAdapter;
import com.eghuihe.module_user.activities.mvp.SelectActivityCourseContract;
import com.eghuihe.module_user.activities.mvp.SelectActivityCoursePresenter;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

public class SelectActivityCourseActivity extends BaseMvpRvRefreshTitleActivity<ActivityCourseRvAdapter, SelectActivityCoursePresenter>
        implements SelectActivityCourseContract.View {

    private BusinessActivityTypeEntity businessActivityTypeEntity;
    private BaseDialog setSinglePriceOrSaleDialog;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("选择活动课程");
    }

    @Override
    protected void doRefresh() {

        getPresenter().queryCourseList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                "2",
                null,
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryCourseList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                "2",
                null,
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(this, 15);
    }

    @Override
    protected ActivityCourseRvAdapter getAdapter() {
        return new ActivityCourseRvAdapter(
                R.layout.item_select_activity_course,
                this);
    }

    @Override
    protected SelectActivityCoursePresenter createPresenter() {
        return new SelectActivityCoursePresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(ArgumentsConfig.KEY_BUSINESS_ACTIVITY_TYPE);
            businessActivityTypeEntity = JsonUtil.fromJson(json, BusinessActivityTypeEntity.class);
        }
        FrameLayout flBottom = getFlBottom();
        if (flBottom != null) {
            View bottomView = View.inflate(this, R.layout.layout_bottom_finish, null);
            bottomView.findViewById(R.id.layout_bottom_finish_tv_finish).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (businessActivityTypeEntity != null) {
                        if ("single_payment".equals(businessActivityTypeEntity.getType())
                                || "salesCourse".equals(businessActivityTypeEntity.getType())) {
                            showSetSinglePriceOrSaleDialog();
                        } else if ("grouping".equals(businessActivityTypeEntity.getType())) {
                            grouping();
                        } else if ("experience_specials".equals(businessActivityTypeEntity.getType())) {
                            cunjie();
                        }else if ("deductions_coupon".equals(businessActivityTypeEntity.getType())) {
                            deductionsCoupon();
                        }
                    }
                }
            });
            flBottom.addView(bottomView);
        }
    }

    private void showSetSinglePriceOrSaleDialog() {
        setSinglePriceOrSaleDialog = new BaseDialog(this) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_set_single_price;
            }

            @Override
            protected void initParams() {
                super.initParams();
                TextView tvTitle = (TextView) getView(R.id.dialog_set_single_price_tv_title);
                TextView tvContent = (TextView) getView(R.id.dialog_set_single_price_tv_content);
                if ("single_payment".equals(businessActivityTypeEntity.getType())) {
                    tvTitle.setText("输入单节课价格");
                    tvContent.setText("请设置参加活动商品的单节课价格");
                } else {
                    tvTitle.setText("输入活动价格");
                    tvContent.setText("请设置参加活动商品的活动价格");
                }
            }

            @Override
            protected void initEvents() {
                final EditText etPrice = (EditText) getView(R.id.dialog_set_single_price_et_content);
                getView(R.id.dialog_set_single_price_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                getView(R.id.dialog_set_single_price_tv_sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if ("single_payment".equals(businessActivityTypeEntity.getType())) {
                            setSinglePrice(etPrice.getText().toString().trim());
                        } else if ("salesCourse".equals(businessActivityTypeEntity.getType())) {
                            setSalePrice(etPrice.getText().toString().trim());
                        }
                    }
                });
            }
        };
        setSinglePriceOrSaleDialog.setPerWidth(243f / 375);
        setSinglePriceOrSaleDialog.setCancelOutside(false);
        setSinglePriceOrSaleDialog.show();
    }

    private void setSinglePrice(String price) {
        if (adapter == null || adapter.getSelectedMasterSetPriceList().size() == 0) {
            ToastUtils.showShortToast(this, "请选择课程");
            return;
        }
        List<String> selectedMasterSetPriceList = adapter.getSelectedMasterSetPriceList();
        String master_set_price_ids = StringUtils.list2String(selectedMasterSetPriceList, ",");
        getPresenter().insertBusinessSinglePaymentActivity(
                businessActivityTypeEntity.isIs_new_customers(),
                master_set_price_ids,
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                businessActivityTypeEntity.getStart_time(),
                businessActivityTypeEntity.getEnd_time(),
                "single_payment",
                businessActivityTypeEntity.getTags(),
                price
        );
    }

    private void setSalePrice(String price) {
        if (adapter == null || adapter.getSelectedMasterSetPriceList().size() == 0) {
            ToastUtils.showShortToast(this, "请选择课程");
            return;
        }
        List<String> selectedMasterSetPriceList = adapter.getSelectedMasterSetPriceList();
        String master_set_price_ids = StringUtils.list2String(selectedMasterSetPriceList, ",");
        getPresenter().insertBusinessSalesCourseActivity(
                businessActivityTypeEntity.isIs_new_customers(),
                master_set_price_ids,
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                businessActivityTypeEntity.getStart_time(),
                businessActivityTypeEntity.getEnd_time(),
                "salesCourse",
                businessActivityTypeEntity.getTags(),
                businessActivityTypeEntity.getCoupon_time(),
                price
        );
    }

    private void grouping() {
        if (adapter == null || adapter.getSelectedMasterSetPriceList().size() == 0) {
            ToastUtils.showShortToast(this, "请选择课程");
            return;
        }
        List<String> selectedMasterSetPriceList = adapter.getSelectedMasterSetPriceList();
        String master_set_price_ids = StringUtils.list2String(selectedMasterSetPriceList, ",");
        getPresenter().insertBusinessGroupingActivity(
                businessActivityTypeEntity.isIs_new_customers(),
                master_set_price_ids,
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                businessActivityTypeEntity.getStart_time(),
                businessActivityTypeEntity.getEnd_time(),
                "grouping",
                businessActivityTypeEntity.getTags(),
                businessActivityTypeEntity.getNumber_of_people(),
                businessActivityTypeEntity.getEach_time_percentage(),
                businessActivityTypeEntity.getEach_time_percentage_max()
        );
    }

    private void cunjie() {
        if (adapter == null || adapter.getSelectedMasterSetPriceList().size() == 0) {
            ToastUtils.showShortToast(this, "请选择课程");
            return;
        }
        List<String> selectedMasterSetPriceList = adapter.getSelectedMasterSetPriceList();
        String master_set_price_ids = StringUtils.list2String(selectedMasterSetPriceList, ",");
        getPresenter().insertBusinessExperienceSpecialsActivity(
                businessActivityTypeEntity.isIs_new_customers(),
                master_set_price_ids,
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                businessActivityTypeEntity.getStart_time(),
                businessActivityTypeEntity.getEnd_time(),
                "experience_specials",
                businessActivityTypeEntity.getTags(),
                businessActivityTypeEntity.getCoupon_time()
        );
    }

    private void deductionsCoupon() {
        if (adapter == null || adapter.getSelectedMasterSetPriceList().size() == 0) {
            ToastUtils.showShortToast(this, "请选择课程");
            return;
        }
        List<String> selectedMasterSetPriceList = adapter.getSelectedMasterSetPriceList();
        String master_set_price_ids = StringUtils.list2String(selectedMasterSetPriceList, ",");
        getPresenter().insertBusinessExperienceSpecialsActivity(
                businessActivityTypeEntity.isIs_new_customers(),
                master_set_price_ids,
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                businessActivityTypeEntity.getStart_time(),
                businessActivityTypeEntity.getEnd_time(),
                "deductions_coupon ",
                businessActivityTypeEntity.getTags(),
                businessActivityTypeEntity.getCoupon_time()
        );
    }

    @Override
    protected void initData() {
        triggerRefreshData();
    }

    @Override
    public void showCourseList(List<MasterSetPriceEntity> exclusiveCoursesEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                if (businessActivityTypeEntity != null)
                    adapter.setType(businessActivityTypeEntity.getType());
                adapter.setData(exclusiveCoursesEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(exclusiveCoursesEntities);
            }
        }

        if (exclusiveCoursesEntities == null || exclusiveCoursesEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void onInsertBusinessActivitySuccess() {
        ToastUtils.showShortToast(this, "设置成功");
        if (setSinglePriceOrSaleDialog != null) {
            setSinglePriceOrSaleDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        if (setSinglePriceOrSaleDialog != null) {
            setSinglePriceOrSaleDialog.dismiss();
        }
        super.onDestroy();
    }
}
