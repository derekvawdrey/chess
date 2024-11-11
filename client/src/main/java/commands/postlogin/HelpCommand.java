package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

import java.util.HashMap;

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
        return true;
    }

    @Override
    public void executeCommand(String... args) {
        HashMap<String, BaseCommand> commands = this.chessClient.getPostLoginCommands();
        commands.forEach((commandName, command) -> {
            System.out.println(command.getHelpMessage());
        });
    }
}
