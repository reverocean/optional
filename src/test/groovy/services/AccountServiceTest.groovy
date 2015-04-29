package services

import repositories.AccountRepository
import vo.Account
import vo.Address
import vo.City

/**
 * Created by hyhe on 4/29/15.
 */
class AccountServiceTest extends spock.lang.Specification {
    def "should return the city name when all are not null"() {
        given:
        def accountService = new AccountService()
        def account = new Account()
        def address = new Address()
        def city = new City()

        city.name = "test"
        address.city = city
        account.address = address

        def accountRepository = Stub(AccountRepository)
        accountRepository.findAccount(1) >> account

        accountService.accountRepository = accountRepository

        when:
        def cityName = accountService.getCityName(1)

        then:
        cityName == "test"
    }

    def "should return null when account is null"() {
        given:
        def accountService = new AccountService()

        def accountRepository = Stub(AccountRepository)
        accountRepository.findAccount(1) >> null

        accountService.accountRepository = accountRepository

        when:
        def cityName = accountService.getCityName(1)

        then:
        cityName == null
    }

    def "should return null when account is not null, but address is null"() {
        given:
        def accountService = new AccountService()

        def account = new Account()

        account.address = null

        def accountRepository = Stub(AccountRepository)
        accountRepository.findAccount(1) >> account
        accountService.accountRepository = accountRepository

        when:
        def cityName = accountService.getCityName(1)

        then:
        cityName == null
    }


    def "should return null when account and address are not null, but city is null"() {
        given:
        def accountService = new AccountService()

        def account = new Account()
        def address = new Address()

        address.city = null
        account.address = address

        def accountRepository = Stub(AccountRepository)
        accountRepository.findAccount(1) >> account
        accountService.accountRepository = accountRepository

        when:
        def cityName = accountService.getCityName(1)

        then:
        cityName == null
    }
}
