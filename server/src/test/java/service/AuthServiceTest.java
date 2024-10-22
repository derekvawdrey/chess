package service;

import dataAccess.DataAccessException;
import dataAccess.DataAccessType;
import dataAccess.interfaces.GameDataAccessInterface;
import dataAccess.interfaces.SessionDataAccessInterface;
import dataAccess.interfaces.UserDataAccessInterface;
import dataAccess.manager.DataAccessManager;
import org.junit.jupiter.api.*;
import service.manager.ServiceManager;

public class AuthServiceTest {

    public static DataAccessManager dataAccessManager;
    public static ServiceManager serviceManager;


    @BeforeAll
    public static void setUp() {
        dataAccessManager = new DataAccessManager(DataAccessType.MEMORY_DATA_ACCESS);
        serviceManager = new ServiceManager(dataAccessManager);
    }

    @BeforeEach
    public void beforeEach() throws DataAccessException {
        dataAccessManager.getDataAccess(GameDataAccessInterface.class).deleteAllGames();
        dataAccessManager.getDataAccess(SessionDataAccessInterface.class).removeAllAuth();
        dataAccessManager.getDataAccess(UserDataAccessInterface.class).deleteAllUsers();
    }

    @AfterAll
    public static void afterEach() {

    }

    @Test
    @Order(1)
    @DisplayName("Login Success")
    public void loginSuccess() throws DataAccessException {

    }


    @Test
    @Order(2)
    @DisplayName("Login Failure")
    public void loginFailure() throws DataAccessException {

    }


    @Test
    @Order(3)
    @DisplayName("Get Auth Data Success")
    public void getAuthDataSuccess() throws DataAccessException {

    }

    @Test
    @Order(4)
    @DisplayName("Get Auth Data Failure")
    public void getAuthDataFailure() throws DataAccessException {

    }

    @Test
    @Order(5)
    @DisplayName("Logout success")
    public void logoutSuccess() throws DataAccessException {

    }

    @Test
    @Order(6)
    @DisplayName("Logout Fail")
    public void logoutFail() throws DataAccessException {

    }

    @Test
    @Order(7)
    @DisplayName("deleteAll Success")
    public void deleteAllSuccess() throws DataAccessException {

    }

    @Test
    @Order(8)
    @DisplayName("deleteAll Fail")
    public void deleteAllFail() throws DataAccessException {
        
    }

}
