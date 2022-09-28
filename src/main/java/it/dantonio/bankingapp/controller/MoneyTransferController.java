package it.dantonio.bankingapp.controller;

import it.dantonio.bankingapp.model.MoneyTransferBody;
import it.dantonio.bankingapp.service.MoneyTransferService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/money-transfer")
public class MoneyTransferController {

  @Autowired
  private MoneyTransferService moneyTransferService;

  Logger logger = Logger.getLogger(MoneyTransferController.class.getName());

  @PostMapping(value = "/{accountId}/payments", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<String> createMoneyTransfer(
      @PathVariable Long accountId,
      @Valid @RequestBody MoneyTransferBody moneyTransferBody
  ) throws IOException {
    logger.log(Level.INFO, "MoneyTransferController - createMoneyTransaction");
    String response = moneyTransferService.createMoneyTransfer(accountId, moneyTransferBody);

    if (response != null) {
      return ResponseEntity.ok(response);
    }
    return ResponseEntity.noContent().build();
  }

}
