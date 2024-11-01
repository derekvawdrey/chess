package dataaccess;

import chess.ChessGame;
import com.google.gson.Gson;
import dataaccess.interfaces.BaseSqlDataAccess;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.manager.DatabaseManager;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class GameSqlDataAccess extends BaseSqlDataAccess implements GameDataAccessInterface {
    public GameSqlDataAccess() throws DataAccessException {
        super();
    }

    @Override
    protected void initalizeDatabaseTables() throws DataAccessException{
        String sql = "CREATE TABLE IF NOT EXISTS games (";
        sql += "id INTEGER PRIMARY KEY AUTO_INCREMENT,";
        sql += "name VARCHAR(255) NOT NULL,";
        sql += "white_username VARCHAR(255) DEFAULT NULL,";
        sql += "black_username VARCHAR(255) DEFAULT NULL,";
        sql += "game TEXT NOT NULL)";

        this.executeSqlUpdate(sql);
    }

    @Override
    public GameListResult getAllGames() throws DataAccessException {
        String sql = "SELECT id, name, white_username, black_username, game FROM games";
        ArrayList<GameDataResponse> games = new ArrayList<>();

        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement(sql)) {
                try (var resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String whiteUsername = resultSet.getString("white_username");
                        String blackUsername = resultSet.getString("black_username");
                        games.add(new GameDataResponse(id, whiteUsername, blackUsername, name));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving games: " + e.getMessage());
        }
        return new GameListResult(games);
    }

    @Override
    public GameData getGame(int gameId) throws DataAccessException {
        String sql = "SELECT id, name, white_username, black_username, game FROM games WHERE id = ?";
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, gameId);
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String whiteUsername = resultSet.getString("white_username");
                        String blackUsername = resultSet.getString("black_username");
                        ChessGame game = this.deserializeGameData(resultSet.getString("game"));
                        return new GameData(id,whiteUsername,blackUsername,name,game);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return null;
    }

    @Override
    public GameData joinGame(JoinGameRequest joinGameRequest) throws DataAccessException {
        String sql;
        if (joinGameRequest.playerColor().equals("WHITE")) {
            sql = "UPDATE games SET white_username = ? WHERE id = ? AND white_username IS NULL";
        } else {
            sql = "UPDATE games SET black_username = ? WHERE id = ? AND black_username IS NULL";
        }

        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, joinGameRequest.username());
                preparedStatement.setInt(2, joinGameRequest.gameID());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    return getGame(joinGameRequest.gameID());
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error joining game: " + e.getMessage());
        }
    }

    @Override
    public GameData createGame(GameData gameData) throws DataAccessException {
        String sql = "INSERT INTO games (name, white_username, black_username, game) VALUES(?,?,?,?)";
        int gameId = this.executeSqlUpdateGetId(sql,
                gameData.gameName(),
                null,
                null,
                new Gson().toJson(gameData.game(), ChessGame.class)
        );
        return getGame(gameId);
    }

    @Override
    public void deleteAllGames() throws DataAccessException {
        String sql = "TRUNCATE games";
        this.executeSqlUpdate(sql);
    }


    private ChessGame deserializeGameData(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, ChessGame.class);
    }


}
