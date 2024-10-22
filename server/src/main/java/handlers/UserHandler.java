package handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;
import service.UserService;
import service.manager.ServiceManager;
import spark.Request;
import spark.Response;
import spark.Spark;

public class UserHandler extends BaseHandler{
    /**
     * Default constructor for handlers
     *
     * @param serviceManager
     */
    public UserHandler(ServiceManager serviceManager) {
        super(serviceManager);
        this.root = "/user";
    }

    @Override
    public void initHandler() {
        Spark.post(this.root, this::register);
    }

    /**
     * Creates a user in the database, if the username doesn't already exist
     * @param req
     * @param res
     * @return
     * @throws DataAccessException If something happens in the database
     * @throws ExceptionHandler If username already taken or bad request
     */
    private Object register(Request req, Response res) throws DataAccessException, ExceptionHandler {
        UserData userData = UserData.fromJson(req.body());
        if(userData == null) {
            throw new ExceptionHandler("bad request", 400);
        }
        UserService userService = this.serviceManager.getService(UserService.class);
        AuthData authData = userService.createUser(userData);
        if(authData == null){
            throw new ExceptionHandler("already taken", 403);
        }

        this.setSuccessHeaders(res);
        return new Gson().toJson(authData);
    }
}
