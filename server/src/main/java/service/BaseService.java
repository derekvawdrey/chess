package service;

import dataAccess.manager.DataAccessManager;

public abstract class BaseService {
    DataAccessManager dataAccessManager;
    public BaseService(DataAccessManager dataAccessManager) {
        this.dataAccessManager = dataAccessManager;
    }


}
