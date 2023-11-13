package petShop.persistence;

import petShop.domain.Account;

import java.util.List;

public interface AccountMapper {
    List<Account> selectAll();

    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    void updateAccount(Account account);

    void insertAccount(Account account);

    void updateProfile(Account account);

    void insertProfile(Account account);

    void updateSignon(Account account);

    void insertSignon(Account account);

}
