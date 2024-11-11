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
        if (args.length != 1) {
            this.chessClient.printError("Invalid number of arguments!");
            return false;
        }

        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            this.chessClient.printError("Invalid ID.");
            return false;
        }

        return true;
    }

    @Override
    public void executeCommand(String... args) {

    }
}
