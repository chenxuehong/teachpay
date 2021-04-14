package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.widget;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.LiveEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.masterSetPriceDisplayEntity;
import com.huihe.base_lib.ui.dialog.BaseMvpDialogFragment;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.tim.tuikit.live.R;
import com.tencent.qcloud.tim.tuikit.live.R2;
import com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.AnchorShopingRvAdapter;
import com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.SelectLiveGoodListActivity;
import com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp.AnchorShoppingListContract;
import com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp.AnchorShoppingListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AnchorShoppingListDialog extends BaseMvpDialogFragment<AnchorShoppingListPresenter>
        implements AnchorShoppingListContract.View, AnchorShopingRvAdapter.OnListener {

    private static final int REQUEST_CODE_ADD_GOOD = 100;
    @BindView(R2.id.fragment_dialog_anchor_shopping_list_SmartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.fragment_dialog_anchor_shopping_list_RecyclerViewFixed)
    RecyclerViewFixed recyclerViewFixed;
    private LoadPagerManager loadPagerManager;
    private AnchorShopingRvAdapter anchorShopingRvAdapter;

    @OnClick({
            R2.id.fragment_dialog_anchor_shopping_list_tv_add
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.fragment_dialog_anchor_shopping_list_tv_add) {
            addGood();
        }
    }

    private void addGood() {
        Intent intent = new Intent(getContext(), SelectLiveGoodListActivity.class);
        intent.putExtra(ArgumentsConfig.KEY_ID, live_streaming_id);
        startActivityForResult(intent, REQUEST_CODE_ADD_GOOD);

    }

    @Override
    protected AnchorShoppingListPresenter createPresenter() {
        return new AnchorShoppingListPresenter();
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
                live_streaming_id
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
        return R.layout.fragment_dialog_anchor_shoping_list;
    }

    @Override
    public void showMasterSetPriceDisplayList(List<MasterSetPriceEntity> masterSetPriceEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (anchorShopingRvAdapter != null) {
                anchorShopingRvAdapter.setData(masterSetPriceEntities);
            }
        } else {
            if (anchorShopingRvAdapter != null) {
                anchorShopingRvAdapter.addData(masterSetPriceEntities);
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
        anchorShopingRvAdapter = new AnchorShopingRvAdapter(R.layout.item_anchor_shopping, getContext(),this);
        recyclerViewFixed.setAdapter(anchorShopingRvAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_GOOD) {
            if (loadPagerManager != null) {
                loadPagerManager.currentPageUp();
            }
            loadData();
        }
    }

    private String live_streaming_id;

    public void setData(String live_streaming_id) {
        this.live_streaming_id = live_streaming_id;
    }

    @Override
    public void startTeach(MasterSetPriceEntity masterSetPriceDisplayEntity) {
        getPresenter().updateMasterSetPriceDisplay(
                masterSetPriceDisplayEntity.getId(),
                true
        );
    }

    @Override
    public void stopTeach(MasterSetPriceEntity masterSetPriceDisplayEntity) {
        getPresenter().updateMasterSetPriceDisplay(
                masterSetPriceDisplayEntity.getId(),
                false
        );
    }

    @Override
    public void onUpdateSuccess() {
        if (loadPagerManager != null) {
            loadPagerManager.currentPageUp();
        }
        loadData();
    }
}
