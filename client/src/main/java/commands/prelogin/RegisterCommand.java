package commands.prelogin;

import commands.BaseCommand;
import model.ChessClient;

public class RegisterCommand extends BaseCommand  {
    public RegisterCommand(ChessClient chessClient) {
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
    public void executeCommand(String[] args) {

    }
}
