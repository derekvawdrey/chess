package commands;

public abstract class BaseCommand {
    protected final String description;
    protected final String usage;
    protected BaseCommand(String description, String usage) {
        this.description = description;
        this.usage = usage;
    }
    protected abstract boolean validateArgs(String[] args);
    protected abstract void executeCommand(String[] args);
}
