package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.me.activity.UpdateTeachPayMechanismCourseActivity;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class TeachPayCourseOnSaleRvAdapter extends EmptyRVAdapter<MasterSetPriceEntity> {

    private boolean is_select;

    public TeachPayCourseOnSaleRvAdapter(int layoutId, Context context, boolean is_select) {
        super(layoutId, context);
        this.is_select = is_select;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterSetPriceEntity exclusiveCoursesEntity, int position) {
        String type = exclusiveCoursesEntity.getType();
        MasterSetPriceEntity.Map map = exclusiveCoursesEntity.getMap();
        viewHolder.setVisible(R.id.item_classical_course_tv_Sold_out, false);
        if (map != null) {

            if ("mechanism_offline".equals(type)) {
                MasterMechanismModel.MasterMechanismEntity masterMechanismEntity = map.getMasterMechanismEntity();
                if (masterMechanismEntity != null) {
                    String id = masterMechanismEntity.getId();
                    if (String.valueOf(LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id()).equals(id)) {
                        viewHolder.setVisible(R.id.item_classical_course_tv_Sold_out, true);
                    }
                }
            } else {
                UserInfoEntity masterinfo = map.getMasterinfo();
                if (masterinfo != null) {
                    String user_id = masterinfo.getUser_id();
                    if (LoginHelper.isMySelf(user_id)) {
                        viewHolder.setVisible(R.id.item_classical_course_tv_Sold_out, true);
                    }
                }
            }
        }
        String face_url = exclusiveCoursesEntity.getFace_url();
        String title = exclusiveCoursesEntity.getTitle();
        String service_type = exclusiveCoursesEntity.getService_type();
        String pay_num = exclusiveCoursesEntity.getPay_num();
        String course_num = exclusiveCoursesEntity.getCourse_num();
        RoundedImageView ivCover = viewHolder.getView(R.id.item_classical_course_iv_cover);
        GlideTools.loadImage(context, face_url, ivCover);
        viewHolder.setVisible(R.id.item_classical_course_iv_play, false);
        viewHolder.setVisible(R.id.item_classical_course_tv_type, false);
        String label = exclusiveCoursesEntity.getLabel();
        String categories = exclusiveCoursesEntity.getCategories();
        if (TextUtils.isEmpty(categories)) {
            viewHolder.setText(R.id.item_classical_course_tv_type, label);
        } else {
            viewHolder.setText(R.id.item_classical_course_tv_type,
                    TextUtils.isEmpty(label) ? categories : categories.concat("/").concat(label));
        }

        viewHolder.setText(R.id.item_classical_course_tv_title,
                "【".concat(course_num).concat("节】").concat(title));
        viewHolder.setText(R.id.item_classical_course_tv_discount_price, NumberUtils.transMoney("0.0").concat("节"));

        // 单价
        String original_price = exclusiveCoursesEntity.getOriginal_price();
        original_price = String.valueOf(NumberUtils.tranToTwoDecimal(original_price));
        // 原价
        String amout = exclusiveCoursesEntity.getAmout();
        // 折扣价
        String discount_amout = exclusiveCoursesEntity.getDiscount_amout();
        discount_amout = String.valueOf(NumberUtils.tranToTwoDecimal(discount_amout));
        // 默认价格
        String default_discount_price = exclusiveCoursesEntity.getDefault_discount_price();
        if (container(exclusiveCoursesEntity, "single_payment")) {
            // 参与单价课活动
            // 显示单价课价格 original_price
            viewHolder.setText(R.id.item_classical_course_tv_discount_price,
                    NumberUtils.transMoney(original_price).concat("/节"));
            // 显示默认价格 default_discount_price
            viewHolder.setText(R.id.item_classical_course_tv_title,
                    "【".concat(course_num)
                            .concat("节 ")
                            .concat(NumberUtils.transMoney(default_discount_price))
                            .concat("】")
                            .concat(title));
            viewHolder.setVisible(R.id.item_classical_course_tv_original_price,
                   false);
        }else {
            // 不参加单价课价格
            // 显示默认折扣价格和原价
            viewHolder.setText(R.id.item_classical_course_tv_discount_price,
                    NumberUtils.transMoney(default_discount_price));
            viewHolder.setStrikeThrough(R.id.item_classical_course_tv_original_price,
                    NumberUtils.transMoney(amout));
            viewHolder.setVisible(R.id.item_classical_course_tv_original_price,
                    true);
        }

        viewHolder.setText(R.id.item_classical_course_tv_purchase_num,
                String.format(context.getResources().getString(R.string.purchased_num), pay_num));
        if (exclusiveCoursesEntity.getStatus() == 1) {
            viewHolder.setText(R.id.item_classical_course_tv_Sold_out, context.getResources().getString(R.string.Put_on_sale));
        } else {
            viewHolder.setText(R.id.item_classical_course_tv_Sold_out, context.getResources().getString(R.string.Sold_out));
        }

        viewHolder.setVisible(R.id.item_classical_course_tv_edit, !is_select);
        if (!is_select) {
            int status = exclusiveCoursesEntity.getStatus();
            if (status == 3) {
                viewHolder.setVisible(R.id.item_classical_course_tv_Sold_out, false);
            } else {
                viewHolder.setVisible(R.id.item_classical_course_tv_Sold_out, true);
            }
        } else {
            viewHolder.setVisible(R.id.item_classical_course_tv_Sold_out, false);
        }

        viewHolder.setVisible(R.id.item_classical_course_iv_grouping_back, false);
        viewHolder.setVisible(R.id.item_classical_course_iv_single_pay, false);
        viewHolder.setVisible(R.id.item_classical_course_iv_all_discount, false);
        if (container(exclusiveCoursesEntity, "experience_specials")) {
            // 春节活动
            viewHolder.setVisible(R.id.item_classical_course_iv_all_discount, true);
        }

        if (container(exclusiveCoursesEntity, "single_payment")) {
            // 单价支付
            viewHolder.setVisible(R.id.item_classical_course_iv_single_pay, true);
        }

        if (container(exclusiveCoursesEntity, "grouping")) {
            // 拼团返现
            viewHolder.setVisible(R.id.item_classical_course_iv_all_discount, true);
        }
        viewHolder.setOnClickListener(R.id.item_classical_course_tv_Sold_out,
                new OnDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {
                        soldOut(exclusiveCoursesEntity.getId(), exclusiveCoursesEntity.getStatus() == 1 ? 2 : 1);
                    }
                });
        viewHolder.setOnClickListener(R.id.item_classical_course_tv_edit,
                new OnDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {
                        // 修改课程数据
                        Intent intent = new Intent(context, UpdateTeachPayMechanismCourseActivity.class);
                        intent.putExtra(ArgumentsConfig.KEY_MASTERSETPRICEENTITY, JsonUtil.toJson(exclusiveCoursesEntity));
                        context.startActivity(intent);
                    }
                });
    }

    private boolean container(MasterSetPriceEntity masterSetPriceEntity, String type) {
        MasterSetPriceEntity.Map map = masterSetPriceEntity.getMap();
        if (map != null) {
            List<BusinessActivityTypeEntity> activityEntityList = map.getActivityEntityList();
            if (activityEntityList != null) {
                for (int i = 0; i < activityEntityList.size(); i++) {
                    BusinessActivityTypeEntity businessActivityTypeEntity = activityEntityList.get(i);
                    if (type.equals(businessActivityTypeEntity.getType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * @param id
     * @param status
     * @desc 上架和下架商品
     */
    private void soldOut(String id, final int status) {
        UserServiceImpl.sold0utExclusiveCourse(id, String.valueOf(status), new NetworkSubscriber<InsertInfoResultModel>(null) {
            @Override
            protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                if (status == 1) {
                    ToastUtils.showShortToast(context, context.getResources().getString(R.string.Sold_out_successful));
                } else {
                    ToastUtils.showShortToast(context, context.getString(R.string.Put_on_sale_successful));
                }
                EventBusUtils.sendEvent(new Event(EventAction.INSERT_MECHANISM_COURSE));
            }
        });
    }

}
