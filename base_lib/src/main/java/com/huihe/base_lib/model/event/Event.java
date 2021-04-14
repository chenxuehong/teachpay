package com.huihe.base_lib.model.event;

public class Event<T> {
    private T data;
    public String action;

    public Event(String action) {
        this.action = action;
    }

    public Event(String action, T data) {
        this.action = action;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public String getAction() {
        return action;
    }
}
