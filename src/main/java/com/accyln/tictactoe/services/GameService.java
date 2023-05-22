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

        char winner=checkWinner(board);
        if(winner != 0) {
            game.setWinner(winner);
            game.setGameStatus("ENDED");
        }

        return game;
    }

    private char checkWinner(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        else if(board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        else return 0;
    }

}
