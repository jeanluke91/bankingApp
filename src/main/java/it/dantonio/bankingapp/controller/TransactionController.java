package it.dantonio.bankingapp.controller;

import it.dantonio.bankingapp.service.TransactionService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  Logger logger = Logger.getLogger(TransactionController.class.getName());

  @GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<String> getAccountTransactions(
      @PathVariable Long accountId,
      @RequestParam String fromAccountingDate,
      @RequestParam String toAccountingDate) throws IOException {
    logger.log(Level.INFO, "TransactionController - getTransactions");
    String accountTransactions = transactionService.getAccountTransactions(accountId,
        fromAccountingDate, toAccountingDate);

    if (accountTransactions != null) {
      return ResponseEntity.ok(accountTransactions);
    }
    return ResponseEntity.noContent().build();

  }

}
