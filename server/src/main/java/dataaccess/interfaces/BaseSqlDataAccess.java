package dataaccess.interfaces;

public abstract class BaseSqlDataAccess {
    protected BaseSqlDataAccess(){
        initalizeDatabaseTables();
    }

    protected boolean initalizeDatabaseTables(){
        return false;
    }

    protected void executeSql(String statement){

    }
}
