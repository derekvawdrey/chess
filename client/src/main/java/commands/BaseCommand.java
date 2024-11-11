package commands;

import commands.postlogin.ListGamesCommand;
import model.ChessClient;
import model.GameDataResponse;
import ui.EscapeSequences;

import java.util.List;

public abstract class BaseCommand {
    protected final String description;
    protected final String usage;
    protected final model.ChessClient chessClient;

    protected BaseCommand(model.ChessClient chessClient, String description, String usage) {
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
