package commands.postlogin;

import commands.BaseCommand;

public class CreateGameCommand extends BaseCommand {
    protected CreateGameCommand() {
        super("Creates a game", "");
    }

    @Override
    protected boolean validateArgs(String[] args) {
        return false;
    }

    @Override
    protected void executeCommand(String[] args) {

    }
}
