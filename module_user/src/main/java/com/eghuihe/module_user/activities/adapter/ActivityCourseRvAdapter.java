package com.eghuihe.module_user.activities.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class ActivityCourseRvAdapter extends EmptyRVAdapter<MasterSetPriceEntity> {

    private final static String TAG = ActivityCourseRvAdapter.class.getSimpleName();
    private List<String> mSelectedMasterSetPriceList;

    public List<String> getSelectedMasterSetPriceList() {
        return mSelectedMasterSetPriceList;
    }

    private RadioButton selectRbnChecked;
    private MasterSetPriceEntity selectMasterSetPriceEntity;

    public ActivityCourseRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
        mSelectedMasterSetPriceList = new ArrayList<>();
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterSetPriceEntity masterSetPriceEntity, int position) {
        String face_url = masterSetPriceEntity.getFace_url();
        GlideTools.loadImage(context,
                face_url,
                (RoundedImageView) viewHolder.getView(R.id.item_select_activity_course_iv_cover));
        String title = masterSetPriceEntity.getTitle();
        viewHolder.setText(R.id.item_select_activity_course_tv_title, title);
        String pay_num = masterSetPriceEntity.getPay_num();
        pay_num = TextUtils.isEmpty(pay_num) ? "0" : pay_num;
        viewHolder.setText(R.id.item_select_activity_course_tv_purchase_num,
                "已售".concat(pay_num));
        String label = masterSetPriceEntity.getLabel();
        String categories = masterSetPriceEntity.getCategories();
        if (!TextUtils.isEmpty(categories)) {
            viewHolder.setText(R.id.item_select_activity_course_tv_label,
                    TextUtils.isEmpty(label) ?
                            categories : categories.concat("/").concat(label));
        } else {
            viewHolder.setText(R.id.item_select_activity_course_tv_label, label);
        }
        String amout = masterSetPriceEntity.getAmout();
        viewHolder.setText(R.id.item_select_activity_course_tv_discount_price,
                NumberUtils.transMoney(amout));
        final RadioButton rbnChecked = viewHolder.getView(R.id.item_select_activity_course_rbtn);
        rbnChecked.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                        if (isChecked) {
                            if ("single_payment".equals(type)
                                    || "salesCourse".equals(type)) {
                                if (selectRbnChecked != null) {
                                    selectRbnChecked.setChecked(false);
                                }
                                if (selectMasterSetPriceEntity != null) {
                                    if (isContain(mSelectedMasterSetPriceList, selectMasterSetPriceEntity)) {
                                        mSelectedMasterSetPriceList.remove(selectMasterSetPriceEntity.getId());
                                    }
                                }
                            }

                            if (!isContain(mSelectedMasterSetPriceList, masterSetPriceEntity)) {
                                mSelectedMasterSetPriceList.add(masterSetPriceEntity.getId());
                            }
                            selectRbnChecked = rbnChecked;
                            selectMasterSetPriceEntity = masterSetPriceEntity;
                        } else {
                            if (isContain(mSelectedMasterSetPriceList, masterSetPriceEntity)) {
                                mSelectedMasterSetPriceList.remove(masterSetPriceEntity.getId());
                            }
                        }
                        printSelectedMasterSetPriceList();
                    }
                }
        );

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("single_payment".equals(type)
                        || "salesCourse".equals(type)) {
                    if (selectRbnChecked != null) {
                        selectRbnChecked.setChecked(false);
                    }
                    rbnChecked.setChecked(true);
                }else {
                    rbnChecked.setChecked(!rbnChecked.isChecked());
                }
            }
        });
    }

    private void printSelectedMasterSetPriceList() {
        if (mSelectedMasterSetPriceList!=null){
            String string = StringUtils.list2String(mSelectedMasterSetPriceList, ",");
            LogUtils.i(TAG,"mSelectedMasterSetPriceList = " .concat(string));
        }
    }

    private boolean isContain(List<String> mSelectedMasterSetPriceList, MasterSetPriceEntity masterSetPriceEntity) {
        String id = masterSetPriceEntity.getId();
        for (int i = 0; i < mSelectedMasterSetPriceList.size(); i++) {
            if (id.equals(mSelectedMasterSetPriceList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private String type;

    public void setType(String type) {
        this.type = type;
    }
}
