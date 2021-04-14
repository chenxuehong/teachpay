package com.huihe.base_lib.utils;

import com.huihe.base_lib.model.event.Event;

import org.greenrobot.eventbus.EventBus;

public class EventBusUtils {

    public static void register(Object object) {

        if (!EventBus.getDefault().isRegistered(object))
            EventBus.getDefault().register(object);
    }

    public static void unRegister(Object object) {

        if (EventBus.getDefault().isRegistered(object))
            EventBus.getDefault().unregister(object);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }
}
