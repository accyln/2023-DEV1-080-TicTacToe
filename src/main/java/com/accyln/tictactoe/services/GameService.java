package com.accyln.tictactoe.services;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.enums.GameStatus;
import com.accyln.tictactoe.exceptions.GameAlreadyFinishedException;
import com.accyln.tictactoe.exceptions.InvalidFirstMovingPlayerSignException;
import com.accyln.tictactoe.exceptions.SamePlayerCannotSignInSuccesion;
import com.accyln.tictactoe.exceptions.SquareAlreadyTakenException;
import com.accyln.tictactoe.helpers.CalculateWinnerHelper;
import com.accyln.tictactoe.helpers.CheckGameRulesHelper;
import com.accyln.tictactoe.repositories.IGameRepository;
import com.accyln.tictactoe.repositories.IPlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService implements IGameService {
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);
    @Autowired
    private IGameRepository gameRepository;
    @Autowired
    private IPlayerRepository playerRepository;
    @Autowired
    private CalculateWinnerHelper calculateWinnerHelper;
    @Autowired
    private CheckGameRulesHelper checkGameRulesHelper;
    public GameService(IGameRepository gameRepository,IPlayerRepository playerRepository,
                       CalculateWinnerHelper calculateWinnerHelper,CheckGameRulesHelper checkGameRulesHelper){
         this.gameRepository=gameRepository;
         this.playerRepository=playerRepository;
         this.calculateWinnerHelper=calculateWinnerHelper;
         this.checkGameRulesHelper=checkGameRulesHelper;
    }
    public Player createPlayer(String name, char sign){
        return playerRepository.save(new Player(name,sign));
    }
    public Game createGame(Long player1Id,Long player2Id){
        return gameRepository.save(new Game(player1Id,player2Id));
    }

    public Game makeAmove(Long gameId, int rowId, int colId, char sign){
        Game game=gameRepository.findById(gameId).orElseThrow(()->new NotFoundException("Game not found with id: "+ gameId));

        //check game is ongoing
        if (checkGameRulesHelper.isGameFinished(game)) {
            throw new GameAlreadyFinishedException("Game has already finished. You cannot sign.");
        }
        //checking FirstPlayer Sign is X
        if(game.getMoveCount()==0 && sign!='X'){
            throw new InvalidFirstMovingPlayerSignException("First sign must be X");
        }
        //checking that played square is empty
        if(!checkGameRulesHelper.isSquareEmpty(game,rowId,colId)){
            throw new SquareAlreadyTakenException("This square is not null, rowId: "+rowId+ " colId: "+colId);
        }
        //checking that current player sign is not equal to last move
        if(!checkGameRulesHelper.isThisSignsTurn(game,sign)){
            throw new SamePlayerCannotSignInSuccesion("Cannot make a move with same sign in succession");
        }

        char[][] board= game.getBoard();
        board[rowId][colId]=sign;
        game.setLastPlayedSign(sign);
        game.setMoveCount(game.getMoveCount()+1);

        char winner=calculateWinnerHelper.checkWinner(board);
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


    public Player getPlayerById(Long playerId){
        return playerRepository.findById(playerId).orElseThrow(()-> new NotFoundException("Player not found "+ playerId));
    }

    public Game getGameById(Long gameId){
        return gameRepository.findById(gameId).orElseThrow(()-> new NotFoundException("Player not found "+ gameId));
    }

    public List<Game> getAllGames(){
        List<Game> result = new ArrayList<Game>();
        gameRepository.findAll().forEach(result::add);
        return result;
    }
}
