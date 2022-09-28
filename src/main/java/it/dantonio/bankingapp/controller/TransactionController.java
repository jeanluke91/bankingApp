package it.dantonio.bankingapp.controller;

import it.dantonio.bankingapp.model.AccountTransaction;
import it.dantonio.bankingapp.service.TransactionService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
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

    @GetMapping("/{accountId}")
    ResponseEntity<List<AccountTransaction>> getAccountTransactions(
            @PathVariable Long accountId,
            @RequestParam String fromAccountingDate,
            @RequestParam String toAccountingDate) throws IOException, JSONException {
        logger.log(Level.INFO, "TransactionController - getTransactions");
        List<AccountTransaction> accountTransactions = transactionService.getAccountTransactions(accountId, fromAccountingDate, toAccountingDate);

        if (accountTransactions != null && accountTransactions.size() > 0) {
            return ResponseEntity.ok(accountTransactions);
        }
        return ResponseEntity.noContent().build();

    }

}
