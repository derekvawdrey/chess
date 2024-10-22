package dataaccess.interfaces;

import dataaccess.DataAccessException;
import model.AuthData;

public interface SessionDataAccessInterface extends BaseDataAccessInterface{

    AuthData insertAuth(AuthData authData) throws DataAccessException;
    AuthData removeAuth(String authToken) throws DataAccessException;
    AuthData getAuth(String authToken) throws DataAccessException;

    void removeAllAuth() throws DataAccessException;
}
