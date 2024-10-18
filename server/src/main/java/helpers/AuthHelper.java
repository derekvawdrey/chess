package helpers;

import java.util.UUID;

public class AuthHelper {

    /**
     * Generates a UUID
     * @return UUID string
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String hashPassword(String password){
        String hashedPassword = BCryp
    }
}
