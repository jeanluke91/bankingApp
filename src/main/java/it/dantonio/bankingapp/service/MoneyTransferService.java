package it.dantonio.bankingapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.dantonio.bankingapp.model.MoneyTransferBody;
import it.dantonio.bankingapp.model.MoneyTransferResponse;
import it.dantonio.bankingapp.utils.ClientMethod;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferService {

  @Autowired
  private ClientMethod clientMethod;

  @Value("${endpoint-money-transfer}")
  private String endpoint;

  @Autowired
  ObjectMapper objectMapper;
  Logger logger = Logger.getLogger(MoneyTransferService.class.getName());

  public MoneyTransferResponse createMoneyTransfer(Long accountId,
      MoneyTransferBody moneyTransferBody)
      throws Exception {
    logger.log(Level.INFO, "MoneyTransferService - createMoneyTransfer started ");
    String url = endpoint.replace("{accountId}", String.valueOf(accountId));

    String json = new ObjectMapper().registerModule(new JavaTimeModule())
        .writeValueAsString(moneyTransferBody);
    String response = clientMethod.callHttpMethod(url, json, HttpMethod.POST);

    logger.log(Level.INFO, "MoneyTransferService - createMoneyTransfer finished ");
    return objectMapper.readValue(response, MoneyTransferResponse.class);
  }
}
