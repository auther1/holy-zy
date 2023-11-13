package petShop.service;

import petShop.domain.Account;
import petShop.persistence.Dao.AccountDao;
import petShop.persistence.impl.AccountDaoImpl;

public class AccountService {
    private AccountDao accountDao;
    private static String username;

    public static String getUserName(){ return username; }

    public static void setUsername(String userName){ username=userName; }

    public AccountService(){
        this.accountDao = new AccountDaoImpl() ;
    }

    public Account getAccount(String username) {
        return accountDao.getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }

    public void insertAccount(Account account) {
        accountDao.insertAccount(account);
        accountDao.insertProfile(account);
        accountDao.insertSignon(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
        accountDao.updateProfile(account);

        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDao.updateSignon(account);
        }
    }

}
