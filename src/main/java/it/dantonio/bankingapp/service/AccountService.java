package it.dantonio.bankingapp.service;

import it.dantonio.bankingapp.utils.ClientMethod;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired
  private ClientMethod clientMethod;

  @Value("${endpoint-account-balance}")
  private String endpoint;

  Logger logger = Logger.getLogger(AccountService.class.getName());

  public String getAccountBalanceById(Long accountId) {
    logger.log(Level.INFO, "AccountService - getAccountBalanceById started ");
    String url = endpoint.replace("{accountId}", String.valueOf(accountId));
    String respomse = clientMethod.callHttpMethod(url, null, HttpMethod.GET);
    logger.log(Level.INFO, "AccountService - getAccountBalanceById finished");
    return respomse;

  }

}
