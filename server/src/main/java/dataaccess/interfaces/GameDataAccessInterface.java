package dataaccess.interfaces;

import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;

public interface GameDataAccessInterface extends BaseDataAccessInterface {

    GameListResult getAllGames();
    GameData getGame(int gameId);
    GameData joinGame(JoinGameRequest joinGameRequest);
    GameData createGame(GameData gameData);
    void deleteAllGames();
}
