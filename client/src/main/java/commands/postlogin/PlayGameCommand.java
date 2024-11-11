package commands.postlogin;

import commands.BaseObserveGameCommand;
import exception.ResponseException;
import core.ChessClient;
import model.JoinGameRequest;

public class PlayGameCommand extends BaseObserveGameCommand {

    public PlayGameCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Join a game.",
                "play <ID> [WHITE|BLACK]"
        );
    }

    @Override
    public boolean validateArgs(String... args) {
        if (args.length != 2) {
            this.chessClient.printError("Invalid number of arguments!");
            return false;
        }

        if(!this.checkGameId(args[0])){
            return false;
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
            if(e.getStatusCode() == 409){
                this.chessClient.printError("There is already a player for that color!");
            }else{
                this.chessClient.printError("An unexpected error occurred.");
            }
        }
    }
}
