package handlers;

import service.manager.ServiceManager;

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

    }
}
