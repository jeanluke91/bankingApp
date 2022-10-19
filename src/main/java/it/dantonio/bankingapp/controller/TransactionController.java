package it.dantonio.bankingapp.controller;

import it.dantonio.bankingapp.model.AccountTransaction;
import it.dantonio.bankingapp.service.TransactionService;
import it.dantonio.bankingapp.utils.CustomResponse;
import it.dantonio.bankingapp.utils.ResponseCode;
import java.util.List;
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

  @Autowired
  private CustomResponse customResponse;

  Logger logger = Logger.getLogger(TransactionController.class.getName());

  @GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Object> getAccountTransactions(
      @PathVariable Long accountId,
      @RequestParam String fromAccountingDate,
      @RequestParam String toAccountingDate) throws Exception {
    logger.log(Level.INFO, "TransactionController - getTransactions");
    List<AccountTransaction> accountTransactions = transactionService.getAccountTransactions(
        accountId,
        fromAccountingDate, toAccountingDate);

    if (accountTransactions != null && accountTransactions.size() > 0) {
      return customResponse.generateResponse(ResponseCode.OK, accountTransactions);
    }
    return customResponse.generateResponse(ResponseCode.NO_TRANSACTION_FOUND.getCode(),
        ResponseCode.NO_TRANSACTION_FOUND.getMsg(), accountTransactions);

  }

}
