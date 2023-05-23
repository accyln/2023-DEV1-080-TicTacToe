package com.accyln.tictactoe.controller;

import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.services.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/game")
public class GameController {
    protected final GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/newPlayer")
    public Player createPlayer(String name, char sign){
       return new Player(1l,name,sign);
    }


}
