package commands.prelogin;

import commands.BaseCommand;
import model.ChessClient;

public class QuitCommand extends BaseCommand {
    public QuitCommand(ChessClient chessClient) {
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
