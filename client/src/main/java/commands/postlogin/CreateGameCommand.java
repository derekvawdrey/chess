package commands.postlogin;

import core.ChessClient;
import commands.BaseCommand;
import exception.ResponseException;
import model.CreateGameRequest;

public class CreateGameCommand extends BaseCommand {
    public CreateGameCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Creates a game with a specified name.",
                "create <NAME>"
        );
    }

    @Override
    public boolean validateArgs(String... args) {
        if (args.length != 1) {
            this.chessClient.printError("Invalid number of arguments!");
            return false;
        }

        if(args[0].isBlank()){
            this.chessClient.printError("Name cannot be blank!");
            return false;
        }

        return true;
    }

    @Override
    public void executeCommand(String... args) {
        try {
            this.chessClient.getServerFacade().createGame(
                    new CreateGameRequest(args[0]),
                    this.chessClient.getAuthData().authToken()
            );
            this.chessClient.printSuccess("The game was successfully created!");
        } catch (ResponseException e) {
            this.chessClient.printError("An unexpected error occurred!");
        }
    }
}
