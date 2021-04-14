package com.eghuihe.module_user.me.activity;

import android.view.View;
import android.widget.ImageView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

public class SwitchIdentityActivity extends BaseTitleActivity {

    @BindView(R2.id.activity_switch_identity_iv_student_check)
    ImageView ivStudentCheckBox;
    @BindView(R2.id.activity_switch_identity_iv_mechanism_check)
    ImageView ivMechanismCheckBox;
    private boolean isStudent;

    @OnClick({
            R2.id.activity_switch_identity_ll_student,
            R2.id.activity_switch_identity_ll_mechanism,
            R2.id.activity_switch_identity_tv_enter_teachPay
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_switch_identity_ll_student) {
            isStudent = true;
            checkStudent(isStudent);
        } else if (view.getId() == R.id.activity_switch_identity_ll_mechanism) {
            isStudent = false;
            checkStudent(isStudent);
        } else if (view.getId() == R.id.activity_switch_identity_tv_enter_teachPay) {
            if (Utils.isSwitchMechanismIdentity() && !isStudent) {
                finish();
            } else if (!Utils.isSwitchMechanismIdentity() && isStudent) {
                finish();
            } else {
                Utils.switchIdentity(isStudent);
                EventBusUtils.sendEvent(new Event(EventAction.TEACH_PAY_SWITCH_IDENTITIES));
                finish();
            }
        }
    }

    private void checkStudent(boolean isStudent) {
        ivStudentCheckBox.setImageResource(isStudent ? R.mipmap.identity_check : R.mipmap.identity_uncheck);
        ivMechanismCheckBox.setImageResource(!isStudent ? R.mipmap.identity_check : R.mipmap.identity_uncheck);
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("切换身份");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_switch_identity;
    }

    @Override
    protected void initData() {
        isStudent = !Utils.isSwitchMechanismIdentity();
        checkStudent(isStudent);
    }
}
