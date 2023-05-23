package com.accyln.tictactoe.services;

import com.accyln.tictactoe.entities.Game;

public interface IGameService {
    /**
     * Create and return a new game
     * @param player1Id
     * @param player2Id
     * @return Game
     */
    Game createGame(Long player1Id,Long player2Id);

    /**
     * Making a move and sign on the board
     * @param game
     * @param rowId
     * @param colId
     * @param sign
     * @return Game
     */
    Game makeAmove(Game game, int rowId, int colId, char sign);

}
