package client;

import core.ServerFacade;
import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.manager.DataAccessManager;
import exception.ResponseException;
import model.AuthData;
import model.LoginRequest;
import model.UserData;
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

    }

    @Test
    @DisplayName("Logout - negative")
    public void logoutNegativeTest() throws ResponseException {

    }

    @Test
    @DisplayName("List Games - positive")
    public void listGamesPositiveTest() throws ResponseException {

    }

    @Test
    @DisplayName("List Games - negative")
    public void listGamesNegativeTest() throws ResponseException {

    }

    @Test
    @DisplayName("Join Game - positive")
    public void joinGamePositiveTest() throws ResponseException {

    }

    @Test
    @DisplayName("Join Game - negative")
    public void joinGameNegativeTest() throws ResponseException {

    }

    @Test
    @DisplayName("Create Game - positive")
    public void createGamePositiveTest() throws ResponseException {

    }

    @Test
    @DisplayName("Create Game - negative")
    public void createGameNegativeTest() throws ResponseException {

    }


}
