package petShop.service;

import petShop.domain.Log;
import petShop.persistence.Dao.LogDAO;
import petShop.persistence.impl.LogDAOImpl;

public class LogService {
    Log log;
    private LogDAO logDAO;

    public LogService(){
        log = new Log();
        logDAO = new LogDAOImpl();
    }

    public String logInfo(Object ...s){
        return log.logInfomation(s);
    }

    public void insertLogInfo(String username, String logInfo){
        logDAO.insertLog(username, logInfo);
    }
}
