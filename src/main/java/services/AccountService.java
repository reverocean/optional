package services;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import repositories.AccountRepository;
import vo.Account;
import vo.Address;
import vo.City;

import static com.google.common.base.Optional.fromNullable;

public class AccountService {
    private AccountRepository accountRepository;

    public String getCityName(int accountId) {
        Account account = accountRepository.findAccount(accountId);
        Optional<Optional<Address>> address = fromNullable(account).transform(toAddress());
        Optional<Optional<City>> city = address.transform(toCity());
        Optional<Optional<String>> cityName = city.transform(toCityName());
        return cityName.get().orNull();
    }

    private Function<Optional<City>, Optional<String>> toCityName() {
        return new Function<Optional<City>, Optional<String>>() {
            @Override
            public Optional<String> apply(Optional<City> input) {
                if (input.isPresent()) {
                    return fromNullable(input.get().getName());
                }
                return Optional.absent();
            }
        };
    }

    private Function<Optional<Address>, Optional<City>> toCity() {
        return new Function<Optional<Address>, Optional<City>>() {
            @Override
            public Optional<City> apply(Optional<Address> input) {
                if (input.isPresent()) {
                    return fromNullable(input.get().getCity());
                }
                return Optional.absent();
            }
        };
    }


    private Function<Account, Optional<Address>> toAddress() {
        return new Function<Account, Optional<Address>>() {
            @Override
            public Optional<Address> apply(Account input) {
                return fromNullable(input.getAddress());
            }
        };
    }


    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
