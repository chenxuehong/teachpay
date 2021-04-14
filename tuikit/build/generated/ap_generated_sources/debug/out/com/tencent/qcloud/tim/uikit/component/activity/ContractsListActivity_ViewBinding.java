// Generated code from Butter Knife. Do not modify!
package com.tencent.qcloud.tim.uikit.component.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContractsListActivity_ViewBinding implements Unbinder {
  private ContractsListActivity target;

  @UiThread
  public ContractsListActivity_ViewBinding(ContractsListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ContractsListActivity_ViewBinding(ContractsListActivity target, View source) {
    this.target = target;

    target.mContactListView = Utils.findRequiredViewAsType(source, R.id.activity_contracts_list, "field 'mContactListView'", ContactListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContractsListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mContactListView = null;
  }
}
