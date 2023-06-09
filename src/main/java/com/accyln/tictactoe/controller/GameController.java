package com.accyln.tictactoe.controller;

import com.accyln.tictactoe.dtos.*;
import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.services.IGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/game")
public class GameController {
    private final IGameService gameService;
    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    /**
     * @author Ahmet Can Ceylan
     *
     * The endpoint for creating a new player
     *
     * @param createPlayerRequestDto
     * @return ResponseEntity<Player>
     */
    @PostMapping("/newPlayer")
    public ResponseEntity<Player> createPlayer(@RequestBody CreatePlayerRequestDto createPlayerRequestDto){
        return new ResponseEntity<>(gameService.createPlayer(createPlayerRequestDto), HttpStatus.CREATED);
    }

    /**
     * @author Ahmet Can Ceylan
     *
     * The endpoint for creating a new game
     *
     * @param createGameRequestDto
     * @return ResponseEntity<Game>
     */
    @PostMapping("/newGame")
    public ResponseEntity<Game> createPlayer(@RequestBody CreateGameRequestDto createGameRequestDto){
        return new ResponseEntity<>(gameService.createGame(createGameRequestDto.getPlayer1Id(), createGameRequestDto.getPlayer2Id()), HttpStatus.CREATED);
    }

    /**
     * @author Ahmet Can Ceylan
     *
     * The endpoint for creating a new game
     *
     * @param createPlayersAndGameRequestDto
     * @return ResponseEntity<Game>
     */
    @PostMapping("/createPlayersAndGame")
    public ResponseEntity<Game> createPlayersAndGame(@RequestBody CreatePlayersAndGameRequestDto createPlayersAndGameRequestDto) {
        return new ResponseEntity<>(gameService.createPlayersAndGame(createPlayersAndGameRequestDto), HttpStatus.CREATED);
    }
    /**
     * @author Ahmet Can Ceylan
     *
     * The endpoint for making a sign on the board
     *
     * @param makeAMoveRequestDto
     * @return ResponseEntity<Game>
     */
    @PostMapping("/makeAmove")
    public ResponseEntity<Game> makeAmove(@RequestBody MakeAMoveRequestDto makeAMoveRequestDto){
        Game game=gameService.makeAmove(makeAMoveRequestDto.getGameId(), makeAMoveRequestDto.getRowId(),makeAMoveRequestDto.getColId(),makeAMoveRequestDto.getSign());
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    /**
     * @author Ahmet Can Ceylan
     *
     * Returns a corresponding player details by playerId
     *
     * @param playerId
     * @return ResponseEntity<Player>
     */
    @GetMapping("/getPlayerById")
    public ResponseEntity<Player> getPlayerById(@RequestParam Long playerId){
        return new ResponseEntity<>(gameService.getPlayerById(playerId),HttpStatus.OK);
    }

    /**
     * @author Ahmet Can Ceylan
     *
     * Returns a corresponding Game details by gameId
     *
     * @param gameId
     * @return ResponseEntity<Game>
     */
    @GetMapping("/getGameById")
    public ResponseEntity<Game> getGameById(@RequestParam Long gameId){
        return new ResponseEntity<>(gameService.getGameById(gameId),HttpStatus.OK);
    }

    /**
     * @author Ahmet Can Ceylan
     *
     * Returns all games with details
     *
     * @return ResponseEntity<List<GameDetailsResponseDto>>
     */
    @GetMapping("/getAllGamesWithDetails")
    public ResponseEntity<List<GameDetailsResponseDto>> getAllGamesWithDetails(){
        return new ResponseEntity<>(gameService.getAllGamesWithDetails(),HttpStatus.OK);
    }
}
