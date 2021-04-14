package com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder;

import android.view.View;

import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;

public interface IOnClassCustomMessageListener {
    void onAgreeAppointment(View view, MessageInfo messageInfo, ClassMessageHelper classMessageHelper, String id);

    void onRefuseAppointment(View view, MessageInfo messageInfo, ClassMessageHelper classMessageHelper, String id);
    void onQueryStatus(View view, MessageInfo messageInfo, ClassMessageHelper classMessageHelper);
}
