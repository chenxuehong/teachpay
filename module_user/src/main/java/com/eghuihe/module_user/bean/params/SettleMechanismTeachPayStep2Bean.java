package com.eghuihe.module_user.bean.params;

public class SettleMechanismTeachPayStep2Bean {
    public SettleMechanismTeachPayStep1Bean settleMechanismTeachPayStep1Bean;
    public String mechanismTel;
    public String mechanismTradeModel;
    public String mechanismContractTel;
    public String mechanismContractName;
    public String alipayAccount;
    public String introduction_content;

    public SettleMechanismTeachPayStep2Bean(
            SettleMechanismTeachPayStep1Bean settleMechanismTeachPayStep1Bean,
            String mechanismTel,
            String mechanismTradeModel,
            String mechanismContractTel,
            String mechanismContractName,
            String alipayAccount,
            String introduction_content
    ) {
        this.settleMechanismTeachPayStep1Bean = settleMechanismTeachPayStep1Bean;
        this.mechanismTel = mechanismTel;
        this.mechanismTradeModel = mechanismTradeModel;
        this.mechanismContractTel = mechanismContractTel;
        this.mechanismContractName = mechanismContractName;
        this.alipayAccount = alipayAccount;
        this.introduction_content = introduction_content;
    }
}
