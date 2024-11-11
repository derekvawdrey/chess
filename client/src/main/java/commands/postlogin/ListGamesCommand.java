package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class ListGamesCommand extends BaseCommand {
    public ListGamesCommand(ChessClient chessClient) {
        super(
                chessClient,
                "",
                ""
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
