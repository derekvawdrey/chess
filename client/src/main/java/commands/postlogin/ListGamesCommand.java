package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;
import model.GameListResult;
import ui.EscapeSequences;

public class ListGamesCommand extends BaseCommand {
    public ListGamesCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Lists all active games",
                "list"
        );
    }

    @Override
    public boolean validateArgs(String... args) {
        return true;
    }

    @Override
    public void executeCommand(String... args) {
        try {
            GameListResult list = this.chessClient.getServerFacade().listGames(this.chessClient.getAuthData().authToken());
            list.games().forEach(game -> {


                System.out.println(
                    EscapeSequences.SET_TEXT_COLOR_MAGENTA
                    + "(ID: " + game.gameID() + "): "
                    + EscapeSequences.RESET_TEXT_COLOR
                    + game.gameName()
                    + EscapeSequences.RESET_TEXT_COLOR
                    + ", white: " + (game.whiteUsername() != null ?
                            game.whiteUsername()
                            : EscapeSequences.SET_TEXT_COLOR_GREEN + "____")
                            + EscapeSequences.RESET_TEXT_COLOR
                    + ", black: " + (game.blackUsername() != null ?
                            game.blackUsername()
                            : EscapeSequences.SET_TEXT_COLOR_GREEN + "____")
                    + EscapeSequences.RESET_TEXT_COLOR
                );
            });
        } catch (Exception e) {
            this.chessClient.printError(e.getMessage());
            this.chessClient.printError("An unexpected error occurred.");
        }
    }
}
