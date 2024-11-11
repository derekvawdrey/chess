package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class PlayGameCommand extends BaseCommand {
    public PlayGameCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Join a game.",
                "join <ID> [WHITE|BLACK]"
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
