package model;

import commands.BaseCommand;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

public class ChessClient {
    protected final HashMap<String, BaseCommand> preLoginCommands;
    protected final HashMap<String, BaseCommand> postLoginCommands;

    public ChessClient() {
        this.preLoginCommands = new HashMap<>();
        this.postLoginCommands = new HashMap<>();
    }

    public static enum LoginState{
        PRE_LOGIN,
        POST_LOGIN
    }


}
