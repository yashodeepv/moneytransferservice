package com.yasho.repo;

import com.yasho.entity.Account;

import java.util.List;

public interface AccountRepository {
    Account save(Account account);

    Account fetchByAccountId(String accountId);

    void update(Account account);

    List<Account> fetchAll();

}
