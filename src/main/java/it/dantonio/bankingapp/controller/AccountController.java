package it.dantonio.bankingapp.controller;

import it.dantonio.bankingapp.model.AccountBalance;
import it.dantonio.bankingapp.model.AccountTransaction;
import it.dantonio.bankingapp.model.MoneyTransferBody;
import it.dantonio.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{accountId}/balance")
    ResponseEntity<AccountBalance> getAccountBalanceById(@PathVariable Long accountId) throws IOException, JSONException {
        logger.log(Level.INFO, "AccountCountroller - getAccountBalanceById");
        AccountBalance accountBalance = accountService.getAccountBalanceById(accountId);

        if (accountBalance != null) {
            return ResponseEntity.ok(accountBalance);
        }
        return ResponseEntity.noContent().build();
    }
}
