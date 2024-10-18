package server;

import dataAccess.manager.DataAccessManager;
import dataAccess.DataAccessType;
import handlers.*;
import service.manager.ServiceManager;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;

public class Server {


    private final List<BaseHandler> handlers;
    private final DataAccessManager dataAccessManager;
    private final ServiceManager serviceManager;

    public Server() {
        this.handlers = new ArrayList<>();
        this.dataAccessManager = new DataAccessManager(DataAccessType.MEMORY_DATA_ACCESS);
        this.serviceManager = new ServiceManager(dataAccessManager);

        this.createHandlers();
    }

    /**
     * Creates the handler classes
     */
    private void createHandlers(){
        this.handlers.add(new SessionHandler(serviceManager));
        this.handlers.add(new GameHandler(serviceManager));
        this.handlers.add(new UserHandler(serviceManager));
        this.handlers.add(new DatabaseHandler(serviceManager));
    }

    /**
     * This initalizes all routes that the handlers control
     */
    private void initalizeHandlers(){
        for(BaseHandler handler : handlers){
            handler.initHandler();
        }
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        initalizeHandlers();

        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
