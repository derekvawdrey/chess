package dataaccess.interfaces;

import dataaccess.DataAccessException;
import model.UserData;

public interface UserDataAccessInterface {

    UserData getUser(String username) throws DataAccessException;


    void deleteAllUsers() throws DataAccessException;
}
