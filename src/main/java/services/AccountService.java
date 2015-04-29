package services;

import repositories.AccountRepository;
import vo.Account;

public class AccountService {
    private AccountRepository accountRepository;

    public String getCityName(int accountId){
        Account account = accountRepository.findAccount(accountId);
        return account.getAddress().getCity().getName();
    }


    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
