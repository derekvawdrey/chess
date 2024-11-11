package commands.prelogin;

import commands.BaseCommand;

public class HelpCommand extends BaseCommand {
    protected HelpCommand(String description, String usage) {
        super(description, usage);
    }

    @Override
    protected boolean validateArgs(String[] args) {
        return false;
    }

    @Override
    protected void executeCommand(String[] args) {

    }
}
