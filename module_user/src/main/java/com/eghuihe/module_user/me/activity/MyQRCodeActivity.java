package com.eghuihe.module_user.me.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.PhotoTools;
import com.huihe.base_lib.utils.ZXingUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import butterknife.BindView;

public class MyQRCodeActivity extends BaseTitleActivity {

    @BindView(R2.id.myqr_code_photoview)
    ImageView photoView;
    private Bitmap bitmap;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.My_QR_code));
        customerTitle.setRightText(getResources().getString(R.string.save));
        customerTitle.setRightTextListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                // 保存
                PhotoTools.saveBitmap(MyQRCodeActivity.this,bitmap);
            }
        });
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_myar_code;
    }

    @Override
    protected void initData() {
        LoginResultEntity loginResultEntity = LoginHelper.getLoginInfo();
        UserServiceImpl.queryUserInfo(
                loginResultEntity.getUserInfoEntity().getUser_id(),
                new NetworkSubscriber<UserInfoModel>(this) {
                    @Override
                    protected void onSuccess(UserInfoModel userInfoModel) {
                        final UserInfoEntity userInfoEntityBean = userInfoModel.getData();
                        if (userInfoEntityBean != null) {
                            String url = userInfoEntityBean.getUrl();
                            bitmap = ZXingUtils.createQRImage(url, 400, 400);
                            photoView.setImageBitmap(bitmap);
                        }
                    }
                });

    }
}
