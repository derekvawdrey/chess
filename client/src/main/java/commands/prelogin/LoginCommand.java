package commands.prelogin;

import commands.BaseCommand;
import model.ChessClient;

public class LoginCommand extends BaseCommand {
    protected LoginCommand(ChessClient chessClient) {
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
