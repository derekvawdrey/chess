package service;

import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.interfaces.UserDataAccessInterface;
import dataaccess.manager.DataAccessManager;
import model.AuthData;
import model.LoginRequest;
import model.UserData;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.manager.ServiceManager;

public class AuthServiceTest extends ServiceTestBase {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceTest.class);

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
        AuthData logged_in_user = serviceManager.getService(SessionService.class)
                .login(new LoginRequest("Amongus","sussy"));
        Assertions.assertNotNull(logged_in_user);
    }


    @Test
    @Order(2)
    @DisplayName("Login Failure")
    public void loginFailure() throws DataAccessException {
        AuthData authData = serviceManager.getService(SessionService.class)
                .login(new LoginRequest("usernotrealhomie", "aa"));
        Assertions.assertNull(authData);
    }


    @Test
    @Order(3)
    @DisplayName("Get Auth Data Success")
    public void getAuthDataSuccess() throws DataAccessException {
        SessionService sessionService = serviceManager.getService(SessionService.class);
        AuthData logged_in_user = sessionService.login(new LoginRequest("Amongus","sussy"));
        AuthData getAuthData = sessionService.getAuthData(logged_in_user.authToken());
        Assertions.assertNotNull(getAuthData);
    }

    @Test
    @Order(4)
    @DisplayName("Get Auth Data Failure")
    public void getAuthDataFailure() throws DataAccessException {
        SessionService sessionService = serviceManager.getService(SessionService.class);
        AuthData getAuthData = sessionService.getAuthData("NOT REAL AUTH TOKEN");
        Assertions.assertNull(getAuthData);
    }

    @Test
    @Order(5)
    @DisplayName("Logout success")
    public void logoutSuccess() throws DataAccessException {
        SessionService sessionService = serviceManager.getService(SessionService.class);
        AuthData logged_in_user = sessionService.login(new LoginRequest("Amongus","sussy"));
        AuthData logout_info = sessionService.logout(logged_in_user.authToken());
        Assertions.assertNotNull(logout_info);
    }

    @Test
    @Order(6)
    @DisplayName("Logout Fail")
    public void logoutFail() throws DataAccessException {
        SessionService sessionService = serviceManager.getService(SessionService.class);
        AuthData logout_info = sessionService.logout("rappi");
        Assertions.assertNull(logout_info);
    }

    @Test
    @Order(7)
    @DisplayName("deleteAll Success")
    public void deleteAllSuccess() throws DataAccessException {
        SessionService sessionService = serviceManager.getService(SessionService.class);
        AuthData logged_in_user = sessionService.login(new LoginRequest("Amongus", "sussy"));
        sessionService.deleteAll();
        AuthData authData = sessionService.getAuthData(logged_in_user.authToken());
        Assertions.assertNull(authData);
    }

}
