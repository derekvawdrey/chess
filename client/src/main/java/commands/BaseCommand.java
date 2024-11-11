package commands;

import model.ChessClient;

public abstract class BaseCommand {
    protected final String description;
    protected final String usage;
    protected final ChessClient chessClient;
    protected BaseCommand(ChessClient chessClient, String description, String usage) {
        this.chessClient = chessClient;
        this.description = description;
        this.usage = usage;
    }
    protected abstract boolean validateArgs(String[] args);
    protected abstract void executeCommand(String[] args);
}
