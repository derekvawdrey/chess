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
        // TODO: Send logout to server facade
        this.chessClient.setState(ChessClient.ClientState.PRE_LOGIN);
        this.chessClient.setAuthData(null);
    }
}
