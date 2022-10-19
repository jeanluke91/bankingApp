package it.dantonio.bankingapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.dantonio.bankingapp.model.AccountTransaction;
import it.dantonio.bankingapp.utils.ClientMethod;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  @Autowired
  private ClientMethod clientMethod;

  @Value("${endpoint-transactions}")
  private String endpoint;

  @Autowired
  ObjectMapper objectMapper;

  Logger logger = Logger.getLogger(TransactionService.class.getName());

  public List<AccountTransaction> getAccountTransactions(Long accountId, String fromAccountingDate,
      String toAccountingDate) throws Exception {
    logger.log(Level.INFO, "TransactionService - getTransactions started ");
    String url = endpoint.replace("{accountId}", String.valueOf(accountId))
        .replace("{fromAccountingDate}", fromAccountingDate)
        .replace("{toAccountingDate}", toAccountingDate);
    String response = clientMethod.callHttpMethod(url, null, HttpMethod.GET);
    logger.log(Level.INFO, "TransactionService - getTransactions finished");
    return objectMapper.readValue(response, new TypeReference<List<AccountTransaction>>() {
    });

  }

}
