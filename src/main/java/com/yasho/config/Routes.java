package com.yasho.config;

import com.yasho.controller.AccountController;
import io.undertow.Handlers;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Methods;

public class Routes {

    private AccountController accountController;

    public Routes(AccountController accountController) {
        this.accountController = accountController;
    }

    public RoutingHandler getRoutes() {
        return Handlers.routing()
                .add(Methods.GET, "/accounts", accountController::getAllAccounts)
                .add(Methods.GET, "/accounts/{accountid}", accountController::getAccount)
                .add(Methods.POST, "/accounts", accountController::saveAccount)
                .add(Methods.POST, "/accounts/transfer", accountController::transfer);
    }


}
