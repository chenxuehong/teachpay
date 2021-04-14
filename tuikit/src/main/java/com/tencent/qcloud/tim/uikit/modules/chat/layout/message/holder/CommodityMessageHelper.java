package com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.CourseMessageBean;
import com.huihe.base_lib.model.SchedulingTipMessage;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tencent.imsdk.v2.V2TIMCustomElem;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;

public class CommodityMessageHelper implements IOnCustomMessageDrawListener {

    public CommodityMessageHelper() {

    }

    @Override
    public void onDraw(ICustomMessageViewGroup parent, final MessageInfo messageInfo) {
        // 获取到自定义消息的json数据
        if (messageInfo.getTimMessage().getElemType() != V2TIMMessage.V2TIM_ELEM_TYPE_CUSTOM) {
            return;
        }
        V2TIMCustomElem elem = messageInfo.getTimMessage().getCustomElem();
         CourseMessageBean courseInfo = new Gson().fromJson(new String(elem.getData()), CourseMessageBean.class);
        // 把自定义消息view添加到TUIKit内部的父容器里
        View courseInfoView = LayoutInflater.from(TUIKit.getAppContext()).inflate(R.layout.chat_item_course_info, null, false);
        parent.addMessageContentView(courseInfoView);
        RoundedImageView ivFaceUrl = courseInfoView.findViewById(R.id.chat_item_course_info_iv_head);
        ImageView ivAllDiscount = courseInfoView.findViewById(R.id.chat_item_course_info_iv_all_discount);
        TextView tvTitle = courseInfoView.findViewById(R.id.chat_item_course_info_tv_title);
        TextView tvDiscountAmount = courseInfoView.findViewById(R.id.chat_item_course_info_tv_discount_amount);
        TextView tvOriginalPrice = courseInfoView.findViewById(R.id.chat_item_course_info_tv_original_price);
        GlideTools.loadImage(courseInfoView.getContext(), courseInfo.getFace_url(), ivFaceUrl);
        String course_num = courseInfo.getCourse_num();
        String title = courseInfo.getTitle();
        String discount_amount = courseInfo.getDiscount_amount();
        String original_price = courseInfo.getOriginal_price();
        title = TextUtils.isEmpty(title) ? "" : title;
        course_num = TextUtils.isEmpty(course_num) ? "0" : course_num;
        tvTitle.setText("【".concat(course_num).concat("节】").concat(title));
        Boolean is_attend_activities = courseInfo.getIs_attend_activities();
        if (is_attend_activities != null && is_attend_activities) {
            tvDiscountAmount.setText(NumberUtils.transMoney(discount_amount).concat("/节"));
            tvOriginalPrice.setText(NumberUtils.transMoney(original_price));
            tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
            ivAllDiscount.setVisibility(View.VISIBLE);
            tvOriginalPrice.setVisibility(View.VISIBLE);
        } else {
            tvDiscountAmount.setText(NumberUtils.transMoney(original_price));
            ivAllDiscount.setVisibility(View.GONE);
            tvOriginalPrice.setVisibility(View.GONE);
        }
        courseInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBusUtils.sendEvent(new Event(EventAction.IM_MECHANISM_COURSE_ENTER, courseInfo.getId()));
            }
        });
    }
}
