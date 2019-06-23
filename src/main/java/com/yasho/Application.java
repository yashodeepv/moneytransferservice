package com.yasho;

import com.yasho.config.Routes;
import com.yasho.controller.AccountController;
import com.yasho.repo.AccountRepository;
import com.yasho.repo.InMemoryAccountRepository;
import com.yasho.services.AccountService;
import io.undertow.Undertow;

public class Application {

    public static void main(String[] args) {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        AccountController accountController = new AccountController(accountService);
        Routes routes = new Routes(accountController);

        Undertow.Builder builder = Undertow.builder();
        builder.setIoThreads(2);
        builder.setWorkerThreads(10);
        builder.addHttpListener(8080, "0.0.0.0");
        builder.setHandler(routes.getRoutes());
        Undertow server = builder.build();
        server.start();
    }
}