package com.huihe.base_lib.model.event;

public class MyPushIntentServiceEvent {
    public String clientid;

    public String getClientid() {
        return clientid;
    }

    public MyPushIntentServiceEvent(String clientid) {
        this.clientid = clientid;
    }
}
