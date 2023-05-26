package com.accyln.tictactoe.services;

import com.accyln.tictactoe.dtos.CreatePlayerRequestDto;
import com.accyln.tictactoe.dtos.CreatePlayersAndGameRequestDto;
import com.accyln.tictactoe.dtos.GameDetailsResponseDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService implements IGameService {
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
    public Player createPlayer(CreatePlayerRequestDto createPlayerRequestDto){
        return playerRepository.save(new Player(createPlayerRequestDto.getName(),createPlayerRequestDto.getSign()));
    }
    public Game createGame(Long player1Id,Long player2Id){
        return gameRepository.save(new Game(player1Id,player2Id));
    }
    public Game createPlayersAndGame(CreatePlayersAndGameRequestDto createPlayersAndGameRequestDto){
        Player player1=createPlayer(createPlayersAndGameRequestDto.getPlayer1());
        Player player2=createPlayer(createPlayersAndGameRequestDto.getPlayer2());
        return gameRepository.save(new Game(player1.getId(),player2.getId()));
    }

    public Game makeAmove(Long gameId, int rowId, int colId, char sign){
        Game game=gameRepository.findById(gameId).orElseThrow(()->new NotFoundException("Game not found with id: "+ gameId));

        //check game is ongoing
        if (checkGameRulesHelper.isGameFinished(game)) {
            throw new GameAlreadyFinishedException("Game has already finished. You cannot make a move.");
        }
        //checking FirstPlayer Sign is X
        if(game.getMoveCount()==0 && sign!='X'){
            throw new InvalidFirstMovingPlayerSignException("First playing sign must be X");
        }
        //checking that played square is empty
        if(!checkGameRulesHelper.isSquareEmpty(game,rowId,colId)){
            throw new SquareAlreadyTakenException("This square is not empty, cannot sign!  (rowId: "+rowId+ " colId: "+colId+")");
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
        return playerRepository.findById(playerId).orElseThrow(()-> new NotFoundException("Player not found with playerId: "+ playerId));
    }

    public Game getGameById(Long gameId){
        return gameRepository.findById(gameId).orElseThrow(()-> new NotFoundException("Game not found with gameId: "+ gameId));
    }

    public List<GameDetailsResponseDto> getAllGamesWithDetails(){
        List<Game> result = new ArrayList<>();
        gameRepository.findAll().forEach(result::add);

        List<GameDetailsResponseDto> gameDetailList=new ArrayList<>();

        for (Game game:result) {
            Player player1=playerRepository.findById(game.getPlayer1Id()).orElseThrow(()-> new NotFoundException("Cannot find a player with id:"+ game.getPlayer1Id()));
            Player player2=playerRepository.findById(game.getPlayer2Id()).orElseThrow(()-> new NotFoundException("Cannot find a player with id:"+ game.getPlayer2Id()));
            gameDetailList.add(new GameDetailsResponseDto(game.getId(),player1,player2,game.getWinner(),game.getGameStatus()));
        }
        return gameDetailList;
    }
}
