package commands.prelogin;

import commands.BaseCommand;

public class RegisterCommand extends BaseCommand  {
    protected RegisterCommand(String description, String usage) {
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
