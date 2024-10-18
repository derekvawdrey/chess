package service;

import dataAccess.DataAccessException;
import dataAccess.interfaces.GameDataAccessInterface;
import dataAccess.manager.DataAccessManager;

public class GameService extends BaseService {
    public GameService(DataAccessManager dataAccessManager) {
        super(dataAccessManager);
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
