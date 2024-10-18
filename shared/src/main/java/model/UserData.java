package model;


import com.google.gson.Gson;

public record UserData(String username, String password, String email) {
    public static UserData fromJson(String json) {
        UserData userData = new Gson().fromJson(json, UserData.class);
        if (userData == null || !userData.isValid()) {
            return null;
        }
        return userData;
    }

    private boolean isValid() {
        return username != null && !username.isEmpty() &&
                password != null && !password.isEmpty() &&
                email != null && !email.isEmpty();
    }
}
