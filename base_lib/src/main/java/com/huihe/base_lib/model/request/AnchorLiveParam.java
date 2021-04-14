package com.huihe.base_lib.model.request;

public class AnchorLiveParam {
    public String live_stream_id;
    public String room_id;
    public String roomName;

    public AnchorLiveParam(String live_stream_id, String room_id, String roomName) {
        this.live_stream_id = live_stream_id;
        this.room_id = room_id;
        this.roomName = roomName;
    }
}
