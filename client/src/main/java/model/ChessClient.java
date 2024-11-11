package model;

import commands.BaseCommand;
import commands.postlogin.CreateGameCommand;
import commands.postlogin.ListGamesCommand;
import commands.postlogin.ObserveGameCommand;
import commands.postlogin.PlayGameCommand;
import commands.prelogin.HelpCommand;
import commands.prelogin.LoginCommand;
import commands.prelogin.QuitCommand;
import commands.prelogin.RegisterCommand;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

public class ChessClient {
    protected final HashMap<String, BaseCommand> preLoginCommands;
    protected final HashMap<String, BaseCommand> postLoginCommands;
    protected final HashMap<String, BaseCommand> inGameCommands;
    private ClientState currentState;
    private AuthData authData;

    public ChessClient() {
        this.inGameCommands = new HashMap<>();
        this.preLoginCommands = new HashMap<>();
        this.postLoginCommands = new HashMap<>();
        this.initCommands();
        this.currentState = ClientState.PRE_LOGIN;
    }

    /**
     * Initalize commands with command strings that will be executed
     */
    private void initCommands(){
        preLoginCommands.put("help", new HelpCommand(this));
        preLoginCommands.put("login", new LoginCommand(this));
        preLoginCommands.put("register", new RegisterCommand(this));
        preLoginCommands.put("quit", new QuitCommand(this));

        postLoginCommands.put("help", new commands.postlogin.HelpCommand(this));
        postLoginCommands.put("play", new PlayGameCommand(this));
        postLoginCommands.put("create", new CreateGameCommand(this));
        postLoginCommands.put("list", new ListGamesCommand(this));
        postLoginCommands.put("observe", new ObserveGameCommand(this));
        postLoginCommands.put("quit", new QuitCommand(this));

    }

    /**
     * Executes a command based on the provided commands string and state of the Client.
     * @param commandString A string like 'help', 'create', 'login' that is in the dictionary.
     * @param args
     */
    private void executeCommand(String commandString, String[] args) {
        BaseCommand executedCommand = switch (currentState) {
            case PRE_LOGIN -> preLoginCommands.get(commandString);
            case POST_LOGIN -> postLoginCommands.get(commandString);
            case IN_GAME -> inGameCommands.get(commandString);
        };

        if(executedCommand != null){
            if(!executedCommand.validateArgs(args)){
                // TODO: Print the commands usage and give an error.
            }
            executedCommand.executeCommand(args);
        }else{
            // TODO: Give an error and print out stuff.
        }
    }

    /**
     * Allows commands to directly modify the current state of the client.
     * Useful for login, register, etc.
     * @param state
     */
    public void setState(ClientState state){
        this.currentState = state;
    }

    /**
     * Used so commands can set authData as it sees fit.
     * @param authData
     */
    public void setAuthData(AuthData authData){
        this.authData = authData;
    }

    /**
     * Gets the users AuthData
     * @return
     */
    public AuthData getAuthData(){
        return this.authData;
    }

    /**
     * Gets the current state of the client.
     * @return
     */
    public ClientState getCurrentState(){
        return this.currentState;
    }

    /**
     * The clients current state
     */
    public static enum ClientState{
        PRE_LOGIN,
        POST_LOGIN,
        IN_GAME
    }


}
