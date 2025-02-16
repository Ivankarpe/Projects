package com.praktukym.gameServer.Mesages;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.praktukym.gameServer.WSsesions.WSsesionService;
import org.springframework.web.socket.TextMessage;
@Service
public class MesegeSender {
    @Autowired
    WSsesionService wSsesionService;


    ObjectMapper objectMapper = new ObjectMapper();

    SimpMessagingTemplate messagingTemplate;

    public MesegeSender(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void SendMessagwToTopic(String topic, Object messagePayload) throws IOException {

        String message = objectMapper.writeValueAsString(messagePayload);
        

        SendMessagwToWS(topic, message);
        SendMessagwToSTOMP(topic, message);
    }

    public void SendMessagwToWS(String topic, String message) throws IOException {
        ArrayList<WebSocketSession> users = wSsesionService.getUsersInGame(topic);
        if(users == null) {
            return;
        }
        for (WebSocketSession user : users) {
           user.sendMessage(new TextMessage(message));
        }
    }

    public void SendMessagwToSTOMP(String topic, String message) throws IOException {
        messagingTemplate.convertAndSend("/topic/" + topic, message);

    }
}
