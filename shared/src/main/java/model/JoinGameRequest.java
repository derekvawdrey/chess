package model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public record JoinGameRequest(String playerColor, String username, int gameID) {
    public static JoinGameRequest fromJson(String json) {
        JoinGameRequest joinGameRequest = new Gson().fromJson(json, JoinGameRequest.class);
        if (joinGameRequest == null || !joinGameRequest.isValid()) {
            return null;
        }

        return joinGameRequest;
    }

    private boolean isValid() {
        return playerColor != null && !playerColor.isEmpty() &&
                (playerColor.equals("WHITE") || playerColor.equals("BLACK")) &&
                username != null && !username.isEmpty() &&
                gameID >= 1;
    }
}
