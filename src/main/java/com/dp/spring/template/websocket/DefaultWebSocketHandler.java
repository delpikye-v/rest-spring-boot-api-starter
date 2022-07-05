package com.dp.spring.template.websocket;

import com.dp.spring.template.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class DefaultWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {
    protected final Logger logger = LoggerFactory.getLogger(DefaultWebSocketHandler.class);
    public static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        sessions.forEach(webSocketSession -> {
            try {
                String request = message.getPayload();
                logger.info("Server received: {}", request);

                String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request));
                logger.info("Server sends: {}", response);
                webSocketSession.sendMessage(new TextMessage(response));
            } catch (IOException e) {
                logger.error("Error occurred.", e);
            }
        });
    }

    /**
     * Notify Websocket event from server to UI
     * **/
    public void sendWebSocketMessages(WebSocketMessage webSocketMessage) throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(JsonUtil.toJson(webSocketMessage)));
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        logger.info("Server transport error: {}", exception.getMessage());
    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.demo.websocket");
    }
}