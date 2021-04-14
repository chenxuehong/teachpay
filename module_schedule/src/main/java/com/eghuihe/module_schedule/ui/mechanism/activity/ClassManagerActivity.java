package com.eghuihe.module_schedule.ui.mechanism.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.adapter.ClassStatusRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.adapter.CourseSelectRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.adapter.MergeClassRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.fragment.classManager.CourseManagerFragment;
import com.eghuihe.module_schedule.ui.mechanism.mvp.ClassManagerContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.ClassManagerPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ClassStatusBean;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismClassEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 班级管理
 */
@Route(path = ARouterConfig.SCHEDULE_CLASSMANAGERACTIVITY)
public class ClassManagerActivity extends BaseMvpTitleActivity<ClassManagerPresenter>
        implements ClassManagerContract.View, MergeClassRvAdapter.OnListener, CourseSelectRvAdapter.OnListener, ClassStatusRvAdapter.OnListener {

    private static final int REQUEST_CODE_SELECT_COURSE = 101;
    @BindView(R2.id.activity_class_manager_framelayout_container)
    FrameLayout frameLayout;
    @BindView(R2.id.activity_class_manager_ll_merge)
    LinearLayout llMerge;
    @BindView(R2.id.activity_class_manager_ll_course)
    LinearLayout llCourse;
    @BindView(R2.id.activity_class_manager_ll_status)
    LinearLayout llStatus;
    private BaseDialog createClassDialog;
    private BaseDialog mergeClassDialog;
    private MasterSetPriceEntity masterSetPriceEntity;
    private TextView tvTitle;
    private CourseManagerFragment courseManagerFragment;

    private MechanismClassEntity mergeToMechanismClassEntity;
    private PopupWindow popCourseSelectWindow;
    private List<MasterSetPriceEntity> exclusiveCoursesEntities;
    private CourseSelectRvAdapter courseSelectRvAdapter;
    private ClassStatusRvAdapter classStatusRvAdapter;
    private PopupWindow popStatusSelectWindow;

    @OnClick({
            R2.id.activity_class_manager_tv_cancel_merge,
            R2.id.activity_class_manager_tv_sure_merge,
            R2.id.activity_class_manager_ll_course,
            R2.id.activity_class_manager_ll_status
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.activity_class_manager_tv_cancel_merge) {
            showMergeView(false);
        } else if (view.getId() == R.id.activity_class_manager_tv_sure_merge) {
            if (courseManagerFragment != null) {
                List<MechanismClassEntity> mechanismClassEntities = courseManagerFragment.getMechanismClassEntities();
                if (mechanismClassEntities == null || mechanismClassEntities.size() < 2) {
                    ToastUtils.showShortToast(this, "需要选择两个以上班级进行合并");
                    return;
                }
                showMergeClassDialog(mechanismClassEntities);
            }
        } else if (view.getId() == R.id.activity_class_manager_ll_course) {
            // 弹出课程选择对话框
            popCourseSelectWindow(frameLayout);
        } else if (view.getId() == R.id.activity_class_manager_ll_status) {
            // 排课状态选择对话框
            popStatusSelectWindow(frameLayout);
        }
    }

    private void popCourseSelectWindow(View parentView) {
        if (exclusiveCoursesEntities != null) {
            View contentView = View.inflate(ClassManagerActivity.this, R.layout.pop_course_select, null);
            popCourseSelectWindow = PopWindowUtils.popupDownWindowNoAlpha(
                    contentView,
                    parentView);
            RecyclerViewFixed rvCourseList = contentView.findViewById(R.id.pop_course_select_rv_list);
            rvCourseList.setVertical(1);
            rvCourseList.setScrollingEnabled(false);
            rvCourseList.setAdapter(courseSelectRvAdapter);
            popCourseSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    updateLLCourseColor(false);
                }
            });
            updateLLCourseColor(true);
        }
    }

    private void updateLLCourseColor(boolean isChecked) {
        if (llCourse != null) {
            int childCount = llCourse.getChildCount();
            if (childCount > 0) {
                View childAt = llCourse.getChildAt(0);
                if (childAt instanceof TextView) {
                    TextView textView = (TextView) childAt;
                    textView.setTextColor(isChecked ? getResources().getColor(R.color.mainColor) : getResources().getColor(R.color.color_333333));
                }
            }
        }
        if (courseManagerFragment != null)
            courseManagerFragment.changeAlpha(isChecked);
    }

    private void popStatusSelectWindow(View parentView) {
        View contentView = View.inflate(ClassManagerActivity.this, R.layout.pop_status_select, null);
        popStatusSelectWindow = PopWindowUtils.popupDownWindowNoAlpha(
                contentView,
                parentView);
        RecyclerViewFixed rvStatusList = contentView.findViewById(R.id.pop_status_select_rv_list);
        rvStatusList.setVertical(1);
        rvStatusList.setScrollingEnabled(false);
        rvStatusList.setAdapter(classStatusRvAdapter);
        popStatusSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                updateLLStatusColor(false);
            }
        });
        updateLLStatusColor(true);
    }

    private void updateLLStatusColor(boolean isChecked) {
        if (llStatus != null) {
            int childCount = llStatus.getChildCount();
            if (childCount > 0) {
                View childAt = llStatus.getChildAt(0);
                if (childAt instanceof TextView) {
                    TextView textView = (TextView) childAt;
                    textView.setTextColor(isChecked ? getResources().getColor(R.color.mainColor) : getResources().getColor(R.color.color_333333));
                }
            }
        }
        if (courseManagerFragment != null)
            courseManagerFragment.changeAlpha(isChecked);
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("班级管理");
        customerTitle.setRightImg(R.mipmap.class_more);
        customerTitle.setImgRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View contentView = View.inflate(ClassManagerActivity.this, R.layout.pop_class_manager, null);
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
                contentView.findViewById(R.id.pop_class_manager_tv_merge).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        window.dismiss();
                        showMergeView(true);
                    }
                });
            }
        });
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
                        Intent intent = new Intent(ClassManagerActivity.this, MechanismCourseListActivity.class);
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

    public void showMergeView(boolean isShow) {
        if (llMerge != null) {
            llMerge.setVisibility(isShow ? View.VISIBLE : View.GONE);
            if (courseManagerFragment != null) {
                courseManagerFragment.showMerge(isShow, this);
            }
        }
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

    private void showMergeClassDialog(List<MechanismClassEntity> mechanismClassEntities) {
        mergeClassDialog = new BaseDialog(this) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_merge_class;
            }

            @Override
            protected void initParams() {
                RecyclerViewFixed rvClassList = (RecyclerViewFixed) getView(R.id.dialog_merge_class_rv_classList);
                rvClassList.setVertical(1);
                rvClassList.setScrollingEnabled(false);
                rvClassList.setAdapter(new MergeClassRvAdapter(R.layout.item_merge_class, ClassManagerActivity.this, mechanismClassEntities, ClassManagerActivity.this));
            }

            @Override
            protected void initEvents() {
                getView(R.id.dialog_merge_class_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                getView(R.id.dialog_merge_class_tv_sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mergeToMechanismClassEntity == null) {
                            ToastUtils.showShortToast(ClassManagerActivity.this, "请选择要合并到的班级");
                            return;
                        }
                        dismiss();
                        showMergeView(false);
                        String merger_ids = getMergeClassId(mechanismClassEntities);
                        getPresenter().updateMergerClass(
                                mergeToMechanismClassEntity.getId(),
                                merger_ids,
                                "2"
                        );
                        mergeToMechanismClassEntity = null;
                    }
                });
            }
        };
        mergeClassDialog.setPerWidth(243f / 375);
        mergeClassDialog.setCancelOutside(false);
        mergeClassDialog.show();
    }

    private String getMergeClassId(List<MechanismClassEntity> mechanismClassEntities) {
        List<String> list = new ArrayList<>();
        if (mechanismClassEntities != null) {
            for (int i = 0; i < mechanismClassEntities.size(); i++) {
                MechanismClassEntity mechanismClassEntity = mechanismClassEntities.get(i);
                String id = mechanismClassEntity.getId();
                if (!id.equals(mergeToMechanismClassEntity.getId()))
                    list.add(id);
            }
        }
        return StringUtils.list2String(list, ",");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_class_manager;
    }

    @Override
    protected void initView() {
        super.initView();
        courseManagerFragment = new CourseManagerFragment();
        getSupportFragmentManager().beginTransaction().replace(
                frameLayout.getId(),
                courseManagerFragment
        ).commitNowAllowingStateLoss();
    }

    @Override
    protected void initData() {
        List<ClassStatusBean> classStatusBeans = new ArrayList<>();
        classStatusBeans.add(new ClassStatusBean("默认", null));
        classStatusBeans.add(new ClassStatusBean("已排课", true));
        classStatusBeans.add(new ClassStatusBean("未排课", false));
        classStatusRvAdapter = new ClassStatusRvAdapter(
                R.layout.item_class_status_select,
                this,
                classStatusBeans,
                this);
        getPresenter().queryExclusiveCourseList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                "2",
                "fixed_scheduling",
                null,
                null
        );
    }

    @Override
    protected ClassManagerPresenter createPresenter() {
        return new ClassManagerPresenter();
    }

    @Override
    protected void onDestroy() {
        try {
            if (createClassDialog != null) {
                createClassDialog.dismiss();
            }
            if (mergeClassDialog != null) {
                mergeClassDialog.dismiss();
            }
            if (popCourseSelectWindow != null) {
                popCourseSelectWindow.dismiss();
            }
            if (popStatusSelectWindow != null) {
                popStatusSelectWindow.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onInsertMechanismClassSuccess() {
        EventBusUtils.sendEvent(new Event(EventAction.INSERT_MECHANISMCLASS_SUCCESS));
    }

    @Override
    public void onUpdateMergeClassSuccess() {
        EventBusUtils.sendEvent(new Event(EventAction.INSERT_MECHANISMCLASS_SUCCESS));
    }

    @Override
    public void showExclusiveCourseList(List<MasterSetPriceEntity> exclusiveCoursesEntities) {
        MasterSetPriceEntity masterSetPriceEntity = new MasterSetPriceEntity();
        masterSetPriceEntity.setTitle("默认");
        masterSetPriceEntity.setId("-1");
        exclusiveCoursesEntities.add(0, masterSetPriceEntity);
        this.exclusiveCoursesEntities = exclusiveCoursesEntities;
        courseSelectRvAdapter = new CourseSelectRvAdapter(
                R.layout.item_course_select,
                this,
                exclusiveCoursesEntities,
                this);
    }

    @Override
    public void onCheckedItem(MechanismClassEntity mechanismClassEntity) {
        mergeToMechanismClassEntity = mechanismClassEntity;
    }

    @Override
    public void onItemCourseClicked(MasterSetPriceEntity masterSetPriceEntity) {
        if (courseManagerFragment != null) {
            courseManagerFragment.updateByMasterSetPriceEntity(masterSetPriceEntity);
        }
        if (popCourseSelectWindow != null) {
            popCourseSelectWindow.dismiss();
        }
    }

    @Override
    public void onItemClassStatusClicked(ClassStatusBean classStatusBean) {
        if (courseManagerFragment != null) {
            courseManagerFragment.updateByStatus(classStatusBean.is_scheduling_over);
        }
        if (popStatusSelectWindow != null) {
            popStatusSelectWindow.dismiss();
        }
    }
}
