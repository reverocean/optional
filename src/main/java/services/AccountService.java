package services;

import functional.Function;
import functional.Optional;
import repositories.AccountRepository;
import vo.Account;
import vo.Address;
import vo.City;

public class AccountService {
    private AccountRepository accountRepository;

    public String getCityName(int accountId) {
        Account account = accountRepository.findAccount(accountId);
        Optional optional = new Optional();
        Address address = optional.map(account, toAddress());
        City city = optional.map(address, toCity());
        return optional.map(city, toCityName());
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


    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
