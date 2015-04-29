package services;

import functional.Function;
import repositories.AccountRepository;
import vo.Account;
import vo.Address;
import vo.City;

public class AccountService {
    private AccountRepository accountRepository;

    public String getCityName(int accountId) {
        Account account = accountRepository.findAccount(accountId);
        if (account != null) {
            Address address = account.getAddress();
            if (address != null) {
                City city = address.getCity();
                if (city != null) {
                    return city.getName();
                }
            }
        }
        return null;
    }

    private <T, R> R map(T value, Function<T, R> transform) {
        if (value != null) {
            return transform.apply(value);
        }
        return null;
    }


    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
