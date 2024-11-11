package commands.prelogin;

import commands.BaseCommand;
import model.ChessClient;

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

    }
}
