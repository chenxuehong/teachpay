package com.tencent.qcloud.tim.uikit.modules.message;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.huihe.base_lib.model.CourseMessageBean;
import com.huihe.base_lib.model.SchedulingMessageBean;
import com.huihe.base_lib.model.SchedulingTipMessage;
import com.huihe.base_lib.utils.JsonUtil;
import com.tencent.imsdk.v2.V2TIMCustomElem;
import com.tencent.imsdk.v2.V2TIMDownloadCallback;
import com.tencent.imsdk.v2.V2TIMElem;
import com.tencent.imsdk.v2.V2TIMFaceElem;
import com.tencent.imsdk.v2.V2TIMFileElem;
import com.tencent.imsdk.v2.V2TIMGroupChangeInfo;
import com.tencent.imsdk.v2.V2TIMGroupMemberChangeInfo;
import com.tencent.imsdk.v2.V2TIMGroupMemberInfo;
import com.tencent.imsdk.v2.V2TIMGroupTipsElem;
import com.tencent.imsdk.v2.V2TIMImageElem;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.imsdk.v2.V2TIMSoundElem;
import com.tencent.imsdk.v2.V2TIMTextElem;
import com.tencent.imsdk.v2.V2TIMVideoElem;
import com.tencent.liteav.model.CallModel;
import com.tencent.liteav.model.LiveModel;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.utils.DateTimeUtil;
import com.tencent.qcloud.tim.uikit.utils.FileUtil;
import com.tencent.qcloud.tim.uikit.utils.ImageUtil;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.qcloud.tim.uikit.utils.TUIKitLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MessageInfoUtil {
    private static final String TAG = MessageInfoUtil.class.getSimpleName();

    /**
     * 创建一条文本消息
     *
     * @param message 消息内容
     * @return
     */
    public static MessageInfo buildTextMessage(String message) {
        MessageInfo info = new MessageInfo();
        V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createTextMessage(message);

        info.setExtra(message);
        info.setMsgTime(System.currentTimeMillis() / 1000);
        info.setSelf(true);
        info.setTimMessage(v2TIMMessage);
        info.setFromUser(V2TIMManager.getInstance().getLoginUser());
        info.setMsgType(MessageInfo.MSG_TYPE_TEXT);
        return info;
    }

    public static MessageInfo buildTextAtMessage(List<String> atUserList, String message) {
        MessageInfo info = new MessageInfo();
        V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createTextAtMessage(message, atUserList);

        info.setExtra(message);
        info.setMsgTime(System.currentTimeMillis() / 1000);
        info.setSelf(true);
        info.setTimMessage(v2TIMMessage);
        info.setFromUser(V2TIMManager.getInstance().getLoginUser());
        info.setMsgType(MessageInfo.MSG_TYPE_TEXT);
        return info;
    }

    /**
     * 创建一条自定义表情的消息
     *
     * @param groupId  自定义表情所在的表情组id
     * @param faceName 表情的名称
     * @return
     */
    public static MessageInfo buildCustomFaceMessage(int groupId, String faceName) {
        MessageInfo info = new MessageInfo();
        V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createFaceMessage(groupId, faceName.getBytes());

        info.setExtra(TUIKit.getAppContext().getString(R.string.custom_emoji));
        info.setMsgTime(System.currentTimeMillis() / 1000);
        info.setSelf(true);
        info.setTimMessage(v2TIMMessage);
        info.setFromUser(V2TIMManager.getInstance().getLoginUser());
        info.setMsgType(MessageInfo.MSG_TYPE_CUSTOM_FACE);
        return info;
    }

    /**
     * 创建一条图片消息
     *
     * @param uri        图片URI
     * @param compressed 是否压缩
     * @return
     */
    public static MessageInfo buildImageMessage(final Uri uri, boolean compressed) {
        final MessageInfo info = new MessageInfo();
        String path = FileUtil.getPathFromUri(uri);
        V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createImageMessage(path);

        info.setDataUri(uri);
        int size[] = ImageUtil.getImageSize(uri);
        info.setDataPath(path);
        info.setImgWidth(size[0]);
        info.setImgHeight(size[1]);
        info.setSelf(true);
        info.setTimMessage(v2TIMMessage);
        info.setExtra(TUIKit.getAppContext().getString(R.string.picture_extra));
        info.setMsgTime(System.currentTimeMillis() / 1000);
        info.setFromUser(V2TIMManager.getInstance().getLoginUser());
        info.setMsgType(MessageInfo.MSG_TYPE_IMAGE);
        return info;
    }

    /**
     * 创建一条视频消息
     *
     * @param imgPath   视频缩略图路径
     * @param videoPath 视频路径
     * @param width     视频的宽
     * @param height    视频的高
     * @param duration  视频的时长
     * @return
     */
    public static MessageInfo buildVideoMessage(String imgPath, String videoPath, int width, int height, long duration) {
        MessageInfo info = new MessageInfo();
        V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createVideoMessage(videoPath, "mp4", (int) duration / 1000, imgPath);

        Uri videoUri = Uri.fromFile(new File(videoPath));
        info.setSelf(true);
        info.setImgWidth(width);
        info.setImgHeight(height);
        info.setDataPath(imgPath);
        info.setDataUri(videoUri);
        info.setTimMessage(v2TIMMessage);
        info.setExtra(TUIKit.getAppContext().getString(R.string.video_extra));
        info.setMsgTime(System.currentTimeMillis() / 1000);
        info.setFromUser(V2TIMManager.getInstance().getLoginUser());
        info.setMsgType(MessageInfo.MSG_TYPE_VIDEO);
        return info;
    }

    /**
     * 创建一条音频消息
     *
     * @param recordPath 音频路径
     * @param duration   音频的时长
     * @return
     */
    public static MessageInfo buildAudioMessage(String recordPath, int duration) {
        MessageInfo info = new MessageInfo();
        V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createSoundMessage(recordPath, duration / 1000);

        info.setDataPath(recordPath);
        info.setSelf(true);
        info.setTimMessage(v2TIMMessage);
        info.setExtra(TUIKit.getAppContext().getString(R.string.audio_extra));
        info.setMsgTime(System.currentTimeMillis() / 1000);
        info.setFromUser(V2TIMManager.getInstance().getLoginUser());
        info.setMsgType(MessageInfo.MSG_TYPE_AUDIO);
        return info;
    }

    /**
     * 创建一条文件消息
     *
     * @param fileUri 文件路径
     * @return
     */
    public static MessageInfo buildFileMessage(Uri fileUri) {
        String filePath = FileUtil.getPathFromUri(fileUri);
        File file = new File(filePath);
        if (file.exists()) {
            MessageInfo info = new MessageInfo();
            V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createFileMessage(filePath, file.getName());

            info.setDataPath(filePath);
            info.setSelf(true);
            info.setTimMessage(v2TIMMessage);
            info.setExtra(TUIKit.getAppContext().getString(R.string.file_extra));
            info.setMsgTime(System.currentTimeMillis() / 1000);
            info.setFromUser(V2TIMManager.getInstance().getLoginUser());
            info.setMsgType(MessageInfo.MSG_TYPE_FILE);
            return info;
        }
        return null;
    }

    /**
     * 创建一条自定义消息
     *
     * @param data 自定义消息内容，可以是任何内容
     * @return
     */
    public static MessageInfo buildCustomMessage(String data) {
        MessageInfo info = new MessageInfo();
        V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createCustomMessage(data.getBytes());

        info.setSelf(true);
        info.setTimMessage(v2TIMMessage);
        info.setMsgTime(System.currentTimeMillis() / 1000);
        info.setMsgType(MessageInfo.MSG_TYPE_CUSTOM);
        info.setFromUser(V2TIMManager.getInstance().getLoginUser());
        info.setExtra(TUIKit.getAppContext().getString(R.string.custom_msg));
        return info;
    }

    /**
     * 创建一条自定义消息
     *
     * @param data 自定义消息内容，可以是任何内容
     * @return
     */
    public static MessageInfo buildCourseCustomMessage(String data) {
        MessageInfo info = new MessageInfo();
        V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createCustomMessage(data.getBytes());
        info.setSelf(true);
        info.setTimMessage(v2TIMMessage);
        info.setMsgTime(System.currentTimeMillis() / 1000);
        info.setMsgType(MessageInfo.MSG_TYPE_CUSTOM);
        info.setFromUser(V2TIMManager.getInstance().getLoginUser());
        info.setExtra(TUIKit.getAppContext().getString(R.string.course_msg));
        return info;
    }

    /**
     * 创建一条群消息自定义内容
     *
     * @param customMessage 消息内容
     * @return
     */
    public static V2TIMMessage buildGroupCustomMessage(String customMessage) {
        V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createCustomMessage(customMessage.getBytes());
        return v2TIMMessage;
    }

    /**
     * 把SDK的消息bean列表转化为TUIKit的消息bean列表
     *
     * @param timMessages SDK的群消息bean列表
     * @param isGroup     是否是群消息
     * @return
     */
    public static List<MessageInfo> TIMMessages2MessageInfos(List<V2TIMMessage> timMessages, boolean isGroup) {
        if (timMessages == null) {
            return null;
        }
        List<MessageInfo> messageInfos = new ArrayList<>();
        for (int i = 0; i < timMessages.size(); i++) {
            V2TIMMessage timMessage = timMessages.get(i);
            List<MessageInfo> info = TIMMessage2MessageInfo(timMessage);
            if (info != null) {
                messageInfos.addAll(info);
            }
        }
        return messageInfos;
    }

    /**
     * 把SDK的消息bean转化为TUIKit的消息bean
     *
     * @param timMessage SDK的群消息bean
     * @return
     */
    public static List<MessageInfo> TIMMessage2MessageInfo(V2TIMMessage timMessage) {
        if (timMessage == null || timMessage.getStatus() == V2TIMMessage.V2TIM_MSG_STATUS_HAS_DELETED || timMessage.getElemType() == V2TIMMessage.V2TIM_ELEM_TYPE_NONE) {
            return null;
        }
        List<MessageInfo> list = new ArrayList<>();
        final MessageInfo msgInfo = createMessageInfo(timMessage);
        if (msgInfo != null) {
            list.add(msgInfo);
        }
        return list;
    }

    public static boolean isOnlineIgnoredDialing(byte[] data) {
        try {
            String str = new String(data, "UTF-8");
            MessageCustom custom = new Gson().fromJson(str, MessageCustom.class);
            if (custom != null
                    && TextUtils.equals(custom.businessID, MessageCustom.BUSINESS_ID_AV_CALL)
                    && custom.version <= TUIKitConstants.version) {
                return true;
            }
            return false;
        } catch (Exception e) {
            TUIKitLog.e(TAG, "parse json error");
        }
        return false;
    }

    public static boolean isTyping(byte[] data) {
        try {
            String str = new String(data, "UTF-8");
            MessageTyping typing = new Gson().fromJson(str, MessageTyping.class);
            if (typing != null
                    && typing.userAction == MessageTyping.TYPE_TYPING
                    && TextUtils.equals(typing.actionParam, MessageTyping.EDIT_START)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            TUIKitLog.e(TAG, "parse json error");
        }
        return false;
    }

    public static boolean isLive(MessageInfo messageInfo) {
        try {
            JSONObject jsonObject = new JSONObject(new String(messageInfo.getTimMessage().getCustomElem().getData()));
            return jsonObject.getString("roomType") != null;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isCommodityMessage(MessageInfo messageInfo) {
        try {
            JSONObject jsonObject = new JSONObject(new String(messageInfo.getTimMessage().getCustomElem().getData()));
            return jsonObject.getString("messageType").equals("commodityMessage");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isClassTipMessage(MessageInfo messageInfo) {
        try {
            JSONObject jsonObject = new JSONObject(new String(messageInfo.getTimMessage().getCustomElem().getData()));
            return jsonObject.getString("messageType").equals("classTipMessage");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isClassMessage(MessageInfo messageInfo) {
        try {
            JSONObject jsonObject = new JSONObject(new String(messageInfo.getTimMessage().getCustomElem().getData()));
            return jsonObject.getString("messageType").equals("classMessage");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static CourseMessageBean getCourseInfo(MessageInfo messageInfo) {
        CourseMessageBean courseMessageBean = null;
        try {
            String json = new String(messageInfo.getTimMessage().getCustomElem().getData());
            courseMessageBean = JsonUtil.fromJson(json, CourseMessageBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseMessageBean;
    }

    public static SchedulingMessageBean getSchedulingMessage(MessageInfo messageInfo) {
        SchedulingMessageBean courseMessageBean = null;
        try {
            String json = new String(messageInfo.getTimMessage().getCustomElem().getData());
            courseMessageBean = JsonUtil.fromJson(json, SchedulingMessageBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseMessageBean;
    }

    public static SchedulingTipMessage getSchedulingtipMessage(MessageInfo messageInfo) {
        SchedulingTipMessage courseMessageBean = null;
        try {
            String json = new String(messageInfo.getTimMessage().getCustomElem().getData());
            courseMessageBean = JsonUtil.fromJson(json, SchedulingMessageBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseMessageBean;
    }

    public static MessageInfo createMessageInfo(V2TIMMessage timMessage) {
        if (timMessage == null
                || timMessage.getStatus() == V2TIMMessage.V2TIM_MSG_STATUS_HAS_DELETED
                || timMessage.getElemType() == V2TIMMessage.V2TIM_ELEM_TYPE_NONE) {
            TUIKitLog.e(TAG, "ele2MessageInfo parameters error");
            return null;
        }
        Context context = TUIKit.getAppContext();
        if (context == null) {
            TUIKitLog.e(TAG, "context == null");
            return new MessageInfo();
        }
        final MessageInfo msgInfo = new MessageInfo();
        boolean isGroup = !TextUtils.isEmpty(timMessage.getGroupID());
        String sender = timMessage.getSender();
        msgInfo.setTimMessage(timMessage);
        msgInfo.setGroup(isGroup);
        msgInfo.setId(timMessage.getMsgID());
        msgInfo.setPeerRead(timMessage.isPeerRead());
        msgInfo.setFromUser(sender);
        if (isGroup) {
            if (!TextUtils.isEmpty(timMessage.getNameCard())) {
                msgInfo.setGroupNameCard(timMessage.getNameCard());
            }
        }
        msgInfo.setMsgTime(timMessage.getTimestamp());
        msgInfo.setSelf(sender.equals(V2TIMManager.getInstance().getLoginUser()));

        int type = timMessage.getElemType();
        if (type == V2TIMMessage.V2TIM_ELEM_TYPE_CUSTOM) {
            V2TIMCustomElem customElem = timMessage.getCustomElem();
            String data = new String(customElem.getData());
            if (data.equals(MessageCustom.BUSINESS_ID_GROUP_CREATE)) {
                // 兼容4.7版本以前的 tuikit
                msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_CREATE);
                String message = TUIKitConstants.covert2HTMLString(
                        TextUtils.isEmpty(msgInfo.getGroupNameCard())
                                ? msgInfo.getFromUser()
                                : msgInfo.getGroupNameCard()) + TUIKit.getAppContext().getString(R.string.create_group);
                ;
                msgInfo.setExtra(message);
            } else {
                if (isTyping(customElem.getData())) {
                    // 忽略正在输入，它不能作为真正的消息展示
                    return null;
                }
                TUIKitLog.i(TAG, "custom data:" + data);
                String content = TUIKit.getAppContext().getString(R.string.custom_msg);
                msgInfo.setMsgType(MessageInfo.MSG_TYPE_CUSTOM);
                msgInfo.setExtra(content);
                try {
                    JSONObject jsonObject = JsonUtil.string2JSONObject(data);
                    String messageType = jsonObject.getString("messageType");
                    if ("commodityMessage".equals(messageType)) {
                        CourseMessageBean courseMessageBean = JsonUtil.fromJson(data, CourseMessageBean.class);
                        content = "[课程:".concat(courseMessageBean.getTitle()).concat("]");
                    } else if ("classMessage".equals(messageType)) {
                        SchedulingMessageBean schedulingMessageBean = JsonUtil.fromJson(data, SchedulingMessageBean.class);
                        content = "[课程:".concat(schedulingMessageBean.title).concat("]");
                    } else if ("classTipMessage".equals(messageType)) {
                        SchedulingTipMessage schedulingTipMessage = JsonUtil.fromJson(data, SchedulingTipMessage.class);
                        if ("0".equals(schedulingTipMessage.result)) {
                            if (msgInfo.isSelf()) {
                                content = "[发送约课邀请]";
                            } else {
                                content = "[收到对方约课邀请]";
                            }
                        } else if ("1".equals(schedulingTipMessage.result)) {
                            if (msgInfo.isSelf()) {
                                content = "[同意对方约课邀请]";
                            } else {
                                content = "[对方同意约课邀请]";
                            }
                        } else {
                            if (msgInfo.isSelf()) {
                                content = "[拒绝对方约课邀请]";
                            } else {
                                content = "[对方拒绝约课邀请]";
                            }
                        }

                    }
                    msgInfo.setExtra(content);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                liveMessageInfo(timMessage, context, msgInfo, isGroup, data, gson);

            }
        } else if (type == V2TIMMessage.V2TIM_ELEM_TYPE_GROUP_TIPS) {
            V2TIMGroupTipsElem groupTipElem = timMessage.getGroupTipsElem();
            int tipsType = groupTipElem.getType();
            String user = "";
            if (groupTipElem.getMemberList().size() > 0) {
                List<V2TIMGroupMemberInfo> v2TIMGroupMemberInfoList = groupTipElem.getMemberList();
                for (int i = 0; i < v2TIMGroupMemberInfoList.size(); i++) {
                    V2TIMGroupMemberInfo v2TIMGroupMemberInfo = v2TIMGroupMemberInfoList.get(i);
                    if (i == 0) {
                        user = user + v2TIMGroupMemberInfo.getUserID();
                    } else {
                        if (i == 2 && v2TIMGroupMemberInfoList.size() > 3) {
                            user = user + context.getString(R.string.etc);
                            break;
                        } else {
                            user = user + "，" + v2TIMGroupMemberInfo.getUserID();
                        }
                    }
                }

            } else {
                user = groupTipElem.getOpMember().getUserID();
            }
            String message = TUIKitConstants.covert2HTMLString(user);
            if (tipsType == V2TIMGroupTipsElem.V2TIM_GROUP_TIPS_TYPE_JOIN) {
                msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_JOIN);
                message = message + context.getString(R.string.join_group);
            }
            if (tipsType == V2TIMGroupTipsElem.V2TIM_GROUP_TIPS_TYPE_INVITE) {
                msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_JOIN);
                message = message + context.getString(R.string.invite_joined_group);
            }
            if (tipsType == V2TIMGroupTipsElem.V2TIM_GROUP_TIPS_TYPE_QUIT) {
                msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_QUITE);
                message = message + context.getString(R.string.quit_group);
            }
            if (tipsType == V2TIMGroupTipsElem.V2TIM_GROUP_TIPS_TYPE_KICKED) {
                msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_KICK);
                message = message + context.getString(R.string.kick_group_tip);
            }
            if (tipsType == V2TIMGroupTipsElem.V2TIM_GROUP_TIPS_TYPE_SET_ADMIN) {
                msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_MODIFY_NOTICE);
                message = message + context.getString(R.string.be_group_manager);
            }
            if (tipsType == V2TIMGroupTipsElem.V2TIM_GROUP_TIPS_TYPE_CANCEL_ADMIN) {
                msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_MODIFY_NOTICE);
                message = message + context.getString(R.string.cancle_group_manager);
            }
            if (tipsType == V2TIMGroupTipsElem.V2TIM_GROUP_TIPS_TYPE_GROUP_INFO_CHANGE) {
                List<V2TIMGroupChangeInfo> modifyList = groupTipElem.getGroupChangeInfoList();
                for (int i = 0; i < modifyList.size(); i++) {
                    V2TIMGroupChangeInfo modifyInfo = modifyList.get(i);
                    int modifyType = modifyInfo.getType();
                    if (modifyType == V2TIMGroupChangeInfo.V2TIM_GROUP_INFO_CHANGE_TYPE_NAME) {
                        msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_MODIFY_NAME);
                        message = message + context.getString(R.string.modify_group_name_is) + "\"" + modifyInfo.getValue() + "\"";
                    } else if (modifyType == V2TIMGroupChangeInfo.V2TIM_GROUP_INFO_CHANGE_TYPE_NOTIFICATION) {
                        msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_MODIFY_NOTICE);
                        message = message + context.getString(R.string.modify_notice) + "\"" + modifyInfo.getValue() + "\"";
                    } else if (modifyType == V2TIMGroupChangeInfo.V2TIM_GROUP_INFO_CHANGE_TYPE_OWNER) {
                        msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_MODIFY_NOTICE);
                        message = message + context.getString(R.string.move_owner) + "\"" + modifyInfo.getValue() + "\"";
                    } else if (modifyType == V2TIMGroupChangeInfo.V2TIM_GROUP_INFO_CHANGE_TYPE_FACE_URL) {
                        msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_MODIFY_NOTICE);
                        message = message + context.getString(R.string.modify_group_avatar);
                    } else if (modifyType == V2TIMGroupChangeInfo.V2TIM_GROUP_INFO_CHANGE_TYPE_INTRODUCTION) {
                        msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_MODIFY_NOTICE);
                        message = message + context.getString(R.string.modify_notice) + "\"" + modifyInfo.getValue() + "\"";
                    }
                    if (i < modifyList.size() - 1) {
                        message = message + "、";
                    }
                }
            }
            if (tipsType == V2TIMGroupTipsElem.V2TIM_GROUP_TIPS_TYPE_MEMBER_INFO_CHANGE) {
                List<V2TIMGroupMemberChangeInfo> modifyList = groupTipElem.getMemberChangeInfoList();
                if (modifyList.size() > 0) {
                    long shutupTime = modifyList.get(0).getMuteTime();
                    if (shutupTime > 0) {
                        msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_MODIFY_NOTICE);
                        message = message + context.getString(R.string.banned) + "\"" + DateTimeUtil.formatSeconds(shutupTime) + "\"";
                    } else {
                        msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_MODIFY_NOTICE);
                        message = message + context.getString(R.string.cancle_banned);
                    }
                }
            }
            if (TextUtils.isEmpty(message)) {
                return null;
            }
            msgInfo.setExtra(message);
        } else {
            if (type == V2TIMMessage.V2TIM_ELEM_TYPE_TEXT) {
                V2TIMTextElem txtEle = timMessage.getTextElem();
                msgInfo.setExtra(txtEle.getText());
            } else if (type == V2TIMMessage.V2TIM_ELEM_TYPE_FACE) {
                V2TIMFaceElem faceElem = timMessage.getFaceElem();
                if (faceElem.getIndex() < 1 || faceElem.getData() == null) {
                    TUIKitLog.e("MessageInfoUtil", "faceElem data is null or index<1");
                    return null;
                }
                msgInfo.setExtra(context.getString(R.string.custom_emoji));


            } else if (type == V2TIMMessage.V2TIM_ELEM_TYPE_SOUND) {
                V2TIMSoundElem soundElemEle = timMessage.getSoundElem();
                if (msgInfo.isSelf()) {
                    msgInfo.setDataPath(soundElemEle.getPath());
                } else {
                    final String path = TUIKitConstants.RECORD_DOWNLOAD_DIR + soundElemEle.getUUID();
                    File file = new File(path);
                    if (!file.exists()) {
                        soundElemEle.downloadSound(path, new V2TIMDownloadCallback() {
                            @Override
                            public void onProgress(V2TIMElem.V2ProgressInfo progressInfo) {
                                long currentSize = progressInfo.getCurrentSize();
                                long totalSize = progressInfo.getTotalSize();
                                int progress = 0;
                                if (totalSize > 0) {
                                    progress = (int) (100 * currentSize / totalSize);
                                }
                                if (progress > 100) {
                                    progress = 100;
                                }
                                TUIKitLog.i("MessageInfoUtil getSoundToFile", "progress:" + progress);
                            }

                            @Override
                            public void onError(int code, String desc) {
                                TUIKitLog.e("MessageInfoUtil getSoundToFile", code + ":" + desc);
                            }

                            @Override
                            public void onSuccess() {
                                msgInfo.setDataPath(path);
                            }
                        });
                    } else {
                        msgInfo.setDataPath(path);
                    }
                }
                msgInfo.setExtra(context.getString(R.string.audio_extra));
            } else if (type == V2TIMMessage.V2TIM_ELEM_TYPE_IMAGE) {
                V2TIMImageElem imageEle = timMessage.getImageElem();
                String localPath = imageEle.getPath();
                if (msgInfo.isSelf() && !TextUtils.isEmpty(localPath)) {
                    msgInfo.setDataPath(localPath);
                    int size[] = ImageUtil.getImageSize(localPath);
                    msgInfo.setImgWidth(size[0]);
                    msgInfo.setImgHeight(size[1]);
                }
                //本地路径为空，可能为更换手机或者是接收的消息
                else {
                    List<V2TIMImageElem.V2TIMImage> imgs = imageEle.getImageList();
                    for (int i = 0; i < imgs.size(); i++) {
                        V2TIMImageElem.V2TIMImage img = imgs.get(i);
                        if (img.getType() == V2TIMImageElem.V2TIM_IMAGE_TYPE_THUMB) {
                            final String path = TUIKitConstants.IMAGE_DOWNLOAD_DIR + img.getUUID();
                            msgInfo.setImgWidth(img.getWidth());
                            msgInfo.setImgHeight(img.getHeight());
                            File file = new File(path);
                            if (file.exists()) {
                                msgInfo.setDataPath(path);
                            }
                        }
                    }
                }
                msgInfo.setExtra(context.getString(R.string.picture_extra));
            } else if (type == V2TIMMessage.V2TIM_ELEM_TYPE_VIDEO) {
                V2TIMVideoElem videoEle = timMessage.getVideoElem();
                if (msgInfo.isSelf() && !TextUtils.isEmpty(videoEle.getSnapshotPath())) {
                    int size[] = ImageUtil.getImageSize(videoEle.getSnapshotPath());
                    msgInfo.setImgWidth(size[0]);
                    msgInfo.setImgHeight(size[1]);
                    msgInfo.setDataPath(videoEle.getSnapshotPath());
                    msgInfo.setDataUri(FileUtil.getUriFromPath(videoEle.getVideoPath()));
                } else {
                    final String videoPath = TUIKitConstants.VIDEO_DOWNLOAD_DIR + videoEle.getVideoUUID();
                    Uri uri = Uri.parse(videoPath);
                    msgInfo.setDataUri(uri);
                    msgInfo.setImgWidth((int) videoEle.getSnapshotWidth());
                    msgInfo.setImgHeight((int) videoEle.getSnapshotHeight());
                    final String snapPath = TUIKitConstants.IMAGE_DOWNLOAD_DIR + videoEle.getSnapshotUUID();
                    //判断快照是否存在,不存在自动下载
                    if (new File(snapPath).exists()) {
                        msgInfo.setDataPath(snapPath);
                    }
                }

                msgInfo.setExtra(context.getString(R.string.video_extra));
            } else if (type == V2TIMMessage.V2TIM_ELEM_TYPE_FILE) {
                V2TIMFileElem fileElem = timMessage.getFileElem();
                String filename = fileElem.getUUID();
                if (TextUtils.isEmpty(filename)) {
                    filename = System.currentTimeMillis() + fileElem.getFileName();
                }
                final String path = TUIKitConstants.FILE_DOWNLOAD_DIR + filename;
                File file = new File(path);
                if (file.exists()) {
                    if (msgInfo.isSelf()) {
                        msgInfo.setStatus(MessageInfo.MSG_STATUS_SEND_SUCCESS);
                    } else {
                        msgInfo.setStatus(MessageInfo.MSG_STATUS_DOWNLOADED);
                    }
                    msgInfo.setDataPath(path);
                } else {
                    if (msgInfo.isSelf()) {
                        if (TextUtils.isEmpty(fileElem.getPath())) {
                            msgInfo.setStatus(MessageInfo.MSG_STATUS_UN_DOWNLOAD);
                            msgInfo.setDataPath(path);
                        } else {
                            file = new File(fileElem.getPath());
                            if (file.exists()) {
                                msgInfo.setStatus(MessageInfo.MSG_STATUS_SEND_SUCCESS);
                                msgInfo.setDataPath(fileElem.getPath());
                            } else {
                                msgInfo.setStatus(MessageInfo.MSG_STATUS_UN_DOWNLOAD);
                                msgInfo.setDataPath(path);
                            }
                        }
                    } else {
                        msgInfo.setStatus(MessageInfo.MSG_STATUS_UN_DOWNLOAD);
                        msgInfo.setDataPath(path);
                    }
                }
                msgInfo.setExtra(context.getString(R.string.file_extra));
            }
            msgInfo.setMsgType(TIMElemType2MessageInfoType(type));
        }

        if (timMessage.getStatus() == V2TIMMessage.V2TIM_MSG_STATUS_LOCAL_REVOKED) {
            msgInfo.setStatus(MessageInfo.MSG_STATUS_REVOKE);
            msgInfo.setMsgType(MessageInfo.MSG_STATUS_REVOKE);
            if (msgInfo.isSelf()) {
                msgInfo.setExtra(context.getString(R.string.revoke_tips_you));
            } else if (msgInfo.isGroup()) {
                String message = TUIKitConstants.covert2HTMLString(msgInfo.getFromUser());
                msgInfo.setExtra(message + context.getString(R.string.revoke_tips));
            } else {
                msgInfo.setExtra(context.getString(R.string.revoke_tips_other));
            }
        } else {
            if (msgInfo.isSelf()) {
                if (timMessage.getStatus() == V2TIMMessage.V2TIM_MSG_STATUS_SEND_FAIL) {
                    msgInfo.setStatus(MessageInfo.MSG_STATUS_SEND_FAIL);
                } else if (timMessage.getStatus() == V2TIMMessage.V2TIM_MSG_STATUS_SEND_SUCC) {
                    msgInfo.setStatus(MessageInfo.MSG_STATUS_SEND_SUCCESS);
                } else if (timMessage.getStatus() == V2TIMMessage.V2TIM_MSG_STATUS_SENDING) {
                    msgInfo.setStatus(MessageInfo.MSG_STATUS_SENDING);
                }
            }
        }
        return msgInfo;
    }

    private static void liveMessageInfo(V2TIMMessage timMessage, Context context, MessageInfo msgInfo, boolean isGroup, String data, Gson gson) {
        String content;
        MessageCustom messageCustom = null;
        try {
            messageCustom = gson.fromJson(data, MessageCustom.class);
            if (!TextUtils.isEmpty(messageCustom.businessID) && messageCustom.businessID.equals(MessageCustom.BUSINESS_ID_GROUP_CREATE)) {
                msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_CREATE);
                String message = TUIKitConstants.covert2HTMLString(messageCustom.opUser) + messageCustom.content;
                msgInfo.setExtra(message);
            } else if (!TextUtils.isEmpty(messageCustom.businessID) && messageCustom.businessID.equals(MessageCustom.BUSINESS_ID_LIVE_GROUP)) {
                Gson liveGson = new Gson();
                LiveMessageInfo liveMessageInfo = liveGson.fromJson(data, LiveMessageInfo.class);
                String anchorName;
                if (!TextUtils.isEmpty(liveMessageInfo.anchorName)) {
                    anchorName = liveMessageInfo.anchorName;
                } else {
                    anchorName = liveMessageInfo.roomName;
                }
                content = anchorName + TUIKit.getAppContext().getString(R.string.live_room);
                msgInfo.setExtra(content);
            } else if (LiveModel.isLiveRoomSignal(messageCustom.data)) {
                LiveModel liveModel = LiveModel.convert2LiveData(timMessage);
                content = liveModel.message;
                msgInfo.setMsgType(MessageInfo.MSG_TYPE_TEXT);
                msgInfo.setExtra(content);
            } else {
                CallModel callModel = CallModel.convert2VideoCallData(timMessage);
                if (callModel != null) {
                    String senderShowName = timMessage.getSender();
                    if (!TextUtils.isEmpty(timMessage.getNameCard())) {
                        senderShowName = timMessage.getNameCard();
                    } else if (!TextUtils.isEmpty(timMessage.getFriendRemark())) {
                        senderShowName = timMessage.getFriendRemark();
                    } else if (!TextUtils.isEmpty(timMessage.getNickName())) {
                        senderShowName = timMessage.getNickName();
                    }
                    switch (callModel.action) {
                        case CallModel.VIDEO_CALL_ACTION_DIALING:
                            content = isGroup ? ("\"" + senderShowName + "\"" + context.getString(R.string.start_group_call)) : (context.getString(R.string.start_call));
                            break;
                        case CallModel.VIDEO_CALL_ACTION_SPONSOR_CANCEL:
                            content = isGroup ? context.getString(R.string.cancle_group_call) : context.getString(R.string.cancle_call);
                            break;
                        case CallModel.VIDEO_CALL_ACTION_LINE_BUSY:
                            content = isGroup ? ("\"" + senderShowName + "\"" + context.getString(R.string.line_busy)) : context.getString(R.string.other_line_busy);
                            break;
                        case CallModel.VIDEO_CALL_ACTION_REJECT:
                            content = isGroup ? ("\"" + senderShowName + "\"" + context.getString(R.string.reject_group_calls)) : context.getString(R.string.reject_calls);
                            break;
                        case CallModel.VIDEO_CALL_ACTION_SPONSOR_TIMEOUT:
                            if (isGroup && callModel.invitedList != null && callModel.invitedList.size() == 1
                                    && callModel.invitedList.get(0).equals(timMessage.getSender())) {
                                content = "\"" + senderShowName + "\"" + context.getString(R.string.no_response_call);
                            } else {
                                StringBuilder inviteeShowStringBuilder = new StringBuilder();
                                if (callModel.invitedList != null && callModel.invitedList.size() > 0) {
                                    for (String invitee : callModel.invitedList) {
                                        inviteeShowStringBuilder.append(invitee).append("、");
                                    }
                                    if (inviteeShowStringBuilder.length() > 0) {
                                        inviteeShowStringBuilder.delete(inviteeShowStringBuilder.length() - 1, inviteeShowStringBuilder.length());
                                    }
                                }
                                content = isGroup ? ("\"" + inviteeShowStringBuilder.toString() + "\"" + context.getString(R.string.no_response_call)) : context.getString(R.string.no_response_call);
                            }
                            break;
                        case CallModel.VIDEO_CALL_ACTION_ACCEPT:
                            content = isGroup ? ("\"" + senderShowName + "\"" + context.getString(R.string.accept_call)) : context.getString(R.string.accept_call);
                            break;
                        case CallModel.VIDEO_CALL_ACTION_HANGUP:
                            content = isGroup ? context.getString(R.string.stop_group_call) : context.getString(R.string.stop_call_tip) + DateTimeUtil.formatSecondsTo00(callModel.duration);
                            break;
                        default:
                            content = context.getString(R.string.invalid_command);
                            break;
                    }
                    if (isGroup) {
                        msgInfo.setMsgType(MessageInfo.MSG_TYPE_GROUP_AV_CALL_NOTICE);
                    } else {
                        msgInfo.setMsgType(MessageInfo.MSG_TYPE_TEXT);
                    }
                    msgInfo.setExtra(content);
                }
            }
        } catch (Exception e) {
            TUIKitLog.e(TAG, "invalid json: " + data + ", exception:" + e);
        }
    }

    private static int TIMElemType2MessageInfoType(int type) {
        switch (type) {
            case V2TIMMessage.V2TIM_ELEM_TYPE_TEXT:
                return MessageInfo.MSG_TYPE_TEXT;
            case V2TIMMessage.V2TIM_ELEM_TYPE_IMAGE:
                return MessageInfo.MSG_TYPE_IMAGE;
            case V2TIMMessage.V2TIM_ELEM_TYPE_SOUND:
                return MessageInfo.MSG_TYPE_AUDIO;
            case V2TIMMessage.V2TIM_ELEM_TYPE_VIDEO:
                return MessageInfo.MSG_TYPE_VIDEO;
            case V2TIMMessage.V2TIM_ELEM_TYPE_FILE:
                return MessageInfo.MSG_TYPE_FILE;
            case V2TIMMessage.V2TIM_ELEM_TYPE_LOCATION:
                return MessageInfo.MSG_TYPE_LOCATION;
            case V2TIMMessage.V2TIM_ELEM_TYPE_FACE:
                return MessageInfo.MSG_TYPE_CUSTOM_FACE;
            case V2TIMMessage.V2TIM_ELEM_TYPE_GROUP_TIPS:
                return MessageInfo.MSG_TYPE_TIPS;
        }

        return -1;
    }
}
