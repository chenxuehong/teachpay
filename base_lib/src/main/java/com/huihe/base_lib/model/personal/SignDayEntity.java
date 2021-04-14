package com.huihe.base_lib.model.personal;

public class SignDayEntity {
    private String day;
    public String catCoin;
    private boolean today_sign_in;

    public SignDayEntity(String day, String catCoin, boolean today_sign_in) {
        this.day = day;
        this.catCoin = catCoin;
        this.today_sign_in = today_sign_in;
    }

    public String getDay() {
        return day;
    }

    public String getCatCoin() {
        return catCoin;
    }

    public boolean isToday_sign_in() {
        return today_sign_in;
    }

    public void setToday_sign_in(boolean today_sign_in) {
        this.today_sign_in = today_sign_in;
    }
}
