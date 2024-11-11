package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class CreateGameCommand extends BaseCommand {
    public CreateGameCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Creates a game with a specified name.",
                "create <NAME>"
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
