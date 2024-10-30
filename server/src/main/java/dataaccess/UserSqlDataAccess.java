package dataaccess;

import dataaccess.interfaces.BaseSqlDataAccess;
import dataaccess.interfaces.SessionDataAccessInterface;
import dataaccess.interfaces.UserDataAccessInterface;
import model.AuthData;
import model.UserData;

public class UserSqlDataAccess extends BaseSqlDataAccess implements UserDataAccessInterface {
    public UserSqlDataAccess() throws DataAccessException{
        super();

    }

    @Override
    protected void initalizeDatabaseTables() throws DataAccessException{
        String sql = "";

        this.executeSql(sql);
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        return null;
    }

    @Override
    public UserData insertUser(UserData user) throws DataAccessException {
        return null;
    }

    @Override
    public void deleteAllUsers() throws DataAccessException {

    }
}
