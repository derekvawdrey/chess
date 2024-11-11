package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class ObserveGameCommand extends BaseCommand {
    public ObserveGameCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Watch a game",
                "observe <ID>"
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
