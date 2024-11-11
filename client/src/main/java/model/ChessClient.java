package model;

import commands.BaseCommand;
import commands.postlogin.*;
import commands.prelogin.HelpCommand;
import commands.prelogin.LoginCommand;
import commands.prelogin.QuitCommand;
import commands.prelogin.RegisterCommand;
import ui.EscapeSequences;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;

public class ChessClient {
    protected final HashMap<String, BaseCommand> preLoginCommands;
    protected final HashMap<String, BaseCommand> postLoginCommands;
    protected final HashMap<String, BaseCommand> inGameCommands;
    private boolean running;
    private ClientState currentState;
    private final ServerFacade serverFacade;
    private AuthData authData;

    public ChessClient() {
        this.inGameCommands = new HashMap<>();
        this.preLoginCommands = new HashMap<>();
        this.postLoginCommands = new HashMap<>();
        this.initCommands();
        this.serverFacade = new ServerFacade(8080);
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
        postLoginCommands.put("logout", new LogoutCommand(this));


        this.running = true;
    }

    /**
     * Start the ChessClient scanning process.
     */
    public void startScan(){
        Scanner scanner = new Scanner(System.in);
        var result = "";
        while(running){
            System.out.print(this.getCommandStartText());
            String line = scanner.nextLine().trim();

            String[] inputParts = line.split(" ");
            String command = inputParts[0];
            String[] args = new String[inputParts.length - 1];
            System.arraycopy(inputParts, 1, args, 0, args.length);

            this.executeCommand(command, args);
        }
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
            if(!executedCommand.validateArgs(args)) {
                return;
            }
            executedCommand.executeCommand(args);
        }else{
            printError("That command does not exist.");
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
     * Used to stop the client from analysing
     * @param running
     */
    public void setRunning(boolean running){
        this.running = running;
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

    public ServerFacade getServerFacade() {
        return serverFacade;
    }

    /**
     * The clients current state
     */
    public static enum ClientState{
        PRE_LOGIN,
        POST_LOGIN,
        IN_GAME
    }

    /**
     * Gets the prefix for the commandline
     */
    private String getCommandStartText(){
        String returnText = "";
        switch (currentState) {
            case PRE_LOGIN -> returnText = "(Not logged in) >>> ";
            case POST_LOGIN -> returnText = "(" + this.authData.username() + ") >>> ";
            case IN_GAME -> returnText = "(Game) >>>";
        };
        return returnText;
    }

    /**
     * Prints an error out with colors
     * @param error The string of the error
     */
    public void printError(String error){
        System.out.println(EscapeSequences.SET_TEXT_COLOR_RED + error + EscapeSequences.RESET_TEXT_COLOR);
    }

    /**
     * Prints a success message with colors
     * @param success The success message
     */
    public void printSuccess(String success){
        System.out.println(EscapeSequences.SET_TEXT_COLOR_GREEN + success + EscapeSequences.RESET_TEXT_COLOR);
    }

    /**
     * Returns all in game commands
     * @return Hashmap of commands
     */
    public HashMap<String, BaseCommand> getInGameCommands() {
        return inGameCommands;
    }

    /**
     * Returns all pre-login commands
     * @return Hashmap of commands
     */
    public HashMap<String, BaseCommand> getPreLoginCommands() {
        return preLoginCommands;
    }

    /**
     * Returns all post login commands
     * @return Hashmap of commands
     */
    public HashMap<String, BaseCommand> getPostLoginCommands() {
        return postLoginCommands;
    }
}
