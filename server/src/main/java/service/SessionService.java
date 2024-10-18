package service;

import dataAccess.DataAccessException;
import dataAccess.interfaces.SessionDataAccessInterface;
import dataAccess.interfaces.UserDataAccessInterface;
import dataAccess.manager.DataAccessManager;
import helpers.AuthHelper;
import model.AuthData;
import model.LoginRequest;
import model.UserData;

/**
 * Handles login, logout. UserService handles creation of user
 */
public class SessionService extends BaseService {

    public SessionService(DataAccessManager dataAccessManager) {
        super(dataAccessManager);
    }

    /**
     * Logs a user in to the system.
     * @param loginRequest
     * @return
     * @throws DataAccessException
     */
    public AuthData login(LoginRequest loginRequest) throws DataAccessException {
        SessionDataAccessInterface sessionDataAccess = this.dataAccessManager.getDataAccess(SessionDataAccessInterface.class);
        UserDataAccessInterface userDataAccess = this.dataAccessManager.getDataAccess(UserDataAccessInterface.class);

        UserData user = userDataAccess.getUser(loginRequest.username());
        if (user == null) {
            return null;
        }

        if(AuthHelper.isPasswordMatch(loginRequest.password(), user.password())){
            return sessionDataAccess.insertAuth(new AuthData(AuthHelper.generateUUID(), loginRequest.username()));
        }
        return null;

    }

    /**
     * Gets auth data
     * @param authToken
     * @return AuthData
     * @throws DataAccessException
     */
    public AuthData getAuthData(String authToken) throws DataAccessException {
        SessionDataAccessInterface sessionDataAccess = this.dataAccessManager.getDataAccess(SessionDataAccessInterface.class);
        return sessionDataAccess.getAuth(authToken);
    }

    /**
     * Logs a user out
     * @param authToken
     * @throws DataAccessException
     */
    public void logout(String authToken) throws DataAccessException {
        SessionDataAccessInterface sessionDataAccess = this.dataAccessManager.getDataAccess(SessionDataAccessInterface.class);
        sessionDataAccess.removeAuth(authToken);
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
