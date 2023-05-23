package com.accyln.tictactoe.controller;

import com.accyln.tictactoe.DTOs.CreatePlayerRequestDto;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.services.GameService;
import com.accyln.tictactoe.services.IGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/game")
public class GameController {
    private final IGameService gameService;
    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/newPlayer")
    public ResponseEntity<Player> createPlayer(@RequestBody CreatePlayerRequestDto createPlayerRequestDto){
        return new ResponseEntity<>(gameService.createPlayer(createPlayerRequestDto.getName(), createPlayerRequestDto.getSign()), HttpStatus.CREATED);
    }


}
