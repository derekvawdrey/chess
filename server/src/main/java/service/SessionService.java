package service;

import dataAccess.DataAccessException;
import dataAccess.interfaces.SessionDataAccessInterface;
import dataAccess.interfaces.UserDataAccessInterface;
import dataAccess.manager.DataAccessManager;
import model.AuthData;
import model.LoginRequest;

/**
 * Handles login, logout. UserService handles creation of user
 */
public class SessionService extends BaseService {

    public SessionService(DataAccessManager dataAccessManager) {
        super(dataAccessManager);
    }


    public AuthData login(LoginRequest loginRequest) throws DataAccessException {
        SessionDataAccessInterface sessionDataAccess = this.dataAccessManager.getDataAccess(SessionDataAccessInterface.class);

    }

    public void logout(String authToken) throws DataAccessException {

    }

    /**
     * Deletes all auth information in the database.
     * @throws DataAccessException
     */
    public void deleteAll() throws DataAccessException {
        SessionDataAccessInterface authData = this.dataAccessManager.getDataAccess(SessionDataAccessInterface.class);
        authData.removeAllAuth();
    }
}
