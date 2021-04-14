// Generated code from Butter Knife. Do not modify!
package com.tencent.qcloud.tim.uikit.component.fragment;

import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudentConversationFragment_ViewBinding implements Unbinder {
  private StudentConversationFragment target;

  @UiThread
  public StudentConversationFragment_ViewBinding(StudentConversationFragment target, View source) {
    this.target = target;

    target.mConversationLayout = Utils.findRequiredViewAsType(source, R.id.fragment_conversationLayout, "field 'mConversationLayout'", ConversationLayout.class);
    target.mflStatus = Utils.findRequiredViewAsType(source, R.id.fragment_conversationLayout_fl_status, "field 'mflStatus'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudentConversationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mConversationLayout = null;
    target.mflStatus = null;
  }
}
