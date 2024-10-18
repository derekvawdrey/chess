package dataAccess.interfaces;

import dataAccess.DataAccessException;
import model.UserData;

public interface UserDataAccessInterface extends BaseDataAccessInterface{

    UserData getUser(String username) throws DataAccessException;
    UserData insertUser(UserData user) throws DataAccessException;
    void deleteUser(String username) throws DataAccessException;
    void deleteAllUsers() throws DataAccessException;
}
