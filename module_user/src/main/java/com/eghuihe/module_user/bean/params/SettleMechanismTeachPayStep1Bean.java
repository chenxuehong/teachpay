package com.eghuihe.module_user.bean.params;

public class SettleMechanismTeachPayStep1Bean {
    public String store_type;
    public String mechanismLogo;
    public String mechanismName;
    public String mechanismAddress;
    public Boolean IsOpenTeachPay;
    public double longitude;
    public double latitude;
    public String facade_view;

    public SettleMechanismTeachPayStep1Bean(
            String store_type,
            String mechanismLogo,
            String mechanismName,
            String mechanismAddress,
            Boolean isOpenTeachPay,
            double longitude,
            double latitude,
            String facade_view
    ) {
        this.store_type = store_type;
        this.mechanismLogo = mechanismLogo;
        this.mechanismName = mechanismName;
        this.mechanismAddress = mechanismAddress;
        this.IsOpenTeachPay = isOpenTeachPay;
        this.longitude = longitude;
        this.latitude = latitude;
        this.facade_view = facade_view;
    }
}
