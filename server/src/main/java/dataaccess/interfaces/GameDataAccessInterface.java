package dataaccess.interfaces;

import dataaccess.DataAccessException;
import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;

public interface GameDataAccessInterface extends BaseDataAccessInterface {

    GameListResult getAllGames() throws DataAccessException;
    GameData getGame(int gameId) throws DataAccessException;
    GameData joinGame(JoinGameRequest joinGameRequest) throws DataAccessException;
    GameData createGame(GameData gameData) throws DataAccessException;
    void deleteAllGames() throws DataAccessException;
}
