package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class PlayGameCommand extends BaseCommand {
    public PlayGameCommand(ChessClient chessClient) {
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
