package model;

import com.google.gson.Gson;

public record JoinGameRequest(String playerColor, String username, int gameID) {
    public static JoinGameRequest fromJson(String json, String username) {
        JoinGameRequest joinGameRequest = new Gson().fromJson(json, JoinGameRequest.class);
        if (joinGameRequest == null || !joinGameRequest.isValid()) {
            return null;
        }

        return new JoinGameRequest(joinGameRequest.playerColor(), username, joinGameRequest.gameID());
    }

    private boolean isValid() {
        return playerColor != null && !playerColor.isEmpty() &&
                (playerColor.equals("WHITE") || playerColor.equals("BLACK")) &&
                username != null && !username.isEmpty() &&
                gameID >= 0;
    }
}
