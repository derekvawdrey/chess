package commands.prelogin;

import commands.BaseCommand;

public class LoginCommand extends BaseCommand {
    protected LoginCommand(String description, String usage) {
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
