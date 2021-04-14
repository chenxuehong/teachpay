package com.huihe.base_lib.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Filter;
import android.widget.SearchView;
import android.widget.TextView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.cityselect.CityModel;
import com.huihe.base_lib.ui.CustomLinearLayoutManager;
import com.huihe.base_lib.ui.adapter.ExpandRVAdapter;
import com.huihe.base_lib.ui.adapter.FilterRvAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.indexBar.widget.LetterSideBar;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerSearchTitle;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.CitySelectUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * @desc 城市选择器
 */
public class CitySelectActivity extends BaseTitleActivity {

    public static final String KEY_DATA = "city";
    RecyclerViewFixed mRvCountrysSelect;
    LetterSideBar letterSideBar;
    TextView mTvSideBarHint;

    CustomerSearchTitle customerSearchTitle;
    private FilterRvAdapter countrysRVAdapter;
    private ExpandRVAdapter expandRVAdapter;
    private List<String> filterData;
    private LoginResultEntity loginResultEntity;
    private List<String> mCityList;
    private String type;

    public static final String TYPE_COUNTRY = "type_country";
    public static final String TYPE_CITY = "type_city";
    public static final String KEY_SELECT_TYPE = "select_type";
    private String selectType;
    private List<CityModel.LocationBean.CountryRegionBean.StateBean> proBeans;
    private StringBuffer stringBuffer;
    private List<CityModel.LocationBean.CountryRegionBean.StateBean.CityBean> citys;

    public LoginResultEntity getLoginResultEntity() {
        return loginResultEntity;
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_country_select;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        super.initTitle(customerTitle);
        customerTitle.setTitle(getResources().getString(R.string.city_select));
    }

    @Override
    protected void initView() {
        super.initView();
        mRvCountrysSelect = inflate.findViewById(R.id.country_select_rv_country_select);
        letterSideBar = inflate.findViewById(R.id.country_select_letterSideBar);
        mTvSideBarHint = inflate.findViewById(R.id.country_tvSideBarHint);
        CustomLinearLayoutManager mManager = new CustomLinearLayoutManager(this);
        mRvCountrysSelect.setLayoutManager(mManager);
        letterSideBar.setPressedShowTextView(mTvSideBarHint)
                .setNeedRealIndex(false)
                .setLayoutManager(mManager);
    }

    @Override
    protected void initData() {
        selectType = getIntent().getStringExtra(KEY_SELECT_TYPE);
        loginResultEntity = LoginHelper.getLoginInfo();
        mCityList = getCityList("中国");
        stringBuffer = new StringBuffer();
        letterSideBar.setData(mCityList);
        countrysRVAdapter = new FilterRvAdapter(R.layout.text, this, mCityList, new Filter.FilterListener() {
            @Override
            public void onFilterComplete(int count) {

            }
        }) {
            @Override
            protected void covert(ViewHolder viewHolder, final String city, final int position) {


                viewHolder.setText(R.id.item_language_tv_name, city);
                viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {

                    @Override
                    public void onNoDoubleClick(View v) {

                        itemClicked(city, position);
                    }
                });
            }
        };
        expandRVAdapter = new ExpandRVAdapter(countrysRVAdapter);
        View searchView = View.inflate(this, R.layout.country_select_search, null);
        initSearchView(searchView);
        expandRVAdapter.addHanderView(searchView);
        mRvCountrysSelect.setAdapter(expandRVAdapter);

    }

    private void itemClicked(String city, int position) {
        // 携带数据
        returnData(city);
    }

    private void returnData(String city) {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        intent.putExtra(KEY_DATA, city);
        finish();
    }

    private List<String> getCityList(String country) {
        mCityList = new ArrayList<>();
        CityModel.LocationBean.CountryRegionBean selectCountryRegionBean = null;
        List<CityModel.LocationBean.CountryRegionBean> allCountryEntityList = CitySelectUtils.getAllCountryEntityList(this);
        for (int i = 0; i < allCountryEntityList.size(); i++) {
            CityModel.LocationBean.CountryRegionBean countryRegionBean = allCountryEntityList.get(i);
            String name = countryRegionBean.getName();
            if (country.equals(name)) {
                selectCountryRegionBean = countryRegionBean;
            }
        }
        if (selectCountryRegionBean != null) {
            List<CityModel.LocationBean.CountryRegionBean.StateBean> stringProyList = selectCountryRegionBean.getState();
            if (stringProyList != null) {
                for (int i = 0; i < stringProyList.size(); i++) {
                    CityModel.LocationBean.CountryRegionBean.StateBean stateBean = stringProyList.get(i);
                    List<CityModel.LocationBean.CountryRegionBean.StateBean.CityBean> cityList = stateBean.getCity();
                    if (cityList != null && cityList.size() > 0) {
                        for (int i1 = 0; i1 < cityList.size(); i1++) {
                            CityModel.LocationBean.CountryRegionBean.StateBean.CityBean cityBean = cityList.get(i1);
                            mCityList.add(cityBean.getName());
//                            List<CityModel.LocationBean.CountryRegionBean.StateBean.CityBean.RegionBean> region = cityBean.getRegion();
//                            if (region!=null){
//                                for (int i2 = 0; i2 < region.size(); i2++) {
//                                    CityModel.LocationBean.CountryRegionBean.StateBean.CityBean.RegionBean regionBean = region.get(i2);
//                                    mCityList.add(regionBean.getName());
//                                }
//                            }
                        }
                    } else {
                        mCityList.add(stateBean.getName());
                    }
                }
            }
        }
        Collections.sort(mCityList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Collator.getInstance(Locale.CHINA).compare(o1, o2);
            }
        });
        return mCityList;
    }

    private void initSearchView(View searchView) {
        customerSearchTitle = searchView.findViewById(R.id.country_select_search_title);
        customerSearchTitle.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (TextUtils.isEmpty(newText) || newText.length() == 0) {
                    // 显示所有的国家列表和字母索引
                    letterSideBar.setVisibility(View.VISIBLE);
                    countrysRVAdapter.setData(mCityList);
                    expandRVAdapter.notifyDataSetChanged();
                } else {
                    letterSideBar.setVisibility(View.GONE);
                    if (countrysRVAdapter != null) {
                        filterData = StringUtils.getFilterData(mCityList, newText);
                        countrysRVAdapter.setData(filterData);
                        expandRVAdapter.notifyDataSetChanged();
                    }
                }

                return false;
            }
        });
    }


}
