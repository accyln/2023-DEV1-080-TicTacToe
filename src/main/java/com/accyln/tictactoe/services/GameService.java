package com.accyln.tictactoe.services;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.enums.GameStatus;
import com.accyln.tictactoe.exceptions.SamePlayerCannotSignInSuccesion;
import com.accyln.tictactoe.exceptions.SquareAlreadyTakenException;
import com.accyln.tictactoe.repositories.IGameRepository;
import com.accyln.tictactoe.repositories.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class GameService implements IGameService {

    @Autowired
    private IGameRepository gameRepository;
    @Autowired
    private IPlayerRepository playerRepository;
    public GameService(IGameRepository gameRepository,IPlayerRepository playerRepository){
         this.gameRepository=gameRepository;
         this.playerRepository=playerRepository;
    }
    public Player createPlayer(String name, char sign){
        return playerRepository.save(new Player(name,sign));
    }
    public Game createGame(Long player1Id,Long player2Id){
        return gameRepository.save(new Game(player1Id,player2Id));
    }

    public Game makeAmove(Long gameId, int rowId, int colId, char sign){
        Game game=gameRepository.findById(gameId).orElseThrow(()->new NotFoundException("Game not found with id: "+ gameId));
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
        game.setMoveCount(game.getMoveCount()+1);

        char winner=checkWinner(board);
        if(winner != 0) {
            game.setWinner(winner);
            game.setGameStatus(GameStatus.ENDED);
        }

        if (game.getMoveCount()==9){
            game.setGameStatus(GameStatus.ENDED);
        }

        gameRepository.save(game);

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
