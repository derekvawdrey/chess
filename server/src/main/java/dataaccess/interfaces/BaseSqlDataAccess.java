package dataaccess.interfaces;

public abstract class BaseSqlDataAccess {
    BaseSqlDataAccess(){
        initalizeDatabaseTables();
    }

    protected boolean initalizeDatabaseTables(){
        return false;
    }
}
