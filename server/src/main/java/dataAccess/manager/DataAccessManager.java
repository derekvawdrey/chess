package dataAccess.manager;

import dataAccess.DataAccessType;
import dataAccess.interfaces.BaseDataAccessInterface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataAccessManager {
    private final DataAccessType dataAccessType;
    private final Map<Class<? extends BaseDataAccessInterface>, BaseDataAccessInterface> dataAccessMap;

    /**
     *
     * @param dataAccessType The type of DataAccess's to create and populate
     */
    public DataAccessManager(DataAccessType dataAccessType) {
        this.dataAccessType = dataAccessType;
        this.dataAccessMap = new ConcurrentHashMap<>();
        this.generateDataAccessInstances();
    }

    private void generateDataAccessInstances(){

    }

    /**
     * Retrieves a DataAccess Object by its class type.
     *
     * @param dataAccessClass The class of the service to retrieve.
     * @param <T> The type of DataAccess.
     * @return The DataAccess instance.
     * @throws IllegalArgumentException If the DataAccess is not found.
     */
    public <T extends BaseDataAccessInterface> T getService(Class<T> dataAccessClass) {
        BaseDataAccessInterface service = dataAccessMap.get(dataAccessClass);
        if (service == null) {
            throw new IllegalArgumentException("Service not found: " + dataAccessClass.getName());
        }
        return dataAccessClass.cast(service);
    }


}
