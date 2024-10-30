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
        sql += "authToken VARCHAR(255) NOT NULL UNIQUE)";

        this.executeSqlUpdate(sql);
    }

    @Override
    public AuthData insertAuth(AuthData authData) throws DataAccessException {
        String sql = "INSERT INTO sessions VALUES(?,?)";
        try {
            this.executeSqlUpdate(sql, authData.username(), authData.authToken());
        } catch (DataAccessException e) {
            return null;
        }
        return authData;
    }

    @Override
    public AuthData removeAuth(String authToken) throws DataAccessException {
        AuthData authData = getAuth(authToken);

        String sql = "DELETE FROM sessions WHERE authToken = ?";
        this.executeSqlUpdate(sql, authToken);
        return authData;
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        String sql = "SELECT * sessions WHERE authToken = ?";
        this.executeSqlUpdate(sql, authToken);
        // THen ret
        return null;
    }

    @Override
    public void removeAllAuth() throws DataAccessException {
        String sql = "TRUNCATE sessions";
        this.executeSqlUpdate(sql);
    }
}
