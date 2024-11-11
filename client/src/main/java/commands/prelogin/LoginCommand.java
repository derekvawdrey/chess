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
        return false;
    }

    @Override
    public void executeCommand(String... args) {

    }
}
