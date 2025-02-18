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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
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
        game.setTopic(gameConectDTO.topic);
        game.setHost(host);
        game.setStatus(0);

        topicToCode.put(newcode + "", gameConectDTO.topic);
        gameConectDTO.code = newcode + "";
        newcode++;

        games.put(gameConectDTO.topic, game);

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
            game.setTurn(1);

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
        game.setTurn(turn);

        if (board[message.y][message.x] == 'S') {
            board[message.y][message.x] = 'X';

            markShipSurrounding(message.y, message.x, board);

            messageSender.SendMessagwToTopic(topic,
                    new updadeStateResponceMessage(game.getHost(), game.getHostBoard(), turn));
            messageSender.SendMessagwToTopic(topic,
                    new updadeStateResponceMessage(game.getGuest(), game.getGuestBoard(), turn));
        } else {
            if (turn == 1) {
                turn = 2;
            } else {
                turn = 1;
            }
        game.setTurn(turn);

            board[message.y][message.x] = 'O';
            messageSender.SendMessagwToTopic(topic,
                    new updadeStateResponceMessage(game.getHost(), game.getHostBoard(), turn));
            messageSender.SendMessagwToTopic(topic,
                    new updadeStateResponceMessage(game.getGuest(), game.getGuestBoard(), turn));
        }
        CheckWin(game);

    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < 10 && col >= 0 && col < 10;
    }

    static Set<List<Integer>> visited = new HashSet<>();

    public void markShipSurrounding(int startRow, int startCol, char[][] Board) {
        visited.clear();

        List<int[]> shipCells;
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        boolean isSingle = true;

        for (int[] d : directions) {
            int newRow = startRow + d[0];
            int newCol = startCol + d[1];

            if (!isValidCell(newRow, newCol)) {
                continue;
            }
            if (Board[newRow][newCol] == 'S' || Board[newRow][newCol] == 'X') {
                isSingle = false;
                break;
            }

        }

        if (isSingle) {
            shipCells = new ArrayList<>();
            shipCells.add(new int[] { startRow, startCol });
        } else {
            shipCells = findShipCells(startRow, startCol, Board);
        }

        boolean isShipDestroyed = true;
        for (int[] cell : shipCells) {
            int row = cell[0];
            int col = cell[1];
            if (Board[row][col] == 'S') {
                isShipDestroyed = false;
            }
        }

        if (!isShipDestroyed) {
            return;
        }

        for (int[] cell : shipCells) {
            int row = cell[0];
            int col = cell[1];
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newRow = row + i;
                    int newCol = col + j;
                    if (isValidCell(newRow, newCol) && Board[newRow][newCol] == ' ') {
                        Board[newRow][newCol] = 'O';
                    }
                }
            }
        }
    }

    private List<int[]> findShipCells(int row, int col, char[][] Board) {
        List<int[]> shipCells = new ArrayList<>();
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int[] d : directions) {
            int newRow = row + d[0];
            int newCol = col + d[1];
            List<Integer> key = Arrays.asList(newRow, newCol);

            if (!visited.contains(key)) {
                visited.add(key);
                if (isValidCell(newRow, newCol) &&
                        (Board[newRow][newCol] == 'S' || Board[newRow][newCol] == 'X')) {
                    shipCells.add(new int[] { newRow, newCol });
                    shipCells.addAll(findShipCells(newRow, newCol, Board));
                }
            }
        }
        return shipCells;
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
        
        
        messageSender.SendMessagwToTopic(topic,
        new updadeStateResponceMessage(game.getHost(), game.getHostBoard(), game.getTurn()));
messageSender.SendMessagwToTopic(topic,
        new updadeStateResponceMessage(game.getGuest(), game.getGuestBoard(), game.getTurn()));
    }

    public void CloseGame(String topic) {
        games.remove(topic);
    }
}
