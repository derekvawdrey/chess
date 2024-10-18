package model;

import com.google.gson.Gson;

public record CreateGameRequest(String gameName) {
    public static CreateGameRequest fromJson(String json) {
        CreateGameRequest createGameRequest = new Gson().fromJson(json, CreateGameRequest.class);
        if (createGameRequest == null || !createGameRequest.isValid()) {
            return null;
        }
        return createGameRequest;
    }

    private boolean isValid() {
        return gameName != null && !gameName.isEmpty();
    }
}
