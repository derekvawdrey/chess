package dataaccess.manager;

import dataaccess.*;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.interfaces.BaseDataAccessInterface;
import dataaccess.interfaces.GameDataAccessInterface;
import dataaccess.interfaces.UserDataAccessInterface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataAccessManager {
    private final DataAccessType dataAccessType;
    private final Map<Class<? extends BaseDataAccessInterface>, BaseDataAccessInterface> dataAccessMap;

    /**
     *
     * @param dataAccessType The type of DataAccess's to create and populate
     */
    public DataAccessManager(DataAccessType dataAccessType) throws DataAccessException {
        this.dataAccessType = dataAccessType;
        this.dataAccessMap = new ConcurrentHashMap<>();
        this.generateDataAccessInstances();
    }

    /**
     * Create the data access instances necessary
     * @throws DataAccessException
     */
    private void generateDataAccessInstances() throws DataAccessException {
        if(dataAccessType == DataAccessType.MEMORY_DATA_ACCESS){
            dataAccessMap.put(SessionDataAccessInterface.class, new SessionMemoryDataAccess());
            dataAccessMap.put(GameDataAccessInterface.class, new GameMemoryDataAccess());
            dataAccessMap.put(UserDataAccessInterface.class, new UserMemoryDataAccess());
        }else{
            DatabaseManager.createDatabase();
            dataAccessMap.put(SessionDataAccessInterface.class, new SessionSqlDataAccess());
            dataAccessMap.put(GameDataAccessInterface.class, new GameSqlDataAccess());
            dataAccessMap.put(UserDataAccessInterface.class, new UserSqlDataAccess());
        }
    }

    /**
     * Retrieves a DataAccess Object by its class type.
     *
     * @param dataAccessClass The class of the service to retrieve.
     * @param <T> The type of DataAccess.
     * @return The DataAccess instance.
     * @throws IllegalArgumentException If the DataAccess is not found.
     */
    public <T extends BaseDataAccessInterface> T getDataAccess(Class<T> dataAccessClass) throws DataAccessException {
        BaseDataAccessInterface service = dataAccessMap.get(dataAccessClass);
        if (service == null) {
            throw new DataAccessException("Service not found: " + dataAccessClass.getName());
        }
        return dataAccessClass.cast(service);
    }


}
