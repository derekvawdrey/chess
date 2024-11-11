package commands.postlogin;

import commands.BaseCommand;
import model.ChessClient;

public class LogoutCommand extends BaseCommand {
    public LogoutCommand(ChessClient chessClient) {
        super(
                chessClient,
                "Logs the user out and redirects them to pre-login",
                "logout"
        );
    }

    @Override
    public boolean validateArgs(String... args) {
        return true;
    }

    @Override
    public void executeCommand(String... args) {
        try {
            this.chessClient.getServerFacade().logout(this.chessClient.getAuthData().authToken());
            this.chessClient.setState(ChessClient.ClientState.PRE_LOGIN);
            this.chessClient.setAuthData(null);
            this.chessClient.printSuccess("Successfully logged out.");
        } catch (Exception e) {
            this.chessClient.printError("An unexpected error has occured.");
        }

    }
}
