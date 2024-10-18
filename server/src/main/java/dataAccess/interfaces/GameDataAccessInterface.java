package dataAccess.interfaces;

import model.GameData;
import model.GameListResult;
import model.JoinGameRequest;
import model.UserData;

import java.util.List;

public interface GameDataAccessInterface extends BaseDataAccessInterface {

    GameListResult getAllGames();
    GameData getGame(int gameId);
    GameData joinGame(JoinGameRequest joinGameRequest);
    GameData createGame(GameData gameData);
    void deleteAllGames();
}
