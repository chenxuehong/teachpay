package com.tencent.qcloud.tim.uikit.component.activity;

import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.R2;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactItemBean;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactListView;

import butterknife.BindView;

public class ContractsListActivity extends BaseTitleActivity {
    @BindView(R2.id.activity_contracts_list)
    ContactListView mContactListView;
    @Override
    protected int getChildLayoutId() {
        return R.layout.acivity_contracts_list;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("联系人");
    }

    @Override
    protected void initData() {
        mContactListView.loadDataSource(ContactListView.DataSource.CONTACT_LIST);
        mContactListView.setOnItemClickListener(new ContactListView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ContactItemBean contact) {

            }
        });
    }
}
