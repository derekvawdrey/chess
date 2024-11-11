package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class ServerFacade {
    private final String serverRoot;
    private final int port;
    public ServerFacade(int port) {
        this.serverRoot = "localhost:";
        this.port = port;
    }

    public AuthData register(UserData userData) {
        return new AuthData(",","");
    }

    public AuthData login(LoginRequest loginRequest) {
        return new AuthData("","");
    }

    public void logout(AuthData authData) {

    }

    private <T> T makeRequest(String method, String path, Object request, Class<T> responseType) throws URISyntaxException {
        try{
            URL url = (new URI(this.serverRoot + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            // Write the body
            this.writeBody(request, http);

            // Make the request
            http.connect();

            // Check if successful
            this.throwIfNotSuccessful(http);

            return this.readBody(http, responseType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException {
        var status = http.getResponseCode();
        if(status / 100 != 2) {
            // Make a ResponseException
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
