package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class PlayGameCommand extends BaseCommand {
    protected PlayGameCommand(ChessClient chessClient) {
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
