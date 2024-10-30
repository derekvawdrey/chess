package dataaccess;

import dataaccess.interfaces.BaseSqlDataAccess;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.manager.DatabaseManager;
import model.AuthData;
import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;

import java.sql.SQLException;

public class SessionSqlDataAccess extends BaseSqlDataAccess implements SessionDataAccessInterface {
    public SessionSqlDataAccess() throws DataAccessException{
        super();

    }

    @Override
    protected void initalizeDatabaseTables() throws DataAccessException{
        String sql = "CREATE TABLE IF NOT EXISTS sessions (";
        sql += "id INTEGER PRIMARY KEY AUTO_INCREMENT,";
        sql += "username VARCHAR(255) NOT NULL,";
        sql += "authToken VARCHAR(255) NOT NULL)";

        this.executeSql(sql);
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
