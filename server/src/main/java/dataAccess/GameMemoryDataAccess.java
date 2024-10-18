package dataAccess;

import dataAccess.interfaces.GameDataAccessInterface;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMemoryDataAccess implements GameDataAccessInterface {
    // Game id, GameData
    Map<Integer, GameData> gameDataMap = new HashMap<>();
    int idCounter = 0;

    @Override
    public GameListResult getAllGames() {
        List<GameDataResponse> gameDataResponses = new ArrayList<>();
        for (GameData gameData : gameDataMap.values()) {
            gameDataResponses.add(new GameDataResponse(
                    gameData.gameId(),
                    gameData.whiteUsername(),
                    gameData.blackUsername(),
                    gameData.gameName()
            ));
        }
        return new GameListResult(new ArrayList<>(gameDataResponses));
    }

    @Override
    public GameData getGame(int gameId) {
        if(gameDataMap.containsKey(gameId)) {
            return gameDataMap.get(gameId);
        }
        return null;
    }

    @Override
    public GameData joinGame(JoinGameRequest joinGameRequest) {
        if(!gameDataMap.containsKey(joinGameRequest.gameID())){
            return null;
        }
        GameData gameData = gameDataMap.get(joinGameRequest.gameID());
        

    }

    @Override
    public GameData createGame(GameData gameData) {
        GameData newGameData = new GameData(idCounter, gameData.blackUsername(), gameData.whiteUsername(), gameData.gameName(), gameData.game());
        gameDataMap.put(idCounter, newGameData);
        idCounter ++;
        return newGameData;
    }

    @Override
    public void deleteAllGames() {
        gameDataMap.clear();
    }
}
