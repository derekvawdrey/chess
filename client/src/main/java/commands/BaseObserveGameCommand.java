package commands;

import commands.postlogin.ListGamesCommand;
import model.ChessClient;
import model.GameDataResponse;

import java.util.List;

public class BaseObserveGameCommand extends BaseCommand{

    protected GameDataResponse selectedGame;

    protected BaseObserveGameCommand(ChessClient chessClient, String description, String usage) {
        super(chessClient, description, usage);
        selectedGame = null;
    }

    /**
     * Only used in the observeGameCommand, and PlayGameCommand.
     * @param id
     * @return
     */
    protected boolean checkGameId(String id) {
        selectedGame = null;
        ListGamesCommand listGamesCommand = (ListGamesCommand) this.chessClient.getPostLoginCommands().get("list");

        if (listGamesCommand != null) {
            List<GameDataResponse> gamesArray = listGamesCommand.getGamesArray();
            int gameCount = gamesArray.size();
            int requestedId;

            try {
                requestedId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                chessClient.printError("Invalid ID format.");
                return false;
            }

            if(requestedId < 0) {
                chessClient.printError("Invalid ID.");
                return false;
            }

            if (gameCount <= requestedId) {
                chessClient.printError("Invalid ID.");
                return false;
            }
            this.selectedGame = gamesArray.get(requestedId);
        }
        return true;
    }

    @Override
    public boolean validateArgs(String... args) {
        return true;
    }

    @Override
    public void executeCommand(String... args) {

    }
}
