package com.praktukym.gameServer.Game;


import com.praktukym.gameServer.DTOs.GameConectDTO;
import com.praktukym.gameServer.WSMessages.GameReadyMessage;
import com.praktukym.gameServer.WSMessages.GameStartMessage;
import com.praktukym.gameServer.WSMessages.MoveMessage;
import com.praktukym.gameServer.WSMessages.bufMessage;
import com.praktukym.gameServer.WSMessages.debufMessage;
import com.praktukym.gameServer.WSMessages.newUserMessage;
import com.praktukym.gameServer.WSMessages.updadeStateMessage;
import com.praktukym.gameServer.WSMessages.updadeStateResponceMessage;
import com.praktukym.gameServer.WSMessages.winnerMessage;
import com.praktukym.gameServer.WSsesions.WSsesionService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.praktukym.gameServer.Mesages.MesegeSender;

@Service
public class GameService {
    static int newcode = 68068;

    static Map<String, Game> games = new HashMap<>();
    static Map<String, String> topicToCode = new HashMap<>();
    @Autowired
    WSsesionService wSsesionService;
    
    @Autowired
    private MesegeSender messageSender;

    public GameConectDTO CreateGame(String host) {
        GameConectDTO gameConectDTO = new GameConectDTO();

        gameConectDTO.topic = UUID.randomUUID().toString();

        Game game = new Game();
        game.setTopic( gameConectDTO.topic);
        game.setHost(host);
        game.setStatus(0);

       
        topicToCode.put(newcode + "",  gameConectDTO.topic);
        gameConectDTO.code = newcode + "";
        newcode++;
        
        
        
        games.put( gameConectDTO.topic, game);

        return gameConectDTO;
    }

    public ResponseEntity<?> JoinGame(String code, String guest) throws Exception {
        
        String topic = topicToCode.get(code);
        if (topic == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }
        Game game = games.get(topic);
        if (game == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }
        if (game.getStatus() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Game already full");
        }
        game.setGuest(guest);
        game.setStatus(1);
        messageSender.SendMessagwToTopic(topic, new GameReadyMessage());
        return ResponseEntity.status(HttpStatus.OK).body(topic);
    }

    public void ConfirmBoard(String topic, String id, char[][] board) throws Exception {
        Game game = games.get(topic);
        if (game == null) {
            return;
        }
        if (game.getStatus() != 1) {
            return;
        }
        if (game.getHost().equals(id)) {
            game.setHostBoard(board);
        } else {
            game.setGuestBoard(board);
        }
        if (game.getHostBoard() != null && game.getGuestBoard() != null) {
            game.setStatus(2);
    
            messageSender.SendMessagwToTopic(topic, new GameStartMessage());
        }
    }


    public void Move(String topic, MoveMessage message) throws Exception {
        Game game = games.get(topic);

        if (game == null) {
            return;
        }
        if (game.getStatus() != 2) {
            return;
        }
        char[][] board;
        int turn;
        if (game.getHost().equals(message.UserId)) {
            turn = 1;
            board = game.getGuestBoard();
        } else {
            turn = 2;
            board = game.getHostBoard();
        }

        if (board[message.y][message.x] == 'S') {
            board[message.y][message.x] = 'X';
            

            messageSender.SendMessagwToTopic(topic, new updadeStateResponceMessage(game.getHost(), game.getHostBoard(), turn));
            messageSender.SendMessagwToTopic(topic, new updadeStateResponceMessage(game.getGuest(), game.getGuestBoard(), turn));
        } else {
            if(turn ==1){
                turn = 2;
            }else{
                turn = 1;
            }
            board[message.y][message.x] = 'O';
            messageSender.SendMessagwToTopic(topic, new updadeStateResponceMessage(game.getHost(), game.getHostBoard(), turn));
            messageSender.SendMessagwToTopic(topic, new updadeStateResponceMessage(game.getGuest(), game.getGuestBoard(), turn));
        }
        CheckWin(game);

    }

    private void CheckWin(Game game) throws Exception {
        String result = game.CheckWinner();
        if (result != null) {
            messageSender.SendMessagwToTopic(game.getTopic(), new winnerMessage(result));
            game.setStatus(4);
            CloseGame(game.getTopic());
        }

    }

    public void Newuser(String topic, newUserMessage message) {

    }

    public void debuf(String topic, debufMessage message) {

    }

    public void buf(String topic, bufMessage message) {

    }

    public void updadeState(String topic, updadeStateMessage message) throws Exception {
        Game game = games.get(topic);
        if (game == null) {
            return;
        }
        if (game.getStatus() != 2) {
            return;
        }
        char[][] board;
        if (game.getHost().equals(message.id)) {
            board = game.getHostBoard();
        } else {
            board = game.getGuestBoard();
        }
        //messageSender.SendMessagwToTopic(topic, new updadeStateResponceMessage(message.id, board));
    }

    

    public void CloseGame( String topic) {
        games.remove(topic);
    }
}
