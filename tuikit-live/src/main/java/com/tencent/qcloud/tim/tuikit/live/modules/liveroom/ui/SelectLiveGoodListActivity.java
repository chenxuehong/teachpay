package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.qcloud.tim.tuikit.live.R;
import com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp.AnchorSelectLiveGoodListContract;
import com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp.AnchorSelectLiveGoodListPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SelectLiveGoodListActivity extends BaseMvpRvRefreshTitleActivity<SelectLiveGoodRvAdapter, AnchorSelectLiveGoodListPresenter>
        implements AnchorSelectLiveGoodListContract.View {

    private TextView tvSelectNum;
    private BaseDialog updateLivePriceDialog;
    private String live_streaming_id;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("添加商品");
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            live_streaming_id = intent.getStringExtra(ArgumentsConfig.KEY_ID);
        }
        FrameLayout flBottom = getFlBottom();
        if (flBottom != null) {
            View bottomView = View.inflate(this, R.layout.select_live_good_bottom, null);
            tvSelectNum = bottomView.findViewById(R.id.select_live_good_bottom_tv_select_num);
            bottomView.findViewById(R.id.select_live_good_bottom_tv_sure).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<Integer, MasterSetPriceEntity> selectEntityMap = adapter.getSelectEntityMap();
                    if (selectEntityMap != null && selectEntityMap.size() == 0) {
                        finish();
                        return;
                    }
                    if (adapter != null) {
                        showUpdateLivePriceDialog(selectEntityMap.get(0));
                    }
                }
            });
            flBottom.addView(bottomView);
        }
    }


    private void showUpdateLivePriceDialog(final MasterSetPriceEntity masterSetPriceEntity) {
        updateLivePriceDialog = new BaseDialog(getContext()) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_update_live_price;
            }

            @Override
            protected void initEvents() {
                final EditText etPrice = (EditText) getView(R.id.dialog_update_live_price_et_price);
                TextView tvCancel = (TextView) getView(R.id.dialog_update_live_price_tv_cancel);
                tvCancel.setText("不设置");
                getView(R.id.dialog_update_live_price_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        commitData(masterSetPriceEntity, etPrice.getText().toString().trim());
                    }
                });
                getView(R.id.dialog_update_live_price_tv_sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        commitData(masterSetPriceEntity, getLive_stream_prices());
                    }
                });

            }
        };
        updateLivePriceDialog.setPerWidth(338f / 414);
        updateLivePriceDialog.setCancelOutside(false);
        updateLivePriceDialog.show();
    }

    private void commitData(MasterSetPriceEntity masterSetPriceEntity, String live_stream_prices) {

        getPresenter().insertMasterSetPriceDisplay(
                masterSetPriceEntity.getId(),
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                live_stream_prices,
                live_streaming_id,
                getLiving_single_session_prices()
        );
    }

    private String getLive_stream_prices() {
        List<String> list = new ArrayList<>();
        if (adapter != null) {
            Map<Integer, MasterSetPriceEntity> selectedMap = adapter.getSelectEntityMap();
            if (selectedMap != null) {
                Iterator<Map.Entry<Integer, MasterSetPriceEntity>> iterator = selectedMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, MasterSetPriceEntity> next = iterator.next();
                    MasterSetPriceEntity masterSetPriceEntity = next.getValue();
                    String default_discount_price = masterSetPriceEntity.getDefault_discount_price();
                    String discount_amout = masterSetPriceEntity.getDiscount_amout();
                    float default_discount_price_float = NumberUtils.tranToADecimal(default_discount_price);
                    float discount_amout_float = NumberUtils.tranToADecimal(discount_amout);
                    if (discount_amout_float > 0) {
                        list.add(discount_amout);
                    } else {
                        list.add(default_discount_price);
                    }
                }
            }
        }

        return StringUtils.list2String(list, ",");
    }

    private String getLiving_single_session_prices() {
        List<String> list = new ArrayList<>();
        if (adapter != null) {
            Map<Integer, MasterSetPriceEntity> selectedMap = adapter.getSelectEntityMap();
            if (selectedMap != null) {
                Iterator<Map.Entry<Integer, MasterSetPriceEntity>> iterator = selectedMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, MasterSetPriceEntity> next = iterator.next();
                    MasterSetPriceEntity masterSetPriceEntity = next.getValue();
                    // 单价
                    String original_price = masterSetPriceEntity.getOriginal_price();
                    float original_price_amount = NumberUtils.tranToADecimal(original_price);
                    if (original_price_amount > 0) {
                        list.add(original_price);
                    } else {
                        list.add("0");
                    }
                }
            }
        }
        return StringUtils.list2String(list, ",");
    }

    @Override
    protected void initData() {
        doRefresh();
    }

    @Override
    protected void doRefresh() {

        getPresenter().queryExclusiveCourseList(
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
        getPresenter().queryExclusiveCourseList(
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
        return DensityUtils.dp2px(this, 12);
    }

    @Override
    protected SelectLiveGoodRvAdapter getAdapter() {
        return new SelectLiveGoodRvAdapter(R.layout.item_select_live_good, this);
    }

    @Override
    protected AnchorSelectLiveGoodListPresenter createPresenter() {
        return new AnchorSelectLiveGoodListPresenter();
    }

    @Override
    public void showExclusiveCourseList(List<MasterSetPriceEntity> exclusiveCoursesEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
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
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {

        if (EventAction.SELECT_NUM.equals(event.getAction())) {
            Object data = event.getData();
            if (data instanceof Integer) {
                int num = (int) data;
                tvSelectNum.setText("已选".concat(String.valueOf(num)).concat("件"));
            }

        }
    }

    @Override
    public void onInsertSuccess() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (updateLivePriceDialog != null) {
            updateLivePriceDialog.dismiss();
        }
    }
}
