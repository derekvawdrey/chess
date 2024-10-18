package service;

import dataAccess.DataAccessException;
import dataAccess.interfaces.GameDataAccessInterface;
import dataAccess.manager.DataAccessManager;
import model.GameData;
import model.GameListResult;

public class GameService extends BaseService {
    public GameService(DataAccessManager dataAccessManager) {
        super(dataAccessManager);
    }

    public GameData createGame(GameData gameData) throws DataAccessException {
        GameDataAccessInterface gameDataAccess = this.dataAccessManager.getDataAccess(GameDataAccessInterface.class);
        return gameDataAccess.createGame(gameData);
    }

    /**
     * Gets all games.
     * @return
     * @throws DataAccessException
     */
    public GameListResult getAllGames() throws DataAccessException {
        GameDataAccessInterface gameDataAccess = this.dataAccessManager.getDataAccess(GameDataAccessInterface.class);
        return gameDataAccess.getAllGames();
    }

    /**
     * Deletes all game information in the database
     * @throws DataAccessException
     */
    public void deleteAll() throws DataAccessException {
        GameDataAccessInterface gameDataAccess = this.dataAccessManager.getDataAccess(GameDataAccessInterface.class);
        gameDataAccess.deleteAllGames();
    }
}
