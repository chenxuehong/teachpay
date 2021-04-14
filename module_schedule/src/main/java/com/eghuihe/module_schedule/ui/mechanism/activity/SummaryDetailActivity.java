package com.eghuihe.module_schedule.ui.mechanism.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.mvp.SummaryDetailContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.SummaryDetailPresenter;
import com.eghuihe.module_schedule.ui.student.adapter.PicRvAdapter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.SummaryInfoEntity;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ConvertUtils;
import com.huihe.base_lib.utils.DensityUtils;

import java.util.List;

import butterknife.BindView;

@Route(path = ARouterConfig.SCHEDULE_SUMMARYDETAILACTIVITY)
public class SummaryDetailActivity extends BaseMvpTitleActivity<SummaryDetailPresenter>
        implements SummaryDetailContract.View {

    @BindView(R2.id.activity_summary_detail_tv_content)
    TextView tvContent;
    @BindView(R2.id.activity_summary_detail_rv_pics)
    RecyclerViewFixed rvPics;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("课程总结");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_summary_detail;
    }

    @Override
    protected SummaryDetailPresenter createPresenter() {
        return new SummaryDetailPresenter();
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            String appointment_id = intent.getStringExtra(ArgumentsConfig.KEY_APPOINTMENT_ID);
            getPresenter().querySummaryList(appointment_id);
        }
    }

    @Override
    public void onSummaryList(List<SummaryInfoEntity> summaryInfoEntities) {
        if (summaryInfoEntities != null && summaryInfoEntities.size() > 0) {
            SummaryInfoEntity summaryInfoEntity = summaryInfoEntities.get(0);
            tvContent.setText(summaryInfoEntity.getContent());
            rvPics.setVertical(3);
            String photo_url = summaryInfoEntity.getPhoto_url();
            if (!TextUtils.isEmpty(photo_url)) {
                String[] split = photo_url.split(",");
                if (split != null && split.length > 0) {
                    rvPics.setVertical(3);
                    rvPics.addGridViewItemDecoration(3, DensityUtils.dp2px(this, 12));
                    List<String> list = ConvertUtils.toList(split);
                    rvPics.setAdapter(new PicRvAdapter(R.layout.item_note_img, this, list));
                }

            }

        }
    }
}
