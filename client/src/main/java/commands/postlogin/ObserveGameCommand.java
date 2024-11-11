package commands.postlogin;

import core.ChessClient;
import commands.BaseObserveGameCommand;

public class ObserveGameCommand extends BaseObserveGameCommand {



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

        return this.checkGameId(args[0]);
    }

    @Override
    public void executeCommand(String... args) {

    }
}
