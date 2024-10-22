package handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.AuthData;
import model.LoginRequest;
import service.SessionService;
import service.manager.ServiceManager;
import spark.Request;
import spark.Response;
import spark.Spark;

/**
 * A handler for managing user sessions.
 * This handles login, registration, and logout capabilities.
 */
public class SessionHandler extends BaseHandler{
    /**
     * Default constructor for handlers
     *
     * @param serviceManager
     */
    public SessionHandler(ServiceManager serviceManager) {
        super(serviceManager);
        this.root = "/session";
    }

    @Override
    public void initHandler() {
        Spark.post(this.root, this::login);
        Spark.delete(this.root, verifyAuth(this::logout));
    }

    /**
     *
     * @param req
     * @param response
     * @return
     */
    public Object login(Request req, Response response) throws ExceptionHandler, DataAccessException {
        LoginRequest loginRequest = LoginRequest.fromJson(req.body());
        if(loginRequest == null){
            throw new ExceptionHandler("bad request", 400);
        }

        SessionService sessionService = this.serviceManager.getService(SessionService.class);
        AuthData authData = sessionService.login(loginRequest);
        if(authData == null){
            throw new ExceptionHandler("unauthorized", 401);
        }

        this.setSuccessHeaders(response);
        return new Gson().toJson(authData);
    }

    /**
     * Logs a user out if it is a valid auth token
     * @param req
     * @param res
     * @return
     */
    public Object logout(Request req, Response res) throws DataAccessException {
        String authToken = req.headers("Authorization");
        SessionService sessionService = this.serviceManager.getService(SessionService.class);
        sessionService.logout(authToken);

        this.setSuccessHeaders(res);
        return new Gson().toJson(new Object());
    }
}
