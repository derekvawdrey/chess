package dataAccess.interfaces;

import dataAccess.DataAccessException;
import model.AuthData;

public interface AuthDataAccessInterface extends BaseDataAccessInterface{

    AuthData insertAuth(AuthData authData) throws DataAccessException;
    AuthData removeAuth(String authToken) throws DataAccessException;
    AuthData getAuth(String authToken) throws DataAccessException;

    void removeAllAuth() throws DataAccessException;
}
