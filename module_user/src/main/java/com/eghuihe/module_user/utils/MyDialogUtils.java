package com.eghuihe.module_user.utils;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.utils.ColorAndSizeTextUtil;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.ToastUtils;

import java.util.List;

public class MyDialogUtils {

    public static BaseDialog showAgreementTipDialog(final Context context, final OnListener onListener) {

        BaseDialog baseDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_tip_agreement;
            }

            @Override
            protected void initEvents() {
                TextView tvTipTitle2 = (TextView) getView(R.id.dialog_tip_agreement_tv_title2);
                final CheckBox checkBox = (CheckBox) getView(R.id.dialog_tip_agreement_agree_checkBox);
                TextView tvAgreeTitle = (TextView) getView(R.id.dialog_tip_agreement_agree_tv_title);
                TextView tvTipCancel = (TextView) getView(R.id.dialog_tip_agreement_cancel);
                TextView tvTipOk = (TextView) getView(R.id.dialog_tip_agreement_ok);

                String content = "请仔细阅读并充分理解《用户协议》和《隐私协议》";
                String agreeContent = "同意表示已阅读并认同《用户协议》和《隐私协议》";
                ColorAndSizeTextUtil.setLinkedText(
                        tvTipTitle2,
                        content,
                        new String[]{
                                "《用户协议》",
                                "《隐私协议》"},
                        new int[]{R.color.color_FF7300, R.color.color_FF7300}
                        , new String[]{
                                DataLoader.getInstance().getCooperation(AppConfigs.Cooperation.ZH.USER_AGREEMENT,
                                        AppConfigs.Cooperation.EN.USER_AGREEMENT),
                                DataLoader.getInstance().getCooperation(AppConfigs.Cooperation.ZH.PRIVACY,
                                        AppConfigs.Cooperation.EN.PRIVACY)},
                        12);
                ColorAndSizeTextUtil.setLinkedText(
                        tvAgreeTitle,
                        agreeContent,
                        new String[]{
                                "《用户协议》",
                                "《隐私协议》"},
                        new int[]{R.color.color_FF7300, R.color.color_FF7300}
                        , new String[]{
                                DataLoader.getInstance().getCooperation(AppConfigs.Cooperation.ZH.USER_AGREEMENT,
                                        AppConfigs.Cooperation.EN.USER_AGREEMENT),
                                DataLoader.getInstance().getCooperation(AppConfigs.Cooperation.ZH.PRIVACY,
                                        AppConfigs.Cooperation.EN.PRIVACY)},
                        12);
                tvTipCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        if (onListener != null) {
                            onListener.onCancel();
                        }

                    }
                });
                tvTipOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!checkBox.isChecked()) {
                            ToastUtils.showShortToast(context, "请先选中同意");
                            return;
                        }
                        dismiss();
                        if (onListener != null) {
                            onListener.onSure();
                        }
                    }
                });
            }
        };
        baseDialog.setCancelOutside(false);
        baseDialog.show();
        return baseDialog;
    }

    public static BaseDialog showMechanismCourseBuySuccessfulDialog(Context context, final MasterSetPriceEntity exclusiveCoursesEntity, final OnCourseBuySuccListener onListener) {
        BaseDialog baseDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_mechanism_course_buy_succ;
            }

            @Override
            protected void initParams() {

                if (exclusiveCoursesEntity != null) {
                    if (container(exclusiveCoursesEntity, "grouping")) {
                        getView(R.id.dialog_mechanism_course_buy_succ_tv_pin_group).setVisibility(View.VISIBLE);
                    }else {
                        getView(R.id.dialog_mechanism_course_buy_succ_tv_pin_group).setVisibility(View.GONE);
                    }
                }
            }

            @Override
            protected void initEvents() {
                getView(R.id.dialog_mechanism_course_buy_succ_tv_go_appointment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener != null) {
                            onListener.onGoAppointment();
                        }
                    }
                });
                getView(R.id.dialog_mechanism_course_buy_succ_tv_pin_group).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener != null) {
                            onListener.onGoPinGroup();
                        }
                    }
                });
                getView(R.id.dialog_mechanism_course_buy_succ_iv_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
            }
        };
        baseDialog.setPerWidth(338f / 414);
        baseDialog.setCancelOutside(false);
        baseDialog.show();
        return baseDialog;
    }

    private static boolean container(MasterSetPriceEntity masterSetPriceEntity, String type) {
        MasterSetPriceEntity.Map map = masterSetPriceEntity.getMap();
        if (map != null) {
            List<BusinessActivityTypeEntity> activityEntityList = map.getActivityEntityList();
            if (activityEntityList != null) {
                for (int i = 0; i < activityEntityList.size(); i++) {
                    BusinessActivityTypeEntity businessActivityTypeEntity = activityEntityList.get(i);
                    if (type.equals(businessActivityTypeEntity.getType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public interface OnCourseBuySuccListener {
        void onGoAppointment();

        void onGoPinGroup();
    }

    public interface OnListener {
        void onCancel();

        void onSure();
    }

}
