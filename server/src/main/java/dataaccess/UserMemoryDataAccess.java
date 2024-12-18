package dataaccess;

import dataaccess.interfaces.UserDataAccessInterface;
import model.UserData;

import java.util.HashMap;
import java.util.Map;

public class UserMemoryDataAccess implements UserDataAccessInterface {
    // Username, and UserData
    Map<String, UserData> users = new HashMap<>();

    @Override
    public UserData getUser(String username) throws DataAccessException {
        if(users.containsKey(username)) {
            return users.get(username);
        }
        return null;
    }

    @Override
    public UserData insertUser(UserData user) throws DataAccessException {
        if(users.containsKey(user.username())){
            return null;
        }
        users.put(user.username(), user);
        return user;
    }

    @Override
    public void deleteAllUsers() throws DataAccessException {
        users.clear();
    }
}
