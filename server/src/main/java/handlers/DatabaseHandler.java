package handlers;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import service.AuthService;
import service.GameService;
import service.UserService;
import service.manager.ServiceManager;
import spark.Request;
import spark.Response;
import spark.Spark;

public class DatabaseHandler extends BaseHandler{
    /**
     * Default constructor for handlers
     *
     * @param serviceManager
     */
    public DatabaseHandler(ServiceManager serviceManager) {
        super(serviceManager);
        this.root = "/db";
    }

    @Override
    public void initHandler() {
        Spark.get(this.root + "/oats", (req, res) -> {res.redirect("/oats.html"); return null;});
        Spark.delete(this.root, this::clearData);
    }

    public Object clearData(Request req, Response res) throws DataAccessException {
        this.serviceManager.getService(AuthService.class).deleteAll();
        this.serviceManager.getService(GameService.class).deleteAll();
        this.serviceManager.getService(UserService.class).deleteAll();
        res.status(200);
        res.type("application/json");
        return new Gson().toJson(new Object());
    }
}
