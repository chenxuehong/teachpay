package com.eghuihe.module_dynamic.ui.fragment.detail;

import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.ui.adapter.DynamicDetailCommentRvAdapter;
import com.eghuihe.module_dynamic.ui.mvp.DynamicDetailCommenPresenter;
import com.eghuihe.module_dynamic.ui.mvp.DynamicDetailCommentContract;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.dynamic.NoteCommentDetailListModel;
import com.huihe.base_lib.model.event.DynamicDetailCommentCountEvent;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class DynamicDetailCommentListFragment extends BaseMvpRVRefreshFragment<DynamicDetailCommentRvAdapter, DynamicDetailCommenPresenter>
        implements DynamicDetailCommentContract.View, DynamicDetailCommentRvAdapter.OnListener {
    private String noteId;

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected DynamicDetailCommentRvAdapter createAdapter() {
        return new DynamicDetailCommentRvAdapter(R.layout.item_dynamic_comment, getContext(), noteId,this);
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    @Override
    public void doRefresh() {
        getPresenter().queryNoteCommentDetailsListPage(
                noteId,
                "0",
                getLoadPagerManager().getCurrentPage(),
                getLoadPagerManager().getPageSize()
        );

    }

    @Override
    public void doLoadMore() {
        getPresenter().queryNoteCommentDetailsListPage(
                noteId,
                "0",
                getLoadPagerManager().getCurrentPage(),
                getLoadPagerManager().getPageSize()
        );

    }

    @Override
    protected void initData() {
        LogUtils.e("triggerAutoRefresh", "initData");
        triggerAutoRefresh();
    }

    @Override
    public void onNoteCommentList(List<NoteCommentDetailListModel.NoteCommentEntity> noteCommentEntities) {

        EventBus.getDefault().post(new DynamicDetailCommentCountEvent(noteCommentEntities == null ? 0 : noteCommentEntities.size()));

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(noteCommentEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(noteCommentEntities);
            }
        }

        if (noteCommentEntities == null || noteCommentEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    protected DynamicDetailCommenPresenter createPresenter() {
        return new DynamicDetailCommenPresenter();
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.DYNAMICDETAILCOMMENTLIST_REFRESH.equals(event.getAction())) {
            triggerAutoRefresh();
        }
    }

    @Override
    public void onInsertChildComment(
            String user_id,
            Boolean is_reply,
            String reply_id,
            String parent_id,
            String note_id,
            String content) {

        getPresenter().insertNoteComment(
                user_id,
                is_reply,
                reply_id,
                parent_id,
                note_id,
                content
        );
    }

    @Override
    public void onDeleteComment(
            String id,
            int position) {
        getPresenter().deleteNoteComment(id);
    }

    @Override
    public void onInsertLike(
            String history_id) {
        getPresenter().insertHistory(
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                history_id,
                "like",
                "notecomment",
                null);
    }

    @Override
    public void onCommentInsertSuccess() {
        doRefresh();
    }

    @Override
    public void onCommentDeleteSuccess() {
        doRefresh();
    }

    @Override
    public void onLikeSuccess() {
        doRefresh();
    }

    @Override
    public void onDestroy() {
        if (adapter!=null){
            adapter.closeDialog();
        }
        super.onDestroy();
    }


}
