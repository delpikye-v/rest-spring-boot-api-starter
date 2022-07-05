package com.dp.spring.template.websocket;

import lombok.Data;

@Data
public class WebSocketMessage<T> {
    public enum Type {
        TUTORIAL
    }

    private Type type;
    private String requestId;
    private long timestamp = 0;
    private T data;

    public WebSocketMessage() {
        this.timestamp = System.currentTimeMillis();
    }

    public WebSocketMessage(Type type, T data) {
        this();
        this.type = type;
        this.data = data;
    }
}
