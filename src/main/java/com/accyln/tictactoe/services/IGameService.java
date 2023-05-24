package com.accyln.tictactoe.services;

import com.accyln.tictactoe.DTOs.GameDetailsResponseDto;
import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;

import java.util.List;

public interface IGameService {
    /**
     * Create and return a new player
     * @param name
     * @param sign
     * @return Player
     */
    Player createPlayer(String name, char sign);

    /**
     * Create and return a new game
     * @param player1Id
     * @param player2Id
     * @return Game
     */
    Game createGame(Long player1Id,Long player2Id);

    /**
     * Making a move and sign on the board
     * @param gameId
     * @param rowId
     * @param colId
     * @param sign
     * @return Game
     */
    Game makeAmove(Long gameId, int rowId, int colId, char sign);

    /**
     * Returns player details by playerId
     * @param playerId
     * @return Player
     */
    Player getPlayerById(Long playerId);

    /**
     * Returns game details by gameId
     * @param gameId
     * @return Game
     */
    Game getGameById(Long gameId);

    /**
     * Returns all games with player details
     * @return List<GameDetailsResponseDto>
     */
    List<GameDetailsResponseDto> getAllGamesWithDetails();

}
