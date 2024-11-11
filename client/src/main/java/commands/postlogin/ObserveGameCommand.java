package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;
import model.GameDataResponse;

import java.util.List;

public class ObserveGameCommand extends BaseCommand {

    private GameDataResponse gameData;

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

        ListGamesCommand listGamesCommand = (ListGamesCommand) this.chessClient.getPostLoginCommands().get("list");

        if (listGamesCommand != null) {
            List<GameDataResponse> gamesArray = listGamesCommand.getGamesArray();
            int gameCount = gamesArray.size();
            int requestedId;

            try {
                requestedId = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                this.chessClient.printError("Invalid ID format.");
                return false;
            }

            if (gameCount <= requestedId) {
                this.chessClient.printError("Invalid ID.");
                return false;
            }
        }

        return true;
    }

    @Override
    public void executeCommand(String... args) {

    }
}
