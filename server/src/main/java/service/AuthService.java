package service;

import dataAccess.DataAccessException;
import dataAccess.interfaces.AuthDataAccessInterface;
import dataAccess.manager.DataAccessManager;

public class AuthService extends BaseService {

    public AuthService(DataAccessManager dataAccessManager) {
        super(dataAccessManager);
    }


    /**
     * Deletes all auth information in the database.
     * @throws DataAccessException
     */
    public void deleteAll() throws DataAccessException {
        AuthDataAccessInterface authData = this.dataAccessManager.getDataAccess(AuthDataAccessInterface.class);
        authData.removeAllAuth();
    }
}
