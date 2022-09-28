package it.dantonio.bankingapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.dantonio.bankingapp.model.AccountTransaction;
import it.dantonio.bankingapp.utils.ClientMethod;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private ClientMethod clientMethod;

    @Value("${endpoint-transactions}")
    private String endpoint;

    Logger logger = Logger.getLogger(TransactionService.class.getName());

    public List<AccountTransaction> getAccountTransactions(Long accountId, String fromAccountingDate, String toAccountingDate) throws IOException, JSONException {
        logger.log(Level.INFO, "TransactionService - getTransactions started ");
        String url = endpoint.replace("{accountId}", String.valueOf(accountId))
            .replace("{fromAccountingDate}",fromAccountingDate)
            .replace("{toAccountingDate}",toAccountingDate);

        List<AccountTransaction> transactions = null;
        String r = null;
        try {
            r = clientMethod.get(url);

             transactions = new ObjectMapper().registerModule(new JavaTimeModule())
                    .readValue(r, new TypeReference<List<AccountTransaction>>() {
                    });

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, "TransactionService - getTransactions finished");
        return transactions;

    }

}
