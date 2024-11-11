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
        return false;
    }

    @Override
    public void executeCommand(String... args) {

    }
}
