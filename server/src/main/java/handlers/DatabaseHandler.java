package handlers;

import service.manager.ServiceManager;
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

    }
}
