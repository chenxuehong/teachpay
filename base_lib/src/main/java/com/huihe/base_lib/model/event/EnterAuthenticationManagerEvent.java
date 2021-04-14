package com.huihe.base_lib.model.event;

import android.content.Context;

public class EnterAuthenticationManagerEvent {
    public Context context;
    public EnterAuthenticationManagerEvent(Context context) {
        this.context= context;
    }
}
