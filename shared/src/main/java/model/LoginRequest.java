package model;

import com.google.gson.Gson;

public record LoginRequest(String username, String password) {
    public static LoginRequest fromJson(String json) {
        LoginRequest loginRequest = new Gson().fromJson(json, LoginRequest.class);
        if (loginRequest == null || !loginRequest.isValid()) {
            return null;
        }
        return loginRequest;
    }

    private boolean isValid() {
        return username != null && !username.isEmpty() &&
                password != null && !password.isEmpty();
    }
}
