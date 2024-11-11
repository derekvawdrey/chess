package commands.prelogin;

import commands.BaseCommand;
import model.ChessClient;

public class RegisterCommand extends BaseCommand  {
    protected RegisterCommand(ChessClient chessClient) {
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
