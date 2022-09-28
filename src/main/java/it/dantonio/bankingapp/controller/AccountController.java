package it.dantonio.bankingapp.controller;

import it.dantonio.bankingapp.service.AccountService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;

  Logger logger = Logger.getLogger(AccountController.class.getName());

  @GetMapping(value = "/{accountId}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<String> getAccountBalanceById(@PathVariable Long accountId)
      throws IOException {
    logger.log(Level.INFO, "AccountCountroller - getAccountBalanceById");
    String accountBalance = accountService.getAccountBalanceById(accountId);

    if (accountBalance != null) {
      return ResponseEntity.ok(accountBalance);
    }
    return ResponseEntity.noContent().build();
  }
}
