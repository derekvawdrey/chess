package dataaccess.interfaces;

import dataaccess.DataAccessException;
import dataaccess.manager.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseSqlDataAccess {
    protected BaseSqlDataAccess() throws DataAccessException {
        initalizeDatabaseTables();
    }

    protected void initalizeDatabaseTables() throws DataAccessException {}


    protected void executeSqlUpdate(String statement, Object... params) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement(statement)) {
                for (int i = 0; i < params.length; i++) {
                    if (params[i] instanceof String) {
                        preparedStatement.setString(i + 1, (String) params[i]);
                    } else if (params[i] instanceof Integer) {
                        preparedStatement.setInt(i + 1, (Integer) params[i]);
                    }
                }
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException("could not execute SQL statement: " + e.getMessage());
        }
    }
}
