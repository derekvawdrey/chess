package dataaccess;

import dataaccess.interfaces.BaseSqlDataAccess;
import dataaccess.interfaces.GameDataAccessInterface;
import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;

public class GameSqlDataAccess extends BaseSqlDataAccess implements GameDataAccessInterface {
    public GameSqlDataAccess(){
        super();

    }

    @Override
    protected boolean initalizeDatabaseTables(){
        return false;
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
