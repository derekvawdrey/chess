package service;

import dataAccess.DataAccessException;
import dataAccess.DataAccessType;
import dataAccess.interfaces.GameDataAccessInterface;
import dataAccess.interfaces.SessionDataAccessInterface;
import dataAccess.interfaces.UserDataAccessInterface;
import dataAccess.manager.DataAccessManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import service.manager.ServiceManager;

public class UserServiceTest {

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

}
