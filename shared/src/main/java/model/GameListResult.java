package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public record GameListResult(ArrayList<GameData> gameDataList) {
    public ArrayList<GameData> getGames() {
        return gameDataList;
    }
}
