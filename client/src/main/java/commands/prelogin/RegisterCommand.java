package commands.prelogin;

import commands.BaseCommand;
import model.ChessClient;

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

    }
}
