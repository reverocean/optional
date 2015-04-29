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
        Address address = map(account, toAddress());
        City city = map(address, toCity());
        return map(city, toCityName());
    }

    private Function<City, String> toCityName() {
        return new Function<City, String>() {
            @Override
            public String apply(City value) {
                return value.getName();
            }
        };
    }

    private Function<Address, City> toCity() {
        return new Function<Address, City>() {
            @Override
            public City apply(Address value) {
                return value.getCity();
            }
        };
    }

    private Function<Account, Address> toAddress() {
        return new Function<Account, Address>() {
            @Override
            public Address apply(Account value) {
                return value.getAddress();
            }
        };
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
