package handlers;

import service.manager.ServiceManager;

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

    }
}
