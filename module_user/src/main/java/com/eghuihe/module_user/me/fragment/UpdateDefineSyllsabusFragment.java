package com.eghuihe.module_user.me.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.bean.params.MechanismCourseParam;
import com.eghuihe.module_user.me.activity.UpdateMechanismCourseSyllabusActivity;
import com.eghuihe.module_user.me.activity.UpdateTeachPayMechanismCourseActivity;
import com.eghuihe.module_user.me.adapter.RvSyllabusAdapter;
import com.eghuihe.module_user.me.mvp.UpdateMechanismCourseStep2Contract;
import com.eghuihe.module_user.me.mvp.UpdateMechanismCourseStep2Presenter;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.AppManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateDefineSyllsabusFragment extends BaseMvpFragment<UpdateMechanismCourseStep2Presenter>
        implements UpdateMechanismCourseStep2Contract.View {

    @BindView(R2.id.fragment_define_syllsabus_rv)
    RecyclerViewFixed rvSyllabus;
    public static final String SP_LINE = "#$*";
    private MechanismCourseParam mechanismCourseParam;
    private RvSyllabusAdapter rvSyllabusAdapter;

    @OnClick({
            R2.id.fragment_define_syllsabus_tv_save_as_draft,
            R2.id.fragment_define_syllsabus_tv_commit
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.fragment_define_syllsabus_tv_save_as_draft) {
            if (inputCheck())
                commitData(3);
        } else if (view.getId() == R.id.fragment_define_syllsabus_tv_commit) {
            if (inputCheck())
                commitData(2);
        }
    }

    private boolean inputCheck() {
        if (mechanismCourseParam == null) {
            return false;
        }
        String titile_url = getSyllabus();
        if (TextUtils.isEmpty(titile_url)) {
            ToastUtils.showShortToast(getContext(), "请输入教学大纲");
            return false;
        }
        if (rvSyllabusAdapter != null) {
            Map<Integer, String> titleMap = rvSyllabusAdapter.getTitleMap();
            if (titleMap != null && titleMap.size() < Integer.valueOf(mechanismCourseParam.course_num)) {
                ToastUtils.showShortToast(getContext(), "请输入课节标题");
                return false;
            }
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
        if (rvSyllabusAdapter != null) {
            Map<Integer, String> titleMap = rvSyllabusAdapter.getTitleMap();
            if (titleMap != null && titleMap.size() > 0) {
                Iterator<Map.Entry<Integer, String>> iterator = titleMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, String> next = iterator.next();
                    Integer key = next.getKey();
                    String value = next.getValue();
                    sbSyllabus.append(value);
                    if (key != titleMap.size() - 1) {
                        sbSyllabus.append(SP_LINE);
                    }
                }
            }
        }
        return sbSyllabus.toString().trim();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_define_syllsabus;
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
        showDefineSyllsabusTitleRvAdapter();
    }

    /**
     * @desc 显示自定义大纲
     */
    private void showDefineSyllsabusTitleRvAdapter() {
        if (mechanismCourseParam != null) {
            rvSyllabus.setVertical(1);
            rvSyllabus.setScrollingEnabled(false);
            List<String> data = new ArrayList<>();
            String courseNumber = mechanismCourseParam.course_num;
            if (!TextUtils.isEmpty(courseNumber)) {
                Integer num = Integer.valueOf(courseNumber);
                for (Integer i = 0; i < num; i++) {
                    data.add(String.format(getResources().getString(R.string.title_params), String.valueOf(i + 1)));
                }
            }
            rvSyllabusAdapter = new RvSyllabusAdapter(R.layout.item_addsalecourse_title, getContext(), data);
            rvSyllabus.setAdapter(rvSyllabusAdapter);
            rvSyllabusAdapter.setTitleUrl(mechanismCourseParam.titile_url);
        }
    }

    @Override
    protected UpdateMechanismCourseStep2Presenter createPresenter() {
        return new UpdateMechanismCourseStep2Presenter();
    }

    @Override
    public void onUpdateMechanismCourseSuccess() {
        EventBusUtils.sendEvent(new Event(EventAction.INSERT_MECHANISM_COURSE));
        AppManager.getInstance().finishActivity(UpdateTeachPayMechanismCourseActivity.class);
        AppManager.getInstance().finishActivity(UpdateMechanismCourseSyllabusActivity.class);
    }
}
