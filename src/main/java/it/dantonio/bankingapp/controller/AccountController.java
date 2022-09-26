package it.dantonio.bankingapp.controller;

import it.dantonio.bankingapp.model.Account;
import it.dantonio.bankingapp.model.AccountBalance;
import it.dantonio.bankingapp.model.AccountTransaction;
import it.dantonio.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    Logger logger = Logger.getLogger(AccountController.class.getName());

    @GetMapping("")
    ResponseEntity<List<Account>> getAllAccounts() throws IOException, JSONException {
        logger.log(Level.INFO, "AccountCountroller - getAllAccounts");
        List<Account> accounts = accountService.getAllAccounts();

        if (accounts != null && accounts.size() > 0) {
            return ResponseEntity.ok(accounts);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{accountId}")
    ResponseEntity<Account> getAccountByAccountId(@PathVariable Long accountId) throws IOException, JSONException {
        logger.log(Level.INFO, "AccountCountroller - getAccountByAccountId");
        Account account = accountService.getAccountById(accountId);

        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{accountId}/balance")
    ResponseEntity<AccountBalance> getAccountBalanceById(@PathVariable Long accountId) throws IOException, JSONException {
        logger.log(Level.INFO, "AccountCountroller - getAccountBalanceById");
        AccountBalance accountBalance = accountService.getAccountBalanceById(accountId);

        if (accountBalance != null) {
            return ResponseEntity.ok(accountBalance);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{accountId}/transactions")
    ResponseEntity<List<AccountTransaction>> getTransactions(
            @PathVariable Long accountId,
            @RequestParam String fromAccountingDate,
            @RequestParam String toAccountingDate) throws IOException, JSONException {
        logger.log(Level.INFO, "AccountCountroller - getTransactions");
        List<AccountTransaction> accountTransactions = accountService.getTransactions(accountId, fromAccountingDate, toAccountingDate);

        if (accountTransactions != null && accountTransactions.size() > 0) {
            return ResponseEntity.ok(accountTransactions);
        }
        return ResponseEntity.noContent().build();

    }

    }
