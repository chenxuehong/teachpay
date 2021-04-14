package com.eghuihe.module_user.me.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.bean.params.SettleMechanismTeachPayStep1Bean;
import com.eghuihe.module_user.bean.params.SettleMechanismTeachPayStep2Bean;
import com.huihe.base_lib.model.ExtraEntity;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.picker.MultiplePicker;
import com.huihe.base_lib.ui.widget.picker.SinglePicker;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SettleMechanismTeachPayStep2Activity extends BaseTitleActivity {
    public static final String KEY_DATA = "data";
    private SettleMechanismTeachPayStep1Bean mechanismTeachPayStep1Bean;

    @BindView(R2.id.activity_settle_mechanism_teach_pay_step2_et_mechanism_tel)
    EditText etMechanismTel;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step2_tv_mechanism_trade_model)
    TextView tvMechanismTradeModel;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step2_et_mechanism_trade_model)
    EditText etMechanismTradeModel;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step2_et_mechanism_contract_tel)
    EditText etMechanismContractTel;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step2_et_mechanism_contract_name)
    EditText etMechanismContractName;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step2_et_alipayAccount)
    EditText etAlipayAccount;
    @BindView(R2.id.activity_settle_mechanism_teach_pay_step2_et_mechanism_desc)
    EditText etMechanismDesc;
    private MultiplePicker mechanismTradeModelPicker;

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_settle_mechanism_teach_pay_step_2;
    }

    @OnClick({
            R2.id.activity_settle_mechanism_teach_pay_step2_tv_mechanism_trade_model,
            R2.id.activity_settle_mechanism_teach_pay_step2_next,
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.activity_settle_mechanism_teach_pay_step2_tv_mechanism_trade_model) {
            selectMechanismTradeModel();
        } else if (view.getId() == R.id.activity_settle_mechanism_teach_pay_step2_next) {
            nextStep();
        }
    }

    /**
     * @desc 选择经营类型
     */
    private void selectMechanismTradeModel() {

        String[] categoriesChildArr = DataLoader.getInstance().getCategoriesChildArr(mechanismTeachPayStep1Bean.store_type);
           mechanismTradeModelPicker = new MultiplePicker(
               this,
                categoriesChildArr);
        mechanismTradeModelPicker.setOnItemPickListener(new MultiplePicker.OnItemPickListener() {
            @Override
            public void onItemPicked(int count, List<String> items) {
                String string = StringUtils.list2String(items, "/");
                tvMechanismTradeModel.setText(string);
            }
        });
        mechanismTradeModelPicker.show();
    }

    /**
     * @desc 下一步
     */
    private void nextStep() {

        if (checkIntput()) {
            startActivity(SettleMechanismTeachPayStep3Activity.class,
                    new ExtraEntity(SettleMechanismTeachPayStep3Activity.KEY_DATA,
                            new SettleMechanismTeachPayStep2Bean(
                                    mechanismTeachPayStep1Bean,
                                    etMechanismTel.getText().toString().trim(),
                                    getMechanismTradeMode(),
                                    etMechanismContractTel.getText().toString().trim(),
                                    etMechanismContractName.getText().toString().trim(),
                                    etAlipayAccount.getText().toString().trim(),
                                    etMechanismDesc.getText().toString().trim()
                            )));
        }
    }

    private boolean enableMechanismTradeModel = true;

    private String getMechanismTradeMode() {

        if (tvMechanismTradeModel.getVisibility() == View.VISIBLE) {
            return tvMechanismTradeModel.getText().toString().trim();
        }
        if (etMechanismTradeModel.getVisibility() == View.VISIBLE) {
            return etMechanismTradeModel.getText().toString().trim();
        }
        return "";
    }

    private boolean checkIntput() {

        if (TextUtils.isEmpty(etMechanismTel.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请填写机构联系电话");
            return false;
        }
        if (enableMechanismTradeModel
                && tvMechanismTradeModel.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(tvMechanismTradeModel.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择机构经营业务类别");
            return false;
        }
        if (enableMechanismTradeModel
                && etMechanismTradeModel.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(etMechanismTradeModel.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入机构经营业务类别");
            return false;
        }
        if (TextUtils.isEmpty(etMechanismContractTel.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请填写机构负责人电话");
            return false;
        }
        if (TextUtils.isEmpty(etMechanismContractName.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请填写机构负责人姓名");
            return false;
        }
        if (TextUtils.isEmpty(etAlipayAccount.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请填写支付宝收款账号");
            return false;
        }
        if (TextUtils.isEmpty(etMechanismDesc.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入详细描述机构的相关介绍信息");
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        try {
            if (mechanismTradeModelPicker != null) {
                mechanismTradeModelPicker.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("机构门店入驻");
    }

    @Override
    protected void initData() {

        mechanismTeachPayStep1Bean = getIntentData(KEY_DATA, SettleMechanismTeachPayStep1Bean.class);
        String[] categoriesChildArr = DataLoader.getInstance().getCategoriesChildArr(mechanismTeachPayStep1Bean.store_type);
        if (categoriesChildArr != null && categoriesChildArr.length > 0) {
            tvMechanismTradeModel.setVisibility(View.VISIBLE);
            etMechanismTradeModel.setVisibility(View.GONE);
        } else {
            tvMechanismTradeModel.setVisibility(View.GONE);
            etMechanismTradeModel.setVisibility(View.VISIBLE);
        }
    }
}
