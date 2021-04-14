package com.eghuihe.module_schedule.ui.mechanism.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.mechanism.adapter.SelectClassListRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.mvp.SelectClassListContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.SelectClassListPresenter;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismClassEntity;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.ui.widget.title.OnCommonTitleListener;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

public class SelectClassListActivity extends BaseMvpRvRefreshTitleActivity<SelectClassListRvAdapter, SelectClassListPresenter>
        implements SelectClassListContract.View, SelectClassListRvAdapter.OnListener {

    private static final int REQUEST_CODE_SELECT_COURSE = 1001;
    private TextView tvTitle;
    private BaseDialog createClassDialog;
    private MasterSetPriceEntity masterSetPriceEntity;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("选择班级");
        commonTitle.setRightIcon1(R.mipmap.class_more);
        commonTitle.setOnCommonTitleListener(
                new OnCommonTitleListener() {
                    @Override
                    public void onLeftTitleClicked(View view) {
                        finish();
                    }

                    @Override
                    public void onRightIcon1Clicked(View view) {
                        int location[] = new int[2];
                        view.getLocationInWindow(location);
                        View contentView = View.inflate(SelectClassListActivity.this, R.layout.pop_class_manager, null);
                        final PopupWindow window = PopWindowUtils.popupDownWindow(
                                contentView,
                                view);
                        contentView.findViewById(R.id.pop_class_manager_tv_create).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                window.dismiss();
                                showCreateClassDialog();
                            }
                        });
                        contentView.findViewById(R.id.pop_class_manager_tv_merge).setVisibility(View.GONE);
                    }
                }
        );
    }
    private void showCreateClassDialog() {
        createClassDialog = new BaseDialog(this) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_create_class;
            }

            @Override
            protected void initEvents() {
                EditText etClassName = (EditText) getView(R.id.dialog_create_class_et_className);
                tvTitle = (TextView) getView(R.id.dialog_create_class_tv_title);
                getView(R.id.dialog_create_class_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                getView(R.id.dialog_create_class_tv_sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (checkCreateClassInput(etClassName)) {
                            getPresenter().insertMechanismClasses(
                                    LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                                    etClassName.getText().toString(),
                                    masterSetPriceEntity.getId(),
                                    masterSetPriceEntity.getConnect_peoplenum()
                            );
                        }
                    }
                });
                getView(R.id.dialog_create_class_tv_title).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SelectClassListActivity.this, MechanismCourseListActivity.class);
                        intent.putExtra(ArgumentsConfig.KEY_APPOINTMENT_TYPE, "fixed_scheduling");
                        startActivityForResult(intent, REQUEST_CODE_SELECT_COURSE);
                    }
                });
            }
        };
        createClassDialog.setPerWidth(243f / 375);
        createClassDialog.setCancelOutside(false);
        createClassDialog.show();
    }

    private boolean checkCreateClassInput(EditText etClassName) {
        if (TextUtils.isEmpty(etClassName.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入班级名称");
            return false;
        }
        if (TextUtils.isEmpty(tvTitle.getText().toString())) {
            ToastUtils.showShortToast(this, "请选择课程");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_COURSE) {
            if (data != null) {
                String json = data.getStringExtra(MechanismCourseListActivity.KEY_COURSE_ENTITY);
                masterSetPriceEntity = JsonUtil.fromJson(json, MasterSetPriceEntity.class);
                if (tvTitle != null && masterSetPriceEntity != null)
                    tvTitle.setText(masterSetPriceEntity.getTitle());
            }
        }
    }

    @Override
    protected void doRefresh() {
        getPresenter().queryMechanismClasses(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                getCurrentPage(),
                getPageSize(),
                masterSetPriceEntity.getId(),
                null,
                "2"
        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryMechanismClasses(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                getCurrentPage(),
                getPageSize(),
                masterSetPriceEntity.getId(),
                null,
                "2"
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
    protected SelectClassListRvAdapter getAdapter() {
        return new SelectClassListRvAdapter(R.layout.item_class_select, this, this);
    }

    @Override
    protected SelectClassListPresenter createPresenter() {
        return new SelectClassListPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(ArgumentsConfig.KEY_MASTERSETPRICEENTITY);
            masterSetPriceEntity = JsonUtil.fromJson(json, MasterSetPriceEntity.class);
        }
    }

    @Override
    protected void initData() {
        triggerRefreshData();
    }

    @Override
    public void onMechanismClassList(List<MechanismClassEntity> mechanismClassEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(mechanismClassEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(mechanismClassEntities);
            }
        }

        if (mechanismClassEntities == null || mechanismClassEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void onInsertMechanismClassSuccess() {
        doRefresh();
    }

    @Override
    public void onArranged(MechanismClassEntity mechanismClassEntity) {

        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        intent.putExtra(ArgumentsConfig.KEY_MECHANISM_MECHANISMCLASS,
                JsonUtil.toJson(mechanismClassEntity));
        finish();
    }

    @Override
    protected void onDestroy() {
        try {
            if (createClassDialog != null) {
                createClassDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
