package com.eghuihe.module_user.me.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.me.adapter.ClassRoomRvAdapter;
import com.eghuihe.module_user.me.mvp.ClassRoomManagerContract;
import com.eghuihe.module_user.me.mvp.ClassRoomManagerPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.model.ClassRoomEntity;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.ui.widget.title.OnCommonTitleListener;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

@Route(path = ARouterConfig.ME_CLASSROOMMANAGERACTIVITY)
public class ClassRoomManagerActivity extends BaseMvpRvRefreshTitleActivity<ClassRoomRvAdapter, ClassRoomManagerPresenter>
        implements ClassRoomManagerContract.View, ClassRoomRvAdapter.OnListener {

    private BaseDialog insertClassRoomDialog;
    private EditText etName;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("教室管理");
        commonTitle.setRightIcon1(R.mipmap.item_classroom_add);
        commonTitle.setRightTitleText("新增");
        commonTitle.setOnCommonTitleListener(
                new OnCommonTitleListener() {
                    @Override
                    public void onLeftTitleClicked(View view) {
                        finish();
                    }

                    @Override
                    public void onRightTitleClicked(View view) {
                        showInsertClassRoomDialog(true);
                    }
                }
        );
    }

    private void showInsertClassRoomDialog(final boolean isInsert) {
        insertClassRoomDialog = new BaseDialog(this) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_insert_classroom;
            }

            @Override
            protected void initParams() {
                etName = (EditText) getView(R.id.dialog_insert_classroom_et_name);
                TextView tvTitle = (TextView) getView(R.id.dialog_insert_classroom_tv_title);
                TextView tvUse = (TextView) getView(R.id.dialog_insert_classroom_tv_use);
                Switch switchUse = (Switch) getView(R.id.dialog_insert_classroom_Switch_use);
                tvTitle.setText(isInsert ? "新增教室" : "修改教室");
                tvUse.setVisibility(isInsert ? View.GONE : View.VISIBLE);
                switchUse.setVisibility(isInsert ? View.GONE : View.VISIBLE);
                switchUse.setChecked(true);
                if (!isInsert && classRoomEntity != null)
                    etName.setText(classRoomEntity.getName());
            }

            @Override
            protected void initEvents() {
                final Switch switchUse = (Switch) getView(R.id.dialog_insert_classroom_Switch_use);
                getView(R.id.dialog_insert_classroom_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                getView(R.id.dialog_insert_classroom_tv_sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (checkInput()) {
                            if (isInsert) {
                                getPresenter().mechanismClassroomInsert(
                                        LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                                        etName.getText().toString().trim()
                                );
                            } else {
                                if (classRoomEntity != null)
                                    getPresenter().mechanismClassroomUpdate(
                                            classRoomEntity.getId(),
                                            etName.getText().toString().trim(),
                                            switchUse.isChecked() ? "2" : "1"
                                    );
                            }

                        }
                    }
                });
            }
        };
        insertClassRoomDialog.setPerWidth(350f / 414);
        insertClassRoomDialog.setCancelOutside(false);
        insertClassRoomDialog.show();
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入教室名");
            return false;
        }
        return true;
    }

    @Override
    protected ClassRoomManagerPresenter createPresenter() {
        return new ClassRoomManagerPresenter();
    }

    @Override
    protected void initData() {
        doRefresh();
    }

    @Override
    protected void doRefresh() {

        getPresenter().queryManagerClassroomListPage(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                getPageSize(),
                getCurrentPage()
        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryManagerClassroomListPage(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                getPageSize(),
                getCurrentPage()
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
    protected ClassRoomRvAdapter getAdapter() {
        return new ClassRoomRvAdapter(R.layout.item_classroom, this, this);
    }

    @Override
    public void onInsertSuccess() {
        doRefresh();
    }

    @Override
    public void onUpdateSuccess() {
        doRefresh();
    }

    @Override
    public void onClassRoomList(List<ClassRoomEntity> classRoomEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(classRoomEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(classRoomEntities);
            }
        }
        if (classRoomEntities == null || classRoomEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    protected void onDestroy() {
        try {
            if (insertClassRoomDialog != null) {
                insertClassRoomDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    private ClassRoomEntity classRoomEntity;

    @Override
    public void onEditClicked(ViewHolder viewHolder, ClassRoomEntity classRoomEntity) {
        this.classRoomEntity = classRoomEntity;
        showInsertClassRoomDialog(false);
    }

    @Override
    public void onDeleteClicked(ViewHolder viewHolder, ClassRoomEntity classRoomEntity) {

    }
}
