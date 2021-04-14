package com.eghuihe.module_user.bean.params;

public class SettleMechanismTeachPayStep3Bean {
    public String categories;
    public String categories_child;
    public String mechanism_logo;
    public String mechanism_name;
    public String mechanism_addr;
    public Boolean is_support_teach_paypal;
    public double longitude;
    public double latitude;
    public String mechanism_telephone;
    public String contact_telephone;
    public String contacts;
    public String payee_logon_id;
    public String introduction_content;
    public String support_means;
    public String introduction_pic;
    public String facade_view;

    public SettleMechanismTeachPayStep3Bean(
            String categories,
            String mechanism_logo,
            String mechanism_name,
            String mechanism_addr,
            Boolean is_support_teach_paypal,
            double longitude,
            double latitude,
            String mechanism_telephone,
            String categories_child,
            String contact_telephone,
            String contacts,
            String payee_logon_id,
            String introduction_content,
            String support_means,
            String introduction_pic,
            String facade_view
    ) {
        this.categories = categories;
        this.mechanism_logo = mechanism_logo;
        this.mechanism_name = mechanism_name;
        this.mechanism_addr = mechanism_addr;
        this.is_support_teach_paypal = is_support_teach_paypal;
        this.longitude = longitude;
        this.latitude = latitude;
        this.mechanism_telephone = mechanism_telephone;
        this.categories_child = categories_child;
        this.contact_telephone = contact_telephone;
        this.contacts = contacts;
        this.payee_logon_id = payee_logon_id;
        this.introduction_content = introduction_content;
        this.support_means = support_means;
        this.introduction_pic = introduction_pic;
        this.facade_view = facade_view;
    }
}
