package com.example.financialsystem.common;

import cn.hutool.json.JSONUtil;
import com.example.financialsystem.entity.ImSingle;
import com.example.financialsystem.service.ImSingleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2024/1/12 11:12
 */

@ServerEndpoint(value = "/imserverSingle/{userId}")
@Component
public class WebSocketSingleServer implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(WebSocketSingleServer.class);

    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();


    private static ImSingleService imSingleService;

    @Autowired
    public void setImSingleService(ImSingleService imSingleService) {
        WebSocketSingleServer.imSingleService = imSingleService;
        log.info("setChatService called, ImSingleService injected");
    }



    @Override
    public void afterPropertiesSet() {
        // 初始化代码可以放在这里
        log.info("WebSocketSingleServer initialized");
    }

    @OnOpen
    public void OnOpen(@PathParam("userId") String userId, Session session) {
        sessionMap.put(session.getId(), session);
        session.getUserProperties().put("userId",userId);
        log.info("[onOpen] 新建连接, session={}, 当前在线人数为: {},连接的ID为:{}", session.getId(), sessionMap.size(),session.getUserProperties().get("userId"));
    }

    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(session.getId());
        log.info("[onClose] 有一连接关闭, session={}, 当前在线人数: {}", session.getId(), sessionMap.size());
    }

    @OnMessage
    public void onMessage(String message, Session fromSession) {
        log.info("服务端收到消息:{}",message);
        ImSingle imSingle = JSONUtil.toBean(message, ImSingle.class);
        //将数据入库
        //添加填写时间
        LocalDateTime now = LocalDateTime.now();
        imSingle.setTime(now);
        imSingleService.insertContent(imSingle);

        //再将数据转发到对应的客户端
        String jsonStr = JSONUtil.toJsonStr(imSingle);
        this.sendAllMessage(jsonStr);
        log.info("[onMessage] 发送消息:{}", jsonStr);
    }

    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务的给客户端[{}]发送消息{}",session.getId(),message);
                session.getBasicRemote().sendText(message);
            }
        } catch(Exception e) {
            log.error("服务端发送消息给客户端失败",e);
        }
    }

    @OnError
    public  void onError(Session session, Throwable error) {
        log.error("[onError] 发生错误",error);
    }
}
