package com.omoto.configurator.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by omoto on 13/2/17.
 */
public class PingPongServiceImpl {
    @Autowired
    SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 20000L)
    @SendTo("/topic/pingpong")
    public void sendPong() {
        template.convertAndSend("/topic/pingpong", "pong (periodic)");
    }

    @MessageMapping("/ping")
    @SendTo("/topic/pingpong")
    public String sendPingResponse() {
        return "pong (response)";
    }
}
