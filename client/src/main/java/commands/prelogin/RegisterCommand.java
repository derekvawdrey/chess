package commands.prelogin;

import commands.BaseCommand;
import exception.ResponseException;
import model.AuthData;
import model.ChessClient;
import model.UserData;

public class RegisterCommand extends BaseCommand  {
    public RegisterCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Register a new user",
                "register <USERNAME> <PASSWORD> <EMAIL>"
        );
    }

    @Override
    public boolean validateArgs(String... args) {
        if (args.length != 3) {
            this.chessClient.printError("You must provide a username, password, and email.");
            return false;
        }

        String username = args[0];
        String password = args[1];
        String email = args[2];

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            this.chessClient.printError("Username, password, and email cannot be empty.");
            return false;
        }
        return true;
    }

    @Override
    public void executeCommand(String... args) {
        String username = args[0];
        String password = args[1];
        String email = args[2];
        try {
            AuthData authData = this.chessClient.getServerFacade().register(new UserData(username, password, email));
            if(authData != null){
                this.chessClient.setAuthData(authData);
                this.chessClient.setState(ChessClient.ClientState.POST_LOGIN);
            }else{
                this.chessClient.printError("That user already exists!");
            }
        }
        catch (ResponseException e) {
            this.chessClient.printError("That user already exists!");
        }
    }
}
