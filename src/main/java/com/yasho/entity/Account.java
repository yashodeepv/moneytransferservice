package com.yasho.entity;

import com.yasho.exception.InsufficientBalanceException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class Account {
    private String accountId;
    private AccountState accountState;
    private AccountType accountType;
    private Set<AccountTransaction> transactions = new TreeSet<>();
    private BigDecimal balance;

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setAccountState(AccountState accountState) {
        this.accountState = accountState;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Account() {

    }

    public Account(AccountState accountState, AccountType accountType, BigDecimal balance) {
        this.accountState = accountState;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Account(String accountId, AccountState accountState, AccountType accountType, BigDecimal balance) {
        this.accountId = accountId;
        this.accountState = accountState;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountState getAccountState() {
        return accountState;
    }

    public AccountType getAccountType() {
        return accountType;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public synchronized BigDecimal addTransaction(BigDecimal amountToBeTransfered, Account toAccount, boolean addTransactionToDestination) {
        if(amountToBeTransfered.compareTo(balance) == 1) {
            throw new InsufficientBalanceException("Insifficient Balance, Fund transfer failed");
        }
        BigDecimal amount = amountToBeTransfered.multiply(BigDecimal.valueOf(-1l));
        AccountTransaction transaction = new AccountTransaction(LocalDate.now(), amount, toAccount);
        transactions.add(transaction);
        if(addTransactionToDestination) {
            toAccount.addTransaction(amount, this, false);
        }
        this.balance = this.balance.subtract(amountToBeTransfered);
        return this.balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", accountState=" + accountState +
                ", accountType=" + accountType +
                ", transactions=" + transactions +
                ", balance=" + balance +
                '}';
    }
}
