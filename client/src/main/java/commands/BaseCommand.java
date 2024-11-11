package commands;

import client_models.ChessClient;
import ui.EscapeSequences;

public abstract class BaseCommand {
    protected final String description;
    protected final String usage;
    protected final ChessClient chessClient;

    protected BaseCommand(ChessClient chessClient, String description, String usage) {
        this.chessClient = chessClient;
        this.description = description;
        this.usage = usage;

    }
    public abstract boolean validateArgs(String... args);
    public abstract void executeCommand(String... args);

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public String getHelpMessage(){
        return EscapeSequences.SET_TEXT_COLOR_BLUE + this.usage +
                EscapeSequences.SET_TEXT_COLOR_MAGENTA + " - " + this.description + EscapeSequences.RESET_TEXT_COLOR;
    }



}
