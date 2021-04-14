package com.huihe.base_lib.model.request;

public class SocialContactEntity {
    public String sex;
    public String country;
    public String overseas_identity_name;
    public String user_id;
    public String type;
    public Integer start_age;
    public Integer end_age;
    public String preference;
    public String languages;
    public String business_type;

    public SocialContactEntity(String sex, String country, String overseas_identity_name, String user_id, String type, Integer start_age,Integer end_age, String preference, String languages,String business_type) {
        this.sex = sex;
        this.country = country;
        this.overseas_identity_name = overseas_identity_name;
        this.user_id = user_id;
        this.type = type;
        this.start_age = start_age;
        this.end_age = end_age;
        this.preference = preference;
        this.languages = languages;
        this.business_type = business_type;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setOverseas_identity_name(String overseas_identity_name) {
        this.overseas_identity_name = overseas_identity_name;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStart_age(int start_age) {
        this.start_age = start_age;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
