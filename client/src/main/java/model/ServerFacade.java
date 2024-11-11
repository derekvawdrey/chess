package model;

public class ServerFacade {

    public AuthData register(UserData userData) {
        return new AuthData(",","");
    }

    public AuthData login(LoginRequest loginRequest) {
        return new AuthData("","");
    }

}
