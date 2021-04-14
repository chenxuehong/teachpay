package com.huihe.entities_lib.transform;

import androidx.fragment.app.Fragment;

public class MainTabBean {

    String title;
    int selectedImgId;
    int unselectedImgId;
    Class<? extends Fragment> fragmentClass;

    public MainTabBean(String title, int selectedImgId, int unselectedImgId, Class<? extends Fragment> fragmentClass) {
        this.title = title;
        this.selectedImgId = selectedImgId;
        this.unselectedImgId = unselectedImgId;
        this.fragmentClass = fragmentClass;
    }

    public String getTitle() {
        return title;
    }

    public int getSelectedImgId() {
        return selectedImgId;
    }

    public int getUnselectedImgId() {
        return unselectedImgId;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }
}
