package com.yasho.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yasho.entity.Account;
import com.yasho.entity.FundTransferRequest;
import com.yasho.entity.Message;
import com.yasho.restutils.Post;
import com.yasho.restutils.ResponseSender;
import com.yasho.services.AccountService;
import io.undertow.server.HttpServerExchange;

public class AccountController {

    private final AccountService accountService;
    private final ResponseSender responseSender;
    private final ObjectMapper objectMapper;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        this.responseSender = new ResponseSender();
        this.objectMapper = new ObjectMapper();
    }

    public void getAllAccounts(HttpServerExchange exchange) {
        responseSender.send(exchange, accountService.fetchAllAccounts());
    }

    public void getAccount(HttpServerExchange exchange) {
        String accountId = exchange.getQueryParameters().get("accountid").getFirst();
        Account account = accountService.getAccount(accountId);
        responseSender.send(exchange, account);
    }

    public void saveAccount(HttpServerExchange exchange) {
        try {
            String bodyString = Post.parseRequestBody(exchange);
            Account account = objectMapper.readValue(bodyString, Account.class);
            Account savedAccount = accountService.addAccount(account);
            System.out.println(savedAccount);
            responseSender.send(exchange, savedAccount);
        } catch (Exception e) {
            responseSender.sendError(exchange, e);
        }
    }

    public void transfer(HttpServerExchange exchange) {
        try {
            String bodyString = Post.parseRequestBody(exchange);
            FundTransferRequest fundTransferRequest = objectMapper.readValue(bodyString, FundTransferRequest.class);
            accountService.transferMoney(
                    fundTransferRequest.getSourceAccountId(),
                    fundTransferRequest.getDestinationAccountId(),
                    fundTransferRequest.getAmount());
            responseSender.send(exchange, new Message("Fund Transfer is Successful"));
        } catch(Exception e) {
            responseSender.sendError(exchange, e);
        }
    }
}
