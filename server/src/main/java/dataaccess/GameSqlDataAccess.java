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

        this.executeSqlUpdate(sql);
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
    public GameData createGame(GameData gameData) throws DataAccessException {
        String sql = "INSERT INTO games (name, white_username, black_username, game) VALUES(?,?,?,?)";
        this.executeSqlUpdate(sql,
                gameData.gameName(),
                gameData.blackUsername(),
                gameData.whiteUsername(),
                gameData.game()
        );
        return gameData;
    }

    @Override
    public void deleteAllGames() throws DataAccessException {
        String sql = "TRUNCATE games";
        this.executeSqlUpdate(sql);
    }
}
