package service;

import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.interfaces.UserDataAccessInterface;
import dataaccess.manager.DataAccessManager;
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
