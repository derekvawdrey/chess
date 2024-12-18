package service;

import dataaccess.DataAccessException;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.manager.DataAccessManager;
import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;

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
     *
     * @param joinGameRequest a JoinGameRequest object
     * @return a GameData object
     * @throws DataAccessException
     */
    public GameData joinGame(JoinGameRequest joinGameRequest) throws DataAccessException {
        GameDataAccessInterface gameDataAccess = this.dataAccessManager.getDataAccess(GameDataAccessInterface.class);
        return gameDataAccess.joinGame(joinGameRequest);
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
