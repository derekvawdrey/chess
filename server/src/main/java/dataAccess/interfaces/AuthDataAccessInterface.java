package dataAccess.interfaces;

import dataAccess.DataAccessException;
import model.AuthData;

public interface AuthDataAccessInterface {

    AuthData insertAuth(AuthData authData) throws DataAccessException;
    AuthData removeAuth(AuthData authData) throws DataAccessException;
    AuthData getAuth(AuthData authData) throws DataAccessException;

    void deleteAllAuth() throws DataAccessException;
}
