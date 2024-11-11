package commands.postlogin;

import commands.BaseCommand;
import exception.ResponseException;
import model.ChessClient;
import model.GameDataResponse;
import model.JoinGameRequest;

import java.util.List;

public class PlayGameCommand extends BaseCommand {

    private GameDataResponse selectedGame;

    public PlayGameCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Join a game.",
                "play <ID> [WHITE|BLACK]"
        );
    }

    @Override
    public boolean validateArgs(String... args) {
        selectedGame = null;
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

            if (gameCount <= requestedId) {
                this.chessClient.printError("Invalid ID.");
                return false;
            }
            selectedGame = gamesArray.get(requestedId);
        }

        if(!args[1].equalsIgnoreCase("WHITE") && !args[1].equalsIgnoreCase("BLACK")){
            this.chessClient.printError("Must choose either white or black.");
            return false;
        }

        return true;
    }

    @Override
    public void executeCommand(String... args) {
        try {
            this.chessClient.getServerFacade().joinGame(
                    new JoinGameRequest(
                            args[1].toUpperCase(),
                            this.chessClient.getAuthData().username(),
                            selectedGame.gameID()
                    ),
                    this.chessClient.getAuthData().authToken()
            );
        }catch (ResponseException e){
            if(e.StatusCode() == 403){
                this.chessClient.printError("There is already a player for that color!");
            }else{
                this.chessClient.printError("An unexpected error occured.");
            }
        }
    }
}
