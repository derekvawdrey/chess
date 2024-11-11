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

    public ChessClient() {
        this.inGameCommands = new HashMap<>();
        this.preLoginCommands = new HashMap<>();
        this.postLoginCommands = new HashMap<>();
        this.initCommands();
    }

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

    public void

    public static enum ClientState{
        PRE_LOGIN,
        POST_LOGIN,
        IN_GAME
    }


}
