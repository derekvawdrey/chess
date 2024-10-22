package handlers;

import dataaccess.DataAccessException;
import model.AuthData;
import service.SessionService;
import service.manager.ServiceManager;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class BaseHandler {

    protected final ServiceManager serviceManager;
    protected String root;

    /**
     * Default constructor for handlers
     * @param serviceManager
     */
    public BaseHandler(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    /**
     * Sets the success headers as well as the response type
     * @param response
     */
    protected void setSuccessHeaders(Response response) {
        response.status(200);
        response.type("application/json");
    }

    /**
     * initalizes endpoints for the handler, if any.
     */
    public abstract void initHandler();

    /**
     * Determines if the authToken is a valid authToken in the database
     * @param request
     * @return true of the authToken is valid given username and auth
     */
    public boolean validAuthToken(Request request) throws DataAccessException {
        String authToken = request.headers("Authorization");
        if (authToken == null) {
            return false;
        }
        SessionService sessionService = this.serviceManager.getService(SessionService.class);
        AuthData authData = sessionService.getAuthData(authToken);
        return authData != null;
    }

    /**
     * Middleware to authenticate a request.
     * @param route The original route to be wrapped.
     * @return A new route that applies authentication before the original.
     */
    public Route verifyAuth(Route route) {
        return (request, response) -> {
            if (!validAuthToken(request)) {
                throw new ExceptionHandler("unauthorized", 401);
            }
            return route.handle(request, response);
        };
    }

    /**
     * Get the endpoint.
     * @return The endpoint string.
     */
    public String getRoot() {
        return root;
    }
}
