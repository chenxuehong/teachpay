package com.tencent.qcloud.tim.uikit.component.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.R2;
import com.tencent.qcloud.tim.uikit.component.activity.ChatActivity;
import com.tencent.qcloud.tim.uikit.component.activity.ContractsListActivity;
import com.tencent.qcloud.tim.uikit.config.Constants;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationManagerKit;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;

import butterknife.BindView;

public class ConversationFragment extends BaseFragment implements ConversationManagerKit.MessageUnreadWatcher {

    @BindView(R2.id.fragment_conversationLayout)
    ConversationLayout mConversationLayout;
    @BindView(R2.id.fragment_conversationLayout_fl_status)
    FrameLayout mflStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_conversation;
    }

    @Override
    protected void initView() {
        super.initView();
        ConversationManagerKit.getInstance().addUnreadWatcher(this);
        ViewGroup.LayoutParams layoutParams = mflStatus.getLayoutParams();
        layoutParams.height = DensityUtils.getStatusHeight();
        mflStatus.setLayoutParams(layoutParams);

        // 从布局文件中获取会话列表面板
        // 会话列表面板的默认UI和交互初始化
        mConversationLayout.initDefault();
        // 通过API设置ConversataonLayout各种属性的样例，开发者可以打开注释，体验效果
//        ConversationLayoutHelper.customizeConversation(mConversationLayout);
        mConversationLayout.getConversationList().setOnItemClickListener(new ConversationListLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ConversationInfo conversationInfo) {
                //此处为demo的实现逻辑，更根据会话类型跳转到相关界面，开发者可根据自己的应用场景灵活实现
                startChatActivity(conversationInfo);
            }
        });
        mConversationLayout.getConversationList().setOnItemDeleteAndTopClickListener(new ConversationListLayout.OnItemDeleteAndTopClickListener() {
            @Override
            public void onItemDeleted(View view, int position, ConversationInfo messageInfo) {
                mConversationLayout.deleteConversation(position, messageInfo);
            }

            @Override
            public void onItemTop(View view, int position, ConversationInfo messageInfo) {
                mConversationLayout.setConversationTop(position, messageInfo);
            }
        });
        mConversationLayout.showSearchView(false);
        mConversationLayout.setOnListener(
                new ConversationLayout.OnListener() {
                    @Override
                    public void onSearchClicked(View view) {

                    }

                    @Override
                    public void onContractsClicked(View view) {

                        startActivity(ContractsListActivity.class);
                    }

                    @Override
                    public void onClassClicked(View view) {
                        // 进入班级管理
                        ActivityToActivity.toActivity(ARouterConfig.SCHEDULE_CLASSMANAGERACTIVITY);
                    }
                }
        );

    }

    private void startChatActivity(ConversationInfo conversationInfo) {
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(conversationInfo.isGroup() ?
                V2TIMConversation.V2TIM_GROUP : V2TIMConversation.V2TIM_C2C);
        chatInfo.setId(conversationInfo.getId());
        chatInfo.setChatName(conversationInfo.getTitle());
        Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.putExtra(Constants.CHAT_INFO, chatInfo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void updateUnread(int count) {
        EventBusUtils.sendEvent(new Event(EventAction.IM_UNREAD_COUNT, String.valueOf(count)));
    }
}
