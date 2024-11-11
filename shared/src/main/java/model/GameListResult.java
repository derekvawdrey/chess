package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public record GameListResult(ArrayList<GameDataResponse> games) {
    public ArrayList<GameDataResponse> getGames() {
        return games;
    }
}
