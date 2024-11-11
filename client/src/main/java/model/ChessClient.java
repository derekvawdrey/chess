package model;

import commands.BaseCommand;
import commands.prelogin.LoginCommand;
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
        preLoginCommands.put("login", new LoginCommand());
        postLoginCommands.put("register", new RegisterCommand());

    }

    public void

    public static enum ClientState{
        PRE_LOGIN,
        POST_LOGIN,
        IN_GAME
    }


}
