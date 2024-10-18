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
    int idCounter = 1;

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
        int gameId = joinGameRequest.gameID();
        if (!gameDataMap.containsKey(gameId)) {
            return null;
        }

        GameData gameData = gameDataMap.get(gameId);
        String playerColor = joinGameRequest.playerColor();

        if ("WHITE".equals(playerColor)) {
            return joinGameAsWhite(joinGameRequest, gameData);
        } else {
            return joinGameAsBlack(joinGameRequest, gameData);
        }
    }
    // TODO: MAKE THIS BETTER BECAUSE PROBABLY SHOULDNT BE HERE LOL
    private GameData joinGameAsWhite(JoinGameRequest joinGameRequest, GameData gameData) {
        if (gameData.whiteUsername().isEmpty()) {
            GameData newGameData = createNewGameData(gameData, joinGameRequest.username(), gameData.blackUsername());
            gameDataMap.put(newGameData.gameId(), newGameData);
            return newGameData;
        }
        return null;
    }

    private GameData joinGameAsBlack(JoinGameRequest joinGameRequest, GameData gameData) {
        if (gameData.blackUsername().isEmpty()) {
            GameData newGameData = createNewGameData(gameData, gameData.whiteUsername(), joinGameRequest.username());
            gameDataMap.put(newGameData.gameId(), newGameData);
            return newGameData;
        }
        return null;
    }

    private GameData createNewGameData(GameData gameData, String whiteUsername, String blackUsername) {
        return new GameData(gameData.gameId(), whiteUsername, blackUsername, gameData.gameName(), gameData.game());
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