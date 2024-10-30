package dataaccess;

import dataaccess.interfaces.BaseSqlDataAccess;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.manager.DatabaseManager;
import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;

import java.sql.SQLException;

public class GameSqlDataAccess extends BaseSqlDataAccess implements GameDataAccessInterface {
    public GameSqlDataAccess() throws DataAccessException {
        super();
    }

    @Override
    protected void initalizeDatabaseTables() throws DataAccessException{
        String sql = "CREATE TABLE IF NOT EXISTS games (";
        sql += "id INTEGER PRIMARY KEY AUTO_INCREMENT,";
        sql += "name VARCHAR(255) NOT NULL,";
        sql += "white_username VARCHAR(255) DEFAULT NULL,";
        sql += "black_username VARCHAR(255) DEFAULT NULL,";
        sql += "game TEXT DEFAULT NULL)";

        this.executeSql(sql);
    }

    @Override
    public GameListResult getAllGames() {
        return null;
    }

    @Override
    public GameData getGame(int gameId) {
        return null;
    }

    @Override
    public GameData joinGame(JoinGameRequest joinGameRequest) {
        return null;
    }

    @Override
    public GameData createGame(GameData gameData) {
        return null;
    }

    @Override
    public void deleteAllGames() {

    }
}
