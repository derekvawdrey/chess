package dataaccess;

import dataaccess.interfaces.BaseSqlDataAccess;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.interfaces.SessionDataAccessInterface;
import model.AuthData;
import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;

public class SessionSqlDataAccess extends BaseSqlDataAccess implements SessionDataAccessInterface {
    public SessionSqlDataAccess(){
        super();

    }

    @Override
    protected boolean initalizeDatabaseTables(){
        return false;
    }

    @Override
    public AuthData insertAuth(AuthData authData) throws DataAccessException {
        return null;
    }

    @Override
    public AuthData removeAuth(String authToken) throws DataAccessException {
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
