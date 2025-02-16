package com.praktukym.gameServer.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.praktukym.gameServer.DTOs.GameConectDTO;
import com.praktukym.gameServer.DTOs.LobyCreateDTO;
import com.praktukym.gameServer.DTOs.LobyJoinDTO;
import com.praktukym.gameServer.Game.GameService;

@Controller
public class CreateController {

    @Autowired
    GameService gameService;


    @PostMapping("/loby")
    public ResponseEntity<?> CreateLoby(@RequestBody LobyCreateDTO request) {
        GameConectDTO GameConect = gameService.CreateGame(request.getIdplayer()); 
        return ResponseEntity.status(HttpStatus.CREATED).body(GameConect);
    }

    @PostMapping("/loby/join")
    public ResponseEntity<?> JoinLoby(@RequestBody LobyJoinDTO request) throws Exception {
        return gameService.JoinGame(request.getTopic(), request.getIdplayer());
    }
}
