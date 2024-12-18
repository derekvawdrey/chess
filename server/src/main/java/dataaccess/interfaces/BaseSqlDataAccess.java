package dataaccess.interfaces;

import dataaccess.DataAccessException;
import dataaccess.manager.DatabaseManager;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseSqlDataAccess {
    protected BaseSqlDataAccess() throws DataAccessException {
        initalizeDatabaseTables();
    }

    protected void initalizeDatabaseTables() throws DataAccessException {}

    /**
     * For simple insert and update things
     * @param statement
     * @param params
     * @throws DataAccessException
     */
    protected void executeSqlUpdate(String statement, Object... params) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement(statement)) {
                setParameters(preparedStatement, params);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error with SQL: " + e.getMessage());
        }
    }

    /**
     * executes a sql update but also returns the id
     * @param statement
     * @param params
     * @return
     * @throws DataAccessException
     */
    protected int executeSqlUpdateGetId(String statement, Object... params) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS)) {
                setParameters(preparedStatement, params);
                preparedStatement.executeUpdate();
                try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new DataAccessException("womp womp");
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error with SQL: " + e.getMessage());
        }
    }

    /**
     * This assumes all nulls are of VARCHAR type.
     * @param preparedStatement
     * @param params
     * @throws SQLException
     */
    private void setParameters(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            switch (params[i]) {
                case String s -> preparedStatement.setString(i + 1, s);
                case Integer integer -> preparedStatement.setInt(i + 1, integer);
                case null -> preparedStatement.setNull(i + 1, java.sql.Types.VARCHAR);
                default -> {
                }
            }
        }
    }
}
