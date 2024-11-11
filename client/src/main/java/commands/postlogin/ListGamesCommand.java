package commands.postlogin;

import commands.BaseCommand;
import client_models.ChessClient;
import model.GameDataResponse;
import model.GameListResult;
import ui.EscapeSequences;

import java.util.ArrayList;
import java.util.List;

public class ListGamesCommand extends BaseCommand {

    private List<GameDataResponse> gamesArray;

    public ListGamesCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Lists all active games",
                "list"
        );
        gamesArray = new ArrayList<>();
    }

    @Override
    public boolean validateArgs(String... args) {
        return true;
    }

    @Override
    public void executeCommand(String... args) {
        gamesArray.clear();
        try {
            GameListResult list = this.chessClient.getServerFacade().listGames(this.chessClient.getAuthData().authToken());
            list.games().forEach(game -> {
                System.out.println(
                    EscapeSequences.SET_TEXT_COLOR_MAGENTA
                    + "(ID: " + gamesArray.size() + "): "
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
                gamesArray.add(game);
            });
        } catch (Exception e) {
            this.chessClient.printError(e.getMessage());
            this.chessClient.printError("An unexpected error occurred.");
        }
    }

    public List<GameDataResponse> getGamesArray() {
        return gamesArray;
    }
}
