package com.tencent.qcloud.tim.uikit.component.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.SchedulingMessageBean;
import com.huihe.base_lib.model.SchedulingTipMessage;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.R2;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.component.mvp.ChatContract;
import com.tencent.qcloud.tim.uikit.component.mvp.ChatPresenter;
import com.tencent.qcloud.tim.uikit.config.Constants;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ClassMessageHelper;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.IOnClassCustomMessageListener;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

public class ChatFragment extends BaseMvpFragment<ChatPresenter>
        implements ChatContract.View {

    @BindView(R2.id.chat_layout)
    ChatLayout mChatLayout;
    private ChatInfo mChatInfo;

    private TitleBarLayout mTitleBar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle bundle = getArguments();
        mChatInfo = (ChatInfo) bundle.getSerializable(Constants.CHAT_INFO);
        if (mChatInfo == null) {
            return;
        }
        String messageJson = bundle.getString(Constants.KEY_COURSE_MESSAGEBEAN);
        String studycard_id = bundle.getString(ArgumentsConfig.KEY_STUDYCARD_ID);
        if (!TextUtils.isEmpty(messageJson)) {
            mChatLayout.setMessageContent(messageJson);
        }
        if (!TextUtils.isEmpty(studycard_id)) {
            mChatLayout.setStudycard_id(studycard_id);
        }
        /*
         * 需要聊天的基本信息
         */
        mChatLayout.setChatInfo(mChatInfo);
        //单聊组件的默认UI和交互初始化
        mChatLayout.initDefault();
//        ChatLayoutHelper.customizeChatLayout(getActivity(), mChatLayout);
        initTitleBar();
        initChatContent();
    }

    private void initTitleBar() {
        //获取单聊面板的标题栏
        mTitleBar = mChatLayout.getTitleBar();
        mTitleBar.setLeftIcon(R.mipmap.icon_back);
        mTitleBar.setBackgroundColor(getResources().getColor(R.color.white));
        //单聊面板标记栏返回按钮点击事件，这里需要开发者自行控制
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void initChatContent() {
        mChatLayout.getMessageLayout().setOnItemClickListener(
                new MessageLayout.OnItemClickListener() {
                    @Override
                    public void onMessageLongClick(View view, int position, MessageInfo messageInfo) {
                        //因为adapter中第一条为加载条目，位置需减1
                        mChatLayout.getMessageLayout().showItemPopMenu(position - 1, messageInfo, view);
                    }

                    @Override
                    public void onUserIconClick(View view, int position, MessageInfo messageInfo) {
                        ToastUtils.showShortToast(getContext(), "点击用户头像");
                    }
                }
        );
        mChatLayout.getMessageLayout().setOnClassCustomMessageListener(new IOnClassCustomMessageListener() {
            @Override
            public void onAgreeAppointment(View view, MessageInfo messageInfo, ClassMessageHelper classMessageHelper, String id) {
                getPresenter().updateUserAppointmentUserConfirm(
                        id,
                        true,
                        classMessageHelper,
                        messageInfo);
            }

            @Override
            public void onRefuseAppointment(View view, MessageInfo messageInfo, ClassMessageHelper classMessageHelper, String id) {
                getPresenter().updateUserAppointmentUserConfirm(
                        id,
                        false,
                        classMessageHelper,
                        messageInfo
                );
            }

            @Override
            public void onQueryStatus(View view, MessageInfo messageInfo, ClassMessageHelper classMessageHelper) {
                SchedulingMessageBean schedulingMessage = MessageInfoUtil.getSchedulingMessage(messageInfo);
                getPresenter().queryMasterAppointmentStatusById(schedulingMessage.id, messageInfo, classMessageHelper);
            }
        });
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.IM_MECHANISM_COURSE_ENTER.equals(event.getAction())) {
            Object data = event.getData();
            if (data instanceof String) {
                String id = (String) data;
                getPresenter().queryMechanismCourseListById(id);
            }
        } else if (EventAction.MECHANISM_SEND_SCHEDULING_MECHANISM_COURSE.equals(event.getAction())) {
            Object data = event.getData();
            if (data instanceof String) {
                // 发送课程邀请
                MessageInfo tipInviteMessage = MessageInfoUtil.buildCourseCustomMessage(JsonUtil.toJson(new SchedulingTipMessage(
                        "0",
                        LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                        mChatInfo.getId(),
                        "classTipMessage"
                )));
                if (mChatLayout != null) {
                    mChatLayout.sendMessage(tipInviteMessage, false);
                }
                // 发送课程信息
                String json = (String) data;
                SchedulingMessageBean schedulingMessageBean = JsonUtil.fromJson(json, SchedulingMessageBean.class);
                MessageInfo messageInfo = MessageInfoUtil.buildCourseCustomMessage(JsonUtil.toJson(schedulingMessageBean));
                if (mChatLayout != null) {
                    mChatLayout.sendMessage(messageInfo, false);
                    mChatLayout.showBottomView(false);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChatLayout != null) {
            mChatLayout.exitChat();
        }
    }

    @Override
    protected ChatPresenter createPresenter() {
        return new ChatPresenter();
    }

    @Override
    public void onMechanismCourseList(List<MasterSetPriceEntity> masterSetPriceEntities) {
        if (masterSetPriceEntities != null && masterSetPriceEntities.size() > 0) {
            MasterSetPriceEntity masterSetPriceEntity = masterSetPriceEntities.get(0);
            EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_DETAIL, masterSetPriceEntity));
        }
    }

    @Override
    public void onUpdateUserAppointmentUserConfirm(Boolean whether, ClassMessageHelper classMessageHelper, MessageInfo messageInfo) {
        if (mChatLayout != null) {
            onMasterAppointmentStatus(messageInfo, whether ? "1" : "2", classMessageHelper);
            mChatLayout.sendMessage(messageInfo, false);
        }
    }

    @Override
    public void onMasterAppointmentStatus(MessageInfo messageInfo, String result, ClassMessageHelper classMessageHelper) {
        if (classMessageHelper != null)
            classMessageHelper.bindData(messageInfo,result);
    }
}
