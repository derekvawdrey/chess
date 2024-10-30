package dataaccess.interfaces;

import dataaccess.DataAccessException;
import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;

public interface GameDataAccessInterface extends BaseDataAccessInterface {

    GameListResult getAllGames();
    GameData getGame(int gameId);
    GameData joinGame(JoinGameRequest joinGameRequest);
    GameData createGame(GameData gameData) throws DataAccessException;
    void deleteAllGames() throws DataAccessException;
}
