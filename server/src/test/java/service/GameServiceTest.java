package service;

import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.interfaces.UserDataAccessInterface;
import dataaccess.manager.DataAccessManager;
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

    }

    @Test
    @Order(2)
    @DisplayName("Create Game Fail")
    public void createGameFail() throws DataAccessException {

    }

    @Test
    @Order(3)
    @DisplayName("Get All Games Success")
    public void getAllGamesSuccess() throws DataAccessException {

    }

    @Test
    @Order(4)
    @DisplayName("Get All Games Fail")
    public void getAllGamesFail() throws DataAccessException {

    }

    @Test
    @Order(5)
    @DisplayName("Join Game Success")
    public void joinGameSuccess() throws DataAccessException {

    }

    @Test
    @Order(6)
    @DisplayName("Join Game Fail")
    public void joinGameFail() throws DataAccessException {

    }

    @Test
    @Order(7)
    @DisplayName("Delete All Success")
    public void deleteAllSuccess() throws DataAccessException {

    }

}
