package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class HelpCommand extends BaseCommand {


    protected HelpCommand(ChessClient chessClient) {
        super(
                chessClient,
                "",
                ""
        );
    }

    @Override
    protected boolean validateArgs(String[] args) {
        return false;
    }

    @Override
    protected void executeCommand(String[] args) {

    }
}
