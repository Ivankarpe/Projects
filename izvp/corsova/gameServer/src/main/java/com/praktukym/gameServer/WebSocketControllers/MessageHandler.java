package com.praktukym.gameServer.WebSocketControllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praktukym.gameServer.Game.GameService;
import com.praktukym.gameServer.Mesages.MesegeSender;
import com.praktukym.gameServer.WSMessages.ConfirmBoard;
import com.praktukym.gameServer.WSMessages.MoveMessage;
import com.praktukym.gameServer.WSMessages.updadeStateMessage;

@Controller
public class MessageHandler {

    @Autowired
    GameService gameService;

 	@Autowired
    private MesegeSender messageSender;


    public void HandleMessageFromWS(String topic, String message) throws Exception {
		messageSender.SendMessagwToSTOMP(topic, message);
		HandleMessage(topic, message);
	}
	public void HandleMessageFromSTOMP(String topic, String message) throws Exception {
		messageSender.SendMessagwToWS(topic, message);
		HandleMessage(topic, message);
	}

    public void HandleMessage(String topic, String message) throws Exception {




		HashMap<String, Object> map = null;
		ObjectMapper objectMapper = new ObjectMapper();
        try {
            map = objectMapper.readValue(message, HashMap.class);
        } catch (Exception e) {
            System.err.println("Error in parsing message: " + message);
        }
		if(map == null) {
			return;
		}
		if(map.get("type") == null) {
			System.err.println(map);
			return;
		}

		switch ((String) map.get("type")) {
			case "Move":
					gameService.Move(topic, objectMapper.readValue(message,  MoveMessage.class));
					break;
			case "newuser":
					break; 
			case "debuf":
					break; 
			case "buf":
					break; 
			case "updadeState":
					gameService.updadeState(topic, objectMapper.readValue(message,  updadeStateMessage.class));;
					break; 
			case "ConfirmBoard":
					ConfirmBoard confirmBoard = objectMapper.readValue(message,  ConfirmBoard.class);
					gameService.ConfirmBoard(topic, confirmBoard.id, confirmBoard.board);
					break; 
			default:
				break;
		}




	}
}
