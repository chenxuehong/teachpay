package com.huihe.base_lib.model.event;

public class SingleRoomRecvGiftMsgEvent {
    public String userName;
    public String userId;
    public String gitName;
    public String gitCount;

    public SingleRoomRecvGiftMsgEvent(String userName, String userId, String gitName, String gitCount) {
        this.userName = userName;
        this.userId = userId;
        this.gitName = gitName;
        this.gitCount = gitCount;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getGitName() {
        return gitName;
    }

    public String getGitCount() {
        return gitCount;
    }
}
