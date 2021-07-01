package com.example.demo.controller;

import com.example.demo.domain.WebSocketChatMessage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {
    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/demo")
    public WebSocketChatMessage sendMessage(@Payload WebSocketChatMessage webSocketChatMessage){
        return webSocketChatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/demo")
    public WebSocketChatMessage newUser(@Payload WebSocketChatMessage webSocketChatMessage,
			SimpMessageHeaderAccessor headerAccessor) {
                headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
                return webSocketChatMessage;
    }
    
}