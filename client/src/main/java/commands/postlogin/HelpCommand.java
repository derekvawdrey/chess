package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class HelpCommand extends BaseCommand {


    public HelpCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Displays all the post-login commands",
                "help"
        );
    }

    @Override
    public boolean validateArgs(String... args) {
        return false;
    }

    @Override
    public void executeCommand(String... args) {

    }
}
