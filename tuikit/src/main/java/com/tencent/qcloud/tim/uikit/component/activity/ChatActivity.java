package com.tencent.qcloud.tim.uikit.component.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.huihe.base_lib.model.CourseMessageBean;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.R2;
import com.tencent.qcloud.tim.uikit.component.fragment.ChatFragment;
import com.tencent.qcloud.tim.uikit.config.Constants;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

import butterknife.BindView;

public class ChatActivity extends BaseActivity {

    @BindView(R2.id.activity_chat_container)
    RelativeLayout rlContainer;
    private ChatInfo mChatInfo;
    private ChatFragment mChatFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.acivity_chat;
    }

    @Override
    protected void initView() {
        super.initView();
        chat(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        chat(intent);
    }

    private void chat(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mChatInfo = (ChatInfo) bundle.getSerializable(Constants.CHAT_INFO);
            mChatFragment = new ChatFragment();
            mChatFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(
                    rlContainer.getId(),
                    mChatFragment
            ).commitNowAllowingStateLoss();
        }
    }

    @Override
    protected void initData() {

    }
}
