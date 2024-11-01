package dataaccess;

import dataaccess.interfaces.BaseDataAccessInterface;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDataAccessTests {

        UserSqlDataAccess userDataAccess;

    @BeforeEach
    public void setUp() throws DataAccessException {
        userDataAccess = new UserSqlDataAccess();
        userDataAccess.initalizeDatabaseTables();
        userDataAccess.deleteAllUsers();
    }

    @Test
    @DisplayName("Insert User Data - Positive")
    public void testInsertUserPositive() throws DataAccessException {
        UserData userData = new UserData(
                "a",
                "a",
                "a@gmail.com"
        );
        Assertions.assertNotNull(userDataAccess.insertUser(userData));
    }

    @Test
    @DisplayName("Insert User Data - Negative")
    public void testInsertUserNegative() throws DataAccessException {
        UserData userData = new UserData(
                "a",
                "a",
                "a@gmail.com"
        );
        userDataAccess.insertUser(userData);
        // Attempt to insert a secondary user with the same username.
        Assertions.assertThrows(DataAccessException.class, () -> userDataAccess.insertUser(userData));
    }

    @Test
    @DisplayName("Get User Data - Positive")
    public void testGetUserPositive() throws DataAccessException {
        UserData userData = new UserData(
                "a",
                "a",
                "a@gmail.com"
        );
        userDataAccess.insertUser(userData);

        Assertions.assertNotNull(userDataAccess.getUser("a"));
    }

    @Test
    @DisplayName("Get User Data - Negative")
    public void testGetUserNegative() throws DataAccessException {
        Assertions.assertNull(userDataAccess.getUser("a"));
    }

    @Test
    @DisplayName("Delete All Users - Positive")
    public void testDeleteAllUsersPositive() throws DataAccessException {
        UserData userData = new UserData(
                "a",
                "a",
                "a@gmail.com"
        );
        userDataAccess.insertUser(userData);

        userDataAccess.deleteAllUsers();
        Assertions.assertNull(userDataAccess.getUser("a"));
    }

}
