package commands.postlogin;

import chess.ChessGame;
import commands.BaseObserveGameCommand;
import exception.ResponseException;
import core.ChessClient;
import model.JoinGameRequest;
import ui.PrettyPrintChessBoard;

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

            System.out.print(PrettyPrintChessBoard.prettyPrint(new ChessGame().getBoard(), ChessGame.TeamColor.WHITE));
            System.out.print(PrettyPrintChessBoard.prettyPrint(new ChessGame().getBoard(), ChessGame.TeamColor.BLACK));
        }catch (ResponseException e){
            if(e.getStatusCode() == 409){
                this.chessClient.printError("There is already a player for that color!");
            }else{
                this.chessClient.printError("An unexpected error occurred.");
            }
        }
    }
}
