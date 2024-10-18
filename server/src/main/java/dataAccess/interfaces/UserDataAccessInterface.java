package dataAccess.interfaces;

import dataAccess.DataAccessException;
import model.UserData;

public interface UserDataAccessInterface {

    UserData getUser(String username) throws DataAccessException;
    UserData insertUser(UserData user) throws DataAccessException;

    void deleteAllUsers() throws DataAccessException;
}
