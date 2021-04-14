package com.eghuihe.module_user.me.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.bean.StageSyllabusBean;
import com.eghuihe.module_user.bean.params.MechanismCourseParam;
import com.eghuihe.module_user.bean.params.StageSyllabusParam;
import com.eghuihe.module_user.me.activity.InsertMechanismCourseActivity;
import com.eghuihe.module_user.me.activity.SelectMechanismCourseTypeActivity;
import com.eghuihe.module_user.me.activity.SetMechanismCourseSyllabusActivity;
import com.eghuihe.module_user.me.activity.UpdateMechanismCourseSyllabusActivity;
import com.eghuihe.module_user.me.activity.UpdateTeachPayMechanismCourseActivity;
import com.eghuihe.module_user.me.adapter.StageSyllabusRvAdapter;
import com.eghuihe.module_user.me.mvp.UpdateMechanismCourseStep2Contract;
import com.eghuihe.module_user.me.mvp.UpdateMechanismCourseStep2Presenter;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.AppManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateStageSyllsabusFragment extends BaseMvpFragment<UpdateMechanismCourseStep2Presenter>
        implements UpdateMechanismCourseStep2Contract.View, StageSyllabusRvAdapter.OnListener {

    private MechanismCourseParam mechanismCourseParam;

    @BindView(R2.id.fragment_stage_syllsabus_rv)
    RecyclerViewFixed rvSageSyllsabus;
    @BindView(R2.id.layout_stage_syllabus_insert_tv_add)
    TextView tvSyllabusAdd;
    @BindView(R2.id.layout_stage_syllabus_insert_tv_delete)
    TextView tvDelete;
    private StageSyllabusRvAdapter stageSyllabusRvAdapter;
    public static final String SP_LINE = "#$*";

    @OnClick({
            R2.id.layout_stage_syllabus_insert_tv_add,
            R2.id.layout_stage_syllabus_insert_tv_delete,
            R2.id.fragment_stage_syllsabus_tv_save_as_draft,
            R2.id.fragment_stage_syllsabus_tv_commit
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.layout_stage_syllabus_insert_tv_add) {
            if (stageSyllabusRvAdapter != null && stageSyllabusRvAdapter.checkNextInput()) {
                if (mechanismCourseParam != null) {
                    String courseNumber = mechanismCourseParam.course_num;
                    stageSyllabusRvAdapter.addData(new StageSyllabusBean(stageSyllabusRvAdapter.getCurStartIndex(),
                            courseNumber), stageSyllabusRvAdapter.getItemCount());
                }
            }
        } else if (view.getId() == R.id.layout_stage_syllabus_insert_tv_delete) {
            if (stageSyllabusRvAdapter != null && stageSyllabusRvAdapter.getItemCount() <= 1) {
                return;
            }
            if (stageSyllabusRvAdapter != null) {
                stageSyllabusRvAdapter.remove(stageSyllabusRvAdapter.getItemCount() - 1);
            }
        } else if (view.getId() == R.id.fragment_stage_syllsabus_tv_save_as_draft) {
            if (inputCheck())
                commitData(3);
        } else if (view.getId() == R.id.fragment_stage_syllsabus_tv_commit) {
            if (inputCheck())
                commitData(2);
        }
    }

    private boolean inputCheck() {
        if (mechanismCourseParam == null) {
            return false;
        }
        // 阶段性大纲
        if (stageSyllabusRvAdapter != null && !stageSyllabusRvAdapter.checkInput()) {
            return false;
        }
        return true;
    }

    private void commitData(int status) {
        String titile_url = getSyllabus();
        mechanismCourseParam.setTitile_url(titile_url);
        mechanismCourseParam.setStatus(status);
        getPresenter().updateMasterSetPrice(
                mechanismCourseParam.id,
                null,
                mechanismCourseParam.appointment_type,
                mechanismCourseParam.title,
                mechanismCourseParam.label,
                mechanismCourseParam.course_num,
                mechanismCourseParam.amout,
                null,
                mechanismCourseParam.discount_amout,
                mechanismCourseParam.introduction_content,
                mechanismCourseParam.status,
                mechanismCourseParam.titile_url,
                mechanismCourseParam.first_free,
                mechanismCourseParam.type,
                mechanismCourseParam.connect_peoplenum,
                mechanismCourseParam.mechanism_id,
                mechanismCourseParam.face_url,
                mechanismCourseParam.introduction_url,
                mechanismCourseParam.duration,
                mechanismCourseParam.price_list,
                mechanismCourseParam.is_attend_activities,
                mechanismCourseParam.start_time,
                mechanismCourseParam.end_time,
                mechanismCourseParam.activity_price,
                mechanismCourseParam.categories
        );
    }

    private String getSyllabus() {
        StringBuffer sbSyllabus = new StringBuffer();
        if (stageSyllabusRvAdapter != null) {
            Map<Integer, StageSyllabusParam> map = stageSyllabusRvAdapter.getMap();
            if (map != null) {
                for (int i = 0; i < map.size(); i++) {
                    StageSyllabusParam stageSyllabusParam = map.get(i);
                    String startIndex = stageSyllabusParam.startIndex;
                    String endIndex = stageSyllabusParam.endIndex;
                    for (int j = Integer.valueOf(startIndex) - 1; j < Integer.valueOf(endIndex); j++) {
                        sbSyllabus.append(stageSyllabusParam.title.concat("-").concat(String.valueOf(j + 1)));
                        if (j != Integer.valueOf(endIndex) - 1) {
                            sbSyllabus.append(SP_LINE);
                        } else if (i != map.size() - 1) {
                            sbSyllabus.append(SP_LINE);
                        }
                    }
                }
            }
        }
        return sbSyllabus.toString().trim();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stage_syllsabus;
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle arguments = getArguments();
        if (arguments != null) {
            String json = arguments.getString(ArgumentsConfig.KEY_MECHANISMCOURSEPARAM);
            mechanismCourseParam = JsonUtil.fromJson(json, MechanismCourseParam.class);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        showStageSyllsabusRvAdapter();
    }

    /**
     * @desc 显示阶段性大纲
     */
    private void showStageSyllsabusRvAdapter() {
        if (mechanismCourseParam != null) {
            rvSageSyllsabus.setVertical(1);
            rvSageSyllsabus.setScrollingEnabled(false);
            List<StageSyllabusBean> stageSyllabusBeans = new ArrayList<>();
            String courseNumber = mechanismCourseParam.course_num;
            stageSyllabusBeans.add(new StageSyllabusBean("1", courseNumber));
            stageSyllabusRvAdapter = new StageSyllabusRvAdapter(
                    R.layout.item_stage_syllabus,
                    getContext(),
                    stageSyllabusBeans,
                    this);
            rvSageSyllsabus.setAdapter(stageSyllabusRvAdapter);
        }
    }

    @Override
    protected UpdateMechanismCourseStep2Presenter createPresenter() {
        return new UpdateMechanismCourseStep2Presenter();
    }

    @Override
    public void prohibitOrAllowAdd(boolean isAllow) {
        if (tvSyllabusAdd != null)
            tvSyllabusAdd.setEnabled(isAllow);
    }

    @Override
    public void onUpdateMechanismCourseSuccess() {
        EventBusUtils.sendEvent(new Event(EventAction.INSERT_MECHANISM_COURSE));
        AppManager.getInstance().finishActivity(UpdateTeachPayMechanismCourseActivity.class);
        AppManager.getInstance().finishActivity(UpdateMechanismCourseSyllabusActivity.class);
    }
}
