package handlers;

import service.manager.ServiceManager;

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

    }
}
