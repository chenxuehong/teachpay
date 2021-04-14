package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.widget;

import androidx.annotation.NonNull;

import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LiveDetailEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.dialog.BaseMvpDialogFragment;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.tim.tuikit.live.R;
import com.tencent.qcloud.tim.tuikit.live.R2;
import com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.AudienceShoppingRvAdapter;
import com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp.AudienceShoppingListContract;
import com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp.AudienceShoppingListPresenter;

import java.util.List;

import butterknife.BindView;

public class AudienceShoppingListDialog extends BaseMvpDialogFragment<AudienceShoppingListPresenter>
        implements AudienceShoppingListContract.View, AudienceShoppingRvAdapter.OnListener {

    @BindView(R2.id.fragment_dialog_audience_shoping_list_SmartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.fragment_dialog_audience_shoping_list_RecyclerViewFixed)
    RecyclerViewFixed recyclerViewFixed;
    private LoadPagerManager loadPagerManager;
    private AudienceShoppingRvAdapter audienceShoppingRvAdapter;

    @Override
    protected AudienceShoppingListPresenter createPresenter() {
        return new AudienceShoppingListPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        loadPagerManager = new LoadPagerManager();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (loadPagerManager != null) {
                    loadPagerManager.scrollToFirst();
                }
                loadData();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (loadPagerManager != null) {
                    loadPagerManager.currentPageUp();
                }
                loadData();
            }
        });
        loadData();
    }

    private void loadData() {
        getPresenter().queryMasterSetPriceDisplay(
                getCurrentPage(),
                getPageSize(),
                "2",
                liveDetailEntity.getId()
        );
    }

    private int getCurrentPage() {
        if (loadPagerManager != null) {
            loadPagerManager.getCurrentPage();
        }
        return 1;
    }

    private int getPageSize() {
        if (loadPagerManager != null) {
            loadPagerManager.getPageSize();
        }
        return 10;
    }

    @Override
    public void closeLoading() {
        super.closeLoading();
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadMore();
        }
    }

    private void finishRefreshWithNoMoreData() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefreshWithNoMoreData();
        }
    }

    private void finishLoadMoreWithNoMoreData() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dialog_audience_shoping_list;
    }

    @Override
    public void showMasterSetPriceDisplayList(List<MasterSetPriceEntity> masterSetPriceEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (audienceShoppingRvAdapter != null) {
                audienceShoppingRvAdapter.setData(masterSetPriceEntities);
            }
        } else {
            if (audienceShoppingRvAdapter != null) {
                audienceShoppingRvAdapter.addData(masterSetPriceEntities);
            }
        }
        if (masterSetPriceEntities == null || masterSetPriceEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    private void initAdapter() {
        recyclerViewFixed.setVertical(1);
        recyclerViewFixed.addGridViewItemDecoration(1, DensityUtils.dp2px(getContext(), 12));
        audienceShoppingRvAdapter = new AudienceShoppingRvAdapter(R.layout.item_audience_shopping, getContext(),this);
        recyclerViewFixed.setAdapter(audienceShoppingRvAdapter);
    }

    private LiveDetailEntity liveDetailEntity;
    public void setData(LiveDetailEntity liveDetailEntity) {
        this.liveDetailEntity = liveDetailEntity;
    }

    @Override
    public void goGuy(MasterSetPriceEntity masterSetPriceEntity) {
        EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_DETAIL, masterSetPriceEntity));
    }
}
