package commands.postlogin;

import commands.BaseCommand;

public class CreateGameCommand extends BaseCommand {
    public CreateGameCommand(model.ChessClient chessClient) {
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

    }
}
