package dataaccess;

import chess.ChessGame;
import dataaccess.interfaces.BaseDataAccessInterface;
import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameDataAccessTests{

    GameSqlDataAccess gameDataAccess;

    @BeforeEach
    public void setUp() throws DataAccessException {
        gameDataAccess = new GameSqlDataAccess();
        gameDataAccess.initalizeDatabaseTables();
        gameDataAccess.deleteAllGames();
    }

    @Test
    @DisplayName("Initialize Database Tables")
    public void testInitializeDatabaseTablesPositive() {
        Assertions.assertDoesNotThrow(() -> gameDataAccess.initalizeDatabaseTables());
    }

    @Test
    @DisplayName("Get All Games - Positive")
    public void testGetAllGamesPositive() throws DataAccessException {
        gameDataAccess.createGame(
                new GameData(1,
                        null,
                        null,
                        "TestGame",
                        new ChessGame()));
        GameListResult games = gameDataAccess.getAllGames();
        Assertions.assertFalse(games.getGames().isEmpty(), "Game list should not be empty");
    }

    @Test
    @DisplayName("Get All Games - Negative")
    public void testGetAllGamesNegative() throws DataAccessException {
        GameListResult games = gameDataAccess.getAllGames();
        Assertions.assertTrue(games.getGames().isEmpty(), "Game list should be empty");
    }

    @Test
    @DisplayName("Get Game by ID - Positive")
    public void testGetGamePositive() throws DataAccessException {
        GameData createdGame = gameDataAccess.createGame(
            new GameData(
                1,
                null,
                null,
                "Game1",
                new ChessGame()
            )
        );
        GameData fetchedGame = gameDataAccess.getGame(createdGame.gameId());
        Assertions.assertNotNull(fetchedGame, "Fetched game should not be null");
    }

    @Test
    @DisplayName("Get Game by ID - Negative")
    public void testGetGameNegative() throws DataAccessException {
        GameData fetchedGame = gameDataAccess.getGame(9999);
        Assertions.assertNull(fetchedGame, "Fetched game should be null for a non-existent ID");
    }

    @Test
    @DisplayName("Join Game - Positive")
    public void testJoinGamePositive() throws DataAccessException {
        GameData createdGame = gameDataAccess.createGame(
                new GameData(
                        1,
                        null,
                        null,
                        "Game1",
                        new ChessGame()
                )
        );
        JoinGameRequest request = new JoinGameRequest("WHITE", "Player1", createdGame.gameId());
        GameData updatedGame = gameDataAccess.joinGame(request);
        Assertions.assertNotNull(updatedGame, "Game should be joined successfully");
        Assertions.assertEquals("Player1", updatedGame.whiteUsername(), "White player username should match");
    }

    @Test
    @DisplayName("Join Game - Negative")
    public void testJoinGameNegative() throws DataAccessException {
        GameData createdGame = gameDataAccess.createGame(
                new GameData(
                        1,
                        "WhitePlayer",
                        "BlackPlayer",
                        "Game1",
                        new ChessGame()
                )
        );
        JoinGameRequest request = new JoinGameRequest("WHITE", "AnotherPlayer", createdGame.gameId());
        GameData updatedGame = gameDataAccess.joinGame(request);
        Assertions.assertNull(updatedGame, "Game should not be joined if the spot is already taken");
    }

    @Test
    @DisplayName("Create Game - Positive")
    public void testCreateGamePositive() throws DataAccessException {
        GameData createdGame = gameDataAccess.createGame(
                new GameData(
                        1,
                        null,
                        null,
                        "NewGame",
                        new ChessGame()
                )
        );
        Assertions.assertNotNull(createdGame, "Created game should not be null");
        Assertions.assertEquals("NewGame", createdGame.gameName(), "Game name should match");
    }

    @Test
    @DisplayName("Create Game - Negative")
    public void testCreateGameNegative() {
        GameData gameData = null;
        Assertions.assertThrows(NullPointerException.class, () ->
                gameDataAccess.createGame(gameData),
                "Should throw an exception when game data is null"
        );
    }

    @Test
    @DisplayName("Delete All Games")
    public void testDeleteAllGamesPositive() throws DataAccessException {
        GameData createdGame = gameDataAccess.createGame(
                new GameData(
                        1,
                        null,
                        null,
                        "Game1",
                        new ChessGame()
                )
        );
        gameDataAccess.deleteAllGames();
        GameListResult games = gameDataAccess.getAllGames();
        Assertions.assertTrue(games.getGames().isEmpty(), "Game list should be empty after deletion");
    }

}
