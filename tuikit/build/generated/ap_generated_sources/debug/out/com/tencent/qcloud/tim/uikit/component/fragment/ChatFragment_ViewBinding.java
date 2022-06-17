// Generated code from Butter Knife. Do not modify!
package com.tencent.qcloud.tim.uikit.component.fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatFragment_ViewBinding implements Unbinder {
  private ChatFragment target;

  @UiThread
  public ChatFragment_ViewBinding(ChatFragment target, View source) {
    this.target = target;

    target.mChatLayout = Utils.findRequiredViewAsType(source, R.id.chat_layout, "field 'mChatLayout'", ChatLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChatFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mChatLayout = null;
  }
}
