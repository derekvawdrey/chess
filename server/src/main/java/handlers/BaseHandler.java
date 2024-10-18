package handlers;

import service.SessionService;
import service.manager.ServiceManager;
import spark.Request;
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
     * initalizes endpoints for the handler, if any.
     */
    public abstract void initHandler();

    /**
     * Determines if the authToken is a valid authToken in the database
     * @param request
     * @return true of the authToken is valid given username and auth
     */
    public boolean validAuthToken(Request request){
        String authToken = request.headers("Authorization");
        SessionService authService = this.serviceManager.getService(SessionService.class);
        return false;
    }

    /**
     * Middleware to authenticate a request.
     * @param route The original route to be wrapped.
     * @return A new route that applies authentication before the original.
     */
    public Route verifyAuth(Route route) {
        return (request, response) -> {
            if (!validAuthToken(request)) {
                response.status(401);
                response.body("Unauthorized");
                return null;
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
