package service;

import dataAccess.DataAccessException;
import dataAccess.interfaces.GameDataAccessInterface;
import dataAccess.manager.DataAccessManager;
import model.GameListResult;

public class GameService extends BaseService {
    public GameService(DataAccessManager dataAccessManager) {
        super(dataAccessManager);
    }

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
