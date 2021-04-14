package com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huihe.base_lib.model.SchedulingMessageBean;
import com.huihe.base_lib.model.SchedulingTipMessage;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.imsdk.v2.V2TIMCustomElem;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil;

public class ClassMessageHelper implements IOnCustomMessageDrawListener {

    private IOnClassCustomMessageListener iOnClassCustomMessageListener;
    private View classInfoView;

    public ClassMessageHelper(IOnClassCustomMessageListener iOnClassCustomMessageListener) {
        this.iOnClassCustomMessageListener = iOnClassCustomMessageListener;
    }

    @Override
    public void onDraw(ICustomMessageViewGroup parent, final MessageInfo messageInfo) {
        // 获取到自定义消息的json数据
        if (messageInfo.getTimMessage().getElemType() != V2TIMMessage.V2TIM_ELEM_TYPE_CUSTOM) {
            return;
        }
        V2TIMCustomElem elem = messageInfo.getTimMessage().getCustomElem();
        final SchedulingMessageBean schedulingMessageBean = new Gson().fromJson(new String(elem.getData()), SchedulingMessageBean.class);
        // 把自定义消息view添加到TUIKit内部的父容器里
        classInfoView = LayoutInflater.from(TUIKit.getAppContext()).inflate(R.layout.chat_item_class_info, null, false);
        parent.addMessageContentView(classInfoView);
        TextView tvTitle = classInfoView.findViewById(R.id.chat_item_class_info_tv_title);
        TextView tvTime = classInfoView.findViewById(R.id.chat_item_class_info_tv_time);
        TextView tvClassRoom = classInfoView.findViewById(R.id.chat_item_class_info_tv_classroom);
        TextView tvStatus = classInfoView.findViewById(R.id.chat_item_class_info_tv_status);
        tvStatus.setText("等待处理");
        LinearLayout llDo = classInfoView.findViewById(R.id.chat_item_class_info_ll_do);
        if (messageInfo.isSelf()) {
            tvStatus.setVisibility(View.VISIBLE);
            llDo.setVisibility(View.GONE);
        } else {
            tvStatus.setVisibility(View.GONE);
            llDo.setVisibility(View.VISIBLE);
        }
        tvTitle.setText(schedulingMessageBean.title);
        tvTime.setText("时间:".concat(schedulingMessageBean.start_time));
        tvClassRoom.setText("地点:".concat(schedulingMessageBean.classroom));

        classInfoView.findViewById(R.id.chat_item_class_info_tv_refuse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromUser = messageInfo.getFromUser();
                String user_id = LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id();
                MessageInfo tipInviteMessage = MessageInfoUtil.buildCourseCustomMessage(JsonUtil.toJson(new SchedulingTipMessage(
                        "2",
                        user_id,
                        fromUser,
                        "classTipMessage"

                )));
                if (iOnClassCustomMessageListener != null) {
                    iOnClassCustomMessageListener.onRefuseAppointment(view, tipInviteMessage, ClassMessageHelper.this, schedulingMessageBean.id);
                }
            }
        });
        classInfoView.findViewById(R.id.chat_item_class_info_tv_agree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromUser = messageInfo.getFromUser();
                String user_id = LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id();

                MessageInfo tipInviteMessage = MessageInfoUtil.buildCourseCustomMessage(JsonUtil.toJson(new SchedulingTipMessage(
                        "1",
                        user_id,
                        fromUser,
                        "classTipMessage"
                )));
                if (iOnClassCustomMessageListener != null) {
                    iOnClassCustomMessageListener.onAgreeAppointment(view, tipInviteMessage, ClassMessageHelper.this, schedulingMessageBean.id);
                }
            }
        });
        if (iOnClassCustomMessageListener != null) {
            iOnClassCustomMessageListener.onQueryStatus(classInfoView, messageInfo, ClassMessageHelper.this);
        }
    }

    public void bindData(MessageInfo messageInfo, String result) {
        if (classInfoView != null) {
            TextView tvStatus = classInfoView.findViewById(R.id.chat_item_class_info_tv_status);
            LinearLayout llDo = classInfoView.findViewById(R.id.chat_item_class_info_ll_do);
            if ("0".equals(result)) {
                if (messageInfo.isSelf()) {
                    tvStatus.setText("等待处理");
                    llDo.setVisibility(View.GONE);
                    tvStatus.setVisibility(View.VISIBLE);
                } else {
                    llDo.setVisibility(View.VISIBLE);
                    tvStatus.setVisibility(View.GONE);
                }
            } else if ("1".equals(result)) {
                llDo.setVisibility(View.GONE);
                tvStatus.setVisibility(View.VISIBLE);
                if (messageInfo.isSelf()) {
                    tvStatus.setText("对方已同意");
                } else {
                    tvStatus.setText("已同意");
                }
            } else if ("2".equals(result)) {
                llDo.setVisibility(View.GONE);
                tvStatus.setVisibility(View.VISIBLE);
                if (messageInfo.isSelf()) {
                    tvStatus.setText("对方已拒绝");
                } else {
                    tvStatus.setText("已拒绝");
                }
            }
        }
    }
}
