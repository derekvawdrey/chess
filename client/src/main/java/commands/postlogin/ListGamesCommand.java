package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class ListGamesCommand extends BaseCommand {
    public ListGamesCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Lists all active games",
                "list"
        );
    }

    @Override
    public boolean validateArgs(String... args) {
        return true;
    }

    @Override
    public void executeCommand(String... args) {

    }
}
