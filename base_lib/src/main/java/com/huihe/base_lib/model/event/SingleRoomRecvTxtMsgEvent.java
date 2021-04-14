package com.huihe.base_lib.model.event;

public class SingleRoomRecvTxtMsgEvent {
    public String userName;
    public String content;

    public SingleRoomRecvTxtMsgEvent(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }
}
