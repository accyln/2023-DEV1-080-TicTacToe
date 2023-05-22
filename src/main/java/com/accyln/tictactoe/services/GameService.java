package com.accyln.tictactoe.services;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.exceptions.SquareAlreadyTakenException;
import org.springframework.stereotype.Service;
@Service
public class GameService {

    public GameService(){

    }

    public Game makeAmove(Game game, int rowId, int colId, char sign){
        char[][] board= game.getBoard();
        //checking that played square cannot play twice
        if(board[rowId][colId]!=0){
            throw new SquareAlreadyTakenException();
        }
        
        board[rowId][colId]=sign;
        game.setLastPlayedSign(sign);

        return game;
    }
}
