package commands.prelogin;

import commands.BaseCommand;
import exception.ResponseException;
import model.AuthData;
import client_models.ChessClient;
import model.LoginRequest;

public class LoginCommand extends BaseCommand {
    public LoginCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Login as an existing user",
                "login <USERNAME> <PASSWORD>"
        );
    }

    @Override
    public boolean validateArgs(String... args) {
        if(args.length > 2) {
            this.chessClient.printError("Too many arguments");
            return false;
        }

        if (args.length != 2) {
            this.chessClient.printError("You must provide both a username and a password.");
            return false;
        }

        String username = args[0];
        String password = args[1];

        if (username.isEmpty() || password.isEmpty()) {
            this.chessClient.printError("Username and password cannot be empty.");
            return false;
        }
        return true;
    }

    @Override
    public void executeCommand(String... args) {

        String username = args[0];
        String password = args[1];
        try {
            AuthData authData = this.chessClient.getServerFacade().login(new LoginRequest(username, password));
            if(authData != null){
                this.chessClient.setAuthData(authData);
                this.chessClient.setState(ChessClient.ClientState.POST_LOGIN);
            }
        }
        catch (ResponseException e) {
            this.chessClient.printError("That username or password is incorrect.");
        }
    }
}
