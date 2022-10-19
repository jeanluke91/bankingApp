package it.dantonio.bankingapp.controller;

import it.dantonio.bankingapp.model.MoneyTransferBody;
import it.dantonio.bankingapp.model.MoneyTransferResponse;
import it.dantonio.bankingapp.service.MoneyTransferService;
import it.dantonio.bankingapp.utils.CustomResponse;
import it.dantonio.bankingapp.utils.ResponseCode;
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

  @Autowired
  private CustomResponse customResponse;

  Logger logger = Logger.getLogger(MoneyTransferController.class.getName());

  @PostMapping(value = "/{accountId}/payments", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Object> createMoneyTransfer(
      @PathVariable Long accountId,
      @Valid @RequestBody MoneyTransferBody moneyTransferBody) {
    logger.log(Level.INFO, "MoneyTransferController - createMoneyTransaction");
    try {
      MoneyTransferResponse moneyTransferResponse = moneyTransferService.createMoneyTransfer(
          accountId, moneyTransferBody);
      if (moneyTransferResponse != null) {
        return customResponse.generateResponse(ResponseCode.OK, moneyTransferResponse);
      }

      return customResponse.generateResponse(ResponseCode.UNEXPECTED_ERROR.getCode(),
          ResponseCode.UNEXPECTED_ERROR.getMsg(), null);

    } catch (Exception e) {
      return customResponse.generateResponse(ResponseCode.MONEY_TRANSFER_ERROR.getCode(),
          ResponseCode.MONEY_TRANSFER_ERROR.getMsg() + accountId, null);
    }
  }

}
