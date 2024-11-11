package handlers;

import chess.ChessGame;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dataaccess.DataAccessException;
import model.*;
import service.GameService;
import service.SessionService;
import service.manager.ServiceManager;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

public class GameHandler extends BaseHandler{
    /**
     * Default constructor for handlers
     *
     * @param serviceManager
     */
    public GameHandler(ServiceManager serviceManager) {
        super(serviceManager);
        this.root = "/game";
    }

    @Override
    public void initHandler() {
        Spark.get(this.root, verifyAuth(this::getListOfGames));
        Spark.post(this.root, verifyAuth(this::createGame));
        Spark.put(this.root, verifyAuth(this::joinGame));
    }

    public Object joinGame(Request req, Response res) throws ExceptionHandler, DataAccessException {
        GameService gameService = this.serviceManager.getService(GameService.class);
        SessionService sessionService = this.serviceManager.getService(SessionService.class);
        String authToken = req.headers("Authorization");

        AuthData authData = sessionService.getAuthData(authToken);
        String username = authData.username();

        JsonObject jsonObject = new Gson().fromJson(req.body(), JsonObject.class);
        jsonObject.addProperty("username", username);
        String modifiedJson = new Gson().toJson(jsonObject);


        JoinGameRequest joinGameRequest = JoinGameRequest.fromJson(modifiedJson);

        if(joinGameRequest == null) {
            throw new ExceptionHandler("bad request", 400);
        }

        GameData game = gameService.joinGame(joinGameRequest);
        if(game == null){
            throw new ExceptionHandler("already taken", 409);
        }

        this.setSuccessHeaders(res);
        Map<String, Object> jsonResponse = new HashMap<>();
        return new Gson().toJson(jsonResponse);
    }

    public Object createGame(Request req, Response res) throws DataAccessException, ExceptionHandler {
        GameService gameService = this.serviceManager.getService(GameService.class);
        CreateGameRequest createGameRequest = CreateGameRequest.fromJson(req.body());

        if(createGameRequest == null){
            throw new ExceptionHandler("bad request", 400);
        }

        GameData gameData = new GameData(0, null, null, createGameRequest.gameName(), new ChessGame());
        GameData newGame = gameService.createGame(gameData);

        this.setSuccessHeaders(res);
        return new Gson().toJson(new CreateGameResponse(newGame.gameId()));
    }

    /**
     * Gets a list of games in the database
     * @param req
     * @param res
     * @return
     * @throws DataAccessException
     */
    public Object getListOfGames(Request req, Response res) throws DataAccessException {
        GameService gameService = this.serviceManager.getService(GameService.class);
        GameListResult gameListResult = gameService.getAllGames();

        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("games", gameListResult.getGames());

        this.setSuccessHeaders(res);
        return new Gson().toJson(jsonResponse);
    }
}
