package service;

import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.interfaces.UserDataAccessInterface;
import dataaccess.manager.DataAccessManager;
import model.AuthData;
import model.UserData;
import org.eclipse.jetty.server.Authentication;
import org.junit.jupiter.api.*;
import service.manager.ServiceManager;

public class UserServiceTest extends ServiceTestBase {

    @Test
    @Order(1)
    @DisplayName("Create User Success")
    public void createUserSuccess() throws DataAccessException {
        UserService userService = serviceManager.getService(UserService.class);
        AuthData auth = userService.createUser(new UserData(
                "sus",
                "among",
                "us@a.com"
        ));
        Assertions.assertNotNull(auth);
    }

    @Test
    @Order(2)
    @DisplayName("Create User Fail - User Already Exists")
    public void createUserFailUserExists() throws DataAccessException {
        UserService userService = serviceManager.getService(UserService.class);
        AuthData auth = userService.createUser(new UserData(
                "sus",
                "among",
                "us@a.com"
        ));
        AuthData newUser = userService.createUser(new UserData(
                "sus",
                "among",
                "us@a.com"
        ));
        Assertions.assertNull(newUser);
    }

    @Test
    @Order(3)
    @DisplayName("Delete All Users Success")
    public void deleteAllUsersSuccess() throws DataAccessException {
        UserService userService = serviceManager.getService(UserService.class);
        AuthData auth = userService.createUser(new UserData(
                "sus2",
                "among",
                "us@a.com"
        ));
        userService.deleteAll();
        AuthData auth2 = userService.createUser(new UserData(
                "sus2",
                "among",
                "us@a.com"
        ));
        Assertions.assertNotNull(auth2);
    }


}

