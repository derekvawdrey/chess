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

public class GameServiceTest extends ServiceTestBase {

    @BeforeEach
    public void beforeEach() throws DataAccessException {

    }

    @AfterAll
    public static void afterEach() {

    }
}
