package service.manager;

import dataAccess.manager.DataAccessManager;
import service.AuthService;
import service.GameService;
import service.BaseService;
import service.UserService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceManager {

    private final Map<Class<? extends BaseService>, BaseService> services;
    private final DataAccessManager dataAccessManager;

    /**
     * Used to manage the services and allow anything to grab the service that it needs.
     * @param dataAccessManager
     */
    public ServiceManager(DataAccessManager dataAccessManager) {
        this.dataAccessManager = dataAccessManager;
        services = new ConcurrentHashMap<>();
        this.generateServices();
    }

    /**
     * Generates all necessary Services, to be used in handlers.
     */
    private void generateServices(){
        this.services.put(AuthService.class, new AuthService(dataAccessManager));
        this.services.put(UserService.class, new UserService(dataAccessManager));
        this.services.put(GameService.class, new GameService(dataAccessManager));
    }

    /**
     * Retrieves a service by its class type.
     *
     * @param serviceClass The class of the service to retrieve.
     * @param <T> The type of service.
     * @return The service instance.
     * @throws IllegalArgumentException If the service is not found.
     */
    public <T extends BaseService> T getService(Class<T> serviceClass) {
        BaseService service = services.get(serviceClass);
        if (service == null) {
            throw new IllegalArgumentException("Service not found: " + serviceClass.getName());
        }
        return serviceClass.cast(service);
    }
}
