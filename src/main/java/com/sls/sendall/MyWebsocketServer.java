package com.sls.sendall;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sls
 **/
@ServerEndpoint("/test")
@Component
@Slf4j
public class MyWebsocketServer {
    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    private Gson gson = new Gson();

    @OnOpen
    public void onOpen(Session session) {
        log.info("有新的客户端连接了：{}", session.getId());
        // 将新用户存入在线的组
        clients.put(session.getId(), session);
    }

    /**
     * 客户端关闭
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("有用户断开了，id为：{}", session.getId());
        // 将掉线的用户移除在线的组里
        clients.remove(session.getId());
    }

    /**
     * 发生错误
     * @param throwable
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     * @param message  消息对象
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("服务端收到客户端发来的消息：{}", message);
        this.sendAll(message);
    }

    /**
     * 群发消息
     * @param message 消息内容
     */
    private void sendAll(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }
}
