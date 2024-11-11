package client;

import core.ServerFacade;
import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.manager.DataAccessManager;
import exception.ResponseException;
import model.*;
import org.junit.jupiter.api.*;
import server.Server;
import service.UserService;
import service.manager.ServiceManager;


public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade serverFacade;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        serverFacade = new ServerFacade(port);
    }

    @BeforeEach
    public void setUp() throws ResponseException {
        serverFacade.clearDatabase();
    }


    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @Test
    @DisplayName("Register - positive")
    public void registerPositiveTest() throws ResponseException {
        AuthData userData = serverFacade.register(new UserData("a","a","a"));
        Assertions.assertNotNull(userData);
    }

    @Test
    @DisplayName("Register - negative")
    public void registerNegativeTest() throws ResponseException {
        UserData userData = new UserData("a", "a", "a");

        try {
            serverFacade.register(userData);
            serverFacade.register(userData);
            Assertions.fail("Womp WOMP should've failed, because you can't have two users with username 'a'");
        } catch (ResponseException e) {
            Assertions.assertEquals(403, e.getStatusCode(), "Status code 403 is expected for already existing registration");
        }
    }

    @Test
    @DisplayName("Login - positive")
    public void loginPositiveTest() throws ResponseException {
        UserData userData = new UserData("a", "a", "a");
        serverFacade.register(userData);
        serverFacade.login(new LoginRequest("a","a"));
    }

    @Test
    @DisplayName("Login - negative")
    public void loginNegativeTest() throws ResponseException {
        try{
            serverFacade.login(new LoginRequest("a","a"));
            Assertions.fail("Homie thought you could login with wrong combos, but was right");
        } catch (ResponseException e) {
            Assertions.assertEquals(401, e.getStatusCode(), "Status code 401 expected, because this user doesn't exist");
        }

    }

    @Test
    @DisplayName("Logout - positive")
    public void logoutPositiveTest() throws ResponseException {
        UserData userData = new UserData("a", "a", "a");
        AuthData authData = serverFacade.register(userData);
        serverFacade.logout(authData.authToken());
        try{
            serverFacade.listGames(authData.authToken());
            Assertions.fail("Auth token should be deleted.");
        } catch (ResponseException e) {
            Assertions.assertEquals(401, e.getStatusCode(), "Status code 401 expected, because the auth no longer exists");
        }
    }

    @Test
    @DisplayName("Logout - negative")
    public void logoutNegativeTest() throws ResponseException {
        try{
            serverFacade.logout("fake auth token");
            Assertions.fail("Auth token should be deleted.");
        } catch (ResponseException e) {
            Assertions.assertEquals(401, e.getStatusCode(), "Status code 401 expected, because the auth never existed.");
        }
    }

    @Test
    @DisplayName("List Games - positive")
    public void listGamesPositiveTest() throws ResponseException {
        UserData userData = new UserData("a", "a", "a");
        AuthData authData = serverFacade.register(userData);

        GameListResult games = serverFacade.listGames(authData.authToken());
        Assertions.assertEquals(0, games.getGames().size());
    }

    @Test
    @DisplayName("List Games - negative")
    public void listGamesNegativeTest() throws ResponseException {
        try{
            serverFacade.listGames("");
            Assertions.fail("Shouldn't be able to access without valid auth token.");
        } catch (ResponseException e) {
            Assertions.assertEquals(401, e.getStatusCode(), "Status code 401 expected, because the auth never existed.");
        }
    }

    @Test
    @DisplayName("Join Game - positive and negative")
    public void joinGamePositiveTest() throws ResponseException {
        UserData userData = new UserData("a", "a", "a");
        AuthData authData = serverFacade.register(userData);

        serverFacade.createGame(new CreateGameRequest("newgame"), authData.authToken());

        Assertions.assertDoesNotThrow(() -> serverFacade.joinGame(new JoinGameRequest(
                "WHITE",
                "a",
                1
        ), authData.authToken()));

        Assertions.assertThrows(ResponseException.class, () -> serverFacade.joinGame(new JoinGameRequest(
                "WHITE",
                "a",
                1
        ), authData.authToken()));
    }

    @Test
    @DisplayName("Join Game - negative 2")
    public void joinGameNegativeTwoTest() throws ResponseException {
        UserData userData = new UserData("a", "a", "a");
        AuthData authData = serverFacade.register(userData);

        serverFacade.createGame(new CreateGameRequest("newgame"), authData.authToken());


    }

    @Test
    @DisplayName("Join Game - negative")
    public void joinGameNegativeTest() throws ResponseException {
        try{
            serverFacade.joinGame(new JoinGameRequest("WHITE", "a", 2), "");
            Assertions.fail("Shouldn't be able to access without valid auth token.");
        } catch (ResponseException e) {
            Assertions.assertEquals(401, e.getStatusCode(), "Status code 401 expected, because the auth never existed.");
        }
    }

    @Test
    @DisplayName("Create Game - positive")
    public void createGamePositiveTest() throws ResponseException {
        UserData userData = new UserData("a", "a", "a");
        AuthData authData = serverFacade.register(userData);

        serverFacade.createGame(new CreateGameRequest("newgame"), authData.authToken());
        GameListResult games = serverFacade.listGames(authData.authToken());
        Assertions.assertEquals(1, games.getGames().size());
    }

    @Test
    @DisplayName("Create Game - negative")
    public void createGameNegativeTest() throws ResponseException {
        UserData userData = new UserData("a", "a", "a");
        AuthData authData = serverFacade.register(userData);
        try {
            serverFacade.createGame(new CreateGameRequest(""), authData.authToken());
            Assertions.fail("Shouldn't be able to create a blank game");
        } catch (ResponseException e) {
            Assertions.assertEquals(400, e.getStatusCode(), "Status code 400 expected, because you cant create a game with an empty name.");
        }
    }


}
