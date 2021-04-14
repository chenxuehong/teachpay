package com.eghuihe.module_user.me.fragment;

import android.os.Bundle;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.me.adapter.CommentRVAdapter;
import com.eghuihe.module_user.me.mvp.CommentContract;
import com.eghuihe.module_user.me.mvp.CommentPresenter;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.personal.CommentEntity;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;

import java.util.List;

public class TeachingCommentListFragment extends BaseMvpRVRefreshFragment<CommentRVAdapter, CommentPresenter>
        implements CommentContract.View {

    private String type;
    private String appointment_id;

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected CommentRVAdapter createAdapter() {
        CommentRVAdapter commentRVAdapter = new CommentRVAdapter(R.layout.fragment_item_comment, getContext());
        return commentRVAdapter;
    }

    @Override
    public void doRefresh() {
        getPresenter().getMasterCommentList(
                getLoadPagerManager().getCurrentPage(),
                getLoadPagerManager().getPageSize(),
                appointment_id,
                type
        );
    }

    @Override
    public void doLoadMore() {
        getPresenter().getMasterCommentList(
                getLoadPagerManager().getCurrentPage(),
                getLoadPagerManager().getPageSize(),
                appointment_id,
                type
        );
    }

    @Override
    protected void initData() {

        Bundle arguments = getArguments();
        if (arguments != null) {
            appointment_id = arguments.getString(ArgumentsConfig.KEY_APPOINTMENT_ID);
            type = arguments.getString(ArgumentsConfig.KEY_TYPE);
        }
        triggerAutoRefresh();
    }

    @Override
    public void showMasterCommentList(List<CommentEntity> commentEntities) {
        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null)
                adapter.setData(commentEntities);
        } else {
            if (adapter != null)
                adapter.addData(commentEntities);
        }
        if (commentEntities == null || commentEntities.size() < getLoadPagerManager().getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }

    }

    @Override
    protected CommentPresenter createPresenter() {
        return new CommentPresenter();
    }
}
