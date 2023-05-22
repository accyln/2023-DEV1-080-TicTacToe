package com.accyln.tictactoe.services;

import com.accyln.tictactoe.entities.Game;
import org.springframework.stereotype.Service;
@Service
public class GameService {

    public GameService(){

    }

    public Game makeAmove(Game game, int rowId, int colId, char sign){
        char[][] board= game.getBoard();
        board[rowId][colId]=sign;
        game.setLastPlayedSign(sign);

        return game;
    }
}
