package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class ListGamesCommand extends BaseCommand {
    protected ListGamesCommand(ChessClient chessClient) {
        super(
                chessClient,
                "",
                ""
        );
    }

    @Override
    protected boolean validateArgs(String[] args) {
        return false;
    }

    @Override
    protected void executeCommand(String[] args) {

    }
}
