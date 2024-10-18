package model;

public record GameDataResponse(int gameId,
                               String whiteUsername,
                               String blackUsername,
                               String gameName) {
}
