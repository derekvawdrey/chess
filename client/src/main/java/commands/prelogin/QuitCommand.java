package commands.prelogin;

import core.ChessClient;
import commands.BaseCommand;

public class QuitCommand extends BaseCommand {
    public QuitCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Exit the program",
                "quit"
        );
    }

    @Override
    public boolean validateArgs(String... args) {
        return true;
    }

    @Override
    public void executeCommand(String... args) {
        this.chessClient.setRunning(false);
    }
}
