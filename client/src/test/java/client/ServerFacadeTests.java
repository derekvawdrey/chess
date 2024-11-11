package client;

import core.ServerFacade;
import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.manager.DataAccessManager;
import exception.ResponseException;
import model.AuthData;
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
    public void registerPositiveTest() {

    }

    @Test
    @DisplayName("Register - negative")
    public void registerNegativeTest() {

    }

    @Test
    @DisplayName("Login - positive")
    public void loginPositiveTest() {

    }

    @Test
    @DisplayName("Login - negative")
    public void loginNegativeTest() {

    }

    @Test
    @DisplayName("Logout - positive")
    public void logoutPositiveTest() {

    }

    @Test
    @DisplayName("Logout - negative")
    public void logoutNegativeTest() {

    }
    
    @Test
    @DisplayName("List Games - positive")
    public void listGamesPositiveTest() {

    }

    @Test
    @DisplayName("List Games - negative")
    public void listGamesNegativeTest() {

    }

    @Test
    @DisplayName("Join Game - positive")
    public void joinGamePositiveTest() {

    }

    @Test
    @DisplayName("Join Game - negative")
    public void joinGameNegativeTest() {

    }

    @Test
    @DisplayName("Create Game - positive")
    public void createGamePositiveTest() {

    }

    @Test
    @DisplayName("Create Game - negative")
    public void createGameNegativeTest() {

    }


}
