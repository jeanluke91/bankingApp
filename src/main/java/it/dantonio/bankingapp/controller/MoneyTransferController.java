package it.dantonio.bankingapp.controller;

import it.dantonio.bankingapp.model.MoneyTransferBody;
import it.dantonio.bankingapp.service.MoneyTransferService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
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

    @PostMapping("/{accountId}/payments/money-transfers")
    ResponseEntity<Object> createMoneyTransfer(
            @PathVariable Long accountId,
            @Valid @RequestBody MoneyTransferBody moneyTransferBody
    ) throws JSONException, IOException {
        logger.log(Level.INFO, "MoneyTransferController - createMoneyTransaction");
        Object obj = moneyTransferService.createMoneyTransfer(accountId, moneyTransferBody);

        if (obj != null) {
            return ResponseEntity.ok(obj);
        }
        return ResponseEntity.noContent().build();
    }

}
