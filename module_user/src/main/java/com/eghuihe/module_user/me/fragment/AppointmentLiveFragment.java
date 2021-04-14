package com.eghuihe.module_user.me.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.dialog.SelectMasterSetPriceListDialogFragment;
import com.eghuihe.module_user.me.mvp.CreateLiveContract;
import com.eghuihe.module_user.me.mvp.CreateLivePresenter;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.widget.picker.DateTimePicker;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.MultiLanguageUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class AppointmentLiveFragment extends BaseMvpFragment<CreateLivePresenter>
        implements CreateLiveContract.View {
    @BindView(R2.id.fragment_appointment_live_et_title)
    EditText etTitle;
    @BindView(R2.id.fragment_appointment_live_tv_startTime)
    TextView tvStartTime;
    @BindView(R2.id.fragment_appointment_live_tv_course)
    TextView tvCourse;

    private SelectMasterSetPriceListDialogFragment selectMasterSetPriceListDialogFragment;
    private Map<Integer, MasterSetPriceEntity> selectedMap;
    private Map<String, String> selectPriceMap;
    private Map<String, String> selectSinglePriceMap;

    @OnClick({
            R2.id.fragment_appointment_live_tv_startTime,
            R2.id.fragment_appointment_live_tv_course,
            R2.id.fragment_appointment_live_tv_appointment
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.fragment_appointment_live_tv_startTime) {
            // 选择开始时间
            selectStartTime();
        } else if (view.getId() == R.id.fragment_appointment_live_tv_course) {
            // 选择商品
            showMasterSetPriceListDialog();
        } else if (view.getId() == R.id.fragment_appointment_live_tv_appointment) {
            // 预约
            if (checkInput()) {
                String master_set_price_ids = getMaster_set_price_ids();
                String live_stream_prices = getLive_stream_prices();
                String living_single_session_prices = getLiving_single_session_prices();
                getPresenter().insertMasterSetPriceStream(
                        master_set_price_ids,
                        tvStartTime.getText().toString().trim(),
                        etTitle.getText().toString().trim(),
                        LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                        live_stream_prices,
                        living_single_session_prices
                );
            }
        }
    }


    private boolean checkInput() {
        if (TextUtils.isEmpty(etTitle.getText().toString())) {
            ToastUtils.showShortToast(getContext(), "请输入直播标题");
            return false;
        }
        if (TextUtils.isEmpty(tvStartTime.getText().toString())) {
            ToastUtils.showShortToast(getContext(), "请选择直播时间");
            return false;
        }
        if (TextUtils.isEmpty(tvCourse.getText().toString())) {
            ToastUtils.showShortToast(getContext(), "请选择直播橱窗课程");
            return false;
        }
        return true;
    }

    private void selectStartTime() {
        DateTimePicker beginTimePicker = new DateTimePicker(getActivity(), DateTimePicker.YEAR_MONTH_DAY, DateTimePicker.HOUR_24);
        String dateStr = DateUtils.getCurDateStr(DateUtils.YMDFormatStr);
        String weekStr = "";
        if (MultiLanguageUtil.getInstance().isZh()) {
            weekStr = DateUtils.getChineseWeek(dateStr, DateUtils.YMDFormatStr);
        } else {
            weekStr = DateUtils.getEngLishWeek(dateStr, DateUtils.YMDFormatStr);
        }
        beginTimePicker.setTitleText(DateUtils.getCurYear() + " (" + weekStr + ")");
        beginTimePicker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                String start_time = year.concat("-").concat(month).concat("-").concat(day).concat(" ").concat(hour).concat(":").concat(minute).concat(":00");
                tvStartTime.setText(start_time);
            }
        });
        beginTimePicker.setResetWhileWheel(false);
        beginTimePicker.setDateRangeStart(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
        String endTime = DateUtils.getSpecifiedDayAfter(DateUtils.getCurDateStr(DateUtils.YMDHMSFormatStr), DateUtils.YMDHMSFormatStr, 15);
        int year = DateUtils.getYear(endTime, DateUtils.YMDHMSFormatStr);
        int month = 12;
        int day = DateUtils.getMonthOfDay(year, 12);
        beginTimePicker.setDateRangeEnd(year + 4, month, day);
        beginTimePicker.setSelectedItem(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay(), DateUtils.getCurHour(), DateUtils.getCurMinute());
        beginTimePicker.show();
    }

    private String getMaster_set_price_ids() {
        List<String> list = new ArrayList<>();
        if (selectedMap != null) {
            Iterator<Map.Entry<Integer, MasterSetPriceEntity>> iterator = selectedMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, MasterSetPriceEntity> next = iterator.next();
                MasterSetPriceEntity masterSetPriceEntity = next.getValue();
                String id = masterSetPriceEntity.getId();
                list.add(id);
            }
        }
        return StringUtils.list2String(list, ",");
    }

    private String getLive_stream_prices() {
        List<String> list = new ArrayList<>();
        if (selectedMap != null) {
            Iterator<Map.Entry<Integer, MasterSetPriceEntity>> iterator = selectedMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, MasterSetPriceEntity> next = iterator.next();
                MasterSetPriceEntity masterSetPriceEntity = next.getValue();
                String default_discount_price = masterSetPriceEntity.getDefault_discount_price();
                String discount_amout = masterSetPriceEntity.getDiscount_amout();
                float default_discount_price_float = NumberUtils.tranToADecimal(default_discount_price);
                float discount_amout_float = NumberUtils.tranToADecimal(discount_amout);
                String price = selectPriceMap.get(masterSetPriceEntity.getId());
                if (!TextUtils.isEmpty(price)) {
                    list.add(price);
                } else if (discount_amout_float > 0) {
                    list.add(discount_amout);
                } else {
                    list.add(default_discount_price);
                }
            }
        }
        return StringUtils.list2String(list, ",");
    }

    private String getLiving_single_session_prices() {
        List<String> list = new ArrayList<>();
        if (selectedMap != null) {
            Iterator<Map.Entry<Integer, MasterSetPriceEntity>> iterator = selectedMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, MasterSetPriceEntity> next = iterator.next();
                MasterSetPriceEntity masterSetPriceEntity = next.getValue();
                // 单价
                String singlePrice = selectSinglePriceMap.get(masterSetPriceEntity.getId());
                singlePrice = TextUtils.isEmpty(singlePrice) ? "0.0" : singlePrice;
                float singlePrice_amount = NumberUtils.tranToADecimal(singlePrice);
                String original_price = masterSetPriceEntity.getOriginal_price();
                original_price = TextUtils.isEmpty(original_price) ? "0.0" : original_price;
                if (singlePrice_amount > 0) {
                    list.add(singlePrice);
                } else {
                    list.add(original_price);
                }
            }
        }
        return StringUtils.list2String(list, ",");
    }

    private void showMasterSetPriceListDialog() {
        selectMasterSetPriceListDialogFragment = new SelectMasterSetPriceListDialogFragment();
        selectMasterSetPriceListDialogFragment.setType("AppointmentLiveFragment");
        selectMasterSetPriceListDialogFragment.show(getChildFragmentManager(), "selectMasterSetPriceListDialogFragment");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appointment_live;
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {

        if (selectMasterSetPriceListDialogFragment != null && "AppointmentLiveFragment".equals(selectMasterSetPriceListDialogFragment.getType())) {
            if (EventAction.SELECT_MASTERSETPRICE.equals(event.getAction())) {

                Object data = event.getData();
                if (data != null && data instanceof Map) {
                    selectedMap = (Map<Integer, MasterSetPriceEntity>) data;
                    tvCourse.setText("已选择".concat(String.valueOf(selectedMap.size())).concat("个商品"));
                }
            } else if (EventAction.SELECT_PRICEMAP.equals(event.getAction())) {

                Object data = event.getData();
                if (data != null && data instanceof Map) {
                    selectPriceMap = (Map<String, String>) data;
                }
            } else if (EventAction.SELECT_SINGLEPRICEMAP.equals(event.getAction())) {

                Object data = event.getData();
                if (data != null && data instanceof Map) {
                    selectSinglePriceMap = (Map<String, String>) data;
                }
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (selectMasterSetPriceListDialogFragment != null) {
            selectMasterSetPriceListDialogFragment.dismiss();
        }
    }

    @Override
    public void insertSuccess(String data) {
        ToastUtils.showShortToast(getContext(), "提交成功");
        EventBusUtils.sendEvent(new Event(EventAction.CREATE_LIVE));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override
    protected CreateLivePresenter createPresenter() {
        return new CreateLivePresenter();
    }
}
