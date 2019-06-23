package com.yasho.services;

import com.yasho.entity.Account;
import com.yasho.entity.AccountState;
import com.yasho.exception.AccountAlreadyPresentException;
import com.yasho.exception.AccountDoesntExists;
import com.yasho.repo.AccountRepository;

import java.math.BigDecimal;
import java.util.List;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account addAccount(Account account) {
        Account existingAccount = accountRepository.fetchByAccountId(account.getAccountId());
        if(existingAccount != null) {
            throw new AccountAlreadyPresentException();
        }
        return accountRepository.save(account);
    }

    public Account getAccount(String accountid) {
        return accountRepository.fetchByAccountId(accountid);
    }

    public void deactiveAccount(String accountId) {
        Account account = accountRepository.fetchByAccountId(accountId);
        if(account == null) {
            throw new AccountDoesntExists();
        }
        account.setAccountState(AccountState.INACTIVE);
        accountRepository.update(account);
    }

    public synchronized void transferMoney(String sourceAccount, String destinationAccount, BigDecimal amountToBeTransfered) {
        Account source = accountRepository.fetchByAccountId(sourceAccount);
        Account destination = accountRepository.fetchByAccountId(destinationAccount);

        source.addTransaction(amountToBeTransfered, destination, true);

        accountRepository.update(source);
        accountRepository.update(destination);

    }

    public List<Account> fetchAllAccounts() {
        return accountRepository.fetchAll();
    }
}
