package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class LogoutCommand extends BaseCommand {
    protected LogoutCommand(ChessClient chessClient) {
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
