package service;

import dataaccess.DataAccessType;
import dataaccess.manager.DataAccessManager;
import org.junit.jupiter.api.BeforeAll;
import service.manager.ServiceManager;

public class ServiceTestBase {

    public static DataAccessManager dataAccessManager;
    public static ServiceManager serviceManager;

    @BeforeAll
    public static void setUp() {
        dataAccessManager = new DataAccessManager(DataAccessType.MEMORY_DATA_ACCESS);
        serviceManager = new ServiceManager(dataAccessManager);
    }





}
