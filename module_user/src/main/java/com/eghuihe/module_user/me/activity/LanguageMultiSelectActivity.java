package com.eghuihe.module_user.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.db.LanguageEntity;
import com.huihe.base_lib.ui.CustomLinearLayoutManager;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.indexBar.suspension.SuspensionDecoration;
import com.huihe.base_lib.ui.widget.indexBar.widget.LetterSideBar;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerSearchTitle;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.MultiLanguageUtil;
import com.huihe.base_lib.utils.PListParserUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LanguageMultiSelectActivity extends BaseTitleActivity {

    @BindView(R2.id.language_multiselect_rv_rm_languages)
    RecyclerViewFixed mRvRMLanguageList;
    @BindView(R2.id.language_multiselect_rv_all_languages)
    RecyclerViewFixed mRvAllLanguageList;
    @BindView(R2.id.language_multiselect_letterSideBar)
    LetterSideBar letterSideBar;
    @BindView(R2.id.country_select_search_title)
    CustomerSearchTitle customerSearchTitle;
    @BindView(R2.id.language_multiselect_complete)
    TextView mTvComplete;
    @BindView(R2.id.language_tvSideBarHint)
    TextView mTvSideBarHint;

    private List<LanguageEntity> checkedLanguageList = new ArrayList<>();
    private CommonRVAdapter<LanguageEntity> rmRVAdapter;
    private CommonRVAdapter<LanguageEntity> allLanguageRvAdapter;
    private List<LanguageEntity> languageEntityList;
    private List<String> languageList = new ArrayList<>();
    private CustomLinearLayoutManager mManager;
    private SuspensionDecoration mDecoration;

    @OnClick({R2.id.language_multiselect_complete})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.language_multiselect_complete) {
                LoginHelper.saveMultiSelectLanguageList(checkedLanguageList);
                Intent intent = new Intent();
                intent.putExtra("language", JsonUtil.toJson(checkedLanguageList)); //放置要传出的数据
                LanguageMultiSelectActivity.this.setResult(Activity.RESULT_OK, intent);
                LanguageMultiSelectActivity.this.finish(); //关闭活动
            }
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        super.initTitle(customerTitle);
        customerTitle.setTitle(getResources().getString(R.string.language));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_language_multiselect;
    }

    @Override
    protected void initView() {
        super.initView();
        mManager = new CustomLinearLayoutManager(this);
        mRvAllLanguageList.setLayoutManager(mManager);
        letterSideBar.setPressedShowTextView(mTvSideBarHint)
                .setNeedRealIndex(false)
                .setLayoutManager(mManager);
    }

    @Override
    protected void initData() {

        mRvRMLanguageList.setVertical(4);
        mRvRMLanguageList.addGridViewItemDecoration(4, 24, 0);

        languageEntityList = PListParserUtils.getLanguageEntityList(this);

        mRvAllLanguageList.addItemDecoration(mDecoration = new SuspensionDecoration(this, languageEntityList));

        languageList.clear();
        for (int i = 0; i < languageEntityList.size(); i++) {
            LanguageEntity languageEntity = languageEntityList.get(i);
            languageList.add(languageEntity.getTarget());
        }
        checkedLanguageList = LoginHelper.getMultiSelectLanguageList() == null ? new ArrayList<>() : LoginHelper.getMultiSelectLanguageList();
        rmRVAdapter = new CommonRVAdapter<LanguageEntity>(R.layout.item_checked_language, this, checkedLanguageList) {
            @Override
            protected void covert(ViewHolder viewHolder, LanguageEntity languageEntity, final int position) {

                String target = languageEntity.getTarget();
                viewHolder.setText(R.id.item_checked_language_tv_name, target);
                viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {
                        checkedLanguageList.remove(position);
                        notifyDataSetChanged();
                    }
                });
            }
        };
        mRvRMLanguageList.setAdapter(rmRVAdapter);
        letterSideBar.setData(languageList);
        allLanguageRvAdapter = new CommonRVAdapter<LanguageEntity>(R.layout.text, this, languageEntityList) {
            @Override
            protected void covert(ViewHolder viewHolder, final LanguageEntity languageEntity, int position) {
                if (MultiLanguageUtil.getInstance().isZh()) {
                    String value = languageEntity.getValue();
                    viewHolder.setText(R.id.item_language_tv_name, value);
                } else {
                    String code = languageEntity.getCode();
                    viewHolder.setText(R.id.item_language_tv_name, code);
                }

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!EventUtils.isFastDoubleClick(v)) {
                            if (hasLanguage(checkedLanguageList, languageEntity.getCode())) {
                                ToastUtils.showLongToast(LanguageMultiSelectActivity.this, "本语言已经选中");
                            } else {
                                checkedLanguageList.add(languageEntity);
                                rmRVAdapter.notifyItemChanged(rmRVAdapter.getItemCount());
                            }

                        }
                    }
                });
            }
        };
        mRvAllLanguageList.setAdapter(allLanguageRvAdapter);
        customerSearchTitle.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (TextUtils.isEmpty(newText) || newText.length() == 0) {
                    letterSideBar.setVisibility(View.VISIBLE);
                    allLanguageRvAdapter.setData(languageEntityList);
                } else {
                    letterSideBar.setVisibility(View.GONE);
                    if (allLanguageRvAdapter != null) {

                        List<LanguageEntity> filterData = getFilterData(languageEntityList, newText);
                        allLanguageRvAdapter.setData(filterData);
                    }
                }
                allLanguageRvAdapter.notifyDataSetChanged();
                return false;
            }
        });
        letterSideBar.setSourceDatas(languageEntityList).invalidate();
    }

    private boolean hasLanguage(List<LanguageEntity> checkedLanguageList, String code) {

        if (checkedLanguageList != null) {
            for (int i = 0; i < checkedLanguageList.size(); i++) {
                LanguageEntity languageEntity = checkedLanguageList.get(i);
                if (languageEntity.getCode().equals(code)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<LanguageEntity> getFilterData(List<LanguageEntity> languageEntities, String newText) {
        List<LanguageEntity> newlist = new ArrayList<>();
        if (languageEntities != null) {
            for (int i = 0; i < languageEntities.size(); i++) {
                LanguageEntity languageEntity = languageEntities.get(i);
                if (MultiLanguageUtil.getInstance().isZh()) {
                    String value = languageEntity.getValue();
                    if (value.trim().toLowerCase().contains(newText.trim().toLowerCase())) {
                        newlist.add(languageEntity);
                    }
                } else {
                    String code = languageEntity.getCode();
                    if (code.trim().toLowerCase().contains(newText.trim().toLowerCase())) {
                        newlist.add(languageEntity);
                    }
                }
            }
        }
        return newlist;
    }

}
