package helpers;

import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;

public class AuthHelper {

    /**
     * Generates a UUID
     * @return UUID string
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Helper function to return a hashed password.
     * @param password
     * @return
     */
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Helper function to check hashed password against original
     * @param password
     * @param hashedPassword
     * @return
     */
    public static boolean isPasswordMatch(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
