package dataAccess;

import dataAccess.interfaces.GameDataAccessInterface;
import model.GameData;
import model.GameListResult;
import model.UserData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMemoryDataAccess implements GameDataAccessInterface {
    // Game name, GameData
    Map<Integer, GameData> gameDataMap = new HashMap<>();

    @Override
    public GameListResult getAllGames() {
        return new GameListResult(new ArrayList<>(gameDataMap.values()));
    }

    @Override
    public GameData getGame(int gameId) {
        if(gameDataMap.containsKey(gameId)) {
            return gameDataMap.get(gameId);
        }
        return null;
    }

    @Override
    public GameData joinGame(UserData user) {
        return null;
    }

    @Override
    public void deleteAllGames() {
        gameDataMap.clear();
    }
}
