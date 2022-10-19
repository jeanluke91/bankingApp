package it.dantonio.bankingapp.controller;

import it.dantonio.bankingapp.model.AccountBalance;
import it.dantonio.bankingapp.service.AccountService;
import it.dantonio.bankingapp.utils.CustomResponse;
import it.dantonio.bankingapp.utils.ResponseCode;
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

  @Autowired
  private CustomResponse customResponse;

  Logger logger = Logger.getLogger(AccountController.class.getName());

  @GetMapping(value = "/{accountId}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Object> getAccountBalanceById(@PathVariable Long accountId)
      throws Exception {
    logger.log(Level.INFO, "AccountCountroller - getAccountBalanceById");
    AccountBalance accountBalance = accountService.getAccountBalanceById(accountId);
    if (accountBalance != null) {
      return customResponse.generateResponse(ResponseCode.OK, accountBalance);
    }
    return customResponse.generateResponse(ResponseCode.NO_BALANCE_FOUND.getCode(),
        ResponseCode.NO_BALANCE_FOUND.getMsg(),
        null);
  }

}
