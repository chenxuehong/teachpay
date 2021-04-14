package com.eghuihe.module_user.me.dialog;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.adapter.SelectCourseListRvAdapter;
import com.eghuihe.module_user.me.mvp.SelectMasterSetPriceListContract;
import com.eghuihe.module_user.me.mvp.SelectMasterSetPriceListPresenter;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.dialog.BaseMvpDialogFragment;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectMasterSetPriceListDialogFragment extends BaseMvpDialogFragment<SelectMasterSetPriceListPresenter>
        implements SelectMasterSetPriceListContract.View, SelectCourseListRvAdapter.OnListener {

    @BindView(R2.id.dialog_fragment_select_mastersetprice_SmartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.dialog_fragment_select_mastersetprice_RecyclerViewFixed)
    RecyclerViewFixed recyclerViewFixed;
    private LoadPagerManager loadPagerManager;
    private SelectCourseListRvAdapter selectCourseListRvAdapter;
    private BaseDialog updateLivePriceDialog;
    private Map<String, String> selectPriceMap;
    private Map<String, String> selectSinglePriceMap;

    @OnClick({
            R2.id.dialog_fragment_select_mastersetprice_tv_finish
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.dialog_fragment_select_mastersetprice_tv_finish) {
            if (selectCourseListRvAdapter == null || selectCourseListRvAdapter.getSelectedMap() == null || selectCourseListRvAdapter.getSelectedMap().size() == 0) {
                ToastUtils.showShortToast(getContext(), "请选择商品");
                return;
            }
            Map<Integer, MasterSetPriceEntity> selectedMap = selectCourseListRvAdapter.getSelectedMap();
            EventBusUtils.sendEvent(new Event(EventAction.SELECT_MASTERSETPRICE, selectedMap));
            EventBusUtils.sendEvent(new Event(EventAction.SELECT_PRICEMAP, selectPriceMap));
            EventBusUtils.sendEvent(new Event(EventAction.SELECT_SINGLEPRICEMAP, selectSinglePriceMap));
            dismiss();
        }
    }

    @Override
    protected SelectMasterSetPriceListPresenter createPresenter() {
        return new SelectMasterSetPriceListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_fragment_select_mastersetprice;
    }

    @Override
    protected void initView() {
        super.initView();
        loadPagerManager = new LoadPagerManager();
        selectPriceMap = new HashMap<>();
        selectSinglePriceMap = new HashMap<>();
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (loadPagerManager != null) {
                    loadPagerManager.currentPageUp();
                }
                doLoadMore();
            }
        });
        doRefresh();
    }

    private void doRefresh() {
        getPresenter().queryMasterSetPriceList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "2",
                getCurrentPage(),
                getPageSize()
        );
    }

    private void doLoadMore() {
        getPresenter().queryMasterSetPriceList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "2",
                getCurrentPage(),
                getPageSize()
        );
    }

    private Integer getPageSize() {
        if (loadPagerManager != null) {
            return loadPagerManager.getPageSize();
        }
        return 10;
    }

    private Integer getCurrentPage() {
        if (loadPagerManager != null) {
            return loadPagerManager.getCurrentPage();
        }
        return 10;
    }


    @Override
    public void showMasterSetPriceList(List<MasterSetPriceEntity> exclusiveCoursesEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (selectCourseListRvAdapter != null) {
                selectCourseListRvAdapter.setData(exclusiveCoursesEntities);
            }
        } else {
            if (selectCourseListRvAdapter != null) {
                selectCourseListRvAdapter.addData(exclusiveCoursesEntities);
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

    private void finishLoadMoreWithNoMoreData() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    private void finishRefreshWithNoMoreData() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefreshWithNoMoreData();
        }
    }

    private void initAdapter() {
        recyclerViewFixed.setVertical(1);
        selectCourseListRvAdapter = new SelectCourseListRvAdapter(R.layout.item_select_mastersetprice, getContext(), this);
        recyclerViewFixed.setAdapter(selectCourseListRvAdapter);
    }

    public void closeLoading() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void updateLivePrice(MasterSetPriceEntity masterSetPriceEntity, boolean isSelected) {
        if (isSelected) {
            showUpdateLivePriceDialog(masterSetPriceEntity);
        } else {
            updateLivePriceFromList("", masterSetPriceEntity, "", false);
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
                EditText etPrice = (EditText) getView(R.id.dialog_update_live_price_et_price);
                EditText etSinglePrice = (EditText) getView(R.id.dialog_update_live_price_et_single_price);
                getView(R.id.dialog_update_live_price_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                getView(R.id.dialog_update_live_price_tv_sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateLivePriceFromList(etPrice.getText().toString().trim(), masterSetPriceEntity, etSinglePrice.getText().toString().trim(), true);
                        dismiss();
                    }
                });

            }
        };
        updateLivePriceDialog.setPerWidth(338f / 414);
        updateLivePriceDialog.setCancelOutside(false);
        updateLivePriceDialog.show();
    }

    private void updateLivePriceFromList(String price, MasterSetPriceEntity masterSetPriceEntity, String singlePrice, boolean add) {
        if (selectPriceMap != null) {
            if (add) {
                selectPriceMap.put(masterSetPriceEntity.getId(), price);
                selectSinglePriceMap.put(masterSetPriceEntity.getId(), singlePrice);
            } else {
                selectPriceMap.remove(masterSetPriceEntity.getId());
                selectSinglePriceMap.remove(masterSetPriceEntity.getId());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (updateLivePriceDialog != null) {
            updateLivePriceDialog.dismiss();
        }
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
