package com.eghuihe.module_schedule.ui.mechanism.fragment.schdule;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.mechanism.fragment.scheduling.BaseSchedulingFragment;
import com.eghuihe.module_schedule.ui.mechanism.mvp.MechanismScheduleContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.MechanismSchedulePresenter;
import com.eghuihe.module_schedule.ui.widget.DateScheduleView;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.entities_lib.rep.schedule.ScheduleItemBean;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 已结束
 */
public class ScheduleMechanismOverScheduleFragment extends BaseSchedulingFragment<MechanismSchedulePresenter>
        implements MechanismScheduleContract.View {
    @Override
    protected MechanismSchedulePresenter createPresenter() {
        return new MechanismSchedulePresenter();
    }

    @Override
    protected void loadData(String start_time, String end_time) {
        getPresenter().queryMechanismOfflineSchedule(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                "2",
                "teach_paypal_course",
                null,
                null,
                start_time,
                end_time,
                String.valueOf(DateUtils.getCurTimeZoneOffset())
        );
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.ARRANGE_COURSE.equals(event.getAction())) {
            String appointment_type = (String) event.getData();
            if ("scheduling".equals(appointment_type)) {
                reLoadData();
            }
        } else if (EventAction.INSERT_MECHANISM_COURSE.equals(event.getAction())) {
            reLoadData();
        }
    }

    @Override
    public void onMechanismOfflineScheduleList(List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities) {
        List<ScheduleItemBean> itemBeans = convertModel(mechanismOfflineScheduleEntities);
        dateScheduleView.setOnScheduleListener(itemBeans,
                new DateScheduleView.OnScheduleListener<MechanismOfflineScheduleEntity>() {
                    @Override
                    public void onScheduleItem(FrameLayout itemView, MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity) {
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_mechanism_schedule, null);
                        TextView tvTime = view.findViewById(R.id.item_mechanism_schedule_tv_time);
                        TextView tvTitle = view.findViewById(R.id.item_mechanism_schedule_tv_title);
                        TextView tvChildTitle = view.findViewById(R.id.item_mechanism_schedule_tv_childTitle);
                        TextView tvEdit = view.findViewById(R.id.item_mechanism_schedule_tv_edit);
                        String start_time = mechanismOfflineScheduleEntity.getStart_time();
                        start_time = DateUtils.translateZoneTimeStr(start_time, mechanismOfflineScheduleEntity.getOffset(), DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
                        start_time = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.HMFormatStr);
                        String nickName = "";
                        MechanismOfflineScheduleEntity.Map map = mechanismOfflineScheduleEntity.getMap();
                        if (map != null) {
                            UserInfoEntity masterInfo = map.getMasterInfo();
                            if (masterInfo != null) {
                                nickName = masterInfo.getNick_name();
                            }
                        }
                        tvTitle.setText(mechanismOfflineScheduleEntity.getTitle());
                        tvTime.setText(start_time.concat(" ").concat(nickName));
                        String classroom = mechanismOfflineScheduleEntity.getClassroom();
                        tvChildTitle.setText(classroom);
                        tvEdit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // 取消课程
                                DialogUtils.showTipDialog(
                                        getContext(),
                                        "提醒",
                                        "是否取消课程",
                                        "取消",
                                        "确定",
                                        new DialogUtils.OnListener() {
                                            @Override
                                            public void okClicked() {
                                                getPresenter().deleteMasterAppointment(mechanismOfflineScheduleEntity.getId());
                                            }

                                            @Override
                                            public void cancelClicked() {

                                            }
                                        }
                                );
                            }
                        });
                        itemView.addView(view);
                    }
                });
    }

    @Override
    public void onDeleteCourseSuccess() {
        reLoadData();
        ToastUtils.showShortToast(getContext(), "取消成功");
    }

    private List<ScheduleItemBean> convertModel(List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities) {
        List<ScheduleItemBean> itemBeans = new ArrayList<>();
        List<MechanismOfflineScheduleEntity> amList = new ArrayList<>();
        List<MechanismOfflineScheduleEntity> pmList = new ArrayList<>();
        ScheduleItemBean amitemBeans = new ScheduleItemBean(true, amList);
        ScheduleItemBean pmitemBeans = new ScheduleItemBean(false, pmList);
        if (mechanismOfflineScheduleEntities != null) {
            for (int i = 0; i < mechanismOfflineScheduleEntities.size(); i++) {
                MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity = mechanismOfflineScheduleEntities.get(i);
                if (mechanismOfflineScheduleEntity != null) {
                    String start_time = mechanismOfflineScheduleEntity.getStart_time();
                    String apm = DateUtils.getApm(start_time, DateUtils.YMDHMSFormatStr);
                    if ("上午".equals(apm)) {
                        amList.add(mechanismOfflineScheduleEntity);
                    } else {
                        pmList.add(mechanismOfflineScheduleEntity);
                    }
                }
            }
        }
        itemBeans.add(amitemBeans);
        itemBeans.add(pmitemBeans);
        return itemBeans;
    }
}
