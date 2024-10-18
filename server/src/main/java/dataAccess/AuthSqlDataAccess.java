package dataAccess;

import dataAccess.interfaces.AuthDataAccessInterface;
import model.AuthData;

public class AuthSqlDataAccess implements AuthDataAccessInterface {

    @Override
    public AuthData insertAuth(AuthData authData) throws DataAccessException {
        return null;
    }

    @Override
    public AuthData removeAuth(AuthData authData) throws DataAccessException {
        return null;
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        return null;
    }

    @Override
    public void removeAllAuth() throws DataAccessException {

    }
}
