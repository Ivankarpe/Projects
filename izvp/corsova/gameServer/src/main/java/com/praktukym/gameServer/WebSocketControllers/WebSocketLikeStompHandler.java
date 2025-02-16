package com.praktukym.gameServer.WebSocketControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.praktukym.gameServer.WSsesions.WSsesionService;

import org.springframework.web.socket.TextMessage;

@Component
public class WebSocketLikeStompHandler implements WebSocketHandler {

    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private WSsesionService wSsesionService;

   

    
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (wSsesionService.isUserInGame(session.getId())) {
            messageHandler.HandleMessageFromWS(wSsesionService.getGameTopic(session.getId()), ((TextMessage) message).getPayload());
        }
        else {
          //  String topic = ((TextMessage) message).getPayload().split("\"")[1];
            String topic = ((TextMessage) message).getPayload();
            System.err.println("topic " + topic);  
            wSsesionService.addSession(session, topic);
        }
        
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
      
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        wSsesionService.removeSession(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}