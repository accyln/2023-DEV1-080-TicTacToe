package com.accyln.tictactoe.services;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.exceptions.SamePlayerCannotSignInSuccesion;
import com.accyln.tictactoe.exceptions.SquareAlreadyTakenException;
import org.springframework.stereotype.Service;
@Service
public class GameService {

    public GameService(){

    }
    public Game createGame(Long player1Id,Long player2Id){
        return new Game(player1Id,player2Id);
    }

    public Game makeAmove(Game game, int rowId, int colId, char sign){
        char[][] board= game.getBoard();
        //checking that played square cannot play twice
        if(board[rowId][colId]!=0){
            throw new SquareAlreadyTakenException();
        }
        //checking that current player sign is not equal to last move
        if(sign==game.getLastPlayedSign()){
            throw new SamePlayerCannotSignInSuccesion();
        }

        board[rowId][colId]=sign;
        game.setLastPlayedSign(sign);

        return game;
    }

}