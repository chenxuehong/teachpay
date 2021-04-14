package com.huihe.base_lib.model.personal;

import java.util.List;

public class IncomeEntity {


    private int not_Account;
    private int cumulativeMontlyEarnings;
    private int cumulativeEarnings;
    private List<AppointmentinfoBean> userAppointmentEntities;

    public int getNot_Account() {
        return not_Account;
    }

    public void setNot_Account(int not_Account) {
        this.not_Account = not_Account;
    }

    public int getCumulativeMontlyEarnings() {
        return cumulativeMontlyEarnings;
    }

    public void setCumulativeMontlyEarnings(int cumulativeMontlyEarnings) {
        this.cumulativeMontlyEarnings = cumulativeMontlyEarnings;
    }

    public int getCumulativeEarnings() {
        return cumulativeEarnings;
    }

    public void setCumulativeEarnings(int cumulativeEarnings) {
        this.cumulativeEarnings = cumulativeEarnings;
    }

    public List<AppointmentinfoBean> getUserAppointmentEntities() {
        return userAppointmentEntities;
    }

    public void setUserAppointmentEntities(List<AppointmentinfoBean> userAppointmentEntities) {
        this.userAppointmentEntities = userAppointmentEntities;
    }

}
