package service;

import dataaccess.DataAccessException;
import dataaccess.DataAccessType;
import dataaccess.manager.DataAccessManager;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.BeforeAll;
import service.manager.ServiceManager;

public class ServiceTestBase {

    protected static DataAccessManager dataAccessManager;
    protected static ServiceManager serviceManager;

    @BeforeAll
    public static void setUp() throws DataAccessException {
        dataAccessManager = new DataAccessManager(DataAccessType.MEMORY_DATA_ACCESS);
        serviceManager = new ServiceManager(dataAccessManager);
        AuthData newUser = serviceManager.getService(UserService.class)
                .createUser(new UserData("Amongus","sussy","test@test.com"));
    }





}
