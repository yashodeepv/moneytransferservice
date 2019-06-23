package com.yasho.services;

import com.yasho.entity.Account;
import com.yasho.entity.AccountState;
import com.yasho.entity.AccountType;
import com.yasho.exception.AccountAlreadyPresentException;
import com.yasho.exception.InsufficientBalanceException;
import com.yasho.repo.InMemoryAccountRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.List;

public class AccountServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    AccountService accountService;

    @Before
    public void setup() {
        accountService = new AccountService(new InMemoryAccountRepository());
    }

    @Test
    public void shouldAddAccount() {
        // given

        // when
        Account account = new Account("account1",
                AccountState.ACTIVE,
                AccountType.DEPOSIT,
                BigDecimal.ZERO);
        accountService.addAccount(account);
        Account actual = accountService.getAccount("account1");

        // then
        Assert.assertThat(account.getAccountId(), CoreMatchers.is("account1"));

    }

    @Test(expected = AccountAlreadyPresentException.class)
    public void shouldNotAddDuplicateAccount() {
        // given
        Account account = new Account(
                "account1",
                AccountState.ACTIVE,
                AccountType.DEPOSIT,
                BigDecimal.ZERO
        );
        accountService.addAccount(account);

        // when
        // Adding same account again
        accountService.addAccount(account);

        // then
        // Exception expected
    }

    @Test
    public void shouldDeActiveAccount() {
        // given
        Account account = new Account(
                "account1",
                AccountState.ACTIVE,
                AccountType.DEPOSIT,
                BigDecimal.ZERO
        );
        accountService.addAccount(account);

        // when
        accountService.deactiveAccount(account.getAccountId());
        Account actual = accountService.getAccount(account.getAccountId());

        // then
        Assert.assertThat(actual.getAccountState(), CoreMatchers.is(AccountState.INACTIVE));
    }

    @Test
    public void shouldFetchAllAccounts() {
        // given
        Account account1 = new Account("account1",
                AccountState.ACTIVE,
                AccountType.DEPOSIT,
                new BigDecimal("100"));
        Account account2 = new Account("account2",
                AccountState.ACTIVE,
                AccountType.DEPOSIT,
                new BigDecimal("200"));
        accountService.addAccount(account1);
        accountService.addAccount(account2);

        // when
        List<Account> actual = accountService.fetchAllAccounts();
        // then

    }

    @Test
    public void shouldTransferMoneyToOtherAccount() {
        // given
        Account account1 = new Account("account1",
                AccountState.ACTIVE,
                AccountType.DEPOSIT,
                new BigDecimal("100"));
        Account account2 = new Account("account2",
                AccountState.ACTIVE,
                AccountType.DEPOSIT,
                new BigDecimal("200"));
        accountService.addAccount(account1);
        accountService.addAccount(account2);
        // transfer money from account 1 to account 2

        // when
        accountService.transferMoney("account1", "account2", new BigDecimal("10"));
        Account actual = accountService.getAccount("account2");

        // then
        Assert.assertThat(actual.getBalance().intValue(), CoreMatchers.is(210));
    }

    @Test(expected = InsufficientBalanceException.class)
    public void shouldNotTransferMoneyToOtherAccountIfBalanceIsles() {
        // given
        Account account1 = new Account("account1",
                AccountState.ACTIVE,
                AccountType.DEPOSIT,
                new BigDecimal("100"));
        Account account2 = new Account("account2",
                AccountState.ACTIVE,
                AccountType.DEPOSIT,
                new BigDecimal("200"));
        accountService.addAccount(account1);
        accountService.addAccount(account2);
        // transfer money from account 1 to account 2

        // when
        accountService.transferMoney("account1", "account2", new BigDecimal("120"));

        // then -- Exception is expected
    }
}
