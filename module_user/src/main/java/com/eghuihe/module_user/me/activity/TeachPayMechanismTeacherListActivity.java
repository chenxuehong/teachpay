package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.me.adapter.TeachPayMechanismTeacherListRvAdapter;
import com.eghuihe.module_user.me.mvp.TeachPayMechanismTeacherListContract;
import com.eghuihe.module_user.me.mvp.TeachPayMechanismTeacherListPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ExtraEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

@Route(path = ARouterConfig.ME_TEACHPAY_MECHANISM_TEACHER_LIST_ACTIVITY)
public class TeachPayMechanismTeacherListActivity extends BaseMvpRvRefreshTitleActivity<EmptyRVAdapter, TeachPayMechanismTeacherListPresenter>
        implements TeachPayMechanismTeacherListContract.View {

    private static final int REQUEST_CODE_INSERT_TEACHER = 100;
    private String is_select;
    private String status;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("机构老师");
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected void initView() {
        super.initView();
        View bottomView = LayoutInflater.from(this).inflate(R.layout.layout_mechanism_teacher_add, null);
        bottomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(InsertMechanismTeacherActivity.class, REQUEST_CODE_INSERT_TEACHER);
            }
        });
        getFlBottom().addView(bottomView);
        getSmartRefreshLayout().setBackgroundColor(getResources().getColor(R.color.color_88e6e6e6));
    }

    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(this, 15);
    }

    @Override
    protected EmptyRVAdapter getAdapter() {
        return new TeachPayMechanismTeacherListRvAdapter(R.layout.item_teachpay_mechanism_teacher, this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            is_select = intent.getStringExtra(ArgumentsConfig.KEY_IS_SELECT);
            status = intent.getStringExtra(ArgumentsConfig.KEY_STATUS);
        }
        triggerRefreshData();
    }

    @Override
    protected void doRefresh() {
        getPresenter().queryMechanismMasterInfoList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                getCurrentPage(),
                getPageSize(),
                "teach_paypal",
                status
        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryMechanismMasterInfoList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                getCurrentPage(),
                getPageSize(),
                "teach_paypal",
                status
        );
    }

    @Override
    protected TeachPayMechanismTeacherListPresenter createPresenter() {
        return new TeachPayMechanismTeacherListPresenter();
    }

    @Override
    public void onMechanismMasterInfoList(List<MasterInfoHomeModel.MasterInfoHomeEntity> masterInfoHomeEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();

            if (adapter != null) {
                adapter.setOnClickListener(new EmptyRVAdapter.OnItemClickListener<MasterInfoHomeModel.MasterInfoHomeEntity>() {
                    @Override
                    public void onItemClicked(View v, MasterInfoHomeModel.MasterInfoHomeEntity masterInfoHomeEntity, int position) {
                        if ("true".equals(is_select)) {
                            // 携带数据
                            EventBusUtils.sendEvent(new Event(EventAction.KEY_TEACHER_ENTITY, masterInfoHomeEntity));
                            AppManager.getInstance().finishActivity(TeachPayMechanismTeacherListActivity.class);
                        } else {
                            startActivityForResult(UpdateMechanismTeacherActivity.class,
                                    new ExtraEntity(UpdateMechanismTeacherActivity.KEY_DATA, masterInfoHomeEntity), REQUEST_CODE_INSERT_TEACHER);
                        }

                    }

                    @Override
                    public void onItemLongClicked(View v, MasterInfoHomeModel.MasterInfoHomeEntity masterInfoHomeEntity, int position) {

                    }
                });
                adapter.setData(masterInfoHomeEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(masterInfoHomeEntities);
            }
        }
        if (masterInfoHomeEntities == null || masterInfoHomeEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_INSERT_TEACHER) {
            triggerRefreshData();
        }
    }
}
