package handlers;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import model.GameListResult;
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
    }

    public Object getListOfGames(Request req, Response res) throws DataAccessException {
        GameService gameService = this.serviceManager.getService(GameService.class);
        GameListResult gameListResult = gameService.getAllGames();

        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("games", gameListResult.getGames());

        this.setSuccessHeaders(res);
        return new Gson().toJson(jsonResponse);
    }
}
