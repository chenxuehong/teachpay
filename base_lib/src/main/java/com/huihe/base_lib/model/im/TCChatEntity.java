package com.huihe.base_lib.model.im;


import java.util.Objects;

/**
 * Module:   TCChatEntity
 * <p>
 * Function: 消息载体类。
 */
public class TCChatEntity {
    private String grpSendName;    // 发送者的名字
    private String id;    // 发送者的名字
    private String content;        // 消息内容
    private int type;            // 消息类型
    private String userAvatar;

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public String getSenderName() {
        return grpSendName != null ? grpSendName : "";
    }

    public void setSenderName(String grpSendName) {
        this.grpSendName = grpSendName;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String context) {
        this.content = context;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TCChatEntity)) return false;

        TCChatEntity that = (TCChatEntity) o;

        if (getType() != that.getType()) return false;
        if (!Objects.equals(grpSendName, that.grpSendName))
            return false;
        return getContent() != null ? getContent().equals(that.getContent()) : that.getContent() == null;

    }

    @Override
    public int hashCode() {
        int result = grpSendName != null ? grpSendName.hashCode() : 0;
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + getType();
        return result;
    }
}
