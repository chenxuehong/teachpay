package com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huihe.base_lib.model.SchedulingTipMessage;
import com.tencent.imsdk.v2.V2TIMCustomElem;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.utils.DateTimeUtil;

import java.util.Date;

public class ClassTipMessageHelper implements IOnCustomMessageDrawListener {

    public ClassTipMessageHelper() {

    }

    @Override
    public void onDraw(ICustomMessageViewGroup parent, final MessageInfo messageInfo) {
        // 获取到自定义消息的json数据
        if (messageInfo.getTimMessage().getElemType() != V2TIMMessage.V2TIM_ELEM_TYPE_CUSTOM) {
            return;
        }
        V2TIMCustomElem elem = messageInfo.getTimMessage().getCustomElem();
        final SchedulingTipMessage schedulingTipMessage = new Gson().fromJson(new String(elem.getData()), SchedulingTipMessage.class);

        // 把自定义消息view添加到TUIKit内部的父容器里
        View view = LayoutInflater.from(TUIKit.getAppContext()).inflate(R.layout.message_adapter_content_scheduling_tip, null, false);
        parent.addMessageItemView(view);

        TextView tvTipTime = view.findViewById(R.id.message_adapter_content_scheduling_tip_tv_time);
        tvTipTime.setText(DateTimeUtil.getTimeFormatText(new Date(messageInfo.getMsgTime() * 1000)));
        TextView tvTipContent = view.findViewById(R.id.message_adapter_content_scheduling_tip_tv_content);
        String result = schedulingTipMessage.result;
        if ("0".equals(result)) {
            if (messageInfo.isSelf()) {
                tvTipContent.setText("您向对方发送了约课邀请");
            } else {
                tvTipContent.setText("对方向您发送了约课邀请");
            }
        } else if ("1".equals(result)) {
            if (messageInfo.isSelf()) {
                tvTipContent.setText("您同意了对方的约课邀请");
            } else {
                tvTipContent.setText("对方同意了您的约课邀请");
            }
        } else if ("2".equals(result)) {
            if (messageInfo.isSelf()) {
                tvTipContent.setText("您拒绝了对方的约课邀请");
            } else {
                tvTipContent.setText("对方拒绝了您的约课邀请");
            }
        }
    }
}
