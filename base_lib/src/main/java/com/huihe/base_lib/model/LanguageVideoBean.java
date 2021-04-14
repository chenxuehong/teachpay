package com.huihe.base_lib.model;

public class LanguageVideoBean {
    public String language;
    public String languageUrl;

    public LanguageVideoBean(String language, String languageUrl) {
        this.language = language;
        this.languageUrl = languageUrl;
    }

    public String getLanguage() {
        return language;
    }

    public String getLanguageUrl() {
        return languageUrl;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLanguageUrl(String languageUrl) {
        this.languageUrl = languageUrl;
    }
}
