package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class CreateGameCommand extends BaseCommand {
    public CreateGameCommand(ChessClient chessClient) {
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
