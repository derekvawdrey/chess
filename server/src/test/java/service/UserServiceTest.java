package service;

import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.interfaces.UserDataAccessInterface;
import dataaccess.manager.DataAccessManager;
import org.junit.jupiter.api.*;
import service.manager.ServiceManager;

public class UserServiceTest extends ServiceTestBase {

    @Test
    @Order(1)
    @DisplayName("Create User Success")
    public void createUserSuccess() throws DataAccessException {

    }

    @Test
    @Order(2)
    @DisplayName("Create User Fail - User Already Exists")
    public void createUserFailUserExists() throws DataAccessException {

    }

    @Test
    @Order(3)
    @DisplayName("Delete All Users Success")
    public void deleteAllUsersSuccess() throws DataAccessException {

    }

    @Test
    @Order(4)
    @DisplayName("Delete All Users Fail")
    public void deleteAllUsersFail() throws DataAccessException {

    }


}

