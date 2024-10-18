package service;

import dataAccess.DataAccessException;
import dataAccess.interfaces.GameDataAccessInterface;
import dataAccess.interfaces.UserDataAccessInterface;
import dataAccess.manager.DataAccessManager;

public class UserService extends BaseService {
    public UserService(DataAccessManager dataAccessManager) {
        super(dataAccessManager);
    }

    /**
     * Deletes all user data in the database
     * @throws DataAccessException
     */
    public void deleteAll() throws DataAccessException {
        UserDataAccessInterface userDataAccess = this.dataAccessManager.getDataAccess(UserDataAccessInterface.class);
        userDataAccess.deleteAllUsers();
    }
}
