package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;
import model.GameDataResponse;

import java.util.List;

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
        if (args.length != 2) {
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

            if (gameCount >= requestedId) {
                this.chessClient.printError("Invalid ID.");
                return false;
            }
        }

        if(!args[1].equalsIgnoreCase("WHITE") && !args[1].equalsIgnoreCase("BLACK")){
            this.chessClient.printError("Must choose either white or black.");
            return false;
        }

        return true;
    }

    @Override
    public void executeCommand(String... args) {

    }
}
