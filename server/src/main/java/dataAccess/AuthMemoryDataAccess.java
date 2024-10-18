package dataAccess;

import dataAccess.interfaces.AuthDataAccessInterface;
import model.AuthData;

import java.util.HashMap;
import java.util.Map;

public class AuthMemoryDataAccess implements AuthDataAccessInterface {

    Map<String, AuthData> authDataMap = new HashMap<>();

    @Override
    public AuthData insertAuth(AuthData authData) throws DataAccessException {
        if(authDataMap.containsKey(authData.authToken())){
            return null;
        }
        authDataMap.put(authData.authToken(), authData);
        return authData;
    }

    @Override
    public AuthData removeAuth(String authToken) throws DataAccessException {
        if(!authDataMap.containsKey(authToken)){
            return null;
        }
        return authDataMap.remove(authToken);
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        if(!authDataMap.containsKey(authToken)){
            return null;
        }
        return authDataMap.get(authToken);
    }

    @Override
    public void removeAllAuth() throws DataAccessException {
        authDataMap.clear();
    }
}
