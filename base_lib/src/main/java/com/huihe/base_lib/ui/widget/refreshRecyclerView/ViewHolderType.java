package com.huihe.base_lib.ui.widget.refreshRecyclerView;

public interface ViewHolderType {
    int TYPE_REFRESH_HEADER = 100;
    int TYPE_LOAD_MORE_FOOTER = 101;

    // 下面是刷新ui状态
    int TYPE_REFRESH_RELEASE = 102;
    int TYPE_REFRESH_PULL_DOWN = 103;
}
