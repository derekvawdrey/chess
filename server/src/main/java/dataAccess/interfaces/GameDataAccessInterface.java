package dataAccess.interfaces;

import model.GameData;
import model.GameListResult;
import model.UserData;

import java.util.List;

public interface GameDataAccessInterface extends BaseDataAccessInterface {

    GameListResult getAllGames();
    GameData getGame(int gameId);
    GameData createGame(GameData gameData);
    GameData joinGame(UserData user);
    void deleteAllGames();
}
