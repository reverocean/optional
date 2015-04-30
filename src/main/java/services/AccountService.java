package services;

import repositories.AccountRepository;
import vo.Account;

import java.util.Optional;

public class AccountService {
    private AccountRepository accountRepository;

    public String getCityName(int accountId) {
        Account account = accountRepository.findAccount(accountId);
        return Optional.ofNullable(account)
                .map(acc -> acc.getAddress())
                .map(add -> add.getCity())
                .map(city -> city.getName())
                .orElse(null);
    }


    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
