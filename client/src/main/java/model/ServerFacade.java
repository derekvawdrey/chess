package model;

import com.google.gson.Gson;
import exception.ResponseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class ServerFacade {
    private final String serverRoot;
    private final int port;
    public ServerFacade(int port) {

        this.port = port;
        this.serverRoot = "http://localhost:" + this.port;
    }

    // PRE LOGIN ENDPOINTS

    public AuthData register(UserData userData) throws ResponseException {
        String path = "/user";
        return this.makeRequest("POST", path, userData, AuthData.class, null);
    }

    public AuthData login(LoginRequest loginRequest) throws ResponseException {
        String path = "/session";
        return this.makeRequest("POST", path, loginRequest, AuthData.class, null);
    }

    public void logout(String authToken) throws ResponseException {
        String path = "/session";
        this.makeRequest("DELETE", path, null, null, authToken);
    }

    // POST LOGIN ENDPOINTS
    public GameListResult listGames(String authToken) throws ResponseException{
        String path = "/game";
        return this.makeRequest("GET", path, null, GameListResult.class, authToken);
    }

    public void joinGame(JoinGameRequest joinGameRequest, String authToken) throws ResponseException {
        String path = "/game";
        this.makeRequest("PUT", path, joinGameRequest, GameData.class, authToken);
    }

    private <T> T makeRequest(String method, String path, Object request, Class<T> responseType, String authToken) throws ResponseException {
        try{
            URL url = (new URI(this.serverRoot + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            if(authToken != null) {
                http.setRequestProperty("Authorization", authToken);
            }

            // Write the body
            this.writeBody(request, http);

            // Make the request
            http.connect();

            // Check if successful
            this.throwIfNotSuccessful(http);

            return this.readBody(http, responseType);
        } catch (Exception e) {
            throw new ResponseException(500, e.getMessage());
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws ResponseException, IOException {
        var status = http.getResponseCode();
        if(status / 100 != 2) {
            throw new ResponseException(status, "failure: " + status);
        }
    }

    private void writeBody(Object request, HttpURLConnection http) throws IOException {
        if(request != null) {
            http.addRequestProperty("Content-Type", "application/json");
            String json = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(json.getBytes());
            }
        }
    }

    private <T> T readBody(HttpURLConnection http, Class<T> responseType) throws IOException {
        T response = null;
        if(http.getContentLength() < 0){
            try(InputStream in = http.getInputStream()){
                InputStreamReader reader = new InputStreamReader(in);
                if(responseType != null){
                    response = new Gson().fromJson(reader, responseType);
                }
            }
        }
        return response;
    }

}
