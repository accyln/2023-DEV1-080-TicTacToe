package com.accyln.tictactoe.mockServices;

import com.accyln.tictactoe.helpers.CalculateWinnerHelper;
import com.accyln.tictactoe.helpers.CheckGameRulesHelper;
import com.accyln.tictactoe.mockRepositories.MockGameRepository;
import com.accyln.tictactoe.mockRepositories.MockPlayerRepository;
import com.accyln.tictactoe.repositories.IGameRepository;
import com.accyln.tictactoe.repositories.IPlayerRepository;
import com.accyln.tictactoe.services.GameService;
import com.accyln.tictactoe.services.IGameService;
import org.springframework.boot.test.mock.mockito.MockBean;

public final class MockServiceFactory {
    @MockBean
    private static IGameService gameService;
    public static IGameService getMockGameService(){
        IGameRepository mockGameRepository = MockGameRepository.getMockGameRepository();
        IPlayerRepository mockPlayerRepository = MockPlayerRepository.getMockPlayerRepository();
        CalculateWinnerHelper calculateWinnerHelper=new CalculateWinnerHelper();
        CheckGameRulesHelper checkGameRulesHelper = new CheckGameRulesHelper();

        gameService=new GameService(mockGameRepository,mockPlayerRepository,calculateWinnerHelper,checkGameRulesHelper);

        return gameService;
    }
}
