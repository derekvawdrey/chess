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


    @Test
    @Order(2)
    @DisplayName("Login Failure")


    @Test
    @Order(3)
    @DisplayName("Get Auth Data Success")

    @Test
    @Order(4)
    @DisplayName("Get Auth Data Failure")

    @Test
    @Order(5)
    @DisplayName("Normal User Login")

    @Test
    @Order(6)
    @DisplayName("Normal User Login")

    @Test
    @Order(7)
    @DisplayName("Normal User Login")

    @Test
    @Order(8)
    @DisplayName("Normal User Login")

}
