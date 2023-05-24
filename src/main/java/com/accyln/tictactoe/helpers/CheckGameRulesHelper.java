package com.accyln.tictactoe.helpers;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.enums.GameStatus;
import org.springframework.stereotype.Component;

@Component
public class CheckGameRulesHelper {
    public boolean isGameFinished(Game game){
        if(!game.getGameStatus().equals(GameStatus.ONGOING)){
            return true;
        } else return false;
    }

    public boolean isSquareEmpty(Game game,int rowId,int colId){
        char[][] board= game.getBoard();
        if(board[rowId][colId]!=0){
            return false;
        } else return true;
    }

    public boolean isThisSignsTurn(Game game,char sign){
        if(sign==game.getLastPlayedSign()){
            return false;
        } else return true;
    }
}
