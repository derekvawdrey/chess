package handlers;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import model.AuthData;
import model.LoginRequest;
import service.SessionService;
import service.UserService;
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
        Spark.post(this.root, verifyAuth(this::login));
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

        return new Gson().toJson(authData);
    }

    /**
     *
     * @param req
     * @param response
     * @return
     */
    public Object logout(Request req, Response response) {

    }
}
