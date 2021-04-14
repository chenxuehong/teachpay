package com.eghuihe.module_user.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.mvp.MotherTongueSettingContract;
import com.eghuihe.module_user.me.mvp.MotherTongueSettingPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.db.LanguageEntity;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.MultiLanguageUtil;
import com.huihe.base_lib.utils.PListParserUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 翻译设置
 */
@Route(path = ARouterConfig.ME_MOTHERTONGUESETTINGACTIVITY)
public class MotherTongueSettingActivity extends BaseMvpTitleActivity<MotherTongueSettingPresenter>
implements MotherTongueSettingContract.View {

    @BindView(R2.id.transition_set_tv_motherTongue)
    TextView tvMotherTongue;

    @OnClick({
            R2.id.transition_set_ll_motherTongue
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.transition_set_ll_motherTongue){
            LanguageActivity.isShowNoLimit(false);
            startActivityForResult(new Intent(AppManager.getInstance().currentActivity(), LanguageActivity.class), LanguageActivity.REQUEST_CODE);
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Translation_set));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_translation_set;
    }

    @Override
    protected void initData() {
        UserInfoEntity userInfoEntity = LoginHelper.getLoginInfo().getUserInfoEntity();
        String mother_tongue = userInfoEntity.getMother_tongue();
        if (MultiLanguageUtil.getInstance().isZh()) {
            mother_tongue = PListParserUtils.findChineseForEnglish(this, mother_tongue);
        }
        tvMotherTongue.setText(mother_tongue);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == LanguageActivity.REQUEST_CODE) {
                    String json = data.getStringExtra(LanguageActivity.KEY_LANGUAGE_ENTITY);
                    final LanguageEntity languageEntity = JsonUtil.fromJson(json, LanguageEntity.class);
                    if (languageEntity != null) {
                        getPresenter().setMotherTongue(
                                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                                languageEntity.getCode()
                        );
                    }

                }
            }
        }
    }

    @Override
    public void onSetMotherTongueSuccess(String mother_tongue) {
        LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
        UserInfoEntity userInfoEntity = loginInfo.getUserInfoEntity();
        userInfoEntity.setMother_tongue(mother_tongue);
        loginInfo.setUserInfoEntity(userInfoEntity);
        LoginHelper.saveUserData(loginInfo);
        initData();
        ToastUtils.showShortToast(MotherTongueSettingActivity.this, getResources().getString(R.string.Set_successfully));
    }

    @Override
    protected MotherTongueSettingPresenter createPresenter() {
        return new MotherTongueSettingPresenter();
    }
}
