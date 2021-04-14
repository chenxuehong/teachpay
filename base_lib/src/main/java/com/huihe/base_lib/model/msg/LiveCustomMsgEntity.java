package com.huihe.base_lib.model.msg;

import com.huihe.base_lib.model.im.TCChatEntity;

public class LiveCustomMsgEntity {

    private int type = 1; // 1 聊天消息，2 礼物消息，3 公告,4 进入房间的通知 ，5 退出房间的通知

    public boolean isGiftMsg() {
        return type == 2;
    }
    public boolean isNotice() {
        return type == 3;
    }
    public boolean isEnterRoom() {
        return type == 4;
    }
    public boolean isExitRoom() {
        return type == 5;
    }

    private TCChatEntity tcChatEntity;
    private TCGiftEntity tcGiftEntity;

    public int getType() {
        return type;
    }

    public TCChatEntity getTcChatEntity() {
        return tcChatEntity;
    }

    public TCGiftEntity getTcGiftEntity() {
        return tcGiftEntity;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTcChatEntity(TCChatEntity tcChatEntity) {
        this.tcChatEntity = tcChatEntity;
    }

    public void setTcGiftEntity(TCGiftEntity tcGiftEntity) {
        this.tcGiftEntity = tcGiftEntity;
    }

    public static class TCGiftEntity {
        private String giftNum;
        private String giftUrl;
        private String giftMsg;
        private String giftName;

        public void setGiftName(String giftName) {
            this.giftName = giftName;
        }

        public String getGiftName() {
            return giftName;
        }

        public String getGiftNum() {
            return giftNum;
        }

        public String getGiftUrl() {
            return giftUrl;
        }

        public String getGiftMsg() {
            return giftMsg;
        }

        public void setGiftNum(String giftNum) {
            this.giftNum = giftNum;
        }

        public void setGiftUrl(String giftUrl) {
            this.giftUrl = giftUrl;
        }

        public void setGiftMsg(String giftMsg) {
            this.giftMsg = giftMsg;
        }
    }
}
