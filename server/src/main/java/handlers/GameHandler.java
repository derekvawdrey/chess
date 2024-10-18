package handlers;

import chess.ChessGame;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import model.CreateGameRequest;
import model.GameData;
import model.GameListResult;
import model.LoginRequest;
import service.GameService;
import service.UserService;
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
    }

    public Object createGame(Request req, Response res) throws DataAccessException, ExceptionHandler {
        GameService gameService = this.serviceManager.getService(GameService.class);
        CreateGameRequest createGameRequest = CreateGameRequest.fromJson(req.body());

        if(createGameRequest == null){
            throw new ExceptionHandler("bad request", 400);
        }

        GameData gameData = new GameData(0, null,null, createGameRequest.gameName(), new ChessGame());
        gameService.createGame(gameData);

        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("gameID", gameData.gameId());

        this.setSuccessHeaders(res);
        return new Gson().toJson(jsonResponse);
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
