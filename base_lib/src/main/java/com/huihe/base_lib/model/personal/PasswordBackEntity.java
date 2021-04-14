package com.huihe.base_lib.model.personal;

public class PasswordBackEntity {
    public int type;
    public String code;
    public String phoneNumber;
    public String email;

    public int getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public PasswordBackEntity(int type, String code, String phoneNumber, String email) {
        this.type = type;
        this.code = code;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
