package dataaccess.interfaces;

import dataaccess.DataAccessException;
import dataaccess.manager.DatabaseManager;

import java.sql.SQLException;

public abstract class BaseSqlDataAccess {
    protected BaseSqlDataAccess() throws DataAccessException {
        initalizeDatabaseTables();
    }

    protected void initalizeDatabaseTables() throws DataAccessException {}

    protected void executeSql(String statement) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataAccessException("could not create table for games");
        }
    }
}
