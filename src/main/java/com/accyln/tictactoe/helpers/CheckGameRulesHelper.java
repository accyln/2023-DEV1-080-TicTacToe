package com.accyln.tictactoe.helpers;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.enums.GameStatus;
import org.springframework.stereotype.Component;

@Component
public class CheckGameRulesHelper {
    public boolean isGameFinished(Game game){
        return !game.getGameStatus().equals(GameStatus.ONGOING);
    }

    public boolean isSquareEmpty(Game game,int rowId,int colId){
        char[][] board= game.getBoard();
        return board[rowId][colId]==0;
    }

    public boolean isThisSignsTurn(Game game,char sign){
        return sign!=game.getLastPlayedSign();
    }
}
