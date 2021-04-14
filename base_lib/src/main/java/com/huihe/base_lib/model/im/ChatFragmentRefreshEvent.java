package com.huihe.base_lib.model.im;

public class ChatFragmentRefreshEvent {
    public static final int CLEAR_CHAT_HISTORY = 100;
    public static final int DELETE_CHAT_FRIEND = 101;
    public static final int EXIT_GROUP = 102;
    public static final int LOAD_CONVERSATION_LIST = 103;

    public int type;
    public ChatFragmentRefreshEvent(int type) {
        this.type = type;
    }
}
