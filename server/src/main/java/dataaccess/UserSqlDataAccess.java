package dataaccess;

import dataaccess.interfaces.BaseSqlDataAccess;
import dataaccess.interfaces.UserDataAccessInterface;
import dataaccess.manager.DatabaseManager;
import model.UserData;

import java.sql.SQLException;

public class UserSqlDataAccess extends BaseSqlDataAccess implements UserDataAccessInterface {
    public UserSqlDataAccess() throws DataAccessException{
        super();

    }

    @Override
    protected void initalizeDatabaseTables() throws DataAccessException{
        String sql = "CREATE TABLE IF NOT EXISTS userData (";
        sql += "id INTEGER PRIMARY KEY AUTO_INCREMENT,";
        sql += "username VARCHAR(255) NOT NULL UNIQUE,";
        sql += "email VARCHAR(255) NOT NULL,";
        sql += "password VARCHAR(255) NOT NULL)";

        this.executeSqlUpdate(sql);
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        String sql = "SELECT email, password FROM userData WHERE username = ?";
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String email = resultSet.getString("email");
                        String password = resultSet.getString("password");
                        return new UserData(username, password, email);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return null;
    }

    @Override
    public UserData insertUser(UserData user) throws DataAccessException {
        String sql = "INSERT INTO userData (username, email, password) VALUES (?,?,?)";
        this.executeSqlUpdate(sql, user.username(), user.email(), user.password());
        return user;
    }

    @Override
    public void deleteAllUsers() throws DataAccessException {
        String sql = "TRUNCATE userData";
        this.executeSqlUpdate(sql);
    }
}
