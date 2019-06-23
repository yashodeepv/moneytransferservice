package com.yasho.repo;

import com.yasho.entity.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryAccountRepository implements AccountRepository {

    Map<String, Account> inMemoryDataStore = new HashMap<>();

    AtomicLong idSequence = new AtomicLong(01);

    @Override
    public synchronized Account save(Account account) {
        String accountId = account.getAccountId();
        if(accountId == null) {
            accountId = "account"+idSequence.getAndIncrement();
        }
        Account savedAccount = new Account(accountId, account.getAccountState(), account.getAccountType(), account.getBalance());
        inMemoryDataStore.put(savedAccount.getAccountId(), savedAccount);
        return savedAccount;
    }

    @Override
    public Account fetchByAccountId(String accountId) {
        return inMemoryDataStore.get(accountId);
    }

    @Override
    public void update(Account account) {
        inMemoryDataStore.put(account.getAccountId(), account);
    }

    @Override
    public List<Account> fetchAll() {
        return new ArrayList<>(inMemoryDataStore.values());
    }
}
