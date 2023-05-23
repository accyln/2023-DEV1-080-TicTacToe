package com.accyln.tictactoe.services;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;

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

}
