package dataaccess;

import dataaccess.interfaces.BaseDataAccessInterface;
import model.AuthData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionDataAccessTests {

    SessionSqlDataAccess sessionDataAccess;

    @BeforeEach
    public void setUp() throws DataAccessException {
        sessionDataAccess = new SessionSqlDataAccess();
        sessionDataAccess.initalizeDatabaseTables();
        sessionDataAccess.removeAllAuth();
    }

    @Test
    @DisplayName("Insert Auth Data - Positive")
    public void testInsertAuthPositive() throws DataAccessException {
        AuthData authData = new AuthData("aaaaa", "aaa");
        Assertions.assertDoesNotThrow(() -> sessionDataAccess.insertAuth(authData));
    }

    @Test
    @DisplayName("Insert Auth Data - Negative")
    public void testInsertAuthNegative() throws DataAccessException {
        AuthData authData = new AuthData("aaaaa", "aaa");
        sessionDataAccess.insertAuth(authData);
        // We do a second one to say that you cant put in the same auth token to the person
        Assertions.assertNull(sessionDataAccess.insertAuth(authData));

    }

    @Test
    @DisplayName("Remove Auth Data - Positive")
    public void testRemoveAuthPositive() throws DataAccessException {
        // Insert the auth to remove
        AuthData authData = new AuthData("aaaaa", "aaa");
        sessionDataAccess.insertAuth(authData);

        Assertions.assertDoesNotThrow(() -> sessionDataAccess.removeAuth("aaaaa"));
    }
    @Test
    @DisplayName("Remove Auth Data - Negative")
    public void testRemoveAuthNegative() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> sessionDataAccess.removeAuth(null));
    }

    @Test
    @DisplayName("Get Auth Data - Positive")
    public void testGetAuthPositive() throws DataAccessException {
        AuthData authData = new AuthData("aaaaa", "aaa");
        sessionDataAccess.insertAuth(authData);

        Assertions.assertNotNull(sessionDataAccess.getAuth("aaaaa"));
    }


    @Test
    @DisplayName("Get Auth Data - Negative")
    public void testGetAuthNegative() throws DataAccessException{
        Assertions.assertNull(sessionDataAccess.getAuth("aaaaa"));
    }

    @Test
    @DisplayName("Remove All Auth Data - Positive")
    public void testRemoveAllAuthPositive() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> sessionDataAccess.removeAllAuth());
    }

}
