package com.eghuihe.module_home.home.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;

import com.eghuihe.module_home.R;
import com.eghuihe.module_home.R2;
import com.huihe.base_lib.db.MySQLiteDBDao;
import com.huihe.base_lib.db.MySQLiteOpenHelper;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.adapter.ExpandRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerSearchTitle;

import java.util.List;

import butterknife.BindView;

/**
 * @desc 查询界面
 */
public class MechanismQueryListActivity extends BaseActivity {

    @BindView(R2.id.activity_mechanism_query_list_search_title)
    CustomerSearchTitle customerSearchTitle;
    @BindView(R2.id.activity_mechanism_query_list_rv_history)
    RecyclerViewFixed rvHistoryList;
    private CommonRVAdapter historyListRvAdapter;
    private MySQLiteDBDao sqLiteDBDao;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mechanism_query_list;
    }


    @Override
    protected void initView() {
        super.initView();
        customerSearchTitle.setQueryHint(getResources().getString(R.string.Search_org_name));
        customerSearchTitle.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String history) {
                onSearch(history);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    @Override
    protected void initData() {
        rvHistoryList.setVertical(1);
        List<String> historyList = getHistoryList();
        historyListRvAdapter = new CommonRVAdapter<String>(R.layout.item_query_history, this, historyList) {
            @Override
            protected void covert(ViewHolder viewHolder, final String history, final int position) {

                viewHolder.setText(R.id.item_query_history_tv_history, history);
                viewHolder.setOnClickListener(R.id.item_query_history_tv_close,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                delete(history);
                            }
                        });
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onSearch(history);
                    }
                });
            }
        };
        ExpandRVAdapter expandAdapter = new ExpandRVAdapter(historyListRvAdapter);
        if (historyList != null && historyList.size() > 0) {
            View historyTitleView = LayoutInflater.from(this).inflate(R.layout.layout_query_history, null);
            expandAdapter.addHanderView(historyTitleView);
            View historyClearView = LayoutInflater.from(this).inflate(R.layout.layout_query_history_clear, null);
            historyClearView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearHistoryData();
                }
            });
            expandAdapter.addFootView(historyClearView);
        }

        rvHistoryList.setAdapter(
                expandAdapter
        );
    }

    private void delete(String history) {
        sqLiteDBDao = MySQLiteDBDao.getInstance(this);
        sqLiteDBDao.deleteMechanismHistory(MySQLiteOpenHelper.table.MechanismHistoryTable.TABLE_NAME, history);
        initData();
    }

    private void clearHistoryData() {
        try {
            sqLiteDBDao = MySQLiteDBDao.getInstance(this);
            List<String> historyList = getHistoryList();
            for (int i = 0; i < historyList.size(); i++) {
                sqLiteDBDao.deleteMechanismHistory(MySQLiteOpenHelper.table.MechanismHistoryTable.TABLE_NAME, historyList.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        initData();
    }

    private List<String> getHistoryList() {
        sqLiteDBDao = MySQLiteDBDao.getInstance(this);
        return sqLiteDBDao.queryAllMechanismHistoryList(MySQLiteOpenHelper.table.MechanismHistoryTable.TABLE_NAME);
    }

    private void onSearch(String history) {

        if (!containHistory(history)) {
            sqLiteDBDao = MySQLiteDBDao.getInstance(this);
            if (historyListRvAdapter != null) {
                List<String> data = historyListRvAdapter.getData();
                if (data != null && data.size() > 5) {
                    String deleteHistory = data.get(0);
                    sqLiteDBDao.deleteMechanismHistory(MySQLiteOpenHelper.table.MechanismHistoryTable.TABLE_NAME, deleteHistory);
                }
            }
            sqLiteDBDao.insertMechanismHistory(MySQLiteOpenHelper.table.MechanismHistoryTable.TABLE_NAME, history);
        }
        Intent intent = new Intent(this, MechanismListByQueryActivity.class);
        intent.putExtra(MechanismListByQueryActivity.KEY_MECHANISM_NAME, history);
        startActivity(intent);
        initData();
    }

    private boolean containHistory(String history) {
        if (historyListRvAdapter == null) {
            return false;
        }
        List<String> data = historyListRvAdapter.getData();
        if (data != null) {
            if (data.contains(history)) {
                return true;
            }
        }
        return false;
    }

}
