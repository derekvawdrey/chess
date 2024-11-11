package commands;

public abstract class BaseCommand {
    protected final String description;
    protected final String[] commandString;
    protected final int numberOfArguments;


    protected BaseCommand(String description, String[] commandString, int numberOfArguments) {
        this.description = description;
        this.commandString = commandString;
        this.numberOfArguments = numberOfArguments;
    }

    protected void executeCommand(String[] args){

    }
}
