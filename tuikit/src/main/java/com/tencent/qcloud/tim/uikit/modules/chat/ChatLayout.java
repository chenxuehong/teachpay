package com.tencent.qcloud.tim.uikit.modules.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.CourseMessageBean;
import com.huihe.base_lib.model.SchedulingMessageBean;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.widget.AutoFitImageView;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.AbsChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatManagerKit;
import com.tencent.qcloud.tim.uikit.modules.group.apply.GroupApplyInfo;
import com.tencent.qcloud.tim.uikit.modules.group.apply.GroupApplyManagerActivity;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfo;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfoActivity;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatLayout extends AbsChatLayout implements GroupChatManagerKit.GroupNotifyHandler {

    private GroupInfo mGroupInfo;
    private GroupChatManagerKit mGroupChatManager;
    private C2CChatManagerKit mC2CChatManager;
    private boolean isGroup = false;
    private RoundedImageView ivCourseCover;
    private AutoFitImageView ivAllDiscount;
    private TextView tvCourseTitle;
    private TextView tvDiscountAmount;
    private TextView tvOriginalPrice;
    private View bottomView;
    String studycard_id;
    private String messageContentJson;

    public ChatLayout(Context context) {
        super(context);
    }

    public ChatLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChatLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getBottomLayout() {
        return R.layout.chat_bottom;
    }

    @Override
    protected void initBottomView(View bottomView) {
        this.bottomView = bottomView;
    }

    public void setMessageContent(String messageContentJson) {
        this.messageContentJson = messageContentJson;
        showBottomView(true);
        try {
            JSONObject jsonObject = JsonUtil.string2JSONObject(messageContentJson);
            String messageType = jsonObject.getString("messageType");
            if ("commodityMessage".equals(messageType)) {
                bindCommondityMessage();
            } else if ("classMessage".equals(messageType)) {
                bindClassMessage();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void bindCommondityMessage() {

        FrameLayout flChatBottom = bottomView.findViewById(R.id.chat_bottom_fl_container);
        View view = LayoutInflater.from(bottomView.getContext()).inflate(R.layout.chat_bottom_course_info, null);
        flChatBottom.addView(view);

        ivCourseCover = view.findViewById(R.id.chat_bottom_course_info_iv_head);
        ivAllDiscount = view.findViewById(R.id.chat_bottom_course_info_iv_all_discount);
        tvCourseTitle = view.findViewById(R.id.chat_bottom_course_info_tv_title);
        tvDiscountAmount = view.findViewById(R.id.chat_bottom_course_info_tv_discount_amount);
        tvOriginalPrice = view.findViewById(R.id.chat_bottom_course_info_tv_original_price);
        final CourseMessageBean courseMessageBean = JsonUtil.fromJson(messageContentJson, CourseMessageBean.class);
        view.findViewById(R.id.chat_bottom_course_info_iv_send).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageInfo messageInfo = MessageInfoUtil.buildCourseCustomMessage(JsonUtil.toJson(courseMessageBean));
                sendMessage(messageInfo, false);
                showBottomView(false);
            }
        });
        view.findViewById(R.id.chat_bottom_course_info_fl_close).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showBottomView(false);
                    }
                }
        );
        String course_num = courseMessageBean.getCourse_num();
        String title = courseMessageBean.getTitle();
        String face_url = courseMessageBean.getFace_url();
        GlideTools.loadImage(getContext(), face_url, ivCourseCover);
        tvCourseTitle.setText(
                "【"
                        .concat(course_num)
                        .concat("节】")
                        .concat(title));
        String discount_amount = courseMessageBean.getDiscount_amount();
        String original_price = courseMessageBean.getOriginal_price();
        Boolean is_attend_activities = courseMessageBean.getIs_attend_activities();
        if (is_attend_activities != null && is_attend_activities) {
            tvDiscountAmount.setText(NumberUtils.transMoney(discount_amount));
            tvOriginalPrice.setText(NumberUtils.transMoney(original_price).concat("/节"));
            tvOriginalPrice.setVisibility(View.VISIBLE);
            tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
            ivAllDiscount.setVisibility(View.VISIBLE);
        } else {
            tvDiscountAmount.setText(NumberUtils.transMoney(original_price));
            tvOriginalPrice.setVisibility(View.GONE);
            ivAllDiscount.setVisibility(View.GONE);
        }
        bottomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBusUtils.sendEvent(new Event(EventAction.IM_MECHANISM_COURSE_ENTER, courseMessageBean.getId()));
            }
        });
    }

    public void setStudycard_id(String studycard_id) {
        this.studycard_id = studycard_id;
    }

    private void bindClassMessage() {
        SchedulingMessageBean schedulingMessageBean = JsonUtil.fromJson(messageContentJson, SchedulingMessageBean.class);
        FrameLayout flChatBottom = bottomView.findViewById(R.id.chat_bottom_fl_container);
        View view = LayoutInflater.from(bottomView.getContext()).inflate(R.layout.chat_bottom_class_info, null);
        view.findViewById(R.id.chat_bottom_class_info_iv_send).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, String> params = new HashMap<>();
                        params.put(ArgumentsConfig.KEY_STUDYCARD_ID,studycard_id);
                        params.put(ArgumentsConfig.KEY_ID,schedulingMessageBean.id);
                        ActivityToActivity.toActivity(ARouterConfig.SCHEDULE_ARRANGESCHEDULINGMECHANISMCOURSEACTIVITY,params);
                    }
                }
        );
        flChatBottom.addView(view);
    }

    public void setChatInfo(ChatInfo chatInfo) {
        super.setChatInfo(chatInfo);
        if (chatInfo == null) {
            return;
        }

        if (chatInfo.getType() == V2TIMConversation.V2TIM_C2C) {
            isGroup = false;
        } else {
            isGroup = true;
        }

        if (isGroup) {
            mGroupChatManager = GroupChatManagerKit.getInstance();
            mGroupChatManager.setGroupHandler(this);
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.setId(chatInfo.getId());
            groupInfo.setChatName(chatInfo.getChatName());
            mGroupChatManager.setCurrentChatInfo(groupInfo);
            mGroupInfo = groupInfo;
            loadChatMessages(null);
            loadApplyList();
            getTitleBar().getRightIcon().setVisibility(View.GONE);
            getTitleBar().getRightIcon().setImageResource(R.drawable.chat_group);
            if (getTitleBar().getRightIcon().getVisibility() == View.VISIBLE) {
                getTitleBar().setOnRightClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mGroupInfo != null) {
                            Intent intent = new Intent(getContext(), GroupInfoActivity.class);
                            intent.putExtra(TUIKitConstants.Group.GROUP_ID, mGroupInfo.getId());
                            getContext().startActivity(intent);
                        } else {
                            ToastUtil.toastLongMessage(TUIKit.getAppContext().getString(R.string.wait_tip));
                        }
                    }
                });
            }
            mGroupApplyLayout.setOnNoticeClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), GroupApplyManagerActivity.class);
                    intent.putExtra(TUIKitConstants.Group.GROUP_INFO, mGroupInfo);
                    getContext().startActivity(intent);
                }
            });
        } else {
            getTitleBar().getRightIcon().setVisibility(View.GONE);
            getTitleBar().getRightIcon().setImageResource(R.drawable.chat_c2c);
            mC2CChatManager = C2CChatManagerKit.getInstance();
            mC2CChatManager.setCurrentChatInfo(chatInfo);
            loadChatMessages(null);
        }
    }

    @Override
    public ChatManagerKit getChatManager() {
        if (isGroup) {
            return mGroupChatManager;
        } else {
            return mC2CChatManager;
        }
    }

    private void loadApplyList() {
        mGroupChatManager.getProvider().loadGroupApplies(new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                List<GroupApplyInfo> applies = (List<GroupApplyInfo>) data;
                if (applies != null && applies.size() > 0) {
                    mGroupApplyLayout.getContent().setText(getContext().getString(R.string.group_apply_tips, applies.size()));
                    mGroupApplyLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtil.toastLongMessage("loadApplyList onError: " + errMsg);
            }
        });
    }

    @Override
    public void onGroupForceExit() {
        if (getContext() instanceof Activity) {
            ((Activity) getContext()).finish();
        }
    }

    @Override
    public void onGroupNameChanged(String newName) {
        getTitleBar().setTitle(newName, TitleBarLayout.POSITION.MIDDLE);
    }

    @Override
    public void onApplied(int size) {
        if (size == 0) {
            mGroupApplyLayout.setVisibility(View.GONE);
        } else {
            mGroupApplyLayout.getContent().setText(getContext().getString(R.string.group_apply_tips, size));
            mGroupApplyLayout.setVisibility(View.VISIBLE);
        }
    }

}
