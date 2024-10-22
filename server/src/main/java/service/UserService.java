package service;

import dataaccess.DataAccessException;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.interfaces.UserDataAccessInterface;
import dataaccess.manager.DataAccessManager;
import helpers.AuthHelper;
import model.AuthData;
import model.UserData;

public class UserService extends BaseService {
    public UserService(DataAccessManager dataAccessManager) {
        super(dataAccessManager);
    }

    /**
     * Creates a user and provides an auth token
     * @param user user information
     * @return AuthData
     * @throws DataAccessException If there is an issue with something when fetching information from database
     */
    public AuthData createUser(UserData user) throws DataAccessException {
        UserDataAccessInterface userDataAccess = this.dataAccessManager.getDataAccess(UserDataAccessInterface.class);
        if(userDataAccess.getUser(user.username()) != null){
            return null;
        }
        UserData newUser = new UserData(user.username(), AuthHelper.hashPassword(user.password()), user.email());
        userDataAccess.insertUser(newUser);

        SessionDataAccessInterface authDataAccess = this.dataAccessManager.getDataAccess(SessionDataAccessInterface.class);

        return authDataAccess.insertAuth(
                new AuthData(AuthHelper.generateUUID(), newUser.username())
        );
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
