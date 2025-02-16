package com.praktukym.gameServer.WebSocketControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.praktukym.gameServer.Game.GameService;



@Controller
public class WebSocketController {

	@Autowired
    GameService gameService;

	@Autowired
    MessageHandler messageHandler;

	@MessageMapping("/topic/{topic}")
	public void HandleMessage(@DestinationVariable String topic, String message) throws Exception {
		messageHandler.HandleMessageFromSTOMP(topic, message);
	}
	
}