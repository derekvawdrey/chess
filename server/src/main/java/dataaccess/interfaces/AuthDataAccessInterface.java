package dataaccess.interfaces;

import dataaccess.DataAccessException;
import model.AuthData;

public interface AuthDataAccessInterface {

    AuthData createAuth(AuthData authData) throws DataAccessException;
    AuthData getAuth(int id) throws DataAccessException;

    void deleteAllAuth() throws DataAccessException;
}
