package service;

import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.interfaces.UserDataAccessInterface;
import dataaccess.manager.DataAccessManager;
import org.junit.jupiter.api.*;
import service.manager.ServiceManager;

public class AuthServiceTest extends ServiceTestBase {

    @BeforeEach
    public void beforeEach() throws DataAccessException {

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
