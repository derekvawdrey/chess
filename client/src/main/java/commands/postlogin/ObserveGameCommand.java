package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class ObserveGameCommand extends BaseCommand {
    public ObserveGameCommand(ChessClient chessClient) {
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
