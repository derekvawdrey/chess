package commands.prelogin;

import commands.BaseCommand;
import model.ChessClient;

public class QuitCommand extends BaseCommand {
    protected QuitCommand(ChessClient chessClient) {
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
