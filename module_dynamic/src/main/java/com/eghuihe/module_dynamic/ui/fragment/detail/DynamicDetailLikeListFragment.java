package com.eghuihe.module_dynamic.ui.fragment.detail;

import android.text.TextUtils;

import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.ui.mvp.DynamicDetailLikeListContract;
import com.eghuihe.module_dynamic.ui.mvp.DynamicDetailLikeListPresenter;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;
import com.huihe.base_lib.model.event.DynamicDetailLikeCountEvent;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.event.RefreshDynamicDetailLikeListEvent;
import com.huihe.base_lib.model.personal.HistoryListModel;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class DynamicDetailLikeListFragment extends BaseMvpRVRefreshFragment<EmptyRVAdapter, DynamicDetailLikeListPresenter>
        implements DynamicDetailLikeListContract.View {

    private NoteUserEntity noteUserEntity;
    private LoginResultEntity loginResultEntity;

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    protected EmptyRVAdapter createAdapter() {
        return new EmptyRVAdapter<HistoryListModel.HistoryListEntity>(R.layout.item_dynamic_detail_like, getContext()) {

            @Override
            protected void convert(ViewHolder viewHolder, final HistoryListModel.HistoryListEntity historyListEntity, int position) {
                if (!TextUtils.isEmpty(historyListEntity.getCreate_time()))
                    viewHolder.setText(R.id.item_dynamic_like_tv_time, DateUtils.convertTimeToFormat(DateUtils.stringToLong(historyListEntity.getCreate_time(), DateUtils.YMDHMSFormatStr)));
                if (historyListEntity.getMap() != null && historyListEntity.getMap().getUserinfo() != null) {
                    GlideTools.loadImage(context, historyListEntity.getMap().getUserinfo().getAvatar(),
                            (CircleImageView) viewHolder.getView(R.id.item_dynamic_like_iv_head));
                    GlideTools.loadImage(context, historyListEntity.getMap().getUserinfo().getNational_flag(),
                            (CircleImageView) viewHolder.getView(R.id.item_dynamic_like_iv_national_flag));
                    viewHolder.setText(R.id.item_dynamic_like_tv_nickName,
                            historyListEntity.getMap().getUserinfo().getNick_name());
                    viewHolder.setImageResource(R.id.item_dynamic_like_iv_sex,
                            historyListEntity.getMap().getUserinfo().getSex() == 1 ? R.mipmap.xingbie_nan : R.mipmap.xingbie_nv);
                }
            }
        };
    }

    public void setData(NoteUserEntity noteUserEntity) {
        this.noteUserEntity = noteUserEntity;
    }

    @Override
    public void doLoadMore() {
        getPresenter().queryHistoryListPage(
                1, noteUserEntity.getId(),
                "like", "note", getLoadPagerManager().getCurrentPage(),
                getLoadPagerManager().getPageSize()
        );
    }

    @Override
    public void doRefresh() {
        getPresenter().queryHistoryListPage(
                1, noteUserEntity.getId(),
                "like", "note", getLoadPagerManager().getCurrentPage(),
                getLoadPagerManager().getPageSize()
        );
    }

    @Override
    protected void initData() {
        loginResultEntity = LoginHelper.getLoginInfo();
        triggerAutoRefresh();
    }

    @Subscribe
    public void refresh(Event event) {
        if (EventAction.REFRESH_DYNAMIC_DETAIL_LIKELIST_EVENT.equals(event.getAction()))
            triggerAutoRefresh();
    }

    @Override
    protected DynamicDetailLikeListPresenter createPresenter() {
        return new DynamicDetailLikeListPresenter();
    }

    @Override
    public void onHistoryList(List<HistoryListModel.HistoryListEntity> historyListEntities) {
        EventBus.getDefault().post(new DynamicDetailLikeCountEvent(historyListEntities == null ? 0 : historyListEntities.size()));
        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(historyListEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(historyListEntities);
            }
        }

        if (historyListEntities == null || historyListEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }
}
