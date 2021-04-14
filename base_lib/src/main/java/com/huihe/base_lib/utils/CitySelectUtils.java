package com.huihe.base_lib.utils;

import android.content.Context;
import android.text.TextUtils;

import com.huihe.base_lib.model.cityselect.CityModel;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class CitySelectUtils {

    private static String fileName = "_762982city.json";

    public static List<String> getAllCountryNameList(final Context context) {

        final List<String> mCountryNameList = new ArrayList<>();
        String jsonStr = JsonFileUtils.toJsonStr(fileName, context);
        CityModel cityModel = JsonUtil.fromJson(jsonStr, CityModel.class);
        for (int i = 0; i < cityModel.getLocation().getCountryRegion().size(); i++) {
            final CityModel.LocationBean.CountryRegionBean countryRegionBean = cityModel.getLocation().getCountryRegion().get(i);
            mCountryNameList.add(countryRegionBean.getName());
        }
        if (mCountryNameList != null && mCountryNameList.size() > 0) {
            Collections.sort(mCountryNameList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {

                    return  Collator.getInstance(Locale.CHINA).compare(o1,o2);
                }
            });
        }

        return mCountryNameList;
    }

    /**
     * 获取所有国家
     * @param context
     * @return
     */
    public static List<CityModel.LocationBean.CountryRegionBean> getAllCountryEntityList(Context context) {
        String jsonStr = JsonFileUtils.toJsonStr(fileName, context);
        CityModel cityModel = JsonUtil.fromJson(jsonStr, CityModel.class);
        List<CityModel.LocationBean.CountryRegionBean> countryRegionBeans = cityModel.getLocation().getCountryRegion();
        if (countryRegionBeans != null && countryRegionBeans.size() > 0) {
            Collections.sort(countryRegionBeans, new Comparator<CityModel.LocationBean.CountryRegionBean>() {
                @Override
                public int compare(CityModel.LocationBean.CountryRegionBean o1, CityModel.LocationBean.CountryRegionBean o2) {
                    return Collator.getInstance(Locale.CHINA).compare(o1.getName(),o2.getName());
                }
            });
        }

        return countryRegionBeans;
    }
}
