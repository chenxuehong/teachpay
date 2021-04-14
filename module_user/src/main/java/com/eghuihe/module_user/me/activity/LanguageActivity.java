package com.eghuihe.module_user.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.db.LanguageEntity;
import com.huihe.base_lib.ui.CustomLinearLayoutManager;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.indexBar.suspension.SuspensionDecoration;
import com.huihe.base_lib.ui.widget.indexBar.widget.LetterSideBar;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.MultiLanguageUtil;
import com.huihe.base_lib.utils.PListParserUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LanguageActivity extends BaseTitleActivity {

    public static final String KEY_LANGUAGE_ENTITY = "key_language";
    public static final int REQUEST_CODE = 100;
    @BindView(R2.id.language_rv)
    RecyclerViewFixed languageRv;
    @BindView(R2.id.language_letterSideBar)
    LetterSideBar letterSideBar;
    @BindView(R2.id.language_tvSideBarHint)
    TextView mTvSideBarHint;
    @BindView(R2.id.language_tv_history_title)
    TextView mTvHistoryTitle;
    @BindView(R2.id.language_rv_history_list)
    RecyclerViewFixed mRVHistory;
    //    @InjectView(R.id.head_history_tv_language)
//    TextView tvLanguage;
//    @InjectView(R.id.head_history_tv_no_limit)
//    TextView tvLimit;
    private CustomLinearLayoutManager mManager;
    private SuspensionDecoration mDecoration;
    private List<LanguageEntity> mData;
    private CommonRVAdapter mAdapter;
    private List<LanguageEntity> multiSelectLanguageList;

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_language;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        super.initTitle(customerTitle);
        customerTitle.setTitle(getResources().getString(R.string.language));
    }

    @Override
    protected void initView() {
        super.initView();
        mManager = new CustomLinearLayoutManager(this);
        languageRv.setLayoutManager(mManager);
        letterSideBar.setPressedShowTextView(mTvSideBarHint)
                .setNeedRealIndex(false)
                .setLayoutManager(mManager);

        // 历史记录
        mRVHistory.setVertical(3);
        mRVHistory.addGridViewItemDecoration(3, DensityUtils.dp2px(this,12));
        mRVHistory.setScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        mData = PListParserUtils.getLanguageEntityList(this);
        languageRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mData));

        multiSelectLanguageList = LoginHelper.getMultiSelectLanguageList();
        List<LanguageEntity> languageEntities = new ArrayList<>();
        if (multiSelectLanguageList != null && multiSelectLanguageList.size() > 0)
            languageEntities.addAll(multiSelectLanguageList);
        if (mIsShowNoLimitl) {
            LanguageEntity languageEntity = new LanguageEntity("No limit", "No limit", "不限");
            languageEntities.add(0,languageEntity);
        }

        mAdapter = new CommonRVAdapter<LanguageEntity>(R.layout.text, this, new ArrayList<>()) {
            @Override
            protected void covert(ViewHolder viewHolder, final LanguageEntity languageEntity) {
                if (MultiLanguageUtil.getInstance().isZh()) {
                    viewHolder.setText(R.id.item_language_tv_name, languageEntity.getValue());
                } else {
                    viewHolder.setText(R.id.item_language_tv_name, languageEntity.getCode());
                }

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!EventUtils.isFastDoubleClick(v)) {

                            LoginHelper.saveHomeShowLanguage(languageEntity);
                            if (multiSelectLanguageList == null) {
                                multiSelectLanguageList = new ArrayList<>();
                            }

                            if (!containerLanguage(multiSelectLanguageList,languageEntity)){
                                if (multiSelectLanguageList != null && multiSelectLanguageList.size() > 4) {
                                    multiSelectLanguageList.remove(0);
                                }
                                multiSelectLanguageList.add(languageEntity);
                            }
                            LoginHelper.saveMultiSelectLanguageList(multiSelectLanguageList);

                            // 携带数据
                            Intent intent = new Intent();
                            LanguageActivity.this.setResult(Activity.RESULT_OK, intent);
                            intent.putExtra(KEY_LANGUAGE_ENTITY, JsonUtil.toJson(languageEntity));
                            AppManager.getInstance().finishActivity(LanguageActivity.class);
                        }
                    }
                });
            }
        };

        if (languageEntities != null && languageEntities.size() > 0) {
            mRVHistory.setAdapter(new CommonRVAdapter<LanguageEntity>(
                    R.layout.item_language_history,
                    this,
                    languageEntities) {
                @Override
                protected void covert(ViewHolder viewHolder, final LanguageEntity languageEntity, int position) {

                    if (MultiLanguageUtil.getInstance().isZh()) {
                        viewHolder.setText(R.id.head_history_tv_language, languageEntity.getValue());
                    } else {
                        viewHolder.setText(R.id.head_history_tv_language, languageEntity.getCode());
                    }
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!"No limit".equals(languageEntity.getKey())) {
                                LoginHelper.saveHomeShowLanguage(languageEntity);
                                if (multiSelectLanguageList == null) {
                                    multiSelectLanguageList = new ArrayList<>();
                                }

                                if (!containerLanguage(multiSelectLanguageList,languageEntity)){
                                    if (multiSelectLanguageList != null && multiSelectLanguageList.size() > 4) {
                                        multiSelectLanguageList.remove(0);
                                    }
                                    multiSelectLanguageList.add(languageEntity);
                                }

                                LoginHelper.saveMultiSelectLanguageList(multiSelectLanguageList);
                                // 携带数据
                                Intent intent = new Intent();
                                LanguageActivity.this.setResult(Activity.RESULT_OK, intent);
                                intent.putExtra(KEY_LANGUAGE_ENTITY, JsonUtil.toJson(languageEntity));
                                AppManager.getInstance().finishActivity(LanguageActivity.class);
                            } else {
                                // 携带数据
                                Intent intent = new Intent();
                                LanguageActivity.this.setResult(Activity.RESULT_OK, intent);
                                intent.putExtra(KEY_LANGUAGE_ENTITY, "");
                                AppManager.getInstance().finishActivity(LanguageActivity.class);
                            }
                        }
                    });
                }
            });
        }
        languageRv.setAdapter(mAdapter);
        setDataSource(mData);
    }

    private boolean containerLanguage(List<LanguageEntity> multiSelectLanguageList, LanguageEntity languageEntity) {

        if (multiSelectLanguageList!=null && multiSelectLanguageList.size()>0){
            for (int i = 0; i < multiSelectLanguageList.size(); i++) {
                LanguageEntity item = multiSelectLanguageList.get(i);
                if (item.getCode().equals(languageEntity.getCode())){
                    return true;
                }
            }
        }
        return false;
    }

    public void setDataSource(List<LanguageEntity> data) {
        this.mData = data;
        mAdapter.setData(mData);
        letterSideBar.setSourceDatas(mData).invalidate();
        mDecoration.setDatas(mData);
    }

    private static boolean mIsShowNoLimitl;

    public static void isShowNoLimit(boolean isShowNoLimit) {
        mIsShowNoLimitl = isShowNoLimit;
    }
}
