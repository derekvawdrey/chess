package service;

import chess.ChessGame;
import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.interfaces.UserDataAccessInterface;
import dataaccess.manager.DataAccessManager;
import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;
import org.junit.jupiter.api.*;
import service.manager.ServiceManager;

public class GameServiceTest extends ServiceTestBase {

    @BeforeEach
    public void beforeEach() throws DataAccessException {

    }

    @AfterAll
    public static void afterEach() {

    }

    @Test
    @Order(1)
    @DisplayName("Create Game Success")
    public void createGameSuccess() throws DataAccessException {
        GameService gameService = serviceManager.getService(GameService.class);
        GameData gameData = gameService.createGame(
                new GameData(1,"","","aaa",new ChessGame())
        );
        Assertions.assertNotNull(gameData);
    }

    @Test
    @Order(3)
    @DisplayName("Get All Games Success")
    public void getAllGamesSuccess() throws DataAccessException {
        GameService gameService = serviceManager.getService(GameService.class);
        gameService.createGame(
                new GameData(1,"","","aaa", new ChessGame())
        );
        GameListResult list = gameService.getAllGames();
        Assertions.assertFalse(list.getGames().isEmpty());
    }

    @Test
    @Order(5)
    @DisplayName("Join Game Fail")
    public void joinGameFail() throws DataAccessException {
        GameService gameService = serviceManager.getService(GameService.class);
        GameData data = gameService.createGame(
                new GameData(1,"AlreadyAssigned","YEEEE","aaa", new ChessGame())
        );
        GameData newData = gameService.joinGame(
            new JoinGameRequest("WHITE", "sussy", data.gameId())
        );
        Assertions.assertNull(newData);
    }

    @Test
    @Order(6)
    @DisplayName("Join Game Success")
    public void joinGameSuccess() throws DataAccessException {
        GameService gameService = serviceManager.getService(GameService.class);
        GameData data = gameService.createGame(
                new GameData(1,null,"YEEEE","aaa", new ChessGame())
        );
        GameData newData = gameService.joinGame(
                new JoinGameRequest("WHITE", "sussy", data.gameId())
        );
        Assertions.assertNull(newData);
    }

    @Test
    @Order(7)
    @DisplayName("Delete All Success")
    public void deleteAllSuccess() throws DataAccessException {
        GameService gameService = serviceManager.getService(GameService.class);
        GameData data = gameService.createGame(
                new GameData(1,null,"YEEEE","aaa", new ChessGame())
        );
        gameService.deleteAll();
        GameListResult list = gameService.getAllGames();
        Assertions.assertTrue(list.getGames().isEmpty());
    }

}
